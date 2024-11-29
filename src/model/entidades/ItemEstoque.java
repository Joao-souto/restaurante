package src.model.entidades;

public class ItemEstoque {
    private int quantidade;
    private int quantidadeMinima;
    private int idIngrediente;
    private int idFornecedor;
    private int idFilial;

    public ItemEstoque(int quantidade, int quantidadeMinima, int idIngrediente, int idFilial, int idFornecedor) {
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
        this.idIngrediente = idIngrediente;
        this.idFilial = idFilial;
        this.idFornecedor = idFornecedor;
    }

    public void visualizarItem(){
        System.out.println("");
        System.out.println("***ITEM DO ESTOQUE***"); 
        System.out.println("ID_INGREDIENTE:" + this.idIngrediente); 
        System.out.println("QUANTIDADE:" + this.quantidade); 
        System.out.println("QUANTIDADE_MINIMA:" + this.quantidadeMinima);
        System.out.println("ID_FORNECEDOR:" + this.idFornecedor);
        System.out.println("ID_FILIAL:" + this.idFilial);
        System.out.println("");
    }

    // GETTERS E SETTERS
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public int getidIngrediente() {
        return idIngrediente;
    }

    public void setidIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public int getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(int idFilial) {
        this.idFilial = idFilial;
    }

}
