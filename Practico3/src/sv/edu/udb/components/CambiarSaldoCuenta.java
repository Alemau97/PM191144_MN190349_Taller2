package sv.edu.udb.components;

import sv.edu.udb.beans.CuentaBeans;
import sv.edu.udb.datos.CuentaDatos;
import sv.edu.udb.form.CuentasMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CambiarSaldoCuenta extends JDialog {
    private JPanel contentPane;
    private JButton btnAbonar;
    private JButton buttonCancel;
    private JButton btnRetirar;
    private JTextField txtCantidad;

    CuentaDatos cuentaDatos = new CuentaDatos();

    public CambiarSaldoCuenta(CuentaBeans cuentaSaldo) {
        setContentPane(contentPane);
        setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(450, 250));
        this.setLocationRelativeTo(getParent());
        getRootPane().setDefaultButton(btnAbonar);

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
        btnAbonar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAbonar(cuentaSaldo.getIdCuenta(),cuentaSaldo.getDUI());
            }
        });
        btnRetirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRetirar(cuentaSaldo.getIdCuenta(),cuentaSaldo.getDUI());
            }
        });
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void onAbonar(int idCuenta, String dui){
        int monto = 0;

        monto = Integer.parseInt(txtCantidad.getText());

        int rowsChanged = cuentaDatos.ingresarSaldo(idCuenta,dui,monto);

        if(rowsChanged > 0){
            GeneralConfirm errorConfirmation = new GeneralConfirm("Abono realizado correctamente, actualice la tabla");
            errorConfirmation.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            errorConfirmation.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            errorConfirmation.setVisible(true);
            this.dispose();
        }else {
            GeneralConfirm errorConfirmation = new GeneralConfirm("Algo salió mal");
            errorConfirmation.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            errorConfirmation.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            errorConfirmation.setVisible(true);
        }
    }

    private void onRetirar(int idCuenta, String dui){
        int monto = 0;

        monto = Integer.parseInt(txtCantidad.getText());

        int rowsChanged = cuentaDatos.retirarSaldo(idCuenta,dui,monto);

        if(rowsChanged > 0){
            GeneralConfirm errorConfirmation = new GeneralConfirm("Retiro realizado correctamente, actualice la tabla");
            errorConfirmation.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            errorConfirmation.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            errorConfirmation.setVisible(true);
            this.dispose();
        }else {
            GeneralConfirm errorConfirmation = new GeneralConfirm("Está intentando retirar una cantidad mayor a su saldo");
            errorConfirmation.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            errorConfirmation.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            errorConfirmation.setVisible(true);
        }
    }


    public static void main(String[] args) {
        CuentaBeans cuentaBeans = new CuentaBeans(0,"", "", 0);
        CambiarSaldoCuenta dialog = new CambiarSaldoCuenta(cuentaBeans);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }


}
