import ImageResizer.ImageResizer;
import Query.ConnectDB;
import Query.Create;
import Query.Select;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPanel mainPanel;
    private JTextField txtId;
    private JPasswordField pfPwd;
    private JLabel lblLogo;
    private JButton btnLogin;
    private JButton btnRegist;

    private boolean isLogin = false;

    private void ClearForm() {
        txtId.setText("");
        pfPwd.setText("");
    }

    Login() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickName = new String(Select.Login(new ConnectDB(), txtId.getText(), pfPwd.getPassword()));
                System.out.println("Login user: " + nickName);

                if (!nickName.equals(null)) {
                    MainBoard mainBoard = new MainBoard(nickName);
                    dispose();
                }
            }
        });

        btnRegist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registration loginToRegist = new Registration();
                dispose();
            }
        });

        setContentPane(mainPanel);

        setSize(400, 300);
        ImageResizer.LoginImage(lblLogo);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Instagram Login");
        setVisible(true);
    }
}
