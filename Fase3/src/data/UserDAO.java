package data;

import business.User;

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

    public UserDAO() {
        try(Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
            Statement stm = conn.createStatement()){

            String sql = "CREATE TABLE IF NOT EXISTS User (" +
                         "username varchar(50) NOT NULL PRIMARY KEY, " +
                         "password varchar(50) NOT NULL," +
                         "premium boolean NOT NULL)";
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

    // Número de users na bd
    @Override
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM User") )
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


    // Base de Dados não tem users
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }


    // Existe um dado user na base de dados
    @Override
    public boolean containsKey(Object key) {
        boolean r ;
        try (Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT username FROM User WHERE username='"+key.toString()+"'"))
        {
            r = rs.next();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    @Override
    public boolean containsValue(Object value)
    {
        User user = (User) value;
        return this.containsKey(user.getUsername());
    }

    @Override
    public User get(Object key)
    {
        User user = null;
        try (Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT * FROM User WHERE username='"+key+"'") )
        {
            if (rs.next())
            {
                user = new User (rs.getString("username"), rs.getBoolean("premium"),rs.getString("password"));

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return user;


    }

    @Override
    public User put(String key, User value) {
        throw  new NullPointerException("Não implementado");
    }

    @Override
    public User remove(Object key) {
        User u = this.get(key);
        try(Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
            Statement stm = conn.createStatement()) {
            stm.executeUpdate("DELETE  FROM User WHERE username = '" + key.toString() + "' ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return u;
    }

    @Override
    public void putAll(Map<? extends String, ? extends User> users) {
        for(User u : users.values())
            this.put((u.getUsername()),u);
    }

    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.execute("TRUNCATE User");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Set<String> keySet() {
        Set<String> r = new HashSet<>();
        try(Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT username FROM User")){

            while(rs.next()) {
                String u = rs.getString("Username");
                r.add(u);
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return r;

    }

    @Override
    public Collection<User> values() {
        Collection <User> r = new HashSet<>();
        try(Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT username FROM User")){
            while(rs.next()){
                String user = rs.getString("username");
                User u = this.get(user);
                r.add(u);
            }
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return r;
    }

    public static void povoa(){
        try(Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
            Statement stm = conn.createStatement()){
            String sql = "INSERT INTO User (username,password,premium)"+
                         "VALUES ('joao'+'joao'+false)" +
                         "('gato100'+'100gato'+false)"+
                         "('meninagira'+'102030'+true)"+
                         "ON DUPLICATE KEY UPDATE username=VALUES(username)";
            stm.executeUpdate(sql);
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    @Override
    public Set<Entry<String, User>> entrySet() {
        throw  new NullPointerException("Não implementado");
    }
}

