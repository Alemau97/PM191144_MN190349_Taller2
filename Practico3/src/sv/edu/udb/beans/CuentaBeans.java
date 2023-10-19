package sv.edu.udb.beans;

public class CuentaBeans {
    int id_cuenta;
    String dui;
    int saldo;

    String titular;

    public CuentaBeans(int id_cuenta,String titular, String dui, int saldo){
        this.id_cuenta = id_cuenta;
        this.titular = titular;
        this.dui = dui;
        this.saldo = saldo;
    }

    public  int getIdCuenta(){
        return id_cuenta;
    }

    public void setIdcuenta(int id_cuenta){
        this.id_cuenta = id_cuenta;
    }

    public String getDUI(){
        return dui;
    }

    public void setDUI(String dui){
        this.dui = dui;
    }

    public  int getSaldo(){
        return saldo;
    }

    public void setSaldo(int saldo){
        this.saldo = saldo;
    }

    public String getTitular(){
        return titular;
    }

    public void setTitular(String titular){
        this.titular = titular;
    }
}
