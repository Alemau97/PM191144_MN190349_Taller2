package sv.edu.udb.form;

import sv.edu.udb.beans.ClienteBeans;
import sv.edu.udb.components.Confirmation;
import sv.edu.udb.components.GeneralConfirm;
import sv.edu.udb.datos.ClientesDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class CuentaLogin extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton btnCancelar;
    private JTextField txtDUI;
    private JTextField txtPIN;
    private JButton btnEntrar;

    ClienteBeans clienteBeans = null;
    ClientesDatos clientesDatos = new ClientesDatos();
    public CuentaLogin() {
        setContentPane(contentPane);
        setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(450, 250));
        this.setLocationRelativeTo(getParent());
        getRootPane().setDefaultButton(buttonOK);

        btnCancelar.addActionListener(new ActionListener() {
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
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onEntrar();
            }
        });
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void onEntrar(){
        String dui, pin;

        dui = txtDUI.getText();
        pin = txtPIN.getText();

        clienteBeans = new ClienteBeans("",dui, pin);

        String[] response = clientesDatos.login(dui, pin);
        ClienteBeans clienteResponse = new ClienteBeans(response[0], response[1], response[2]);
        if (response[1].equals(dui)){
            if (response[2].equals(pin)){
                CuentasMenu cuentasMenu = new CuentasMenu(clienteResponse);
                cuentasMenu.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                cuentasMenu.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                cuentasMenu.setVisible(true);
                this.dispose();
            }
            else{
                GeneralConfirm confirmationPwd = new GeneralConfirm("La contraseña es incorrecta, vuelva a intentarlo");
                confirmationPwd.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                confirmationPwd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                confirmationPwd.setVisible(true);
            }
        }
        else{
            GeneralConfirm confirmationBadLogin = new GeneralConfirm("El usuario con dui "+dui+" no existe");
            confirmationBadLogin.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            confirmationBadLogin.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            confirmationBadLogin.setVisible(true);
        }
    }

    public static void main(String[] args) {
        CuentaLogin dialog = new CuentaLogin();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
