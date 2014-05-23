package wrap;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PingAccountTimestampThreaded extends Thread{

	
 //this will now just visit a PHP script every 5 mins or so.  It will update the IP address and time stamp if the IP is the same or timestamp is recent (one hour)!
	
	
    Core passed_core;

  
	 public PingAccountTimestampThreaded(Core passed_core) {

		 this.passed_core = passed_core;
		 
	}
    
	
	
	 public void run() {	  
	
		 while(true){
			
		 
		if(passed_core.ACCOUNT_NAME != null && passed_core.current_MainMenu_Screen==-1  /*&& passed_core.loadedcustommaporbook*/   ) {
			
			
			 
			
			System.out.println("Updating Account Timestamp");
			
			

			// send username and password (plaintext :() out to the website with
			// POST

			try {

				// you need to encode ONLY the values of the parameters
				String param = "username=" + URLEncoder.encode(passed_core.ACCOUNT_NAME, "UTF-8")
						+ "&ipaddress=" + URLEncoder.encode(passed_core.ipAddr_global, "UTF-8");

				HttpURLConnection conn = (HttpURLConnection) new URL("http://baneforge.com/pingaccount.php").openConnection();
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				//conn.setDoInput(true);

				
				conn.setFixedLengthStreamingMode(param.getBytes().length);
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				// send the POST out
				PrintWriter out = new PrintWriter(conn.getOutputStream());
				out.print(param);
				out.close();

				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			
			
			
		 
		 
		 //connect to mySQL, check count of users with my name
/*
		     Connection con = null;
		     PreparedStatement st = null;
	        ResultSet rs = null;


	        String dburl = "jdbc:mysql://baneforge.com/toasty1_accounts";
	        String dbuser = "toasty1_ingame";
	        String dbpassword = "*OpTLe23#(G_";
	        
	        
	        String username = (passed_core.ACCOUNT_NAME).toLowerCase();
	        try {
	        
	            con = DriverManager.getConnection(dburl, dbuser, dbpassword);
	            String query = "";
	       
	            
	            ///see if user exists
	            
	            //query = "SELECT COUNT(*) FROM users WHERE name = ?";	            
	            query = "SELECT COUNT(*) AS total " +
	            		"FROM users " +
	            		"WHERE (name = ?) " +
	            		"AND (TIMESTAMP BETWEEN NOW() - INTERVAL 9 MINUTE AND NOW())";
	           
	            
	            st = con.prepareStatement(query);
	            st.setString(1,username);
	           
	            rs = st.executeQuery(); 
	            
	            rs.next();
	            int count_otherexistingusers = rs.getInt("total");
	            
	            
	            
	            
	            
	            //update timestamp on my account line.  THIS SHOULD BE DONE BY PHP
	            
	            query = "UPDATE users " +
	            		"SET timestamp = current_timestamp " +
	            		"WHERE (name = ?) ";
	           
	            
	            st = con.prepareStatement(query);
	            st.setString(1,username);
	           
	            st.executeUpdate();
	            
	           
	            
	            
	            if(count_otherexistingusers > 0){

	            System.out.println("MULTIPLE USERS DETECTED!!!!");
	            //CLOSE OUT OF GAME OR SOMETHING!
	            
	            passed_core.ShowWarningDialog("This account is in use by another person! Exiting...");
	        	try {
					sleep(1000*5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	        	
	        	//passed_core.GameRunning = false;  //do not crash the game, just disallow 999 people from joining with this acct!!!
	        	
	        	
	            }
	            
	          

	     
	            

	        } catch (SQLException ex) {
	          System.out.println(ex);

	        } finally {
	            try {
	                if (rs != null) {
	                    rs.close();
	                }
	                if (st != null) {
	                    st.close();
	                }
	                if (con != null) {
	                    con.close();
	                }

	            } catch (SQLException ex) {
	            	  System.out.println(ex);
	            }
	        }
	        
		      */
		 
			 System.out.println("Waiting five mins");
			 try {
				sleep(1000*60*5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		 
		
	 }
		
		
		
		 System.out.println("Waiting five mins");
		 try {
			sleep(1000*60*5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
		
		
		




		 }
	
	 }
	
}
