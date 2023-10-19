package sv.edu.udb.datos;

import sv.edu.udb.util.Conexion;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class TransaccionDatos {

    private final String SQL_INSERT = "INSERT INTO transacciones1(id_cuenta,tipo,monto) VALUES(?,?,?)";
    private final String SQL_SELECT = "SELECT * FROM transacciones1 WHERE id_cuenta=?";

    public int insert(int idCuenta, String tipo, int monto){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;//no se utiliza en este ejercicio
        int rows = 0; //registros afectados
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;//contador de columnas
            stmt.setInt(index++, idCuenta);
            stmt.setString(index++, tipo);
            stmt.setInt(index++, monto);


            rows = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public DefaultTableModel getTransacciones(int id_cuenta){
        DefaultTableModel dtm = new DefaultTableModel();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, id_cuenta);
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

}
