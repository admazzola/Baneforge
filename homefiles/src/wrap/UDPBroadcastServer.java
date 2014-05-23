package wrap;

import java.io.*;
import java.net.*;


public class UDPBroadcastServer implements Runnable{   


	String UDPIPADDRESSGROUP = "228.5.6.7";
	 int UDPPort = 34460; 
	 
	 int TCPPort = 36650;/// the default.. gets changed
    
    Core passed_core;

    // This chat server can accept up to 10 clients' connections

    //public static void main(String args[]) {
    
    public UDPBroadcastServer(int i, Core passed_core){
		 this.TCPPort = i;
		 this.passed_core = passed_core;
	}
    
    
    
    InetAddress group;
    MulticastSocket socket;
    public void run(){    	
    	   	
    	
    	try {
	    	group = InetAddress.getByName(UDPIPADDRESSGROUP);
	    	socket = new MulticastSocket(UDPPort);
	    	 socket.setReuseAddress(true);
	    	 socket.joinGroup(group);
	    	} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 
		while(true){
			
		     byte[] buf = new byte[16];
	       

	        //put stuff in buf
				try {
					
					
					  //wait for a packet
			    	  DatagramPacket packet = new DatagramPacket(buf, buf.length);
			    	  socket.receive(packet);
					
			    	  //bytes to int
			    	  ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
			          DataInputStream ois = new DataInputStream(bais);
			          Object incomingnumber = ois.readInt();
			          bais.close();
			          ois.close();
			         
			    	  if((int) (incomingnumber) == 0 ){
			    	  
			    	  
					
					  
					//int to bytes
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
			        DataOutputStream oos = new DataOutputStream(baos);
			        oos.writeInt(TCPPort);
			        buf = baos.toByteArray();
			        oos.close();
			        baos.close();
					
					
				
				socket.send(new DatagramPacket(buf, buf.length, group, UDPPort)); //broadcast packets of my TCP IP Address and TCP Port
			
			    	  }
			
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
   
    } 

    
    
    
   
	   
}