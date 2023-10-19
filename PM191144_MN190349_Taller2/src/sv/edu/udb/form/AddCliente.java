package sv.edu.udb.form;

import sv.edu.udb.beans.ClienteBeans;
import sv.edu.udb.components.Confirmation;
import sv.edu.udb.components.GeneralConfirm;
import sv.edu.udb.datos.ClientesDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class AddCliente extends JDialog {
    private JPanel contentPane;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JTextField txtDUI;
    private JTextField txtTitular;

    ClienteBeans clienteBeans = null;
    ClientesDatos clientesDatos = new ClientesDatos();

    public AddCliente() {
        setContentPane(contentPane);
        setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(450, 250));
        this.setLocationRelativeTo(getParent());
        getRootPane().setDefaultButton(btnGuardar);

        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

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
    }

    private void onOK() {
        // add your code here

        String titular, dui, pin;

        pin = "";
        titular = txtTitular.getText();
        dui = txtDUI.getText();
        Random random = new Random();
        for(int i = 0; i <= 3; i++){
            pin += random.nextInt(10 - 1) + 1;
        }

        clienteBeans = new ClienteBeans(titular, dui, pin);

        String response = clientesDatos.insert(clienteBeans);
        if(!response.contains("Duplicate")){
            try{
                Confirmation confirmationDialog = new Confirmation("Registro agregado correctamente", pin);
                confirmationDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                confirmationDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                confirmationDialog.setVisible(true);
                this.dispose();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else{
            try{
                GeneralConfirm confirmationDialog = new GeneralConfirm("Dui repetido, este usuario ya tiene una cuenta");
                confirmationDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                confirmationDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                confirmationDialog.setVisible(true);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        AddCliente dialog = new AddCliente();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
