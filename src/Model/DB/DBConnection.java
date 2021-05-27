package Model.DB;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class DBConnection implements Runnable {
    
    private static final String  driver = "jdbc:sqlserver://hildur.ucn.dk:1433";
    private static final String  databaseName = ";databaseName=dmaj0920_1089350";
    
    private static String  userName = ";user=dmaj0920_1089350";
    private static String password = ";password=Password1!";
   
    private DatabaseMetaData dma;
    private static Connection con;

    private static DBConnection instance = null;

    private static boolean hasBeenLoaded = false;
    private static boolean loadingFinished = false;
    private static boolean isLoading = true;

    private DBConnection()
    {
        hasBeenLoaded = true;
        String url = driver + databaseName + userName + password;

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
        
        
        isLoading = false;
        loadingFinished = true;
    }
       
	public static void closeConnection()
    {
       try{
            con.close();
            instance= null;
            isLoading= false;
            System.out.println("The connection is closed");
        }
         catch (Exception e){
            System.out.println("Error trying to close the database " +  e.getMessage());
         }
    }
	
    public static DBConnection getInstance()
    {
    	/*while(isLoading && !loadingFinished) {
	        if (instance == null && !loadingFinished && !hasBeenLoaded)
	        {
	        	(new Thread(instance = new DBConnection())).start();
	        }
        }*/
    	
    	if(instance == null) {
    		instance = new DBConnection();
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