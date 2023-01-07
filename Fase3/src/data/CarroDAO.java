package data;

import business.Carro;
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

public class CarroDAO implements Map<Integer, Carro> {

    private static CarroDAO singleton = null;

    private CarroDAO(){
        try(Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD); 
            Statement stm = conn.createStatement()){

            String sql =    "CREATE TABLE IF NOT EXISTS Carros (" +
                            "ID int NOT NULL PRIMARY KEY"+
                            "Modelo varchar(50) NOT NULL ," +
                            "Marca varchar(50) NOT NULL ," +
                            "cilindrada int NOT NULL "+
                            "fiabilidade int DEFAULT NULL" +
                            "potencia int DEFAULT NULL," +
                            "tipoPneu varchar(5) DEFAULT NULL," +
                            "modoMotor varchar(5) DEFAULT NULL," +
                            "CONSTAINT CheckTipoPneu CHECK(tipoPneu in ('macio', 'duro', 'chuva')";
                    
            stm.executeUpdate(sql);
            //CONSTRAINT `MarcaModelo` PRIMARY KEY (`marca`, `modelo`),
                        //CONSTRAINT `MarcaModelo` PRIMARY KEY (`marca`, `modelo`),
                        //CONSTRAINT `CheckTipo` CHECK(`tipoCarro` IN ('C1', 'C1H', 'C2', 'C2H', 'GT', 'GTH', 'SC')),
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
                     stm.executeQuery("SELECT ID FROM Carros WHERE Marca='"+key.toString()+"'"))
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
        Carro a = this.get(Carro.getMarca());
        Carro b = this.get(Carro.getModelo());
        return ( Carro.equals(a) && Carro.equals(b) );
    }

    @Override
    public Carro get(Object key)
    {
        Carro Carro = null;
        try (Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT * FROM Carros WHERE ID='"+key+"'") )
        {
            if (rs.next())
            {
                Carro = new Carro(rs.getInt("ID"),
                        rs.getString("Marca"),
                        rs.getString("Modelo"),
                        rs.getInt("cilindrada"),
                        rs.getInt("potencia"),
                        0, new Piloto(),
                        rs.getString("tipoPneu"),
                        rs.getString("modoMotor")) {

                    @Override
                    public business.Carro clone() {
                        return null;
                    }

                    @Override
                    public boolean DNF(int volta, int totalvoltas, int clima) {
                        return false;
                    }
                };
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
    public Carro put(Integer key, Carro value)
    {
        Carro oldVal = this.get(key);
        try (Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
             Statement stm = conn.createStatement())
        {
            stm.executeUpdate(
                            "INSERT INTO Carros "+
                                        "VALUES ('"+ value.getID()+ ", "+
                                                     value.getCilindrada()+", "+
                                                     value.getPotencia()+ "', '"+
                                                     value.getMarca()+ "', '"+
                                                     value.getModelo()+ "', '"+
                                                     value.getTipoPneu()+ "', '"+
                                                     value.getModoMotor()+"', NULL) " +
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
            stm.executeUpdate("DELETE FROM Carros WHERE Marca='"+key.toString()+"'");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return a;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Carro> m)
    {
        for (Carro a : m.values())
            this.put(a.getID(), a);
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
    public Set<Integer> keySet() {
        Set<Integer> r = new HashSet<>();
        try(Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT ID from Carros")){
            while(rs.next()){
                int c = rs.getInt("ID");

            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return r;
    }

    @Override
    public Collection<Carro> values()
    {
        Collection<Carro> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT ID from Carros"))
        {
            while(rs.next())
            {
                String IDCArro = rs.getString("ID");
                Carro a = this.get(IDCArro);
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
    public Set<Entry<Integer, Carro>> entrySet() {
        throw  new NullPointerException("Não implementado");
    }

}
