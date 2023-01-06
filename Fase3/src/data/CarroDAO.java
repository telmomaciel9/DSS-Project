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

public class CarroDAO implements Map<String, Carro> {

    private static CarroDAO singleton = null;

    private CarroDAO(){
        try(Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD); 
            Statement stm = conn.createStatement()){

            /*  
            String sql =    "CREATE TABLE IF NOT EXISTS Carros (" +
                            "Num varchar(10) NOT NULL PRIMARY KEY," +
                            "Nome varchar(45) DEFAULT NULL," +
                            "Email varchar(45) DEFAULT NULL," +
                            "Turma varchar(10), foreign key(Turma) references turmas(Id))";
            stm.executeUpdate(sql);
            */
        }catch (SQLException e){
            // Erro a criar tabela
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static CarroDAO getInstance()
    {
        if (CarroDAO.singleton == null)
        {
            CarroDAO.singleton = new CarroDAO();
        }
        return CarroDAO.singleton;
    }
    

    @Override
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT count(*) FROM Carros") )
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

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT Num FROM Carros WHERE Num='"+key.toString()+"'"))
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
        Carro Carro = (Carro) value;
        Carro a = this.get(Carro.getNumero());
        return Carro.equals(a);
    }

    @Override
    public Carro get(Object key)
    {
        Carro Carro = null;
        try (Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT * FROM Carros WHERE Num='"+key+"'") )
        {
            if (rs.next())
            {
                Carro = new Carro (rs.getString("Num"), rs.getString("Nome"), rs.getString("Email"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return Carro;
    }

    @Override
    public Carro put(String key, Carro value)
    {
        Carro oldVal = this.get(key);
        try (Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
             Statement stm = conn.createStatement())
        {
            stm.executeUpdate(
                            "INSERT INTO Carros "+
                                        "VALUES ('"+ value.getNumero()+ "', '"+
                                                     value.getNome()+ "', '"+
                                                     value.getEmail()+"', NULL) " +
                                        "ON DUPLICATE KEY UPDATE Nome=Values(Nome), "+
                                                                 "Email=Values(Email)");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return oldVal;
    }

    @Override
    public Carro remove(Object key)
    {
        Carro a = this.get(key);
        try (Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
             Statement stm = conn.createStatement())
        {
            stm.executeUpdate("DELETE FROM Carros WHERE Num='"+key.toString()+"'");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return a;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Carro> m)
    {
        for (Carro a : m.values())
            this.put(a.getNumero(), a);
    }

    @Override
    public void clear()
    {
        try (Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
             Statement stm = conn.createStatement())
        {
            stm.executeUpdate("TRUNCATE Carros");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Carro> values()
    {
        Collection<Carro> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Num from Carros"))
        {
            while(rs.next())
            {
                String NumCarro = rs.getString("Num");
                Carro a = this.get(NumCarro);
                res.add(a);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public Set<Entry<String, Carro>> entrySet() {
        return null;
    }

}
