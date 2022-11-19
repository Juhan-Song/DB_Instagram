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
    private JButton btnOk;
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
    private JLabel lblOk;
    private JLabel lblRe;

    private boolean isIdPossible = false;
    private boolean isSuccess = false;

    private void ClearForm() {
        txtName.setText("");
        rdMale.setSelected(false);
        rdFemale.setSelected(false);
        txtId.setText("");
        lblOk.setText("");
        pfPwd.setText("");
        pfRePwd.setText("");
        txtNickname.setText("");
        txtBirth.setText("");
        txtPhone.setText("");
        ckAgree.setSelected(false);
    }

    Registration() {
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Check Duplicated Id...");
                isIdPossible = Select.isIdPossible(new ConnectDB(), txtId.getText());
                System.out.println(isIdPossible);

                if (isIdPossible == true) {
                    lblOk.setForeground(Color.GREEN);
                    lblOk.setText("Possible");
                }
                else {
                    lblOk.setForeground(Color.RED);
                    lblOk.setText("Impossible");
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

                if (isIdPossible == true && ckAgree.isSelected() && String.valueOf(pfPwd.getPassword()).length() >= 8) {
                    //String pwd = new String(String.valueOf(pfPwd.getPassword()));
                    if (rdMale.isSelected() && String.valueOf(pfPwd.getPassword()).equals(String.valueOf(pfRePwd.getPassword()))) {
                        nickName = Insert.InsertUserInfo(new ConnectDB(), txtId.getText(), pfRePwd.getPassword(),
                            txtName.getText(), rdMale.getText(), txtNickname.getText(), txtBirth.getText(), txtPhone.getText());
                        isSuccess = true;
                    }
                    else if (rdFemale.isSelected() && String.valueOf(pfPwd.getPassword()).equals(String.valueOf(pfRePwd.getPassword()))) {
                        nickName = Insert.InsertUserInfo(new ConnectDB(), txtId.getText(), pfRePwd.getPassword(),
                            txtName.getText(), rdFemale.getText(), txtNickname.getText(), txtBirth.getText(), txtPhone.getText());
                        isSuccess = true;
                    }
                    else if (!String.valueOf(pfPwd.getPassword()).equals(String.valueOf(pfRePwd.getPassword()))) {
                        System.out.println("Not Equal");
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
