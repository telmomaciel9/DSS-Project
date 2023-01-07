package data;

import business.Carro;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserDAO implements Map<String, User> {
    private static UserDAO singleton = null;

    private UserDAO() {
        try(Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
            Statement stm = conn.createStatement()){

            String sql = "CREATE TABLE IF NOT EXISTS Users (" +
                         "Username varchar(50) NOT NULL PRIMARY KEY, " +
                         "Password varchar(50) NOT NULL)" +
                         "Admin boolean NOT NULL" +
                         "Premium boolean NOT NULL";
            stm.executeUpdate(sql);
            stm.close();
        }catch (SQLException e){
        e.printStackTrace();
        throw new NullPointerException(e.getMessage());
        }
    }

    public static UserDAO getInstance()
    {
        if (UserDAO.singleton == null)
        {
            UserDAO.singleton = new UserDAO();
        }
        return UserDAO.singleton;
    }

    @Override
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM Users") )
        {
            if (rs.next())
            {
                i = rs.getInt(1);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }

}
