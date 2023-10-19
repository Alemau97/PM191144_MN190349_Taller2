package sv.edu.udb.form;

import sv.edu.udb.components.Confirmation;
import sv.edu.udb.components.GeneralConfirm;
import sv.edu.udb.datos.CuentaDatos;
import sv.edu.udb.datos.TransaccionDatos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class TransaccionesMenu extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox cmbCuentas;
    private JButton btnVer;
    private JTable tblTransacciones;

    TransaccionDatos transaccionDatos = new TransaccionDatos();
    CuentaDatos cuentaDatos = new CuentaDatos();
    DefaultTableModel modelo = null;

    public TransaccionesMenu(String dui) {
        setContentPane(contentPane);
        setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 400));
        this.setLocationRelativeTo(getParent());
        getRootPane().setDefaultButton(buttonOK);

        cmbCuentas.setModel(cuentaDatos.selectCuentas(dui));

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        btnVer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getTransacciones();
            }
        });
    }

    public void getTransacciones(){
        int idCuentaSelected = 0;

        try{
            idCuentaSelected = Integer.parseInt(cmbCuentas.getSelectedItem().toString());
        }catch(Exception error) {

        }



        modelo = transaccionDatos.getTransacciones(idCuentaSelected);

        int filas = modelo.getRowCount();

        if(filas > 0){
            tblTransacciones.setModel(modelo);
        }else{
            modelo.setRowCount(0);
            tblTransacciones.setModel(modelo);
            GeneralConfirm confirmation = new GeneralConfirm("Esta cuenta no tiene transacciones");
            confirmation.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            confirmation.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            confirmation.setVisible(true);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        TransaccionesMenu dialog = new TransaccionesMenu("########-#");
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
