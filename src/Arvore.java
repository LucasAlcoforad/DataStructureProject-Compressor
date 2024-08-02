import java.util.IllegalFormatCodePointException;

public class Arvore {
    public No primeiro;
    public No ultimo;
    public String cripto;
    public String dado;
    public String frase;
    public int i = 0;


    public Arvore() {
        this.primeiro = null;
        this.ultimo = null;
    }

    public void compactar(String frase){
        char[] caracteres = frase.toCharArray();
        int i = 0;
        for(char caractere : caracteres){
            No novo = new No(caractere);
            this.add(novo);
            i++;
        }
        System.out.println(i);
        this.show();
        while (primeiro!=ultimo){
            No novo = new No(primeiro,primeiro.prox,primeiro.vezes+primeiro.prox.vezes);
            ultimo.prox = novo;
            ultimo = novo;
            primeiro = primeiro.prox.prox;
            novo.fEsquerso.prox=null;
            novo.fDireito.prox=null;
        }
        this.print(this.primeiro);
        for(char caractere : caracteres){
            this.reestruturar(this.primeiro,caractere);
        }
        System.out.println(cripto);
    }

    public void descompactar(String codigo){
        this.dado = codigo;
        this.primeiro = null;
        this.ultimo = null;
        montarArvore();
        //print(primeiro);
        lerArvore(primeiro);
        System.out.println(frase);
    }

    private void lerArvore(No raiz) {
        No atual = raiz;
        while (i < dado.length()) {
            if (atual.dadoB != null) {
                if (frase == null){
                    frase = "" + atual.dado;
                } else {
                    frase += atual.dado;
                }
                atual = this.primeiro;
            } else {
                char c = dado.charAt(i);
                i++;
                if (c == '0') {
                    atual = atual.fEsquerso;
                } else if (c == '1') {
                    atual = atual.fDireito;
                }
            }
        }
    }

    public void montarArvore(){
        char c = dado.charAt(i);
        primeiro = new No();
        if (c == '0'){
            i++;
            primeiro.fEsquerso = new No();
            primeiro.fDireito = new No();
            montarArvore(primeiro.fEsquerso);
            montarArvore(primeiro.fDireito);
        }
        if (c == '1'){
            String b = "";
            for (int j = i + 1; j <= i + 8; j++) {
                b = b + dado.charAt(j);
            }
            primeiro.dadoB = b;
            primeiro.dado = (char) Integer.parseInt(primeiro.dadoB, 2);
            i = i + 9;
        }
    }

    public void montarArvore(No raiz){
        char c = dado.charAt(i);
        if (c == '1'){
            String b = "";
            for (int j = i + 1; j <= i + 8; j++) {
                b = b + dado.charAt(j);
            }
            raiz.dadoB = b;
            raiz.dado = (char) Integer.parseInt(raiz.dadoB, 2);
            i = i + 9;
        } else {
            i++;
            raiz.fEsquerso = new No();
            raiz.fDireito = new No();
            montarArvore(raiz.fEsquerso);
            montarArvore(raiz.fDireito);
        }
    }


    public void reestruturar(No raiz ,char c){
        if (raiz.fEsquerso!=null){
            reestruturar(raiz.fEsquerso,c);
        }
        if (raiz.fDireito!=null){
            reestruturar(raiz.fDireito,c);
        }
        if (raiz.fEsquerso==null && raiz.fDireito==null && raiz.dado==c){
            cripto = cripto + raiz.dadoNewB;
        }
    }

    public void add(No novo){
        No aux = primeiro;
        No anterior = null;
        while (aux!=null){
            if (aux.dadoB.equals(novo.dadoB)){
                aux.vezes++;
                realocarNo(aux);
                break;
            } else {
                aux = aux.prox;
            }
        }
        if (aux==null){
            if (primeiro == null){
                primeiro = novo;
                ultimo = novo;
            } else {
                novo.prox = primeiro;
                primeiro = novo;
            }
        }
    }

    public void realocarNo(No alterado) {
        if (alterado != ultimo) {
            No aux = primeiro;
            if (aux != alterado) {
                while (aux.prox != alterado) {
                    aux = aux.prox;
                }
                aux.prox = alterado.prox;
            } else {
                primeiro = primeiro.prox;
            }
            alterado.prox = null;
            if (ultimo.vezes <= alterado.vezes) {
                ultimo.prox = alterado;
                ultimo = alterado;
            } else {
                aux = primeiro;
                while (aux.prox.vezes < alterado.vezes) {
                    aux = aux.prox;
                }
                alterado.prox = aux.prox;
                aux.prox = alterado;
            }
        }
    }

    public void print(No raiz){
        if (raiz.dadoB==null){
            if (cripto==null){
                cripto = "0";
            } else {
                cripto = cripto + "0";
            }
        } else {
            raiz.dadoNewB = dado;
            cripto = cripto + "1" + raiz.dadoB;
        }
        System.out.println(raiz.dado+" "+ raiz.vezes + " " + raiz.dadoB + " " + raiz.dadoNewB);
        if (raiz.fEsquerso!=null){
            if (dado == null){
                dado = "0";
            } else {
                dado = dado + "0";
            }
            print(raiz.fEsquerso);
            dado = dado.substring(0, dado.length() - 1);
        }
        if (raiz.fDireito!=null){
            dado = dado + "1";
            print(raiz.fDireito);
            dado = dado.substring(0, dado.length() - 1);
        }
    }
    public void show(){
        String fila = "";
        No aux = primeiro;
        while (aux!=null){
            fila = fila + aux.dado;
            aux = aux.prox;
        }
        System.out.println(fila);
    }
}
