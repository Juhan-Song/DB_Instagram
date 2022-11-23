import Query.ConnectDB;
import Query.Delete;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserBoard extends JFrame {
    private String user;
    private JButton btnLogout;
    private JPanel userBoard;

    private boolean isLogout = false;
    public JPanel getUserBoard() {
        return userBoard;
    }
    public boolean getIsLogout() {
        return isLogout;
    }

    UserBoard(String nickName) {
        this.user = nickName;

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Delete.Logout(new ConnectDB(), user);
                MainFrame.setLogout(true);
            }
        });

//        setSize(400, 400);
//        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        setTitle("Instagram - UserBoard");
//        setVisible(true);
    }
}
