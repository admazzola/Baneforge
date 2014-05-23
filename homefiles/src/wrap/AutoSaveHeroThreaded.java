package wrap;

import java.io.*;

public class AutoSaveHeroThreaded extends Thread{

	
    //Stat declarations
	public static final int LEVEL = 0;		
	public static final int EXP = 1;	
	public static final int HEALTH = 2;  //current
	public static final int HEALTHREGEN = 3;  //current
	public static final int ENERGY = 4;  //current	
	public static final int ENERGYREGEN = 5;  //current
	public static final int DAMAGE = 6;  //damage of current weapon		
	public static final int ARMOR = 7;  //armor, helps blocking to reduce dmg	
	public static final int ENDUR = 8;  //max health
    public static final int STRENGTH = 9;  //strength with weapons
	public static final int AGILITY = 10;  //agility for dodging
	public static final int SPEED = 11;  //speed of current weapon
	public static final int INTEL = 12;  //intelligence, can help dodging
	public static final int WILL = 13;   //willpower
	public static final int LUCK = 14;   //luck
	public static final int LIGHT = 15;   //amount of light given off by the hero, like from holding a torch
	public static final int EXP_REWARD = 16; //for enemies, the amt of EXP granted when they are slain	
	public static final int PLAYER_OWNERSHIP = 17;
	public static final int GOLD_CARRIED = 18;
	public static final int WALK_MOVES_COUNT = 19;
	public static final int ATTACK_MOVES_COUNT = 20;
	public static final int PROFILEIMAGE_ID = 21;
	public static final int MODEL_ID = 22;//to draw
	public static final int HARDCOREMODE = 23;
	
	
	
	
	
	
	
	public static final int NUM_OF_UNITSTATS = 45;
    

    Core passed_core;
    int myPNum;
    String sFileName;
  
	 public AutoSaveHeroThreaded(String sFileName, int myPNum, Core passed_core) {

		 this.passed_core = passed_core;
		 this.myPNum = myPNum;
		 this.sFileName = sFileName;
	}
    
	
	
	 public void run() {	  
	
		
				
				//if(sFileName.equals("lastknownpath")){sFileName=lastHeroFilePath;}else{lastHeroFilePath=sFileName;}
				
				System.out.println(sFileName);
				
				if(sFileName.substring(sFileName.length()-4,sFileName.length()).equals(".vhf")){}else{sFileName += (".vhf");}
				   
					    try {
					    	
							FileWriter writer = new FileWriter(sFileName);
							
							
							writer.append(passed_core.Players[passed_core.myPNum].name + "\n");
							writer.append(passed_core.Players[passed_core.myPNum].herodescription + "\n");

							
							//profileimage
							//writer.append(passed_core.Units[myPNum].stat[PROFILEIMAGE_ID] + "\n");
							//renderimage
							//writer.append(passed_core.Units[myPNum].stat[MODEL_ID] + "\n");
							
							//stats
							for(int i=0;i<NUM_OF_UNITSTATS;i++){writer.append(passed_core.Units[myPNum].stat[i] + "\n"); }
							
							//items			
							for(int i=0;i<40;i++){writer.append(passed_core.Units[myPNum].itemInSlot[i] + "\n"); }
							
							//abilitylevels
							//NOTIMPLEMENTEDYET
							
							 writer.flush();
							 writer.close();
							
							
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				   
				   
				   
			
	
	 }
	 
	 



	
	
	
	
}
