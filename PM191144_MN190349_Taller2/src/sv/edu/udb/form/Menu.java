package sv.edu.udb.form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu extends JFrame {
    private JLabel lblMenuTitle;
    private JButton btnAgregarMenu;
    private JButton btnRevisarMenu;
    private JButton btnSalirMenu;
    private JPanel pnlMenu;

    public Menu(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pnlMenu);
        this.setMinimumSize(new Dimension(600,500));
        this.setLocationRelativeTo(getParent());

        btnSalirMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnAgregarMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarMenuFn();
            }
        });
        btnRevisarMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarLoginFn();
            }
        });
    }

    public static void main(String[] args){
        JFrame frame = new Menu("Menu");
        frame.setVisible(true);
    }
    public void agregarMenuFn(){
        try{
            AddCliente clienteDialog = new AddCliente();
            clienteDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            clienteDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            clienteDialog.setVisible(true);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void agregarLoginFn(){
        try{
            CuentaLogin loginDialog = new CuentaLogin();
            loginDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            loginDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            loginDialog.setVisible(true);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
