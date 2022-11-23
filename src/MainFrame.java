import ImageResizer.ImageResizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private String user;
    private JPanel mainBoard;
    private JPanel userBoard;
    private JButton btnHome;
    private JButton btnSearch;
    private JButton btnVideo;
    private JButton btnUser;
    private JButton btnShop;
    private JLabel lblLogo;
    private JPanel mainPanel;
    private JPanel mainFrame;
    private static boolean isLogout;

    public boolean isLogout() {
        return isLogout;
    }

    public static void setLogout(boolean logout) {
        isLogout = logout;
    }

    MainFrame(String nickName) {
        this.user = nickName;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (isLogout == true) {
                    dispose();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (isLogout == true) {
                    dispose();
                }
            }
        });
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                mainPanel.add(new MainBoard().getMainBoard());
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                mainPanel.add(new SearchBoard().getSearchBoard());
                mainPanel.revalidate();
                mainPanel.repaint();
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
                mainPanel.removeAll();
                mainPanel.add(new UserBoard(nickName).getUserBoard());
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        JButton[] btnGroup = new JButton[5];
        btnGroup[0] = btnHome;
        btnGroup[1] = btnSearch;
        btnGroup[2] = btnVideo;
        btnGroup[3] = btnShop;
        btnGroup[4] = btnUser;

        setContentPane(mainFrame);
        mainPanel.setLayout(new GridLayout(1, 1));
        mainPanel.add(new MainBoard().getMainBoard());

        setSize(400, 500);
        ImageResizer.MainBoardImage(lblLogo);
        ImageResizer.InterfaceImage(btnGroup);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Instagram - MainBoard");
        setVisible(true);
    }
}
