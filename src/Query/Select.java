package Query;

import java.sql.*;

public class Select {
    public static boolean isIdPossible(ConnectDB connectDB, String id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            String sql = "select count(id) as result from userinfo where id = ?";
            pstmt = connectDB.getCon().prepareStatement(sql);

            pstmt.setString(1, id);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("result") == 0) result = true;
                else result = false;
            }

            rs.close();
            pstmt.close();
            connectDB.Disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isNicknamePossible(ConnectDB connectDB, String nickname) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            String sql = "select count(nickname) as result from userinfo where nickname = ?";
            pstmt = connectDB.getCon().prepareStatement(sql);

            pstmt.setString(1, nickname);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("result") == 0) result = true;
                else result = false;
            }

            rs.close();
            pstmt.close();
            connectDB.Disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String Login(ConnectDB connectDB, String id, char[] pwd) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String result = new String("");

        try {
            String sql = "select name, gender, nickname from userinfo where id = ? and pwd = ?";
            pstmt = connectDB.getCon().prepareStatement(sql);

            pstmt.setString(1, id);
            pstmt.setString(2, String.valueOf(pwd));

            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (!rs.getString("nickname").equals(null)) {
                    String nickname = rs.getString("nickname");
                    String name = rs.getString("name");
                    String gender = rs.getString("gender");

                    Insert.InsertLogined(connectDB, nickname, name, gender);
                    Update.UpdateLoginTime(connectDB, nickname);

                    result = nickname;
                }
            }

            rs.close();
            pstmt.close();
            connectDB.Disconnect();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
