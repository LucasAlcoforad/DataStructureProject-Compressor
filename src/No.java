public class No {
    public No prox;
    public No fEsquerso;
    public No fDireito;
    public char dado;
    public String dadoB;
    public String dadoNewB;
    public int vezes;

    public No(char dado) {
        this.dado = dado;
        this.vezes = 1;
        this.dadoB = String.format("%8s", Integer.toBinaryString((int) dado)).replace(' ', '0');
    }

    public No(No fEsquerso, No fDireito, int vezes) {
        this.fEsquerso = fEsquerso;
        this.fDireito = fDireito;
        this.vezes = vezes;
    }

    public No() {
    }
}
