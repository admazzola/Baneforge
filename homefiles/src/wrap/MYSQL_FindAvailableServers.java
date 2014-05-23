package wrap;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MYSQL_FindAvailableServers extends Thread{

	
   

    Core passed_core;

  
	 public MYSQL_FindAvailableServers(Core passed_core) {

		 this.passed_core = passed_core;
		 
	}
    
	
	
	 public void run() {	  
	
		 while(true){
			 //System.out.println("Waiting eight secs");
			 try {
				sleep(1000*8);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		 
		if( passed_core.current_MainMenu_Screen==passed_core.MENUSCREEN_JOINSERVER  && passed_core.serverbrowser_mode == 1) {
			
			
		
			
			System.out.println("Checking Account Timestamp");
		 

		     Connection con = null;
	        Statement st = null;
	        ResultSet rs = null;


	        String url = "jdbc:mysql://baneforge.com/toasty1_srvlst";
	        String user = "toasty1_ingame";
	        String password = "*OpTLe23#(G_";
	        
	        
	        
	        try {
	        	
	            con = DriverManager.getConnection(url, user, password);
	            st = con.createStatement();
	            
	           
	            ///should select where timestamp is within two hours!!
	            String query = 
	            		"SELECT * " +
	            		"FROM servers " +
	            		"WHERE (TIMESTAMP BETWEEN NOW() - INTERVAL 150 MINUTE AND NOW()) " +
	            		"AND (VERSION = '" + SharedData.CURRENTVERSION + "')";
	            rs = st.executeQuery(query);
	            
	            int num_found_rem_hosts = 0;
	            
	            
	                while ( rs.next() && num_found_rem_hosts < 98 ) {
	                    int numColumns = rs.getMetaData().getColumnCount();
	                    
	                   //id
	                    String IPADDRESS = rs.getObject(2).toString();
	                    String PORT = rs.getObject(3).toString();
	                   //time
	                    String NAME = rs.getObject(5).toString();
	                    
	                    System.out.println( "Found remote server at " + IPADDRESS );
	                    
	                    passed_core.myFoundRemoteHosts[num_found_rem_hosts].IPAddress=IPADDRESS;
	                    passed_core.myFoundRemoteHosts[num_found_rem_hosts].port=Integer.parseInt(PORT);
	                    passed_core.myFoundRemoteHosts[num_found_rem_hosts].name=NAME;
	                    passed_core.myFoundRemoteHosts[num_found_rem_hosts].isActive=true;
	                    
	                    num_found_rem_hosts++;
	                }
	            


	                
	              //sort
	    	    	for(int i=0;i< passed_core.myFoundRemoteHosts.length;i++){
	    	    		for(int j = i;j< passed_core.myFoundRemoteHosts.length;j++){
	    	    			
	    	    			if( passed_core.myFoundRemoteHosts[i].isActive &&  passed_core.myFoundRemoteHosts[j].isActive && i!= j){
	    	    			if( passed_core.myFoundRemoteHosts[i].IPAddress.equals( passed_core.myFoundRemoteHosts[j].IPAddress) &&  passed_core.myFoundRemoteHosts[i].port ==  passed_core.myFoundRemoteHosts[j].port  ){
	    	    				 passed_core.myFoundRemoteHosts[i].isActive=false;
	    	    			}
	    	    			}
	    	    			
	    	    		
	    	    		}
	    	    		
	    	    	}
	    	    	
	    	    	
	    	    	
	    	    	
					RemoteServerTestPing[] myTestPing = new RemoteServerTestPing[passed_core.myFoundRemoteHosts.length];
					Thread[] RemoteServerTestPingThread  = new Thread[passed_core.myFoundRemoteHosts.length];
										
	    	    	for(int i=0;i< passed_core.myFoundRemoteHosts.length;i++){	    	    	
	    	    			
	    	    			if( passed_core.myFoundRemoteHosts[i].isActive ){//set all to inactive, create test pinger for each
	    	    				passed_core.myFoundRemoteHosts[i].isActive=false;
	    	    				myTestPing[i] = new RemoteServerTestPing(passed_core.myFoundRemoteHosts[i]);	   
	    	    				RemoteServerTestPingThread[i] = new Thread(myTestPing[i]);
	    	    				RemoteServerTestPingThread[i].start();
	    	    				
	    	    			}
	    	    			
	    	    		
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
	        
		     
		     
		 
		 
		 
		 
		 
		 
		
	 }




		 }
	
	 }
	
}
