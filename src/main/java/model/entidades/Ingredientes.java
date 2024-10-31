package model.entidades;

public class Ingredientes {
    private int idIngrediente;
    private String nome;
    private String unidadeDeMedida;

    public Ingredientes(String nome, String unidadeDeMedida) {
        this.nome = nome;
        this.unidadeDeMedida = unidadeDeMedida;
    }

    public void visualizarIngrediente(){
        System.out.println("");
        System.out.println("***INGREDIENTE***");
        System.out.println("ID_INGREDIENTE: " + this.getIdIngrediente());
        System.out.println("NOME: " + this.getNome());
        System.out.println("UNIDADE_MEDIDA: " + this.getUnidadeDeMedida());
        System.out.println("");
    }

    // GETTERS E SETTERS
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getUnidadeDeMedida() {
        return unidadeDeMedida;
    }
    public void setUnidadeDeMedida(String unidadeDeMedida) {
        this.unidadeDeMedida = unidadeDeMedida;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }
}