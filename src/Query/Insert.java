package Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert {
    public static String InsertUserInfo(ConnectDB connectDB, String id, char[] pwd, String name, String gender, String nickname, String birth, String phone) {
        PreparedStatement pstmt = null;
        String result = new String();

        try {
            String psql = "insert into userinfo values (?, ?, ?, ?, ?, ?, ?, NULL, NULL)";
            pstmt = connectDB.getCon().prepareStatement(psql);

            pstmt.setString(1, id);
            pstmt.setString(2, String.valueOf(pwd));
            pstmt.setString(3, name);
            pstmt.setString(4, gender);
            pstmt.setString(5, nickname);
            pstmt.setString(6, birth);
            pstmt.setString(7, phone);

            int count = pstmt.executeUpdate();
            System.out.println("Updated Number Of Row: " + count);

            result = nickname;

            pstmt.close();
            connectDB.Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void InsertLogined(ConnectDB connectDB, String nickname, String name, String gender) {
        PreparedStatement pstmt = null;

        try {
            String psql = "insert into logined values (?, ?, ?, NOW())";
            pstmt = connectDB.getCon().prepareStatement(psql);

            pstmt.setString(1, nickname);
            pstmt.setString(2, name);
            pstmt.setString(3, gender);

            int count = pstmt.executeUpdate();

            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
