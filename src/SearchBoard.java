import Query.ConnectDB;
import Query.Select;
import User.User;

import javax.swing.*;
import javax.swing.text.TextAction;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class SearchBoard extends JFrame {
    private Connection con;
    private JPanel searchBoard;
    private JTextField txtSearch;
    private JScrollPane scrSearch;

    public JPanel getSearchBoard() {
        return searchBoard;
    }

    SearchBoard() {
        scrSearch.setLayout(new GridLayout());
        //scrSearch.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
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

        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                scrSearch.removeAll();
                String nickName = txtSearch.getText();
                ArrayList<User> searched = new ArrayList<User>();

                searched = Select.SearchUser(con, nickName);
                System.out.println(nickName);

                for (int i = 0; i < searched.size(); i++) {
                    JButton btn = new JButton();
                    btn.setText(searched.get(i).getNickName());
                    System.out.println(searched.get(i).getNickName());
                    scrSearch.add(btn);

                }
            }
        });
    }

//    public class TestPane extends JPanel implements Scrollable {
//        public TestPane(ArrayList<User> searched) {
//            setLayout(new GridBagLayout());
//            GridBagConstraints gbc = new GridBagConstraints();
//            gbc.gridx = 0;
//            gbc.gridy = 0;
//
//            for (int index = 0; index < searched.size(); index++) {
//                add(new JLabel(searched.get(index).getNickName()), gbc);
//                gbc.gridy++;
//            }
//        }
//
//        @Override
//        public Dimension getPreferredScrollableViewportSize() {
//            return new Dimension(100, 50);
//        }
//
//        @Override
//        public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
//            return 32;
//        }
//
//        @Override
//        public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
//            return 32;
//        }
//
//        @Override
//        public boolean getScrollableTracksViewportWidth() {
//            return getPreferredSize().width <= getWidth();
//        }
//
//        @Override
//        public boolean getScrollableTracksViewportHeight() {
//            return false;
//        }
//    }
}
