package model.entidades;

public class Bebidas {
    private String nome;
    private String descricao;
    private float valor;
    private int idBebida;
    
    public Bebidas(String nome, String descricao, float valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public void visualizarBebida(){
        System.out.println("");
        System.out.println("***BEBIDA***");
        System.out.println("NOME: " + this.nome);
        System.out.println("VALOR: " + this.valor);
        System.out.println("DESCRICAO: " + this.descricao);
        System.out.println("ID_BEBIDA: " + this.idBebida);
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
    public int getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(int idBebida) {
        this.idBebida = idBebida;
    }
}
