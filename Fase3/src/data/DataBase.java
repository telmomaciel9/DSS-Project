package data;

public class DataBase {
    static final String USERNAME = "telmomaciel9";                       // Actualizar
    static final String PASSWORD = "password";                       // Actualizar
    private static final String DATABASE = "F1Manager";          // Actualizar
	private static final String DRIVER = "jdbc:mariadb";        // Usar para MariaDB
	// private static final String DRIVER = "jdbc:mysql";        // Usar para MySQL
    
    static final String URL = DRIVER+"://localhost:3306/"+DATABASE;

}
