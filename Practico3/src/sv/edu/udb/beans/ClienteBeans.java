package sv.edu.udb.beans;

public class ClienteBeans {
    int id_cliente;
    String titular;
    String dui;
    String pin;

    public ClienteBeans(String titular, String dui, String pin){
        this.titular = titular;
        this.dui = dui;
        this.pin = pin;
    }

    public int getIdCliente(){
        return id_cliente;
    }

    public void setIdCliente(int id_cliente){
        this.id_cliente = id_cliente;
    }

    public String getTitular(){
        return titular;
    }

    public void setTitular(String titular){
        this.titular = titular;
    }

    public String getDui(){
        return dui;
    }

    public void setDui(String dui){
        this.dui = dui;
    }

    public String getPin(){
        return pin;
    }

    public void  setPin(String pin){
        this.pin = pin;
    }
}
