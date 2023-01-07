package data;

import business.Piloto;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PilotoDAO implements Map<String, Piloto>{

    private static PilotoDAO singleton = null;

    private PilotoDAO(){
        try(Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD); 
            Statement stm = conn.createStatement()){

            String sql =    "CREATE TABLE IF NOT EXISTS Pilotos (" +
                            "Nome varchar(100) NOT NULL PRIMARY KEY," +
                            "Niveis_pericia varchar(45) DEFAULT NULL," +
                            "CTS decimal(2,1) DEFAULT NULL," +
                            "SVA decimal(2,1) DEFAULT NULL";
            stm.executeUpdate(sql);

        }catch (SQLException e){
            // Erro a criar tabela
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static PilotoDAO getInstance()
    {
        if (PilotoDAO.singleton == null)
        {
            PilotoDAO.singleton = new PilotoDAO();
        }
        return PilotoDAO.singleton;
    }
    

    @Override
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT count(*) FROM Pilotos") )
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
                     stm.executeQuery("SELECT Nome FROM Pilotos WHERE Nome='"+key.toString()+"'"))
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
        Piloto piloto = (Piloto) value;
        Piloto a = this.get(piloto.getNome());
        return piloto.equals(a);
    }

    @Override
    public Piloto get(Object key)
    {
        Piloto piloto = null;
        try (Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT * FROM Pilotos WHERE Nome='"+key+"'") )
        {
            if (rs.next())
            {
                piloto = new Piloto (rs.getString("Nome"), rs.getFloat("Niveis_pericia"), rs.getFloat("Cts"), rs.getFloat("Sva"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return piloto;
    }

    @Override
    public Piloto put(String key, Piloto value)
    {
        Piloto oldVal = this.get(key);
        try (Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
             Statement stm = conn.createStatement())
        {
            stm.executeUpdate(
                            "INSERT INTO Pilotos "+
                                        "VALUES ('"+ value.getNome()+ "', '"+
                                                     value.getNiveisPericia()+ "', '"+
                                                     value.getCts()+"', NULL) " +
                                                     value.getSva()+"', NULL) " +
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
    public Piloto remove(Object key)
    {
        Piloto a = this.get(key);
        try (Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
             Statement stm = conn.createStatement())
        {
            stm.executeUpdate("DELETE FROM Pilotos WHERE Nome='"+key.toString()+"'");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return a;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Piloto> m)
    {
        for (Piloto a : m.values())
            this.put(a.getNome(), a);
    }

    @Override
    public void clear()
    {
        try (Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
             Statement stm = conn.createStatement())
        {
            stm.executeUpdate("TRUNCATE Pilotos");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Set<String> keySet() {
        Set<String> r = new HashSet<>();
        try(Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT Nome from Pilotos")) {

            while (rs.next()){
                String p = rs.getString("Nome");
                r.add(p);
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return r;
    }

    @Override
    public Collection<Piloto> values()
    {
        Collection<Piloto> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Nome from Pilotos"))
        {
            while(rs.next())
            {
                String NomePiloto = rs.getString("Nome");
                Piloto a = this.get(NomePiloto);
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
    public Set<Entry<String, Piloto>> entrySet() {
        throw  new NullPointerException("Não implementado");
    }


}
