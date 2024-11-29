package src.model.entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clientes {
    private String cpf;
    private String nome;
    private String sexo;
    private String senha;
    private int idade;
    private int idCliente;
    private int idFilial;
    private String endereco;
    private String email;
    private String telefone;
    private String dataCadastro;
    private boolean valid;
    private String mensagem;

    public Clientes(String cpf, String nome, String senha, String sexo, int idade, String endereco, String email,
            String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.sexo = sexo;
        this.idade = idade;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        this.dataCadastro = formattedDateTime;
    }

    public void visualizarCliente() {
        System.out.println("");
        System.out.println("***CLIENTE***");
        System.out.println("NOME: " + this.nome);
        System.out.println("SENHA: " + this.senha);
        System.out.println("CPF: " + this.cpf);
        System.out.println("SEXO: " + this.sexo);
        System.out.println("IDADE: " + this.idade);
        System.out.println("ENDERECO: " + this.endereco);
        System.out.println("EMAIL: " + this.email);
        System.out.println("TELEFONE: " + this.telefone);
        System.out.println("ID_CLIENTE: " + this.idCliente);
        System.out.println("ID_FILIAL: " + this.idFilial);
        System.out.println("DATA_CADASTRO: " + this.dataCadastro);
        System.out.println("");
    }

    // GETTERS E SETTERS
    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataCadastro() {
        return this.dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(int idFilial) {
        this.idFilial = idFilial;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}