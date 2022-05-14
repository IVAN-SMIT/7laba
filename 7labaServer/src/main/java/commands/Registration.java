package commands;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 *мда треш
 * эта штука по идее должна регистрировать
 */

public class Registration {
    public static String run( String username, String password, Connection database){

        try {
            Statement st = database.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users where username = '" + username + "'");
            if (rs.next()) {

                if (password.equals(rs.getString(2))) {
                    rs.close();
                    st.close();
                    //System.out.println("вход успешен!");
                    return "ВХОД ВЫПОЛНЕН";
                } else {
                    rs.close();
                    st.close();
                    return "НЕВЕРНЫЙ ПАРОЛЬ";
                }
            } else {
                System.out.println("добавлен новый пользователь: "+ username);
                st.executeUpdate("INSERT INTO users(username, password) VALUES('" + username + "', '" + password + "')");
                rs.close();
                st.close();
                return "РЕГИСТРАЦИЯ ПРОИЗОШЛА УСПЕШНО";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ОШИБКА";
    }
}
