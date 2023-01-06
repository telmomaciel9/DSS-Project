package data;


public class DataBaseConfig {
    static final String USERNAME = "root";                       // Actualizar
    static final String PASSWORD = "root";                       // Actualizar
    private static final String DATABASE = "F1Manager";          // Actualizar
	private static final String DRIVER = "jdbc:mariadb";        // Usar para MariaDB
	// private static final String DRIVER = "jdbc:mysql";        // Usar para MySQL
    
    static final String URL = DRIVER+"://localhost:3306/"+DATABASE;

}