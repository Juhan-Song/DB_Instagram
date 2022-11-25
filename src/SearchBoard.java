import Query.ConnectDB;
import Query.Select;
import User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchBoard extends JFrame {
    private Connection con;
    private JPanel searchBoard;
    private JTextField txtSearch;
    private JScrollPane scrSearch;
    private JPanel container;
    private ArrayList<User> searched;

    public JPanel getSearchBoard() {
        return searchBoard;
    }

    SearchBoard() {
        searched = new ArrayList<User>();
        container.setLayout(new GridLayout(100, 1));

        scrSearch.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        txtSearch.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ConnectDB connectDB = new ConnectDB();
                con = connectDB.getCon();
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        txtSearch.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                return;
            }

            @Override
            public void keyPressed(KeyEvent e) {
                return;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //searchBoard.remove(scrSearch);
                container.removeAll();
                //scrSearch.revalidate();
                //scrSearch.repaint();
                scrSearch.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                String nickName = txtSearch.getText();

                searched = Select.SearchUser(con, nickName);
                System.out.println(nickName);

                //container = new JPanel(new GridLayout(100, 1));
                //container.setBackground(Color.RED);

                for (int i = 0; i < searched.size(); i++) {
                    JLabel lbl = new JLabel();
                    lbl.setText(searched.get(i).getNickName());
                    lbl.setSize(180,60);
                    container.add(lbl);
                    System.out.println(searched.get(i).getNickName());
                }

//                for (int i = 0; i < searched.size(); i++) {
//                    container.add(lblGroup.get(i));
//                }


                //scrSearch.add(container);

                //scrSearch.revalidate();
                //scrSearch.repaint();

                System.out.println("1");
                MainFrame.getTarget().removeAll();
                System.out.println("2");
                MainFrame.getTarget().add(searchBoard);
                System.out.println("3");
                txtSearch.requestFocus();
//                MainFrame.getTarget().revalidate();
//                MainFrame.getTarget().repaint();
//                MainFrame.setIsChange(true);
            }
        });

//        setTitle("Search-Board");
//        setSize(400, 400);
//        setContentPane(searchBoard);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setVisible(true);
    }
}
