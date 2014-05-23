package wrap;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;




public class ServerMultiThreaded implements Runnable{   



	String message;
	
    // Declaration section:
    // declare a server socket and a client socket for the server
    // declare an input and an output stream
    
   Socket clientSocket = null;
   ServerSocket serverSocket = null;
    
    int port=36550; //default
    Core passed_core;
    
    

    // This chat server can accept up to 10 clients' connections

     /* static */ clientThread t[] = new clientThread[10];           
    
    
    //public static void main(String args[]) {
    
    public ServerMultiThreaded(int i, Core passed_core){
		 this.port = i;
		 this.passed_core = passed_core;
	}
    
    
    
    
    
    public void run(){
	
	

    try {
	   serverSocket = new ServerSocket(port);   // --------------------------------------DISABLE FOR TESTING
	    System.out.println("Server bound to port "+ port);
        }
        catch (IOException e)
	    {
        	System.out.println(e);
        	passed_core.serverPortError();
	    }




    
	while(serverSocket!=null){
		
		
		
	    try {
		clientSocket = serverSocket.accept(); //--------------------------------------DISABLE FOR TESTING
		
		
		
		DataInputStream init_is = new DataInputStream(clientSocket.getInputStream());
		//DataOutputStream init_os = new DataOutputStream(clientSocket.getOutputStream());		    
		   		    
		    String initialmessage = init_is.readUTF();//wait until a message is received
		    
		    
			if(initialmessage.equals("/joiningashero")){
			CreateNewHeroThread();
			}
			
			if(initialmessage.equals("/pinging")){
				System.out.println("Got Pinged");
			}
		
		
		
	    }
	    catch (IOException e) {
		System.out.println(e);}

	
	}
	
	 
    }
    
    
    private void CreateNewHeroThread(){
    	
    	for(int i=2; i< 9 ; i++){		
		    
		    if(passed_core.Players[i].IsConnected == false)	
			{
			    (t[i] = new clientThread(clientSocket,t,i,passed_core)).start();
			    System.out.println("Client Thread "+ i + " Started");
			    break;
			}
		}
    	
    }
    
    
   
} 




class clientThread extends Thread{    //sets up the server's many other threads for communicating back and forth with clients
    
	
	
	/* Number of layers in the map. 
	public static final int NUMBER_OF_LAYERS = 6;
	
	public static final int TERRAIN_LAYER_LOWER = 0;   //types of terrain tiles include WALLS, DOORS, STONETILE...
	public static final int TERRAIN_LAYER_HIGHER = 1;
	public static final int SMALLOBJECTS_LAYER = 2; 
	public static final int PLAYER_LAYER = 3;   //types of player-layer tiles include HEROSTARTPOINT1 2 3, ENEMIES, CHEST, TRAP...

	public static final int LARGEOBJECTS_LAYER = 4; 

	public static final int NOTES_LAYER = 5; 
	
	*/
	
	
	
	
	
	public static final int MAP_SIZE = 100;
	
	 Core passed_core;
	 
	String currentmapfilepiece[] = new String[9];
	String[] s = new String[SharedData.NUM_OF_UNITSTATS];
	String[] item = new String[200];
	
	
	BufferedImage mybufferedBufferedimg;
	boolean myBufferedImageReady = false;
	
    DataInputStream is = null;
    DataOutputStream os = null;
    Socket clientSocket = null;       
    clientThread t[]; 
    String[] playernames;
    String thisclients_playername;
    int thisclients_playernumber;
   // boolean ready = false;   //CAN I GET RID OF THIS?
  // boolean endedturn = false;   
   
    
    String lastchatmessage = null;
    
    public clientThread(Socket clientSocket, clientThread[] t, int thisclients_playernumber, Core passed_core){
	this.clientSocket=clientSocket;
        this.t=t;
        this.thisclients_playernumber = thisclients_playernumber;
        this.passed_core = passed_core;
    }
    
