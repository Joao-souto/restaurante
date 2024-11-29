package src.model.entidades;

public class Fornecedores {
    private int idFornecedor;
    private String nome;
    private String cnpj;
    private String tipoMaterial;
    private String email;
    private String telefone;
    private float valorMaterial;

    public Fornecedores(String nome, String cnpj, String tipoMaterial, String email, String telefone,
            float valorMaterial) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.tipoMaterial = tipoMaterial;
        this.email = email;
        this.telefone = telefone;
        this.valorMaterial = valorMaterial;
    }

    public void visualizarFornecedor(){
        System.out.println("");
        System.out.println("***FORNECEDOR***");
        System.out.println("ID_FORNECEDOR: : " + this.idFornecedor);
        System.out.println("NOME: : " + this.nome);
        System.out.println("CNPJ: " + this.cnpj);
        System.out.println("TIPO_MATERIAL: " + this.tipoMaterial);
        System.out.println("EMAIL: : " + this.email);
        System.out.println("TELEFONE: : " + this.telefone);
        System.out.println("VALOR_MATERIAL: " + this.valorMaterial);
        System.out.println("");
    }
    
    // GETTERS E SETTERS
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getTipoMaterial() {
        return tipoMaterial;
    }
    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
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
    public float getValorMaterial() {
        return valorMaterial;
    }
    public void setValorMaterial(float valorMaterial) {
        this.valorMaterial = valorMaterial;
    }


    public int getIdFornecedor() {
        return idFornecedor;
    }


    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    
}
