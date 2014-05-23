package wrap;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;


//http://stackoverflow.com/questions/9796633/sending-data-from-ios-to-java-socket-server-and-recognizes-the-message

public class RemoteServerTestPing implements Runnable{

	

    
   Socket clientSocket = null;
   
    String host;
    int port;
    Core passed_core;
    
    HostServerInfo remote_server;
    
    boolean connection_lost=false;
    
    
     DataOutputStream os = null;
     DataInputStream is = null;
    BufferedReader inputLine = null;
    
  
	 public RemoteServerTestPing(HostServerInfo remsrv) {
		 this.remote_server = remsrv;
		 this.host = remsrv.IPAddress;
		 this.port = remsrv.port;
	}
    
	 		 
	 /*
	 public void run() {	
		 
		 
		 
		 System.out.println("Testing "+host+" via port "+ port);

	
	try {
       clientSocket = new Socket(host, port);    
       
     
			os = new DataOutputStream(clientSocket.getOutputStream());
		
					
			 os.writeUTF("/pinging"); 
			 
          
            System.out.println("Test validated with "+host+" in port "+ port);
            remote_server.isActive = true;
        } catch (UnknownHostException e) {
            System.out.println("Don't know about host "+host);
            remote_server.isActive = false;
            
        } catch (IOException e) {
        	System.err.println(e);
        	System.out.println("Couldn't get I/O for the connection to the host "+host);   //THIS IS FIRING WHEN TRYING TO REMOTELY CONNECT
        	remote_server.isActive = false;
        }
	

	
   }*/
		 
		 
public void run() {	
	System.out.println("Testing "+host+" via port "+ port);


	try {
       clientSocket = new Socket(host, port);                    
          
            System.out.println("Connected to "+host+" in port "+ port);
            
        } catch (UnknownHostException e) {
            System.out.println("Don't know about host "+host);
            
        } catch (IOException e) {
        	System.err.println(e);
        	System.out.println("Couldn't get I/O for the connection to the host "+host);   //THIS IS FIRING WHEN TRYING TO REMOTELY CONNECT
            
        }
	

	
	  if (clientSocket != null ) { 
		  remote_server.isActive = true;
		  
		  try {
				os = new DataOutputStream(clientSocket.getOutputStream());
				is = new DataInputStream(clientSocket.getInputStream());
				
				
					os.writeUTF("/pinging");
				
				 
				 clientSocket.close();
				 
				 
		  } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
	  }
}
		 
		 
		 
		boolean StringToBoolean(String s){
			  
			  boolean b = false;
			  if( Integer.parseInt(s) == 1){b = true;}
			  
			  return b;
		  }
		 
		int StringToInteger(String s) {
			
			int i = 0;
			
			if(s!=null){
			if (s.indexOf("-") > -1) {// handles numbers like "0-5" and makes it -5
				s = s.substring(s.indexOf("-"));
			}
			}

			try {
				i = Integer.parseInt(s);
			} catch (Exception e) {}

				
			
			return i;
		}

			
	  }
	 
	 
	 
	 
	  	 

	
	  
	  
	
	 
	 
	 
	
	
	
	
	
	
