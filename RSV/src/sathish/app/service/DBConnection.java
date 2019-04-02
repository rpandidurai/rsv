/**
 * 
 */
package sathish.app.service;

/**
 * @author EswaranKuppusamy
 *
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
*/

public class DBConnection {
	
	private static final Logger logger = LogManager.getLogger("RSVtraders");
	public static Connection makeConnection(){
		Connection connection = null;
		try {
			// Load the JDBC driver
			/*			
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:vtbeacon");			
			*/
                        
			 Context envCtx = (Context) new InitialContext().lookup("java:comp/env"); 
			 DataSource ds = (DataSource) envCtx.lookup("theapp"); 
                                
			connection = ds.getConnection();
			System.out.println("Db Connected Successfully");
			return connection;		
			
		} catch (SQLException e) {
			logger.info("SQL Error in DBConnection: "+e);
			System.out.println("Could not connect to the database "	+ e.getMessage());
		} catch (Exception e){
			logger.info("Error in DBConnection: "+e);
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void closeConnection(Connection con, Statement st, ResultSet rs) {
		try{
			if(rs!=null) rs.close();
			if(st!=null) st.close();
			if(con!=null) con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}