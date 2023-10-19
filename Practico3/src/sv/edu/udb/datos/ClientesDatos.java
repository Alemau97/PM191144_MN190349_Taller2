package sv.edu.udb.datos;

import sv.edu.udb.beans.ClienteBeans;
import sv.edu.udb.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientesDatos {

    private final String SQL_INSERT = "INSERT INTO clientes(titular, dui, pin) VALUES(?,?,?)";
    private final String SQL_LOGIN = "SELECT * FROM clientes WHERE dui = ?";

    public String insert(ClienteBeans clienteBeans){
        String error = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;//no se utiliza en este ejercicio
        int rows = 0; //registros afectados
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;//contador de columnas
            stmt.setString(index++, clienteBeans.getTitular());
            stmt.setString(index++, clienteBeans.getDui());
            stmt.setString(index++, clienteBeans.getPin());
            rows = stmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();

            error = e.toString();
        }finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return error;
    }

    public String[] login(String dui, String pin){
        String error = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;//no se utiliza en este ejercicio
        String titularResponse = "", duiResponse = "", pinResponse = "";
        String[] response = new String[3];
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_LOGIN);
            stmt.setString(1, dui);

            rs = stmt.executeQuery();
            while (rs.next()){
                titularResponse = rs.getString("titular");
                duiResponse = rs.getString("dui");
                pinResponse = rs.getString("pin");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        response[0] = titularResponse;
        response[1] = duiResponse;
        response[2] = pinResponse;

        return response;
    }
}
