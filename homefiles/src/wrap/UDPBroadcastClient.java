package wrap;

import java.io.*;
import java.net.*;


public class UDPBroadcastClient implements Runnable{   


	String UDPIPADDRESSGROUP = "228.5.6.7";
	 int UDPPort = 34460; 
	 int TCPPort = 0;
    
    Core passed_core;
    
    
    public UDPBroadcastClient(Core passed_core){
		  this.passed_core = passed_core;
	}
    
    
    InetAddress group;
    MulticastSocket socket;
    public void run(){
    	
    	  System.out.println("started UDP client");
    	
    	  try {
    		  
    		 

    		group = InetAddress.getByName("228.5.6.7");
        	socket = new MulticastSocket(UDPPort);
        	 socket.setReuseAddress(true);
        	 socket.joinGroup(group);
    	    	} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			socket.close();
    		}
    	
    	  byte[] buf = new byte[16];   
    	  
    	
    		
    		  
    		  new Thread(new Runnable() {
    				
					 public void run() {
						
	    				  while(!passed_core.finished_map_download){  
	    				  
	    				  byte[] buf = new byte[16];  
						  try {
							//int to bytes
			  				ByteArrayOutputStream baos = new ByteArrayOutputStream();
			  		        DataOutputStream oos = new DataOutputStream(baos);
							oos.writeInt(0);
			  		        buf = baos.toByteArray();
			  		        oos.close();
			  		        baos.close();
			  				socket.send(new DatagramPacket(buf, buf.length, group, UDPPort)); //broadcast packets of my TCP IP Address and TCP Port
			  		
			  				Thread.currentThread().sleep(1000);
			  				 
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	    				  
						  
	    				  }
						  
					} 
					 }).start();
    				 
    			  
    			
    	while(!passed_core.finished_map_download){
  				
  				
  			try {	
  				
    	  
    	  DatagramPacket packet = new DatagramPacket(buf, buf.length);
    	
    	  socket.receive(packet);
    	
    	  
    	  //bytes to int
    	  ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
          DataInputStream ois = new DataInputStream(bais);
          Object TCPPort = ois.readInt();
          bais.close();
          ois.close();
         
          
        
    	  
    	 
    	    
            InetAddress IPAddress = packet.getAddress();     
            
            System.out.println("UDP Packet from a host: " + IPAddress + " " + TCPPort);
    	    
            if( (int)(TCPPort) > 0){
            	
    	    passed_core.myFoundLocalHosts[0].isActive=true;
    	    passed_core.myFoundLocalHosts[0].IPAddress=IPAddress.toString().substring(1);
    	    passed_core.myFoundLocalHosts[0].port = (int) TCPPort;
    	    //need to fix this so multiple servers does not screw it all up!!
    	    }
    	    
    	    
    	    
    	   
    	 
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			socket.close();
		}
    	
    		  
    		 
    		  
    		  
    	  }
    	
    	 
    	
    	

    
    	
    
    	
} 


	   
}