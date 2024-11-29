package src.model.entidades;

public class Pratos {
    private String nome;
    private String descricao;
    private float valor;
    private int idPrato;
    
    public Pratos(String nome, String descricao, float valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public void visualizarPrato(){
        System.out.println("");
        System.out.println("***PRATO***");
        System.out.println("ID_PRATO: " + this.idPrato);
        System.out.println("NOME: " + this.nome);
        System.out.println("DESCRIÇÃO: " + this.descricao);
        System.out.println("VALOR: " + this.valor);
        System.out.println("");
    }

    // GETTERS E SETTERS
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getIdPrato() {
        return idPrato;
    }

    public void setIdPrato(int idPrato) {
        this.idPrato = idPrato;
    }
}
