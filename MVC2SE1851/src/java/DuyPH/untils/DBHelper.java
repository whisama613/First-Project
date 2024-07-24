/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuyPH.untils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author whisa
 */
public class DBHelper implements Serializable {
    public static Connection getConnection()
        throws ClassNotFoundException, SQLException{
        //1. load Driver (Driver is available in progarm)
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2. Create Connection String
        String url = "jdbc:sqlserver://localhost:1433;"
                + "databaseName=SinhVien2K17;"
                + "instanceName=SQL2019";
        //3. Open Connection
        Connection con = DriverManager.getConnection(url, "sa", "12345");
        
        return con;
    }
    
    public static Connection getConnection1()
        throws ClassNotFoundException, SQLException, NamingException{
        Connection con = null;
        Context context = new InitialContext();
        Context end = (Context) context.lookup("java:comp/env");
        DataSource ds = (DataSource) end.lookup("DBCon");
        con = ds.getConnection();
        return con;
    }
}
