package commands;

import java.sql.*;
import java.util.UUID;

/**
 *мда треш
 * эта штука по идее должна регистрировать
 */

public class Registration {
    public static String reg( String username, String password, Connection database){

       // if(password == null){return "НЕВЕРНЫЙ ПАРОЛЬ";}

        try {
            PreparedStatement st = database.prepareStatement("SELECT * FROM users where username = ?");
            PreparedStatement st2 = database.prepareStatement("INSERT INTO users(id, username, password) VALUES(?, ?, ?)");
            st.setString(1,username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                if(username.equals(rs.getString(2))){
                    rs.close();
                    st.close();
                    return "ПОЛЬЗОВАТЕЛЬ С ТАКИМ ИМЕНЕМ УЖЕ СУЩЕСТВУЕТ";
                }
            } else {
                System.out.println("добавлен новый пользователь: "+ username);
                long id = (long) Math.floor(Math.abs(UUID.randomUUID().hashCode()/100000));
                st2.setString(1, String.valueOf(id));
                st2.setString(2,username);
                st2.setString(3,password);
                st2.executeUpdate();
                st.close();
                rs.close();
                st2.close();
                return "РЕГИСТРАЦИЯ ПРОИЗОШЛА УСПЕШНО";
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "ОШИБКА";
    }
    public static String asEnter(String username, String password, Connection database ){
        try{
            PreparedStatement st = database.prepareStatement("SELECT * FROM users where username = ?");
            st.setString(1,username);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                if (password.equals(rs.getString(3))) {
                    rs.close();
                    st.close();
                    return "ВХОД ВЫПОЛНЕН";
                } else {
                    rs.close();
                    st.close();
                    return "НЕВЕРНЫЙ ПАРОЛЬ";
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return "ПОЛЬЗОВАТЕЛЯ С ТАКИМ ИМЕНЕМ НЕ СУЩЕСТВУЕТ";
    }
}
