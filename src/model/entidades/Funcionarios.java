package src.model.entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Funcionarios {
    private String cpf;
    private String nome;
    private String senha;
    private String sexo;
    private int idade;
    private int idFuncionario;
    private int idFilial;
    private String endereco;
    private String email;
    private String telefone;
    private String dataContratacao;
    private String cargo;

    public Funcionarios(String cpf, String nome, String senha, String sexo, int idade, String endereco, String email,
            String telefone,
            String cargo, int idFilial) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.sexo = sexo;
        this.idade = idade;
        this.endereco = endereco;
        this.email = email;
        this.idFilial = idFilial;
        this.telefone = telefone;
        this.cargo = cargo;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        this.dataContratacao = formattedDateTime;
    }

    public void visualizarFuncionario() {
        System.out.println("");
        System.out.println("***FUNCIONARIO***");
        System.out.println("ID_FUNC: " + this.getIdFuncionario());
        System.out.println("CPF: " + this.getCpf());
        System.out.println("SENHA: " + this.getSenha());
        System.out.println("NOME: " + this.getNome());
        System.out.println("SEXO: " + this.getSexo());
        System.out.println("IDADE: " + this.getIdade());
        System.out.println("ENDERECO: " + this.getEndereco());
        System.out.println("EMAIL: " + this.getEmail());
        System.out.println("TELEFONE: " + this.getTelefone());
        System.out.println("ID_FILIAL: " + this.getIdFilial());
        System.out.println("CARGO: " + this.getCargo());
        System.out.println("DATA_CONTRATACAO: " + this.getDataContratacao());
        System.out.println("");
    }

    // GETTERS E SETTERS
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

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

    public String getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(String dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(int idFilial) {
        this.idFilial = idFilial;
    }
}