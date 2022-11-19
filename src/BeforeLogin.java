import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeforeLogin extends JFrame {
    private JLabel lblLogo;
    private JButton btnLogin;
    private JButton btnRegist;
    private JLabel lblLogin;
    private JLabel lblRegist;
    private JPanel mainPanel;

    BeforeLogin() {
        btnLogin.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Login login = new Login();
           }
        });

        btnRegist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registration registration = new Registration();
            }
        });

        setContentPane(mainPanel);

        setSize(600, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Instagram Main Screen");
        setVisible(true);
    }


}
