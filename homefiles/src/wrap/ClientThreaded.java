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

public class ClientThreaded implements Runnable{

	
	String message;
	//String playername;
String Hostsplayername;

/** Number of layers in the map. */
public static final int NUMBER_OF_LAYERS = 6;
/** The map layer of the ground. */
public static final int TERRAIN_LAYER_LOWER = 0;   //types of terrain tiles include WALLS, DOORS, STONETILE...
public static final int TERRAIN_LAYER_HIGHER = 1;
/** The map layer of the small objects low on the ground like items and traps. */
public static final int SMALLOBJECTS_LAYER = 2; 
/** The map layer of the Objects high up on the ground. */
public static final int PLAYER_LAYER = 3;   //types of player-layer tiles include HEROSTARTPOINT1 2 3, ENEMIES, CHEST, TRAP...
/** The map layer of the large objects that cover up players like walls and pillars. */
public static final int LARGEOBJECTS_LAYER = 4; 
/** The map layer of DM notes */
public static final int NOTES_LAYER = 5; 




    // Declaration section
    // clientClient: the client socket
    // os: the output stream
    // is: the input stream
    
   Socket clientSocket = null;
   
   BufferedImage mybufferedBufferedimg;
	int PlayerProfileImageReady = 0;
	
  
    String host;
    int port;
    Core passed_core;
    
    boolean connection_lost=false;
    
//boolean ClientStartGame;
String lastchatmessage;
    
     DataOutputStream os = null;
     DataInputStream is = null;
    BufferedReader inputLine = null;
    
  
	 public ClientThreaded(String s, int i, Core passed_core) {
		 this.host = s;
		 this.port = i;
		 this.passed_core = passed_core;
		
	}
    
	 		 
	 
