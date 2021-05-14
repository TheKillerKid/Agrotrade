package Model.DB;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

 


public class DBConnection {   
    //Constants used to get access to the database
    
    private static final String  driver = "jdbc:sqlserver://hildur.ucn.dk:1433";
    private static final String  databaseName = ";databaseName=dmaj0920_1089350";
    
    private static String  userName = "; user=dmaj0920_1089350";
    private static String password = ";password=Password1!";
   
    private DatabaseMetaData dma;
    private static Connection con;
    
    // an instance of the class is generated
    private static DBConnection  instance = null;

 

    // the constructor is private to ensure that only one object of this class is created
    private DBConnection()
    {
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
            //connection to the database
            con = DriverManager.getConnection(url);
            con.setAutoCommit(true);
            dma = con.getMetaData(); // get meta data
            System.out.println("Connection to " + dma.getURL());
            System.out.println("Driver " + dma.getDriverName());
            System.out.println("Database product name " + dma.getDatabaseProductName());
        }//end try
        catch(Exception e){
            System.out.println("Problems with the connection to the database:");
            System.out.println(e.getMessage());
            System.out.println(url);
        }//end catch
    }//end  constructor
       
  //closeDb: closes the connection to the database
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
    }//end closeDB
        
    //getDBcon: returns the singleton instance of the DB connection
    public Connection getDBcon()
    {
       return con;
    }
    //getDBcon: returns the singleton instance of the DB connection
    public static boolean instanceIsNull()
    {
       return (instance == null);
    }    
    //this method is used to get the instance of the connection
    public static DBConnection getInstance()
    {
        if (instance == null)
        {
          instance = new DBConnection();
        }
        return instance;
    }
    
    public Connection getConnection() {
        return con;
    }
    
    public int executeUpdate(String sql) throws SQLException {
        System.out.println("DBConnection, Updating: " + sql);
        int res = -1;
        try (Statement s = con.createStatement()){
            res = s.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return res;
    }

 

}//end DbConnection