package sv.edu.udb.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GeneralConfirm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel lblContent;
    private JButton buttonCancel;

    public GeneralConfirm(String msg) {
        setContentPane(contentPane);
        setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 150));
        this.setLocationRelativeTo(getParent());
        getRootPane().setDefaultButton(buttonOK);

        lblContent.setText(msg);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    public static void main(String[] args) {
        GeneralConfirm dialog = new GeneralConfirm("");
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
