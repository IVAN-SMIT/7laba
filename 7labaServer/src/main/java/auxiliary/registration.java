package auxiliary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 *мда треш
 * эта штука по идее должна регистрировать
 */

public class registration {
    public static String run(String argument, String username, String password, Connection database){
        if (argument != null) {
            throw new IllegalArgumentException("registration не имеет аргументов!");
        }

        try {
            Statement st = database.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users where username = '" + username + "'");
            if (rs.next()) {

                if (password.equals(rs.getString(2))) {
                    rs.close();
                    st.close();
                    System.out.println("зарегался!");
                    return "SIGNED";
                } else {
                    rs.close();
                    st.close();
                    return "WRONG_PASS";
                }
            } else {
                //System.out.println(password);
                st.executeUpdate("INSERT INTO users(username, password) VALUES('" + username + "', '" + password + "')");
                rs.close();
                st.close();
                return "REGISTERED";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }
}
