package wrap;

import java.awt.Color;

public class DB_Types {


	
	int[] minimapcolors = new int[3];
	
	
	String namestring;
	
	String description;
	
	Image2D image;
	
	String[] options = new String[10];  //for the dm!!!!
	
	boolean Can_Contain_Items = false; //if true, a hero who right clicks on this object will see a loot menu pop up

	boolean Items_Dropped_areInstanced = false; 
	
	int[] LootTable_itemgroups = new int[6];//each slot can drop something from a different item group
	int[] LootTable_itemgroupchances = new int[6];// chance for each slot!
	
	int spritesheet=0;//1 is small50, 2 is large50. 3 is large100.
	int sheet_index = 0;


	int light_radiation=0;


	boolean isEverLit=false;
	
	boolean DoNotRender=false;
	
	
	//for lightblocking
	int lightblock_offset_h_y = 0;
	int lightblock_offset_h_x = 0;
	int lightblock_offset_v_y = 0;
	int lightblock_offset_v_x = 0;
	
}