	 public void run() {	
		 
		 
		 
		 System.out.println("Connecting to "+host+" in port "+ port);

	// Initialization section:
	// Try to open a socket on a given host and port
	// Try to open input and output streams
	try {
       clientSocket = new Socket(host, port);                    
          
            System.out.println("Connected to "+host+" in port "+ port);
            
        } catch (UnknownHostException e) {
            System.out.println("Don't know about host "+host);
            passed_core.clientCouldNotJoinServer();
            
        } catch (IOException e) {
        	System.err.println(e);
        	System.out.println("Couldn't get I/O for the connection to the host "+host);   //THIS IS FIRING WHEN TRYING TO REMOTELY CONNECT
            passed_core.clientCouldNotJoinServer();
        }
	

	
	  if (clientSocket != null ) { 
      
		 passed_core.QueueNewMainMenuScreen(passed_core.MENUSCREEN_LOADSCREEN, 3);
			
		  
      	  inputLine = new BufferedReader(new InputStreamReader(System.in));
      	
			try {
			os = new DataOutputStream(clientSocket.getOutputStream());
			is = new DataInputStream(clientSocket.getInputStream());
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
			 sendMessage("/joiningashero"); 
		 
		 
			do{
								
				try {
					ReadTCPMessage();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					connection_lost=true;
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				
			
		}while(!connection_lost);
			connection_lost = true;
			passed_core.ShowWarningDialog("Lost connection to host!");
	  
	}
   }
		 
		 
		 
		 public void ReadTCPMessage() throws IOException{//experiencing deadlock from the display loop??
			 message = is.readUTF();
			 
				
			
			if(!message.startsWith("/map.") && !message.startsWith("/profile")){
		        System.out.println("Server sent: "+message);   //constantly keep waiting for inputs
			}
		        
		        
		      if(message.startsWith("playername?")){ 
		    	  int old_PNum = passed_core.myPNum; //THIS HAS TO BE ZERO
		    	  
		    	  passed_core.myPNum=Integer.parseInt(message.substring(19, 20));
		    	  System.out.println("ive been assigned "+ passed_core.myPNum);
		    	  os.writeUTF("playername:"+passed_core.Players[old_PNum].name + "|" + passed_core.Players[old_PNum].herodescription);
		    	
		    	  
		    	  
		    	  
		    	//store stats for passing
		    	String m = null;
			    	m = "/mystats.";
		    	String[] s = new String[SharedData.NUM_OF_UNITSTATS];
				
		    	
				for(int i=0;i<SharedData.NUM_OF_UNITSTATS;i++){
					s[i]=""+passed_core.Units[old_PNum].stat[i];//my init stats are stored in my units[1]
					if(i==SharedData.PLAYER_OWNERSHIP){s[i]= ""+passed_core.myPNum ;}  //always tell the server that my hero is owned by me!!
					if(s[i].length()==1){s[i]="00"+s[i];}else if(s[i].length()==2){s[i]="0"+s[i];}
					m += s[i]+".";
				}
		    	  
		    	  sendMessage(m);   System.out.println("Sent My Stats:"+m);
		    	  
		    	  
		    	  
			     m = null;
			    m = "/myitems.";
		    	s = new String[100];
		    	
				for(int i=0;i<100;i++){
					s[i]=""+passed_core.Units[old_PNum].itemInSlot[i];//my init stats are stored in my units[1]
					if(s[i].length()==1){s[i]="00"+s[i];}else if(s[i].length()==2){s[i]="0"+s[i];}
					m += s[i]+".";
				}
		    	  sendMessage(m); System.out.println("Sent My Items:"+m);
		    	  
		    	 
		    	  
		    	  m = null;
				    m = "/myitemquantities.";
			    	s = new String[100];
			    	
					for(int i=0;i<100;i++){
						s[i]=""+passed_core.Units[old_PNum].itemQuantity[i];//my init stats are stored in my units[1]
						if(s[i].length()==1){s[i]="00"+s[i];}else if(s[i].length()==2){s[i]="0"+s[i];}
						m += s[i]+".";
					}
			    	  sendMessage(m); System.out.println("Sent My Item Quantities:"+m);
		    	  
		    	  
		    	  

		    	  
		    	  
		    	  
		      }
			
		      
		  
	
		  
		      
		if(message.startsWith("/sendingspellbook")){receiveSpellbook();}//server tells client to get into the game
	      
		        
		      if(message.startsWith("/map.")){passed_core.client_downloadmap(Integer.parseInt(message.substring(5, 6)),message.substring(7, message.length()));}//server tells client to get into the game
		      
		      
		   
		      /*
		      if(message.startsWith("/newplayerjoined")){System.out.println("DOLPHIN1");
			    	int offset = message.indexOf(".") + 1;
			    	
			    	int descripindex = message.lastIndexOf("|");
			    	  String name=message.substring(12, descripindex);
			    	 String descrip=message.substring(descripindex + 1, message.length());
			    	 
			    	 
			    	passed_core.tellclient_HeroJoinedGame(
			    			StringToInteger(message.substring(offset,offset+3)),
			    			StringToInteger(message.substring(offset+4,offset+7)),
			    			StringToInteger(message.substring(offset+8,offset+11)),
			    			name,descrip
			    			);
			    }
		      */
		      
		      
		      
		      
		      
		      
		      if(message.equals("/giveprofileimage")){
		    	  sendProfileImage();
		      }
		      
		      
		     if(message.equals("/finisheduploadingmap")){
		    	 passed_core.Client_finished_map_download(); 
		  
		     }
		     
		     if(message.startsWith("/profileimage.")){
			    	
				 
				    	int num = Integer.parseInt(  message.substring(14,15));
				    	int my_x = Integer.parseInt(  message.substring(16,19));
				    	
				    	if(my_x == 0){mybufferedBufferedimg = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);}
				    			
				    	//  for (int x = 0; x < 100; x++) {
							    for (int y = 0; y < 128; y++) {
							    	
							    int offset = 20;
							    int z = ( y)*12 ;
							    	
							    	
							  	String c = message.substring(offset + z,offset + z+10); //this crashed, string index out of range: 1230 !
							    
							  	int r = StringToInteger(c.substring(0, c.indexOf("x")));
							  	
							  //	System.out.println(r);
							  	
							    try{	
							    	mybufferedBufferedimg.setRGB(my_x, y , r);
							    }catch(Exception e){}
							    
							  //  passed_core.LoadProfileImageWithBufferedImage(num,tempimg);
							    
							   
							        }
							   // }
				    	  
				    	  if(my_x == 127){
				    		 // passed_core.LoadProfileImageWithBufferedImage(num,mybufferedBufferedimg);
				    		  PlayerProfileImageReady = num;
				    	  }
				    	  
				    }
		     
		      

		      if(message.equals("/nextturn")){passed_core.nextturn();}//server tells client to end turn globally
		      
		      if(message.equals("/savegame")){passed_core.Client_GetSaveGame();}//server tells client to end turn globally
		      
		   if(message.startsWith("/chat")){
			   passed_core.addLineToChatHistory(message.substring(5, message.length()));
		   }
		   if(message.startsWith("/unitchat")){
			   passed_core.getUnitChatMessage( 
					   StringToInteger( message.substring(9, 15) ) , message.substring(16, message.length())  
					   );
		   }
		   
		   if(message.startsWith("/unitbubbletext")){
			   passed_core.setUnitChatBubbleMessage( 
					   StringToInteger( message.substring(15, 21) ) , message.substring(22, message.length())  
					   );
		   }
		   
		   
		   
		   if(message.startsWith("/storylog")){
			   passed_core.client_receivenewStoryLogmessage(message.substring((message.indexOf(":")+1), message.length()));
		   }
		   
		   
		 
		 		   
		        
		//if(message.startsWith("/storylog.")){passed_core.client_receivenewStoryLogmessage(message.substring(10, message.length()));}
		  
		if(message.startsWith("/move")){passed_core.client_receiveObjectMovement(PLAYER_LAYER, Integer.parseInt(message.substring(7, 10)),Integer.parseInt(message.substring(12, 15)),Integer.parseInt(message.substring(20, 23)),Integer.parseInt(message.substring(25, 28)),Integer.parseInt(message.substring(29, 32)),Integer.parseInt(message.substring(33, 36))   );}// "/move.x030,y040.to.x031,y050"
		
			
		if(message.startsWith("/spawn.")){passed_core.client_receiveSpawnObject(Integer.parseInt(message.substring(7, 10)),Integer.parseInt(message.substring(11, 14)),Integer.parseInt(message.substring(15, 18)),Integer.parseInt(message.substring(19, 22)),Integer.parseInt(message.substring(23, 26)) );}// "/spawn.001.030.040,040"
		
		if(message.startsWith("/createNPC.")){passed_core.Create_New_NPC(Integer.parseInt(message.substring(11, 14)),Integer.parseInt(message.substring(15, 18)),Integer.parseInt(message.substring(19, 22)),Integer.parseInt(message.substring(23, 26)));}
		
		
		if(message.startsWith("/delete.")){passed_core.anyone_DeleteObject(Integer.parseInt(message.substring(8, 11)),Integer.parseInt(message.substring(12, 15)),Integer.parseInt(message.substring(16, 19)));}// "/delete.001.040,040"
		
		//if(message.startsWith("/playerendedturn")){passed_core.client_receivePlayerEndedTurn(Integer.parseInt(message.substring(16, 17)));}
		
		if(message.startsWith("/unitstatchange.")){passed_core.receiveStatChange(Integer.parseInt(message.substring(16, 19)),Integer.parseInt(message.substring(20, 23)),Integer.parseInt(message.substring(24, 27)),Integer.parseInt(message.substring(28, 31)) );}  // /unitstatchange.sss.vvv.xxx.yyy.aaa
		
	
		if(message.startsWith("/editinventory.")){
			
		}
		
		if(message.startsWith("/projectile.")){			
			
			passed_core.SpawnProjectile(Integer.parseInt(message.substring(12, 15)), Integer.parseInt(message.substring(16, 19)), Integer.parseInt(message.substring(20, 23)), Integer.parseInt(message.substring(24, 27)), Integer.parseInt(message.substring(28, 31)) );
			}// /animation.aaa.xxx,yyy
		
		if(message.startsWith("/applycondition")){
			int offset = message.indexOf(".") + 1;			
			
			//passed_core.receiveApplyCondition(Integer.parseInt(message.substring(15, 18)),Integer.parseInt(message.substring(19, 22)),Integer.parseInt(message.substring(23, 26)),Integer.parseInt(message.substring(27, 30)),Integer.parseInt(message.substring(31, 34)),Integer.parseInt(message.substring(35, 38)) );
			 passed_core.receiveApplyCondition(
					 Integer.parseInt(message.substring(offset,offset+3)),
		    			Integer.parseInt(message.substring(offset+4,offset+7)),
		    			Integer.parseInt(message.substring(offset+8,offset+11)),
		    			Integer.parseInt(message.substring(offset+12,offset+15)),
		    			Integer.parseInt(message.substring(offset+16,offset+19)),
		    			Integer.parseInt(message.substring(offset+20,offset+23))
		    			);
		
		}  // /applyconditionCCC.DDD.XXX,YYY
		
		 if(message.startsWith("/unitanimation")){
				int offset = message.indexOf(".") + 1;
			 passed_core.StartUnitAnimation(
					 StringToInteger(message.substring(offset,offset+3)),
		    			StringToInteger(message.substring(offset+4,offset+7)),
		    			StringToInteger(message.substring(offset+8,offset+11))
		    			);
			 }
		 
		 if(message.startsWith("/playpossound")){
				int offset = message.indexOf(".") + 1;
			 passed_core.PlayPositionalSound(
					 StringToInteger(message.substring(offset,offset+3)),
		    			StringToInteger(message.substring(offset+4,offset+7)),
		    			StringToInteger(message.substring(offset+8,offset+11)),
		    			StringToInteger(message.substring(offset+12,offset+15))
		    			);
			 }
		 
		 if(message.startsWith("/unitslide")){
				int offset = message.indexOf(".") + 1;
			 passed_core.SetUnitSlide(
					 StringToInteger(message.substring(offset,offset+3)),
		    			StringToInteger(message.substring(offset+4,offset+7)),
		    			StringToInteger(message.substring(offset+8,offset+11)),
		    			StringToInteger(message.substring(offset+12,offset+15)),
		    			StringToInteger(message.substring(offset+16,offset+19))
		    			);
			 }
			
		
	
	     
	    if(message.startsWith("/edittileitemslotquantity")){
	    	int offset = message.indexOf(".") + 1;
	    	passed_core.EditTileItemSlotQuantity(
	    			StringToInteger(message.substring(offset,offset+3)),
	    			StringToInteger(message.substring(offset+4,offset+7)),
	    			StringToInteger(message.substring(offset+8,offset+11)),
	    			StringToInteger(message.substring(offset+12,offset+15)),
	    			StringToBoolean(message.substring(offset+16,offset+17))
	    			);} 
	    
	    if(message.startsWith("/edittileitemidquantity")){
	    	int offset = message.indexOf(".") + 1;
	    	passed_core.EditTileItemIDQuantity(
	    			StringToInteger(message.substring(offset,offset+3)),
	    			StringToInteger(message.substring(offset+4,offset+7)),
	    			StringToInteger(message.substring(offset+8,offset+14)),
	    			StringToInteger(message.substring(offset+15,offset+18)),
	    			StringToBoolean(message.substring(offset+19,offset+20))
	    			   );} 
	
	    
	    if(message.startsWith("/editunititemslotquantity")){
	    	int offset = message.indexOf(".") + 1;
	    	passed_core.EditUnitItemSlotQuantity(
	    			StringToInteger(message.substring(offset,offset+3)),
	    			StringToInteger(message.substring(offset+4,offset+7)),
	    			StringToInteger(message.substring(offset+8,offset+11)),
	    			StringToInteger(message.substring(offset+12,offset+15)),
	    			StringToBoolean(message.substring(offset+16,offset+17))
	    			   );} 
	    
	    if(message.startsWith("/editunititemidquantity")){
	    	int offset = message.indexOf(".") + 1;
	    	passed_core.EditUnitItemIDQuantity(
	    			StringToInteger(message.substring(offset,offset+3)),
	    			StringToInteger(message.substring(offset+4,offset+7)),
	    			StringToInteger(message.substring(offset+8,offset+14)),
	    			StringToInteger(message.substring(offset+15,offset+18)),
	    			StringToBoolean(message.substring(offset+19,offset+20))
	    			   );} 
	    
	    
	    
	    if(message.startsWith("/swapunitbagslotitems")){
	    	int offset = message.indexOf(".") + 1;
	    	passed_core.SwapUnitBagSlotItems(
	    			StringToInteger(message.substring(offset,offset+3)),
	    			StringToInteger(message.substring(offset+4,offset+7)),
	    			StringToInteger(message.substring(offset+8,offset+11)),
	    			StringToInteger(message.substring(offset+12,offset+15)));} 
	    
	    
	    
	    
	    if(message.startsWith("/heroexists")){
	    	int offset = message.indexOf(".") + 1;
	    	
	    	int descripindex = message.lastIndexOf("|");
	    	  String name=message.substring(24, descripindex);
	    	 String descrip=message.substring(descripindex + 1, message.length());
	    	 
	    	 
	    	passed_core.tellclient_HeroExists(
	    			StringToInteger(message.substring(offset,offset+3)),
	    			StringToInteger(message.substring(offset+4,offset+7)),
	    			StringToInteger(message.substring(offset+8,offset+11)),
	    			name,descrip
	    			);
	    }
	    
	    
	  
	    

		
		
		 if(message.startsWith("/beginphase")){
		    	int offset = message.indexOf(".") + 1;
		    	passed_core.anyone_receive_changephase(
		    			StringToInteger(message.substring(offset,offset+3)));
		 }
		    	
		 if(message.startsWith("/playerdisconnected")){
		    	int offset = message.indexOf(".") + 1;
		    	passed_core.receive_PlayerDisconnected(
		    			StringToInteger(message.substring(offset,offset+3)));
		 }
		 
		 if(message.startsWith("/unitdeath")){
		    	int offset = message.indexOf(".") + 1;
		    	passed_core.receive_UnitDeath(
		    			StringToInteger(message.substring(offset,offset+3)),
		    			StringToInteger(message.substring(offset+4,offset+7)));
		    }
		 
	    
	    
	    if(message.startsWith("/unitexists")){
	    	int offset = message.indexOf(".") + 1;
	    	passed_core.Client_GetExistingUnit(
	    			StringToInteger(message.substring(offset,offset+3)),
	    			StringToInteger(message.substring(offset+4,offset+7)),
	    			StringToInteger(message.substring(offset+8,offset+11)));
	    }
	    
	    //stats of existing players
		if(message.startsWith("/existingunitstats.")){  // /existingunitstats.002.010,001,001,005,005,001 ...
			int offset = message.indexOf(".") + 1;
			
			int[] i = new int[SharedData.NUM_OF_UNITSTATS];
			
			
			 for(int k=0;k<SharedData.NUM_OF_UNITSTATS;k++){i[k]=Integer.parseInt(message.substring(offset+4+(k*4),offset+7+(k*4)));    }
	    	
			passed_core.client_receiveInitialUnitStats(Integer.parseInt(message.substring(offset,offset+3)),i);
			
		
		}
		
		
		
		if(message.startsWith("/existingunitbagitems.")){  // /existingunitbagitems.002.010,001,001,005,005,001 ...
			int offset = message.indexOf(".") + 1;
			
			int[] i = new int[30];			
			
			 for(int k=0;k<30;k++){i[k]=Integer.parseInt(message.substring(offset+3+1+(k*6),offset+9+(k*6)));    }
	    	
			passed_core.client_receiveInitialBagItems(Integer.parseInt(message.substring(offset,offset+3)),i);
			
		
		}
		
		
		if(message.startsWith("/existingunitequipment.")){  // /initialplayerstats.002.010,001,001,005,005,001 ...
			int offset = message.indexOf(".") + 1;
			
			int[] i = new int[20];			
			
			 for(int k=0;k<20;k++){i[k]=Integer.parseInt(message.substring(offset+3+1+(k*6),offset+9+(k*6)));    }
	    	
			passed_core.client_receiveInitialEquipment(Integer.parseInt(message.substring(offset,offset+3)),i);
			
		
		}
		
		
		if(message.startsWith("/activebattleunitenddedturn")){
			passed_core.ActivelyBattlingUnitEndedTurn();
		}
		
		
		
		if(message.startsWith("/pollspecialstats")){
			passed_core.poll_Unit_Special_Stats();
		}
		
		
		
		
	    
	    if(message.startsWith("/bye")){ 
	    	connection_lost = true;
		passed_core.ShowWarningDialog("Lost connection to host!");
		}
	    
		
	 }
					
		
		public void sendMessage(String str){  //allows the main thread to send messages over LAN
			 try {
					os.writeUTF(str);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				//System.out.println("sent:"+str);
				
			    }
		 
		 
		 
		void receiveSpellbook(){
			
			System.out.println("getting asset book");
			
			 try {
				message = is.readUTF();
			
				//get abilities
			passed_core.number_of_assets[0] = Integer.parseInt(message.substring(message.indexOf(":")+1));
			System.out.println(passed_core.number_of_assets[0]);
			
			for(int i= 1 + passed_core.SPECIALABILITYBUFFER;i< 1 + passed_core.number_of_assets[0] + passed_core.SPECIALABILITYBUFFER;i++){
				
			//	String data[]
				
				
				passed_core.myAbilityDB[i].namestring = is.readUTF();   System.out.println("abil " + i + passed_core. myAbilityDB[i].namestring );
				passed_core.myAbilityDB[i].enabled = is.readBoolean();
				passed_core.myAbilityDB[i].description =  is.readUTF();
				passed_core.myAbilityDB[i].icon_ID  = is.readInt();  
				passed_core.myAbilityDB[i].passive = is.readBoolean();
				passed_core.myAbilityDB[i].cast_animation  = is.readInt();  
				passed_core.myAbilityDB[i].statrequired  = is.readInt();  
				passed_core.myAbilityDB[i].statrequired_amt  = is.readInt();  
				passed_core.myAbilityDB[i].abilityspec = is.readInt(); //System.out.println("abilspec " + passed_core. myAbilityDB[i].abilityspec );
				passed_core.myAbilityDB[i].weapon_required  = is.readInt();
				passed_core.myAbilityDB[i].requires_targetting  =  is.readBoolean();
				passed_core.myAbilityDB[i].constrain_to_cardinal_direction  =  is.readBoolean();
				passed_core.myAbilityDB[i].cardinal_direction_distance_override  = is.readInt();
				passed_core.myAbilityDB[i].can_target_where_no_units_exist = is.readBoolean();
				passed_core.myAbilityDB[i].can_target_units = is.readBoolean();
				passed_core.myAbilityDB[i].can_target_allies = is.readBoolean();
				passed_core.myAbilityDB[i].can_target_self  =  is.readBoolean();
				passed_core.myAbilityDB[i].can_target_enemies  =  is.readBoolean();
				passed_core.myAbilityDB[i].require_targetpoint_walkable  = is.readBoolean();
				passed_core.myAbilityDB[i].cast_range_base  = is.readInt();
				passed_core.myAbilityDB[i].stat_increasing_range  = is.readInt();
				passed_core.myAbilityDB[i].percent_factor_of_stat_increasing_range  = is.readInt();
				passed_core.myAbilityDB[i].energy_cost_base  = is.readInt();
				passed_core.myAbilityDB[i].stat_reducing_cost  = is.readInt();
				passed_core.myAbilityDB[i].percent_factor_of_stat_reducing_cost  = is.readInt();
				passed_core.myAbilityDB[i].cooldown_base  = is.readInt();
				passed_core.myAbilityDB[i].stat_reducing_cooldown = is.readInt();
				passed_core.myAbilityDB[i].percent_factor_of_stat_reducing_cooldown  = is.readInt();
				passed_core.myAbilityDB[i].counts_as_a_movement  =  is.readBoolean();
				passed_core.myAbilityDB[i].counts_as_an_attack  =  is.readBoolean();
				for(int n=0;n<10;n++){
					passed_core.myAbilityDB[i].effects[(n)]  = is.readInt();
			
				}
				passed_core.myAbilityDB[i].can_affect_allies  =  is.readBoolean();
				passed_core.myAbilityDB[i].can_affect_self  =  is.readBoolean();
				passed_core.myAbilityDB[i].can_affect_enemies  =  is.readBoolean();
				passed_core.myAbilityDB[i].passive_condition_applied  =  is.readInt();
			}
			
			
			
			//get effects


			message = is.readUTF();
			
			passed_core.number_of_assets[1] = Integer.parseInt(message.substring(message.indexOf(":")+1));
			
			for(int i= 1 + passed_core.SPECIALEFFECTBUFFER ;i<1 + passed_core.number_of_assets[1] + passed_core.SPECIALEFFECTBUFFER ;i++){
				
				passed_core.myEffectDB[i].namestring = is.readUTF(); //System.out.println( myEffectDB[i].namestring );
				passed_core.myEffectDB[i].enabled =  is.readBoolean();
				passed_core.myEffectDB[i].action  = is.readInt();
				passed_core.myEffectDB[i].flip_cast_coords = is.readBoolean();
				passed_core.myEffectDB[i].trajectory_collision = is.readBoolean();
				passed_core.myEffectDB[i].trajectory_endsontop = is.readBoolean();
				passed_core.myEffectDB[i].shape_type  = is.readInt();
				passed_core.myEffectDB[i].shape_radius  = is.readInt();
				passed_core.myEffectDB[i].shape_angle_spread  = is.readInt();
				passed_core.myEffectDB[i].can_affect_allies = is.readBoolean();
				passed_core.myEffectDB[i].can_affect_caster = is.readBoolean();
				passed_core.myEffectDB[i].can_affect_enemies = is.readBoolean();
				passed_core.myEffectDB[i].movement_animation  = is.readInt();
				passed_core.myEffectDB[i].movement_speed  = is.readInt();
				passed_core.myEffectDB[i].force_negative_edit = is.readBoolean();
				for (int n = 0; n < 5; n++) {
					passed_core.myEffectDB[i].stat_to_edit[n]  = is.readInt();
					passed_core.myEffectDB[i].stat_change_amount[n]  = is.readInt();
					passed_core.myEffectDB[i].stat_that_affects_change[n]  = is.readInt();
					passed_core.myEffectDB[i].stat_that_affects_change_pct[n]  = is.readInt();
					passed_core.myEffectDB[i].sets_stat[n]  = is.readBoolean();
					passed_core.myEffectDB[i].use_caster_stats[n]  = is.readBoolean();
				//	passed_core.myEffectDB[i].stat_change_is_temp[n]  = is.readBoolean();
				}
				
				
				passed_core.myEffectDB[i].condition_ID  = is.readInt();
				passed_core.myEffectDB[i].duration_base  = is.readInt();
				passed_core.myEffectDB[i].stat_that_affects_duration  = is.readInt();
				passed_core.myEffectDB[i].stat_that_affects_duration_pct  = is.readInt();
				passed_core.myEffectDB[i].projectiletype  = is.readInt();
				passed_core.myEffectDB[i].projectile_R = is.readInt();
				passed_core.myEffectDB[i].projectile_G = is.readInt();
				passed_core.myEffectDB[i].projectile_B = is.readInt();
				passed_core.myEffectDB[i].projectile_speed  = is.readInt();
				passed_core.myEffectDB[i].projectile_luminescence  = is.readInt();
				passed_core.myEffectDB[i].projectile_requires_unit_on_tile = is.readBoolean();
				for(int n=0;n<5;n++){
					passed_core.myEffectDB[i].projectile_death_effect[n] = is.readInt();
						
				}
				passed_core.myEffectDB[i].unit_type  = is.readInt();
				passed_core.myEffectDB[i].Overlay_GFX_ID  = is.readInt();
				passed_core.myEffectDB[i].Overlay_GFX_R  = is.readInt();
				passed_core.myEffectDB[i].Overlay_GFX_G  = is.readInt();
				passed_core.myEffectDB[i].Overlay_GFX_B  = is.readInt();
				passed_core.myEffectDB[i].Overlay_GFX_Scale  = is.readInt();
				passed_core.myEffectDB[i].SOUND_ID  = is.readInt();
				
			}
			
			
			//get conds
			message = is.readUTF();
			passed_core.number_of_assets[2] = Integer.parseInt(message.substring(message.indexOf(":")+1));
			
			for(int i=1;i< 1 + passed_core.number_of_assets[2];i++){
				
				passed_core.myConditionDB[i].namestring =  is.readUTF();// System.out.println( myItemDB[i].namestring );
				passed_core.myConditionDB[i].enabled =  is.readBoolean();
				passed_core.myConditionDB[i].description =  is.readUTF();
				passed_core.myConditionDB[i].Icon_ID = is.readInt();
				passed_core.myConditionDB[i].binds_to_units= is.readBoolean();
				passed_core.myConditionDB[i].stacks_duration= is.readBoolean();
				passed_core.myConditionDB[i].stacks_magnitude= is.readBoolean();
				passed_core.myConditionDB[i].Overlay_GFX_ID= is.readInt();
				passed_core.myConditionDB[i].Overlay_GFX_R= is.readInt();
				passed_core.myConditionDB[i].Overlay_GFX_G= is.readInt();
				passed_core.myConditionDB[i].Overlay_GFX_B= is.readInt();
				passed_core.myConditionDB[i].Overlay_GFX_Scale= is.readInt();
				passed_core.myConditionDB[i].SOUND_ID= is.readInt();
				for(int j=0;j<5;j++){
					passed_core.myConditionDB[i].passive_effect[j]= is.readInt();
					}
				for(int j=0;j<5;j++){
				passed_core.myConditionDB[i].periodic_effect[j]= is.readInt();
				}
				
			}
			
			
			
			//get items
			message = is.readUTF();
			passed_core.number_of_assets[3] = Integer.parseInt(message.substring(message.indexOf(":")+1));
			
			for(int i=1;i<1 + passed_core.number_of_assets[3];i++){
				
				passed_core.myItemDB[i].namestring =  is.readUTF();// System.out.println( myItemDB[i].namestring );
				passed_core.myItemDB[i].enabled =  is.readBoolean();
				passed_core.myItemDB[i].purchasable =  is.readBoolean();
				passed_core.myItemDB[i].value =  is.readInt();
				passed_core.myItemDB[i].description =  is.readUTF();
				passed_core.myItemDB[i].Icon_ID = is.readInt();
				passed_core.myItemDB[i].slotassignment= is.readInt();
				passed_core.myItemDB[i].equipmodel= is.readInt();
				passed_core.myItemDB[i].model_r= is.readInt();
				passed_core.myItemDB[i].model_g= is.readInt();
				passed_core.myItemDB[i].model_b= is.readInt();
				passed_core.myItemDB[i].weapontype= is.readInt();
				passed_core.myItemDB[i].itemlevel= is.readInt();
				passed_core.myItemDB[i].itemgroup= is.readInt();
				passed_core.myItemDB[i].max_stack_size= is.readInt();
				for(int j=0;j<5;j++){
					passed_core.myItemDB[i].crafting_material[j]= is.readInt();
					passed_core.myItemDB[i].quantity_used[j]= is.readInt();
				}
				for(int j=0;j<5;j++){
					passed_core.myItemDB[i].abilities_granted[j]= is.readInt();
				}
				for(int j=0;j<5;j++){
					passed_core.myItemDB[i].use_effects[j]= is.readInt();
				}
			for(int j=0;j<5;j++){
				passed_core.myItemDB[i].stat_to_increase[j]= is.readInt();
				passed_core.myItemDB[i].stat_bonus_amount[j]= is.readInt();
				passed_core.myItemDB[i].stat_affecting_amount[j]= is.readInt();
				passed_core.myItemDB[i].stat_affecting_amount_factor[j]= is.readInt();
			}
			
			
			if(passed_core.myItemDB[i].purchasable){
			int count = passed_core.number_of_purchasable_items[passed_core.myItemDB[i].itemgroup]++;
			passed_core.purchasable_items[passed_core.myItemDB[i].itemgroup][count] = i;
			}
				
			}
			
			
			//get units
			message = is.readUTF();
			passed_core.number_of_assets[4] = Integer.parseInt(message.substring(message.indexOf(":")+1));
			
			for(int i=1;i<1 + passed_core.number_of_assets[4];i++){
				
				passed_core.myNPCDB[i].namestring =  is.readUTF();// System.out.println( myItemDB[i].namestring );
				passed_core.myNPCDB[i].enabled =  is.readBoolean();
				passed_core.myNPCDB[i].description =  is.readUTF();
				passed_core.myNPCDB[i].alignment =  is.readInt();
				passed_core.myNPCDB[i].model_ID = is.readInt();
				passed_core.myNPCDB[i].sound_id = is.readInt();
				passed_core.myNPCDB[i].category= is.readInt();
				passed_core.myNPCDB[i].invulnerable= is.readBoolean();
				passed_core.myNPCDB[i].immobile= is.readBoolean();
				passed_core.myNPCDB[i].expirytimer_on_spawn= is.readInt();
				passed_core.myNPCDB[i].base_level= is.readInt();
				passed_core.myNPCDB[i].base_endurance= is.readInt();
				passed_core.myNPCDB[i].base_stamina= is.readInt();
				passed_core.myNPCDB[i].base_meleepower= is.readInt();
				passed_core.myNPCDB[i].base_rangedpower= is.readInt();
				passed_core.myNPCDB[i].base_spellpower= is.readInt();
				passed_core.myNPCDB[i].base_armor= is.readInt();
				passed_core.myNPCDB[i].base_magres= is.readInt();
				passed_core.myNPCDB[i].base_toxres= is.readInt();
				passed_core.myNPCDB[i].base_initiative= is.readInt();
				passed_core.myNPCDB[i].base_speed= is.readInt();
				passed_core.myNPCDB[i].exp_reward= is.readInt();
				passed_core.myNPCDB[i].gold_carried= is.readInt();
				
				for(int j=0;j<5;j++){
					passed_core.myNPCDB[i].abilities[j]= is.readInt();
				}
				for(int j=0;j<5;j++){
					passed_core.myNPCDB[i].items_held_default[j]= is.readInt();
				}
			for(int j=0;j<5;j++){
				passed_core.myNPCDB[i].items_dropped[j]= is.readInt();
				passed_core.myNPCDB[i].item_drop_percent[j]= is.readInt();
					}
				
			}
			
			
			
			 } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
			 


			// passed_core.organizeHeroAbilities();//arrange the gotten abilities!!!
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

		
	 private void sendProfileImage() {


	  	 
	     //send my profileimage to the host!  why here? I guess it freezes elsewhere... dumb
	    	 new Thread(new Runnable() {
	    	  	public void run(){
	    	  		String n = "";
	    	  	  	
			    	  for (int x = 0; x < 128; x++) {
			    		  
			    		  String x_2 = ""+x;
			    		  while(x_2.length() < 3){x_2 = "0" + x_2;}
			    		  
			    		  n = "/profileimage." + passed_core.myPNum + "." + x_2 + ".";
			    		  
						    for (int y = 0; y < 128; y++) {
						    	
						    
						  	String c = "";
						    	
						    try{	
						 c = "" + passed_core.myBufferedProfileImage.getRGB(x, y);
						    }catch(Exception e){}
						    
						    while(c.length() < 12){c += "x";}
						    
						    n += c ;
						        }
						    
						    try {
								os.writeUTF(n);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						    
						    }
	    	  	}  }).start(); 
	    	 
		
	}



					
			
	  }
	 
	 
	 
	 
	  	 

	
	  
	  
	
	 
	 
	 
	
	
	
	
	
	
