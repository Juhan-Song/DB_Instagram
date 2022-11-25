import ImageResizer.ImageResizer;
import Query.ConnectDB;
import Query.Select;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements Runnable {
    public static int totalUserNumber;
    private String user;
    private JButton btnHome;
    private JButton btnSearch;
    private JButton btnVideo;
    private JButton btnUser;
    private JButton btnShop;
    private JLabel lblLogo;
    public JPanel mainPanel;
    private static JPanel target;

    public static JPanel getTarget() {
        return target;
    }

    public void setTarget(JPanel target) {
        this.target = target;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel main) {
        mainPanel.removeAll();
        System.out.println("BBBB");
        mainPanel.add(main);
        System.out.println("AAAA");
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JPanel mainFrame;
    private static boolean isChange = false;

    public static boolean getIsChange() {
        return isChange;
    }

    public static void setIsChange(boolean isChange) {
        MainFrame.isChange = isChange;
    }

    private static boolean isLogout = false;

    public boolean isLogout() {
        return isLogout;
    }

    public static void setLogout(boolean logout) {
        isLogout = logout;
    }

    MainFrame(String nickName) {
        this.user = nickName;
        totalUserNumber = Select.TotalUserNumber(new ConnectDB());

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
                target = mainPanel;
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

    public void run() {
//        while (isChange == false) {
//            if (isChange == true) break;
//        }
//        isChange = false;
//        mainPanel = target;
//        mainPanel.revalidate();
//        mainPanel.repaint();
    }
}
