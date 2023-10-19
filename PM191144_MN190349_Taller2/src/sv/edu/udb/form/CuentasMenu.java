package sv.edu.udb.form;

import sv.edu.udb.beans.ClienteBeans;
import sv.edu.udb.beans.CuentaBeans;
import sv.edu.udb.components.CambiarSaldoCuenta;
import sv.edu.udb.datos.CuentaDatos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class CuentasMenu extends JDialog {
    private JPanel contentPane;
    private JButton btnCrearCuenta;
    private JLabel lblBienvenido;
    public JTable tblDatos;
    private JButton btnActualizar;
    private JButton btnTransacciones;
    private JButton buttonOK;


    DefaultTableModel modelo = null;
    CuentaBeans cuentaBeans = null;
    CuentaDatos cuentaDatos = new CuentaDatos();

    public CuentasMenu(ClienteBeans clienteResponse) {
        setContentPane(contentPane);
        setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(450, 250));
        this.setLocationRelativeTo(getParent());
        getRootPane().setDefaultButton(buttonOK);

        lblBienvenido.setText("Bienvenido/a "+clienteResponse.getTitular());

        modelo = cuentaDatos.selectCuenta(clienteResponse.getDui());
        tblDatos.setModel(modelo);
        btnCrearCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCrearCuenta(clienteResponse);
            }
        });

        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tblObtenerDato(e);
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelo = cuentaDatos.selectCuenta(clienteResponse.getDui());
                tblDatos.setModel(modelo);
            }
        });
        btnTransacciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verTransacciones(clienteResponse.getDui());
            }
        });
    }


    public void verTransacciones(String dui){
        TransaccionesMenu transaccionesMenu = new TransaccionesMenu(dui);
        transaccionesMenu.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        transaccionesMenu.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        transaccionesMenu.setVisible(true);
    }

    private void tblObtenerDato(MouseEvent e){
        int fila = tblDatos.rowAtPoint(e.getPoint());
        int columna = tblDatos.columnAtPoint(e.getPoint());
        CuentaBeans cuentaTabla = null;
        if ((fila > -1) && (columna > -1)){
            int idCuentaTabla = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
            String titularTabla = modelo.getValueAt(fila,1).toString();
            String duiTabla = modelo.getValueAt(fila, 2).toString();
            int saldoTabla = Integer.parseInt(modelo.getValueAt(fila, 3).toString());
            cuentaTabla = new CuentaBeans(idCuentaTabla,titularTabla,duiTabla,saldoTabla);
            CambiarSaldoCuenta cambiarSaldoCuenta = new CambiarSaldoCuenta(cuentaTabla);
            cambiarSaldoCuenta.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            cambiarSaldoCuenta.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            cambiarSaldoCuenta.setVisible(true);
        }
    }

    public static void main(String[] args) {
        ClienteBeans clienteBeans = new ClienteBeans("","","");
        CuentasMenu dialog = new CuentasMenu(clienteBeans);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public void onCrearCuenta(ClienteBeans clienteResponse){
        String titular, dui;
        int saldo = 0;

        titular = clienteResponse.getTitular();
        dui = clienteResponse.getDui();

        int response = cuentaDatos.insert(titular, dui, saldo);

        modelo = cuentaDatos.selectCuenta(dui);
        tblDatos.setModel(modelo);
    }
}
