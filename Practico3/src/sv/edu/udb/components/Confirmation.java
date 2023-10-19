package sv.edu.udb.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Confirmation extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel lblMessage;
    private JLabel lblPin;

    public Confirmation(String message, String pin) {
        setContentPane(contentPane);
        setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 150));
        this.setLocationRelativeTo(getParent());
        getRootPane().setDefaultButton(buttonOK);

        lblMessage.setText(message);
        lblPin.setText("Su contraseña es: "+pin);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        dispose();
    }

    public static void main(String[] args) {
        Confirmation dialog = new Confirmation("", "");
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
