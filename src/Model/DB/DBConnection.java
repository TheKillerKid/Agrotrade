package Model.DB;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class DBConnection implements Runnable {
    
    private static final String  driver = "jdbc:sqlserver://hildur.ucn.dk:1433";
    private static final String  databaseName = ";databaseName=dmaj0920_1089350";
    
    private static String  username = ";user=dmaj0920_1089350";
    private static String password = ";password=Password1!";
   
    private DatabaseMetaData dma;
    private static Connection con;

    private static DBConnection  instance = null;

    private DBConnection()
    {
        String url = String.format("%s;databaseName=%s;user=%s;password=%s", driver, databaseName, username, password);

        try{
            //load of driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver class loaded ok");
          
        }
        catch(Exception e){
            System.out.println("Cannot find the driver");
            System.out.println(e.getMessage());
        }
        try{
            con = DriverManager.getConnection(url);
            con.setAutoCommit(true);
            dma = con.getMetaData();
            System.out.println("Connection to " + dma.getURL());
            System.out.println("Driver " + dma.getDriverName());
            System.out.println("Database product name " + dma.getDatabaseProductName());
        }
        catch(Exception e){
            System.out.println("Problems with the connection to the database:");
            System.out.println(e.getMessage());
            System.out.println(url);
        }
    }
    
    private DBConnection(String username, String password, String databaseName) {
    	String url = String.format("%s;databaseName=%s;user=%s;password=%s", driver, databaseName, username, password);

        try{
            //load of driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver class loaded ok");
          
        }
        catch(Exception e){
            System.out.println("Cannot find the driver");
            System.out.println(e.getMessage());
        }
        try{
            con = DriverManager.getConnection(url);
            con.setAutoCommit(true);
            dma = con.getMetaData();
            System.out.println("Connection to " + dma.getURL());
            System.out.println("Driver " + dma.getDriverName());
            System.out.println("Database product name " + dma.getDatabaseProductName());
        }
        catch(Exception e){
            System.out.println("Problems with the connection to the database:");
            System.out.println(e.getMessage());
            System.out.println(url);
        }
    }
    
    public static boolean instanceIsNull()
    {
       return (instance == null);
    }  
       
	public static void closeConnection()
    {
       try{
            con.close();
            instance= null;
            System.out.println("The connection is closed");
        }
         catch (Exception e){
            System.out.println("Error trying to close the database " +  e.getMessage());
         }
    }

    public static DBConnection getTestInstance(String username, String password, String databaseName)
    {
        if (instance == null)
        {
          (new Thread(instance = new DBConnection(username, password, databaseName))).start();
        }
        return instance;
    }

    public static DBConnection getInstance()
    {
        if (instance == null)
        {
          (new Thread(instance = new DBConnection())).start();
        }
        return instance;
    }

    public Connection getConnection() {
        return con;
    }

	@Override
	public void run() {
		
	}
}