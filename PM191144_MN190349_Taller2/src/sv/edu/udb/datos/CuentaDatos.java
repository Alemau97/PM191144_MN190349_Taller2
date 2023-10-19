package sv.edu.udb.datos;

import sv.edu.udb.util.Conexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class CuentaDatos {

    private final String SQL_INSERT = "INSERT INTO cuentas1(titular,dui,saldo) VALUES(?,?,?)";
    private final String SQL_SELECT = "SELECT * FROM cuentas1 WHERE dui = ?";
    private final String SELECT_SALDO = "SELECT saldo FROM cuentas1 WHERE  id_cuenta=? AND dui=?";

    private final String SELECT_CUENTAS = "SELECT id_cuenta FROM cuentas1 WHERE dui=?";

    TransaccionDatos transaccionDatos = new TransaccionDatos();

    public int insert(String titular, String dui,int saldo){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;//no se utiliza en este ejercicio
        int rows = 0; //registros afectados
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;//contador de columnas
            stmt.setString(index++, titular);
            stmt.setString(index++, dui);
            stmt.setInt(index++, saldo);

            rows = stmt.executeUpdate();//no. registros afectados

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public DefaultTableModel selectCuenta(String dui){
        DefaultTableModel dtm = new DefaultTableModel();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setString(1, dui);
            rs = stmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int numberOfColumns = meta.getColumnCount();
//Formando encabezado
            for (int i = 1; i <= numberOfColumns; i++) {
                dtm.addColumn(meta.getColumnLabel(i));
            }
//Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[numberOfColumns];
                for (int i = 0; i < numberOfColumns; i++) {
                    fila[i]=rs.getObject(i+1);
                }
                dtm.addRow(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return dtm;
    }

    private final String MODIFICAR_SALDO = "UPDATE cuentas1 SET saldo = ? WHERE  id_cuenta = ? AND dui = ?";
    public int ingresarSaldo(int idCuenta, String dui, int monto){
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        int rows = 0;
        int montoEnCuenta = 0;
            try{
                conn = Conexion.getConnection();
                stmt = conn.prepareStatement(SELECT_SALDO);

                stmt.setInt(1, idCuenta);
                stmt.setString(2, dui);
                rs = stmt.executeQuery();

                while(rs.next()){
                    montoEnCuenta = rs.getInt("saldo");
                }


                try{
                    stmt1 = conn.prepareStatement(MODIFICAR_SALDO);
                    stmt1.setInt(1, monto+montoEnCuenta);
                    stmt1.setInt(2, idCuenta);
                    stmt1.setString(3, dui);

                    rows = stmt1.executeUpdate();

                    transaccionDatos.insert(idCuenta,"Abono", monto);

                }catch (SQLException e){
                    e.printStackTrace();

                }

            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                Conexion.close(stmt);
                Conexion.close(conn);
            }
        return rows;
    }

    public int retirarSaldo(int idCuenta, String dui, int monto){
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt1 = null;
        int rows = 0;
        int montoEnCuenta = 0;
        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_SALDO);

            stmt.setInt(1, idCuenta);
            stmt.setString(2, dui);
            rs = stmt.executeQuery();

            while(rs.next()){
                montoEnCuenta = rs.getInt("saldo");
            }


            if(montoEnCuenta >= monto){
                try{
                    stmt1 = conn.prepareStatement(MODIFICAR_SALDO);
                    stmt1.setInt(1, montoEnCuenta - monto);
                    stmt1.setInt(2, idCuenta);
                    stmt1.setString(3, dui);

                    rows = stmt1.executeUpdate();

                    transaccionDatos.insert(idCuenta,"Retiro", monto);

                }catch (SQLException e){
                    e.printStackTrace();

                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public DefaultComboBoxModel selectCuentas(String dui){
        DefaultComboBoxModel dtm = new DefaultComboBoxModel();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_CUENTAS);
            stmt.setString(1, dui);
            rs = stmt.executeQuery();
//Creando las filas para el JTable
            while (rs.next()) {
                dtm.addElement(rs.getObject(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return dtm;
    }
}