    public void run() 
    {
	String message=null;

       
        
        for (int z = SharedData.TERRAIN_LAYER_LOWER; z <= SharedData.LARGEOBJECTS_LAYER; z++) {
        	currentmapfilepiece[z] = "";
        	
			for (int y = 0; y < MAP_SIZE; y++) {
				for (int x = 0; x < MAP_SIZE; x++) {
					
					String m=""+passed_core.map[z][y][x];
				
					
					if(m.length()==1){m="00"+m;}else if(m.length()==2){m="0"+m;}
					currentmapfilepiece[z] += (""+m);
					
				}}
				}
        
        
        
        currentmapfilepiece[(SharedData.REGION_LAYER)] = "";        
        for (int y = 0; y < MAP_SIZE; y++) {
			for (int x = 0; x < MAP_SIZE; x++) {
				int i=0;
				if(passed_core.define_regions[SharedData.REGION_INDOORS - 800][y][x]){i=1;}
				currentmapfilepiece[(SharedData.REGION_LAYER)] += (""+i);				
			}
			}
        
      
        currentmapfilepiece[(SharedData.REGION_LAYER+1)] = "";        
        for (int y = 0; y < MAP_SIZE; y++) {
			for (int x = 0; x < MAP_SIZE; x++) {
				int i=0;
				if(passed_core.define_regions[SharedData.REGION_DUNGEON - 800][y][x]){i=1;}
				currentmapfilepiece[(SharedData.REGION_LAYER+1)] += (""+i);				
			}
			}
        
        
        
        
	try{
	    is = new DataInputStream(clientSocket.getInputStream());
	    os = new DataOutputStream(clientSocket.getOutputStream());
	    
	    //os.writeUTF("hello mr.client");
	    os.writeUTF("playername?yournum:"+(thisclients_playernumber));//send initial data string for the client to read and respond to
	    
	    
	    message = is.readUTF();//get name
	    if(message.startsWith("playername:")){ 
	    	int index = message.lastIndexOf("|");
	    	  thisclients_playername=message.substring(11, index);
	    	 String descrip=message.substring(index + 1, message.length());
	    	  passed_core.tellserver_HeroJoinedGame(thisclients_playernumber,thisclients_playername,descrip);
	    	  }	    	
	    
	    message = is.readUTF();//get stats
	    if(message.startsWith("/mystats.")){ 			
			int[] i = new int[SharedData.NUM_OF_UNITSTATS]; for(int k=0;k<SharedData.NUM_OF_UNITSTATS;k++){i[k]=Integer.parseInt(message.substring(9+(4*k),12+(4*k)));   }
	    	passed_core.server_receiveInitialUnitStats(thisclients_playernumber,i);
		}
	   
	    message = is.readUTF();//get items
	    if(message.startsWith("/myitems.")){ 			
			int[] i = new int[100]; for(int k=0;k<100;k++){i[k]=Integer.parseInt(message.substring(9+(4*k),12+(4*k)));   }
	    	passed_core.server_receiveInitialUnitItems(thisclients_playernumber,i);
		}
	    
	    message = is.readUTF();//get item quantities
	    if(message.startsWith("/myitemquantities.")){ 			
			int[] i = new int[100]; for(int k=0;k<100;k++){i[k]=Integer.parseInt(message.substring(18+(4*k),21+(4*k)));   }
	    	passed_core.server_receiveInitialUnitItemQuantities(thisclients_playernumber,i);
		}
	    
	    
	    //upload map
	    os.writeUTF("/map."+SharedData.TERRAIN_LAYER_LOWER+"."+currentmapfilepiece[SharedData.TERRAIN_LAYER_LOWER]); //send the map in pieces    /map.0.DDDAAATTTAAA
	    os.writeUTF("/map."+SharedData.TERRAIN_LAYER_HIGHER+"."+currentmapfilepiece[SharedData.TERRAIN_LAYER_HIGHER]); 
	    os.writeUTF("/map."+SharedData.SMALLOBJECTS_LAYER+"."+currentmapfilepiece[SharedData.SMALLOBJECTS_LAYER]);
	    os.writeUTF("/map."+SharedData.PLAYER_LAYER+"."+currentmapfilepiece[SharedData.PLAYER_LAYER]);
	    os.writeUTF("/map."+SharedData.LARGEOBJECTS_LAYER+"."+currentmapfilepiece[SharedData.LARGEOBJECTS_LAYER]);
	  
	    //send a line message for every note that exists   "/map.4.00x.00y.00t.hellothereimamessage"
	/*    for (int y = 0; y < 100; y++) {
			for (int x = 0; x < 100; x++) {
				if(passed_core.map[NOTES_LAYER][y][x] >= YELLOW_NOTE && passed_core.map[NOTES_LAYER][y][x] <=GREEN_NOTE){

					String s1 =""+x;
					if(s1.length()==1){s1="00"+s1;}else if(s1.length()==2){s1="0"+s1;}
					String s2 =""+y;
					if(s2.length()==1){s2="00"+s2;}else if(s2.length()==2){s2="0"+s2;}
					String s3 =""+passed_core.map[NOTES_LAYER][y][x];
					if(s3.length()==1){s3="00"+s3;}else if(s3.length()==2){s3="0"+s3;}
					String s4 =""+passed_core.NoteMessages[x][y];
										
					os.writeUTF("/map."+NOTES_LAYER+"."+s1+"."+s2+"."+s3+"."+s4); 
					//os.writeUTF("/map."+NOTES_LAYER+".000.000.000.000"); 				
				}
			}
			}*/
	  
	    //send indoor region definitions
	    os.writeUTF("/map."+(SharedData.REGION_LAYER)+"."+currentmapfilepiece[(SharedData.REGION_LAYER)]);//send indoor definitions
	    os.writeUTF("/map."+(SharedData.REGION_LAYER+1)+"."+currentmapfilepiece[(SharedData.REGION_LAYER+1)]);//send dungeon definitions
	    
	    //give the new joiner all of the current players profile images!
	    
	    for (int i = 2; i < 9; i++) {
			if (passed_core.Players[i].IsConnected && i!= thisclients_playernumber) {
				sendProfileImage(i);
			}
			
		}
	    
	    
	    

			
		
		os.writeUTF("/giveprofileimage");//ask for heroes profile image!
	  
	   // try {Thread.sleep(500);} catch (InterruptedException e1) {e1.printStackTrace();}
	    
	   os.writeUTF("/sendingspellbook");
	    sendAssetbook();
	    
	   // try {Thread.sleep(500);} catch (InterruptedException e1) {e1.printStackTrace();}
	    
	    
	    
	    passed_core.sendAllExistingHeroesToAllPlayers();
	    
	    sendExistingUnits();
	    
	    	    
	    //send assets
	    // os.writeUTF("/map."+(NOTES_LAYER+2)+"."+currentmapfilepiece[(NOTES_LAYER+1)]);//send indoor definitions
	    
	    os.writeUTF("/finisheduploadingmap");
	    
	    
	    
	    System.out.println("Client Socket established! Initial data sent!");
	    
	    
		 do{
				message = is.readUTF();  //constantly keep waiting for inputs
				
				
				if(!message.startsWith("/profile")){
			        System.out.println("Client sent: "+message);   
				}
			        
			        /*
			      if(message.startsWith("playername:")){ 
			    	  thisclients_playername=message.substring(11, message.length());
			    	  passed_core.tellserver_HeroJoinedGame(thisclients_playernumber,thisclients_playername);}//if client gives us its playername..
			    	
			        
		  
			      
					if(message.startsWith("/mystats.")){  // /initialplayerstats.002.010,001,001,005,005,001 ...
												
						int[] i = new int[SharedData.NUM_OF_UNITSTATS];
						
						
						 for(int k=0;k<SharedData.NUM_OF_UNITSTATS;k++){i[k]=StringToInteger(message.substring(9+(4*k),12+(4*k)));   }
				    	
						passed_core.server_receiveInitialUnitStats(thisclients_playernumber,i);
						
					
					}
*/

			    	//  passed_core.server_receiveInitialClientStats(thisclients_playernumber,i);}//get all of the client stats
			        
			      
			      
			     if(message.startsWith("/editstats")){
			    	 passed_core.Server_Broadcast_EditStats(StringToInteger(message.substring(11,14)),StringToInteger(message.substring(15,18)),StringToInteger(message.substring(19,22)) );}// /editstats.sss.vvv.xxx.yyy.aaa
			     
			     
			     if(message.startsWith("/respawnmyhero")){
			    	 passed_core.server_BroadcastHeroRespawn( thisclients_playernumber  );
			    	 }// 
			     
			     
			     
			     if(message.startsWith("/spawn")){
				    	int offset = message.indexOf(".") + 1;
				    	passed_core.Server_BroadcastSpawnObject(
				    			StringToInteger(message.substring(offset,offset+3)),
				    			StringToInteger(message.substring(offset+4,offset+7)),
				    			StringToInteger(message.substring(offset+8,offset+11)),
				    			StringToInteger(message.substring(offset+12,offset+15)),
				    			StringToInteger(message.substring(offset+16,offset+19)) );} 
			     
			    if(message.startsWith("/edittileitemslotquantity")){
			    	int offset = message.indexOf(".") + 1;
			    	passed_core.Server_Broadcast_EditTileItemSlotQuantity(
			    			StringToInteger(message.substring(offset,offset+3)),
			    			StringToInteger(message.substring(offset+4,offset+7)),
			    			StringToInteger(message.substring(offset+8,offset+11)),
			    			StringToInteger(message.substring(offset+12,offset+15)),
			    			StringToBoolean(message.substring(offset+16,offset+17)),
			    			thisclients_playernumber);} 
		
			    if(message.startsWith("/edittileitemidquantity")){
			    	int offset = message.indexOf(".") + 1;
			    	passed_core.Server_Broadcast_EditTileItemIDQuantity(
			    			StringToInteger(message.substring(offset,offset+3)),
			    			StringToInteger(message.substring(offset+4,offset+7)),
			    			StringToInteger(message.substring(offset+8,offset+14)),
			    			StringToInteger(message.substring(offset+15,offset+18)),
			    			StringToBoolean(message.substring(offset+19,offset+20)),
			    			thisclients_playernumber);} 
			     
			    if(message.startsWith("/editunititemslotquantity")){
			    	int offset = message.indexOf(".") + 1;
			    	passed_core.Server_Broadcast_EditUnitItemSlotQuantity(
			    			StringToInteger(message.substring(offset,offset+3)),
			    			StringToInteger(message.substring(offset+4,offset+7)),
			    			StringToInteger(message.substring(offset+8,offset+11)),
			    			StringToInteger(message.substring(offset+12,offset+15)),
			    			StringToBoolean(message.substring(offset+16,offset+17)),
			    			thisclients_playernumber);} 
		
			    if(message.startsWith("/editunititemidquantity")){
			    	int offset = message.indexOf(".") + 1;
			    	passed_core.Server_Broadcast_EditUnitItemIDQuantity(
			    			StringToInteger(message.substring(offset,offset+3)),
			    			StringToInteger(message.substring(offset+4,offset+7)),
			    			StringToInteger(message.substring(offset+8,offset+14)),
			    			StringToInteger(message.substring(offset+15,offset+18)),
			    			StringToBoolean(message.substring(offset+19,offset+20)),
			    			thisclients_playernumber);} 
			    
			    
			    
			    if(message.startsWith("/swapunitbagslotitems")){
			    	int offset = message.indexOf(".") + 1;
			    	passed_core.Server_BroadcastSwapUnitBagSlotItems(
			    			StringToInteger(message.substring(offset,offset+3)),
			    			StringToInteger(message.substring(offset+4,offset+7)),
			    			StringToInteger(message.substring(offset+8,offset+11)),
			    			StringToInteger(message.substring(offset+12,offset+15)));} 
			     
			  
			    if(message.startsWith("/executeeffectwhole")){
			    	int offset = message.indexOf(".") + 1;
			    	passed_core.ExecuteEffectWhole(
			    			StringToInteger(message.substring(offset,offset+3)),
			    			StringToInteger(message.substring(offset+4,offset+7)),
			    			StringToInteger(message.substring(offset+8,offset+11)),
			    			StringToInteger(message.substring(offset+12,offset+15)),
			    			StringToInteger(message.substring(offset+16,offset+19)) );} 
			     
			    
			    if(message.startsWith("/profileimage.")){
			    	
			    for (int i = 0; i < 9; i++) {
					if (passed_core.networkThread_serv.t[i] != null) {
						passed_core.networkThread_serv.t[i].sendMessage(message);
					}
					
				}

			    
			    	
			    	
			    	int num = StringToInteger(  message.substring(14,15));
			    	int my_x = StringToInteger(  message.substring(16,19));
			    	
			    	if(my_x == 0){mybufferedBufferedimg = new BufferedImage(127, 127, BufferedImage.TYPE_INT_RGB);}
			    			
			    	//  for (int x = 0; x < 100; x++) {
						    for (int y = 0; y < 127; y++) {
						    	
						    int offset = 20;
						    int z = ( y)*12 ;
						    	
						    	
						  	String c = message.substring(offset + z,offset + z+10);
						    
						  	int r = Integer.parseInt(c.substring(0, c.indexOf("x")));
						  	
						  //	System.out.println(r);
						  	
						    try{	
						    	mybufferedBufferedimg.setRGB(my_x, y , r);
						    }catch(Exception e){}
						    
						  //  passed_core.LoadProfileImageWithBufferedImage(num,tempimg);
						    
						   
						        }
						   // }
			    	  
			    	  if(my_x == 127){
			    		 // passed_core.LoadProfileImageWithBufferedImage(num,mybufferedBufferedimg);
			    		  myBufferedImageReady = true;
			    	  }
			    	  
			    }
			    
			    
			     if(message.startsWith("/castability")){passed_core.Server_ExecuteHeroAbility(Integer.parseInt(message.substring(12,15)),Integer.parseInt(message.substring(16,19)),Integer.parseInt(message.substring(20,23)),Integer.parseInt(message.substring(24,27)),Integer.parseInt(message.substring(28,31)));} // /castabilityAAA.XXX,YYY.000,000
			     
			        //if(message.startsWith("/endmyturn")){passed_core.num_clientsendedturn++;passed_core.server_receiveClientEndTurn(thisclients_playernumber); }
			        	
			    		
			    	
			      if(message.startsWith("/chat")){
			    	  passed_core.Server_Broadcast_PlayerChatMessage(Integer.parseInt(message.substring(5,6)),message.substring(6, message.length()));
			    	    }
			      
			      if(message.startsWith("/storylog")){
			    	  passed_core.serverbroadcast_printtostorylog(message.substring((message.indexOf(":")+1), message.length()));
			    	  }
			      
			      
			      if(message.startsWith("/activebattleunitenddedturn")){
			    	  passed_core.GetUnitEndedBattleTurn();
			    	    }
			      
			      
			      
			      if(message.startsWith("/bye")){
			    	  


			  	
			  		//System.out.println("!!!!!!!!!!"+passed_core.Players[thisclients_playernumber].IsConnected);
			  		
			  		passed_core.tellserver_HeroLeftGame(thisclients_playernumber);
			  	    // close the output stream
			  	    // close the input stream
			  	    // close the socket
			  	    
			  	    is.close();
			  	    os.close();
			  	    clientSocket.close();
			    	  
			    	//t[thisclients_playernumber] = null;
			      }
			      
			      
				    
		//}while(!message.startsWith("/bye"));
	}while(passed_core.Players[thisclients_playernumber].IsConnected);  
	    
	 
   ///code that is here will never run!

	}
	catch(IOException e){};
    }
    

