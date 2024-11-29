    package src.model.entidades;

public class Filiais {
    private int idFilial;
    private String endereco;
    private String email;
    private String telefone;
    private int quantidadeMesas;
    private float avaliacao;

    public Filiais(String endereco, String email, String telefone, int quantidadeMesas, float avaliacao) {
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.quantidadeMesas = quantidadeMesas;
        this.avaliacao = avaliacao;
    }

    public void visualizarFilial(){
        System.out.println("");
        System.out.println("***FILIAL***");
        System.out.println("ID_FILIAL: " + this.idFilial);
        System.out.println("ENDERECO: " + this.endereco);
        System.out.println("EMAIL: " + this.email);
        System.out.println("TELEFONE: " + this.telefone);
        System.out.println("QUANTIDADE_MESAS: " + this.quantidadeMesas);
        System.out.println("AVALIACAO: " + this.avaliacao);
        System.out.println("");
    }

    // GETTERS E SETTERS
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public int getQuantidadeMesas() {
        return quantidadeMesas;
    }
    public void setQuantidadeMesas(int quantidadeMesas) {
        this.quantidadeMesas = quantidadeMesas;
    }
    public float getAvaliacao() {
        return avaliacao;
    }
    public void setAvaliacao(float avaliacao) {
        this.avaliacao = avaliacao;
    }

    public int getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(int idFilial) {
        this.idFilial = idFilial;
    }
    
}
