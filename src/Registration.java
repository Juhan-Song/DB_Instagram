import Query.ConnectDB;
import Query.Create;
import Query.Insert;
import Query.Select;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Registration extends JFrame {
    private JTextField txtName;
    private JTextField txtId;
    private JButton btnId;
    private JPasswordField pfPwd;
    private JPasswordField pfRePwd;
    private JTextField txtNickname;
    private JTextField txtBirth;
    private JTextField txtPhone;
    private JRadioButton rdMale;
    private JRadioButton rdFemale;
    private JPanel mainPanel;
    private JCheckBox ckAgree;
    private JButton btnRegist;
    private JLabel lblId;
    private JLabel lblRe;
    private JButton btnNickname;
    private JLabel lblNickname;

    private boolean isIdPossible = false;
    private boolean isNicknamePossible = false;
    private boolean isSuccess = false;

    private void ClearForm() {
        txtName.setText("");
        rdMale.setSelected(false);
        rdFemale.setSelected(false);
        txtId.setText("");
        lblId.setText("");
        pfPwd.setText("");
        pfRePwd.setText("");
        txtNickname.setText("");
        txtBirth.setText("");
        txtPhone.setText("");
        ckAgree.setSelected(false);
    }

    Registration() {
        btnId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Check Duplicated Id...");
                isIdPossible = Select.isIdPossible(new ConnectDB(), txtId.getText());
                System.out.println(isIdPossible);

                if (isIdPossible == true) {
                    lblId.setForeground(Color.GREEN);
                    lblId.setText("Possible");
                }
                else {
                    lblId.setForeground(Color.RED);
                    lblId.setText("Impossible");
                }
            }
        });

        btnNickname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Check Duplicated Nickname...");
                if (!txtNickname.getText().contains("\'") && !txtNickname.getText().contains("\"")) {
                    isNicknamePossible = Select.isNicknamePossible(new ConnectDB(), txtNickname.getText());
                }
                else {
                    isNicknamePossible = false;
                }

                System.out.println(isNicknamePossible);

                if (isNicknamePossible == true) {
                    lblNickname.setForeground(Color.GREEN);
                    lblNickname.setText("Possible");
                }
                else {
                    lblNickname.setForeground(Color.RED);
                    lblNickname.setText("Impossible");
                }
            }
        });

        txtBirth.addFocusListener(new FocusListener() {
            String hint  = new String("ex) yyyy-mm-dd");
            @Override
            public void focusGained(FocusEvent e) {
                if (txtBirth.getText().equals(hint)) txtBirth.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtBirth.getText().length() == 0) txtBirth.setText("ex) yyyy-mm-dd");
            }
        });

        txtPhone.addFocusListener(new FocusListener() {
            String hint = new String("ex) xxx-xxxx-xxxx");
            @Override
            public void focusGained(FocusEvent e) {
                if (txtPhone.getText().equals(hint)) txtPhone.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtPhone.getText().length() == 0) txtPhone.setText(hint);
            }
        });

        btnRegist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickName = new String();
                System.out.println("Registration...");

                if (isIdPossible == true && isNicknamePossible == true) {
                    if (ckAgree.isSelected() && String.valueOf(pfPwd.getPassword()).length() >= 8) {
                        //String pwd = new String(String.valueOf(pfPwd.getPassword()));
                        if (rdMale.isSelected() && String.valueOf(pfPwd.getPassword()).equals(String.valueOf(pfRePwd.getPassword()))) {
                            nickName = Insert.InsertUserInfo(new ConnectDB(), txtId.getText(), pfRePwd.getPassword(),
                                    txtName.getText(), rdMale.getText(), txtNickname.getText(), txtBirth.getText(), txtPhone.getText());
                            isSuccess = true;
                        } else if (rdFemale.isSelected() && String.valueOf(pfPwd.getPassword()).equals(String.valueOf(pfRePwd.getPassword()))) {
                            nickName = Insert.InsertUserInfo(new ConnectDB(), txtId.getText(), pfRePwd.getPassword(),
                                    txtName.getText(), rdFemale.getText(), txtNickname.getText(), txtBirth.getText(), txtPhone.getText());
                            isSuccess = true;
                        } else if (!String.valueOf(pfPwd.getPassword()).equals(String.valueOf(pfRePwd.getPassword()))) {
                            System.out.println("Not Equal");
                        }
                    }
                }

                if (isSuccess == true) {
                    Create.CreateUserTable(new ConnectDB(), nickName);
                    Login RegistrationToLogin = new Login();
                    dispose();
                }
            }
        });

        setContentPane(mainPanel);

        setSize(600, 500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Instagram Registration Form");
        setVisible(true);
    }
}
