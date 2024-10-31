package model.entidades;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.DAO.PedidosDAO;

public class Pedidos {
    private int idPedido;
    private int idCliente;
    private int idPrato;
    private int idBebida;
    private int quantidadePrato;
    private int quantidadeBebida;
    private float valor;
    private String tipoDePagamento;
    private String endereco;
    private String dataPedido;
    private String status;

    public Pedidos(int idCliente, int idPrato,int quantidadePrato, int idBebida, int quantidadeBebida, String tipoDePagamento, String endereco) {
        this.idCliente = idCliente;
        this.idPrato = idPrato;
        this.quantidadePrato = quantidadePrato;
        this.idBebida = idBebida;
        this.quantidadeBebida = quantidadeBebida;
        this.tipoDePagamento = tipoDePagamento;
        this.endereco = endereco;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        this.dataPedido = formattedDateTime;
        this.status = "Em preparação";
        this.valor = PedidosDAO.valorPedido(idBebida, quantidadeBebida, idPrato, quantidadePrato);
    }

    public void visualizarPedidos() {
        System.out.println("");
        System.out.println("***PEDIDO***");
        System.out.println("ID_PEDIDO: " + this.getIdPedido());
        System.out.println("ID_CLIENTE: " + this.getIdCliente());
        System.out.println("ID_PRATO: " + this.getIdPrato());
        System.out.println("ID_BEBIDA: " + this.getIdBebida());
        System.out.println("VALOR: " + this.getValor());
        System.out.println("TIPO_PAGAMENTO: " + this.getTipoDePagamento());
        System.out.println("ENDERECO: " + this.getEndereco());
        System.out.println("DATA_PEDIDO: " + this.getDataPedido());
        System.out.println("STATUS: " + this.getStatus());
        System.out.println("");
    }

    // GETTERS E SETTERS
    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getTipoDePagamento() {
        return tipoDePagamento;
    }

    public void setTipoDePagamento(String tipoDePagamento) {
        this.tipoDePagamento = tipoDePagamento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdPrato() {
        return idPrato;
    }

    public void setIdPrato(int idPrato) {
        this.idPrato = idPrato;
    }

    public int getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(int idBebida) {
        this.idBebida = idBebida;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantidadePrato() {
        return quantidadePrato;
    }

    public void setQuantidadePrato(int quantidadePrato) {
        this.quantidadePrato = quantidadePrato;
    }

    public int getQuantidadeBebida() {
        return quantidadeBebida;
    }

    public void setQuantidadeBebida(int quantidadeBebida) {
        this.quantidadeBebida = quantidadeBebida;
    }

}