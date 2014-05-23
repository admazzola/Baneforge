
package wrap;


public class Unit {
	

 
    public int x;  
    public int y;
    
    int current_model; //this is here to relieve pressure of the CPU
    
	int[] stat = new int [SharedData.NUM_OF_UNITSTATS];
	//int[] statbonuses = new int [SharedData.NUM_OF_UNITSTATS];
	
	
	
	  int[] itemInSlot = new int [200];  //0-23 is bagslots!   24:head,25:neck,26; Torso, 27:Legs, 28:Feet, 29:Gloves, 30: Ring, 31:Main Hand, 32: Off Hand
	int[] itemQuantity = new int[200];
	

	
    int[] active_skill_cooldowns = new int[10000];//stored by ID
    
    
    
	// int[] LearnedSpells = new int [1000]; 
	 boolean[] LearnedSpells = new boolean [1000]; 
   
	int[] active_spells = new int[10];//abilities on your hotbar currently
    int[] active_spell_cooldowns = new int[10000];//stored by ID
    //int[] abilitycooldowns= new int[1000];//the cooldown that will occur when the ability is activated
    


    int[] activeconditions = new int [100];
    int[] conditionstimeleft = new int [100]; //time = number of turns.  -1 means forever, 0 means not active, >0 means active
    int[] conditionsintensity = new int [100]; //still needs to be implemented! multiplier for the effectiveness of the buff
    int[] conditions_source = new int [100];
    


   int current_animation=0;// is just standing
   int current_animation_frame=0;
  
   String chat_bubble_text;
   int chat_bubble_timeleft;


    double slidepixeloffset_x=0;///strictly for animation!
    double slidepixeloffset_y=0;

    int animation_SlideSpeed = 1;
  //  boolean disabled_fromcrippled;//alternates on and off each turn while crippled! begins on
    
   boolean is_sliding;

   boolean is_recoiling;
   
   boolean hero_who_is_brand_new;

 boolean IsInvisible = false;


boolean IsAlive(){
	return (stat[SharedData.DEAD]==0);
}
 
	
    public Unit(){
    	    	    
    
    	
    	stat[SharedData.FACING]=2;
      stat[SharedData.WALK_MOVES_COUNT] = 1;
      
        
    }
    
 
   

   
    
}