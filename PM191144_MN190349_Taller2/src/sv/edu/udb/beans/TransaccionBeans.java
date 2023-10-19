package sv.edu.udb.beans;

public class TransaccionBeans {

    int id_transaccion;
    int id_cuenta;

    String tipo;

    int monto;

    public TransaccionBeans(int id_transaccion, int id_cuenta, String tipo, int monto){
        this.id_transaccion = id_transaccion;
        this.id_cuenta = id_cuenta;
        this.tipo = tipo;
        this.monto = monto;
    }

    public int getIdTransaccion(){
        return id_transaccion;
    }

    public  void setIdTransaccion(int id_transaccion){
        this.id_transaccion = id_transaccion;
    }

    public int getIdCuenta(){
        return id_cuenta;
    }

    public  void setIdCuenta(int id_cuenta){
        this.id_cuenta = id_cuenta;
    }

    public int getMonto(){
        return monto;
    }

    public  void setMonto(int monto){
        this.monto =  monto;
    }

    public String getTipo(){
        return tipo;
    }

    public  void setTipo(String tipo){
        this.tipo = tipo;
    }
}
