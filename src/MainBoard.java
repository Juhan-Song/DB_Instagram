import ImageResizer.ImageResizer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainBoard extends JFrame {
    private String user;
    private JPanel mainPanel;
    private JButton btnHome;
    private JButton btnSearch;
    private JButton btnVideo;
    private JButton btnUser;
    private JButton btnShop;
    private JLabel lblLogo;

    MainBoard(String nickName) {
        this.user = nickName;
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnVideo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnShop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserBoard userBoard = new UserBoard(user);
            }
        });

        JButton[] btnGroup = new JButton[5];
        btnGroup[0] = btnHome;
        btnGroup[1] = btnSearch;
        btnGroup[2] = btnVideo;
        btnGroup[3] = btnShop;
        btnGroup[4] = btnUser;

        setContentPane(mainPanel);

        setSize(400, 500);
        ImageResizer.MainBoardImage(lblLogo);
        ImageResizer.InterfaceImage(btnGroup);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Instagram - MainBoard");
        setVisible(true);
    }
}