	 public synchronized void sendMessage(String str){  //allows the main thread to send messages over LAN

			try {
				os.writeUTF(str);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
			if(!str.startsWith("/profile")){
			System.out.println("sent:"+str);
			}
			
		    }
	 
	 
	 synchronized void SpecialWrite(String s){
		 try {
			os.writeUTF(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 synchronized void SpecialWrite(int i){
		 try {
			os.writeInt(i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 synchronized void SpecialWrite(boolean b){
		 //int i =0;
		 //if(b){i=1;}
		 try {
			os.writeBoolean(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
   

	 
	 synchronized void sendAssetbook(){
		 System.out.println("sending asset book"+passed_core.number_of_assets[0]);
	
		 try {
			os.writeUTF("abilitycount:"+passed_core.number_of_assets[0]);
		

			for (int i =1 + passed_core.SPECIALABILITYBUFFER; i < 1 + passed_core.number_of_assets[0] + passed_core.SPECIALABILITYBUFFER; i++) {
				
				//String j = ForceStringLength_3(""+i);
				
				SpecialWrite(passed_core.myAbilityDB[i].namestring);  
				SpecialWrite(passed_core.myAbilityDB[i].enabled);
				SpecialWrite(passed_core.myAbilityDB[i].description);
				SpecialWrite(passed_core.myAbilityDB[i].icon_ID);
				SpecialWrite(passed_core.myAbilityDB[i].passive);
				SpecialWrite(passed_core.myAbilityDB[i].cast_animation);
				SpecialWrite(passed_core.myAbilityDB[i].statrequired);
				SpecialWrite(passed_core.myAbilityDB[i].statrequired_amt);
				SpecialWrite(passed_core.myAbilityDB[i].abilityspec);
				SpecialWrite(passed_core.myAbilityDB[i].weapon_required);
				SpecialWrite(passed_core.myAbilityDB[i].requires_targetting);
				SpecialWrite(passed_core.myAbilityDB[i].constrain_to_cardinal_direction);
				SpecialWrite(passed_core.myAbilityDB[i].cardinal_direction_distance_override);
				SpecialWrite(passed_core.myAbilityDB[i].can_target_where_no_units_exist);
				SpecialWrite(passed_core.myAbilityDB[i].can_target_units);
				SpecialWrite(passed_core.myAbilityDB[i].can_target_allies);
				SpecialWrite(passed_core.myAbilityDB[i].can_target_self);
				SpecialWrite(passed_core.myAbilityDB[i].can_target_enemies);
				SpecialWrite(passed_core.myAbilityDB[i].require_targetpoint_walkable);
				SpecialWrite(passed_core.myAbilityDB[i].cast_range_base);
				SpecialWrite(passed_core.myAbilityDB[i].stat_increasing_range);
				SpecialWrite(passed_core.myAbilityDB[i].percent_factor_of_stat_increasing_range);
				SpecialWrite(passed_core.myAbilityDB[i].energy_cost_base);
				SpecialWrite(passed_core.myAbilityDB[i].stat_reducing_cost);
				SpecialWrite(passed_core.myAbilityDB[i].percent_factor_of_stat_reducing_cost);
				SpecialWrite(passed_core.myAbilityDB[i].cooldown_base);
				SpecialWrite(passed_core.myAbilityDB[i].stat_reducing_cooldown);
				SpecialWrite(passed_core.myAbilityDB[i].percent_factor_of_stat_reducing_cooldown);
				SpecialWrite(passed_core.myAbilityDB[i].counts_as_a_movement);
				SpecialWrite(passed_core.myAbilityDB[i].counts_as_an_attack);
				for (int n = 0; n < 10; n++) {
					SpecialWrite(passed_core.myAbilityDB[i].effects[n]);
				}
				SpecialWrite(passed_core.myAbilityDB[i].can_affect_allies);
				SpecialWrite(passed_core.myAbilityDB[i].can_affect_self);
				SpecialWrite(passed_core.myAbilityDB[i].can_affect_enemies);
				SpecialWrite(passed_core.myAbilityDB[i].passive_condition_applied);
			}
		 
		 //System.out.println("SENDIN SPELLBOOK");
			os.writeUTF("effectcount:"+passed_core.number_of_assets[1]);

			for (int i = 1 + passed_core.SPECIALEFFECTBUFFER; i < 1 + passed_core.number_of_assets[1] + passed_core.SPECIALEFFECTBUFFER; i++) {

				SpecialWrite(passed_core.myEffectDB[i].namestring);
				SpecialWrite(passed_core.myEffectDB[i].enabled);
				SpecialWrite(passed_core.myEffectDB[i].action);
				SpecialWrite(passed_core.myEffectDB[i].flip_cast_coords);
				SpecialWrite(passed_core.myEffectDB[i].trajectory_collision);
				SpecialWrite(passed_core.myEffectDB[i].trajectory_endsontop);
				SpecialWrite(passed_core.myEffectDB[i].shape_type);
				SpecialWrite(passed_core.myEffectDB[i].shape_radius);
				SpecialWrite(passed_core.myEffectDB[i].shape_angle_spread);
				SpecialWrite(passed_core.myEffectDB[i].can_affect_allies);
				SpecialWrite(passed_core.myEffectDB[i].can_affect_caster);
				SpecialWrite(passed_core.myEffectDB[i].can_affect_enemies);
				SpecialWrite(passed_core.myEffectDB[i].movement_animation);
				SpecialWrite(passed_core.myEffectDB[i].movement_speed);
				SpecialWrite(passed_core.myEffectDB[i].force_negative_edit);
				for(int n=0;n<5;n++){
					SpecialWrite(passed_core.myEffectDB[i].stat_to_edit[n]);
					SpecialWrite(passed_core.myEffectDB[i].stat_change_amount[n]);
					SpecialWrite(passed_core.myEffectDB[i].stat_that_affects_change[n]);
					SpecialWrite(passed_core.myEffectDB[i].stat_that_affects_change_pct[n]);
					SpecialWrite(passed_core.myEffectDB[i].sets_stat[n]);
					SpecialWrite(passed_core.myEffectDB[i].use_caster_stats[n]);
					//SpecialWrite(passed_core.myEffectDB[i].stat_change_is_temp[n]);
				}
				
				
				SpecialWrite(passed_core.myEffectDB[i].condition_ID);
				SpecialWrite(passed_core.myEffectDB[i].duration_base);
				SpecialWrite(passed_core.myEffectDB[i].stat_that_affects_duration);
				SpecialWrite(passed_core.myEffectDB[i].stat_that_affects_duration_pct);
				SpecialWrite(passed_core.myEffectDB[i].projectiletype);
				SpecialWrite(passed_core.myEffectDB[i].projectile_R);
				SpecialWrite(passed_core.myEffectDB[i].projectile_G);
				SpecialWrite(passed_core.myEffectDB[i].projectile_B);
				SpecialWrite(passed_core.myEffectDB[i].projectile_speed);
				SpecialWrite(passed_core.myEffectDB[i].projectile_luminescence);
				SpecialWrite(passed_core.myEffectDB[i].projectile_requires_unit_on_tile);
				for(int n=0;n<5;n++){
					SpecialWrite(passed_core.myEffectDB[i].projectile_death_effect[n]);
				}
				SpecialWrite(passed_core.myEffectDB[i].unit_type);
				SpecialWrite(passed_core.myEffectDB[i].Overlay_GFX_ID);
				SpecialWrite(passed_core.myEffectDB[i].Overlay_GFX_R);
				SpecialWrite(passed_core.myEffectDB[i].Overlay_GFX_G);
				SpecialWrite(passed_core.myEffectDB[i].Overlay_GFX_B);
				SpecialWrite(passed_core.myEffectDB[i].Overlay_GFX_Scale);
				SpecialWrite(passed_core.myEffectDB[i].SOUND_ID);

			}

			os.writeUTF("conditioncount:"+passed_core.number_of_assets[2]);
			
			for (int i = 1; i < 1 + passed_core.number_of_assets[2]; i++) {

				SpecialWrite(passed_core.myConditionDB[i].namestring);
				SpecialWrite(passed_core.myConditionDB[i].enabled);
				SpecialWrite(passed_core.myConditionDB[i].description);
				SpecialWrite(passed_core.myConditionDB[i].Icon_ID);
				SpecialWrite(passed_core.myConditionDB[i].binds_to_units);
				SpecialWrite(passed_core.myConditionDB[i].stacks_duration);
				SpecialWrite(passed_core.myConditionDB[i].stacks_magnitude);
				SpecialWrite(passed_core.myConditionDB[i].Overlay_GFX_ID);
				SpecialWrite(passed_core.myConditionDB[i].Overlay_GFX_R);
				SpecialWrite(passed_core.myConditionDB[i].Overlay_GFX_G);
				SpecialWrite(passed_core.myConditionDB[i].Overlay_GFX_B);
				SpecialWrite(passed_core.myConditionDB[i].Overlay_GFX_Scale);
				SpecialWrite(passed_core.myConditionDB[i].SOUND_ID);
				for (int j = 0; j < 5; j++) {
					SpecialWrite(passed_core.myConditionDB[i].passive_effect[j]);		
				
				}
				for (int j = 0; j < 5; j++) {
					SpecialWrite(passed_core.myConditionDB[i].periodic_effect[j]);		
				
				}

			}
			
		os.writeUTF("itemcount:"+passed_core.number_of_assets[3]);
			
			for (int i = 1; i < 1 + passed_core.number_of_assets[3]; i++) {

				SpecialWrite(passed_core.myItemDB[i].namestring);
				SpecialWrite(passed_core.myItemDB[i].enabled);
				SpecialWrite(passed_core.myItemDB[i].purchasable);
				SpecialWrite(passed_core.myItemDB[i].value);
				SpecialWrite(passed_core.myItemDB[i].description);
				SpecialWrite(passed_core.myItemDB[i].Icon_ID);
				SpecialWrite(passed_core.myItemDB[i].slotassignment);
				SpecialWrite(passed_core.myItemDB[i].equipmodel);
				SpecialWrite(passed_core.myItemDB[i].model_r);
				SpecialWrite(passed_core.myItemDB[i].model_g);
				SpecialWrite(passed_core.myItemDB[i].model_b);
				SpecialWrite(passed_core.myItemDB[i].weapontype);
				SpecialWrite(passed_core.myItemDB[i].itemlevel);
				SpecialWrite(passed_core.myItemDB[i].itemgroup);
				SpecialWrite(passed_core.myItemDB[i].max_stack_size);
				
				for (int j = 0; j < 5; j++) {
					SpecialWrite(passed_core.myItemDB[i].crafting_material[j]);
					SpecialWrite(passed_core.myItemDB[i].quantity_used[j]);
				}
				for (int j = 0; j < 5; j++) {
					SpecialWrite(passed_core.myItemDB[i].abilities_granted[j]);
				}for (int j = 0; j < 5; j++) {
					SpecialWrite(passed_core.myItemDB[i].use_effects[j]);
				}
				for (int j = 0; j < 5; j++) {
					SpecialWrite(passed_core.myItemDB[i].stat_to_increase[j]);
					SpecialWrite(passed_core.myItemDB[i].stat_bonus_amount[j]);
					SpecialWrite(passed_core.myItemDB[i].stat_affecting_amount[j]);
					SpecialWrite(passed_core.myItemDB[i].stat_affecting_amount_factor[j]);
				}

			}
			
	os.writeUTF("unitcount:"+passed_core.number_of_assets[4]);
			
			for (int i = 1; i < 1 + passed_core.number_of_assets[4]; i++) {

				SpecialWrite(passed_core.myNPCDB[i].namestring);
				SpecialWrite(passed_core.myNPCDB[i].enabled);
				SpecialWrite(passed_core.myNPCDB[i].description);
				SpecialWrite(passed_core.myNPCDB[i].alignment);
				SpecialWrite(passed_core.myNPCDB[i].model_ID);
				SpecialWrite(passed_core.myNPCDB[i].sound_id);
				SpecialWrite(passed_core.myNPCDB[i].category );
				SpecialWrite(passed_core.myNPCDB[i].invulnerable);
				SpecialWrite(passed_core.myNPCDB[i].immobile);
				SpecialWrite(passed_core.myNPCDB[i].expirytimer_on_spawn);
				SpecialWrite(passed_core.myNPCDB[i].base_level);
				SpecialWrite(passed_core.myNPCDB[i].base_endurance);
				SpecialWrite(passed_core.myNPCDB[i].base_stamina);
				SpecialWrite(passed_core.myNPCDB[i].base_meleepower);
				SpecialWrite(passed_core.myNPCDB[i].base_rangedpower);
				SpecialWrite(passed_core.myNPCDB[i].base_spellpower);
				SpecialWrite(passed_core.myNPCDB[i].base_armor);
				SpecialWrite(passed_core.myNPCDB[i].base_magres);
				SpecialWrite(passed_core.myNPCDB[i].base_toxres);
				SpecialWrite(passed_core.myNPCDB[i].base_initiative);
				SpecialWrite(passed_core.myNPCDB[i].base_speed);
				SpecialWrite(passed_core.myNPCDB[i].exp_reward);
				SpecialWrite(passed_core.myNPCDB[i].gold_carried);
				for (int j = 0; j < 5; j++) {
					SpecialWrite(passed_core.myNPCDB[i].abilities[j]);
				}
				for (int j = 0; j < 5; j++) {
					SpecialWrite(passed_core.myNPCDB[i].items_held_default[j]);
				}
				for (int j = 0; j < 5; j++) {
					SpecialWrite(passed_core.myNPCDB[i].items_dropped[j]);
					SpecialWrite(passed_core.myNPCDB[i].item_drop_percent[j]);
				}

			}
			
			
			
			
		 } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	 }
	 
	 void sendExistingHeroes(){
		

		    
		
		 //tells all players about all other players.  Will RESYNC things! wont matter since the game wont be out of sync ever :P
		  for(int i=2; i<9; i++){
		    	
		    	 try {
		    	if(passed_core.Players[i].IsConnected ){ //tell new joiner about all the previously connected players
		    		    
		    		
		    	
		    	
		    	//send stats of existing heroes
		    	//if( i != thisclients_playernumber ){
		    		
		    		String unitID = ForceStringLength_3(""+i);
		    		String x = ForceStringLength_3(""+ passed_core.Units[i].x);
					String y = ForceStringLength_3(""+ passed_core.Units[i].y);
		    		
		    	String m = "";
		    	
		    	
		    	//os.writeUTF("/playerexists"+(i)+"exists:" +passed_core.Players[i].name + "|" + passed_core.Players[i].herodescription);
		    	
		    	
		    	m = "/heroexists."+ForceStringLength(unitID,3)+"."+ForceStringLength(x,3)+"."+ForceStringLength(y,3)+"."+passed_core.Players[i].name + "|" + passed_core.Players[i].herodescription;
		    	
		    	sendMessage(m);
		    	
		    	
		    	m = "/existingunitstats."+ForceStringLength(unitID,3)+".";
		    	
		    	for(int n=0;n<SharedData.NUM_OF_UNITSTATS;n++){
					s[n]=""+passed_core.Units[i].stat[n];
					
					s[n] = ForceStringLength(s[n],3);
					
					m += s[n]+",";
				
				}
		    	
		    	sendMessage(m);


		    	m = "/existingunitbagitems."+unitID+".";		    	
		    	for(int n=0;n<30;n++){
		    		item[n]=""+passed_core.Units[i].itemInSlot[n];
					
		    		item[n] = ForceStringLength(item[n],5);
					
					m += item[n]+",";
				
				}		    	
		    	sendMessage(m);
		    	
		    	
		    	m = "/existingunitequipment."+unitID+".";		    	
		    	for(int n=50;n<70;n++){
		    		item[n]=""+passed_core.Units[i].itemInSlot[n];
					
		    		item[n] = ForceStringLength(item[n],5);
					
					m += item[n]+",";
				
				}		    	
		    	sendMessage(m);
		    
		    }
		 
		    		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
	 }
	 void sendExistingUnits(){
		 for(int i=10;i<10 + passed_core.NUMBER_OF_UNITS;i++){
			 
			 if(passed_core.Units[i]!=null){
			 
			 try {
				String unitID = ForceStringLength_3(""+i);
				String x = ForceStringLength_3(""+ passed_core.Units[i].x);
				String y = ForceStringLength_3(""+ passed_core.Units[i].y);
				
				
				 
					String m = "";
			    	m = "/unitexists."+unitID+"."+x+"."+y;
			    	sendMessage(m);
			    	
			    	
			    	
			    	m = "/existingunitstats."+unitID;			    	
			    	for(int n=0;n<SharedData.NUM_OF_UNITSTATS;n++){
						s[n]=""+passed_core.Units[i].stat[n];
						
						s[n] = ForceStringLength_3(s[n]);
						
						m += "," + s[n];					
					}			    	
			    	sendMessage(m);
			    	
			    	
			    	m = "/existingunitbagitems."+unitID+".";		    	
			    	for(int n=0;n<30;n++){
			    		item[n]=""+passed_core.Units[i].itemInSlot[n];
						
			    		item[n] = ForceStringLength(item[n],5);
						
						m += item[n]+",";
					
					}		    	
			    	sendMessage(m);
			    	
			    	
			    	m = "/existingunitequipment."+unitID+".";		    	
			    	for(int n=50;n<70;n++){
			    		item[n]=""+passed_core.Units[i].itemInSlot[n];
						
			    		item[n] = ForceStringLength(item[n],5);
						
						m += item[n]+",";
					
					}		    	
			    	sendMessage(m);
			    	
			    	
			    	
				
			    	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 }
			 
		 }
		 
		 
	 }
	 
	 
	 
	 String ForceStringLength_3(String s){
		 
			if(s.length()==1){s="00"+s;}
			
			else if(s.length()==2){s="0"+s;}
			
			else if(s.length()==0){s="000";}//weird quick fix
		 
		 
		return s;
		 
		 
		 
		 
	 }
	 
	 

	 private void sendProfileImage(final int p) {


	  	 
	    
	    	// new Thread(new Runnable() {
	    	//  	public void run(){
	    	  		
	    	  		BufferedImage bufProImage = null;
	    	  		
	    	  		bufProImage= passed_core.StoredBufferedProfileImages[p];
	    	  		
	    	  		String n = "";
	    	  	  	
			    	  for (int x = 0; x < 128; x++) {
			    		  
			    		  String x_2 = ""+x;
			    		  while(x_2.length() < 3){x_2 = "0" + x_2;}
			    		  
			    		  n = "/profileimage." + p + "." + x_2 + ".";
			    		  
						    for (int y = 0; y < 128; y++) {
						    
						  	String c = "";
						    	
						    try{	
						 c = "" + bufProImage.getRGB(x, y);
						    }catch(Exception e){}
						    
						    while(c.length() < 12){c += "x";}
						    
						    n += c ;
						        }
						    
						    sendMessage(n);
						    }
	    	 // 	}  }).start(); 
	    	 
		
	}
	 
	 
		boolean StringToBoolean(String s){
			  
			  boolean b = false;
			  if( Integer.parseInt(s) == 1){b = true;}
			  
			  return b;
		  }
		 
		int StringToInteger(String s) {

			int i = 0;

			if (s != null) {
				if (s.indexOf("-") > -1) {// handles numbers like "0-5" and makes it
											// -5
					s = s.substring(s.indexOf("-"));
					
				}
			}

			try {
				i = Integer.parseInt(s);
			} catch (Exception e) {
			}
			
			

			return i;
		}

		 
		
		String ForceStringLength(String s, int mylength) {

			while (s.length() < mylength) {

				s = "0" + s;
			}

			

			return s;
		}
	   
}