package model.entidades;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Reservas {
    private int idReserva;
    private int idFilial;
    private int idCliente;
    private String dataReserva;
    private String mesa;   
    private int capacidade; 

    public Reservas(String mesa, int capacidade,int idFilial, int idCliente) {
        this.mesa = mesa;
        this.capacidade = capacidade;
        this.idFilial = idFilial;
        this.idCliente = idCliente;
        // manipulação provisória
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        this.dataReserva = formattedDateTime;
    }

    public void visualizarReserva(){
        System.out.println("");
        System.out.println("***RESERVA***");
        System.out.println("ID_RESERVA: " + this.idReserva);
        System.out.println("ID_FILIAL: " + this.idFilial);
        System.out.println("ID_CLIENTE: " + this.idCliente);
        System.out.println("DATA_RESERVA: " + this.dataReserva);
        System.out.println("MESA: " + this.mesa);
        System.out.println("CAPACIDADE: " + this.capacidade);
        System.out.println("");
    }
    
    // GETTERS E SETTERS
    public String getDataReserva() {
        return dataReserva;
    }
    public void setDataReserva(String dataReserva) {
        this.dataReserva = dataReserva;
    }
    public String getMesa() {
        return mesa;
    }
    public void setMesa(String mesa) {
        this.mesa = mesa;
    }
    public int getCapacidade() {
        return capacidade;
    }
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
    public void setIdReserva(int idReserva){
        this.idReserva = idReserva;
    }  
    public int getIdReserva(){
        return this.idReserva;
    }
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }  
    public int getIdCliente(){
        return this.idCliente;
    }
    public void setIdFilial(int idFilial){
        this.idFilial = idFilial;
    }  
    public int getIdFilial(){
        return this.idFilial;
    }
}