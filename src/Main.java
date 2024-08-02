import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Arvore teste = new Arvore();
        System.out.println("Aperte 1 para compactar" + "\n" + "Aperte 2 para descompactar");
        if (scanner.nextInt()==1){
            scanner.nextLine();
            System.out.println("Qual o caminho do arquivo");
            String arquivo = scanner.nextLine();
            StringBuilder conteudoArquivo = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    conteudoArquivo.append(linha).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            teste.compactar(String.valueOf(conteudoArquivo));
            arquivo = "C:\\Users\\Usuario\\Desktop\\compactado.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, false))) {
                writer.write(teste.cripto);
                System.out.println("Arquivo criado com sucesso!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            scanner.nextLine();
            System.out.println("Qual o caminho do arquivo");
            String arquivo = scanner.nextLine();
            StringBuilder conteudoArquivo = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    conteudoArquivo.append(linha).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            teste.descompactar(String.valueOf(conteudoArquivo));
            arquivo = "C:\\Users\\Usuario\\Desktop\\decompactado.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, false))) {
                writer.write(teste.frase);
                System.out.println("Arquivo criado com sucesso!");
            } catch (IOException e) {
                e.printStackTrace();
            }
       }
    }
}