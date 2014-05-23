package wrap;




public class DB_Items {
	String imported_data[] = new String[100];
	// 0-23 are normal inventory slots
	boolean enabled; 
	

	
	
	int slotassignment;
	
	int[] crafting_material = new int[5];
	int[] quantity_used = new int[5];
	
	int[] abilities_granted = new int[5];
	int[] use_effects = new int[5];
	
	int[] stat_to_increase = new int[5];
	int[] stat_bonus_amount = new int[5];
	int[] stat_affecting_amount = new int[5];
	int[] stat_affecting_amount_factor = new int[5];
	
	int equipmodel = 0;//draw model on hero!
	int model_r=100;//tinting of model! implement me!
	int model_g=100;
	int model_b=100;
	
	int weapontype = 0;  //0 means any weapontype!!!
	
	int itemlevel = 0;  //affects the weapon skills that come from the weapon, and is also the min level required to equip!!
	
	int itemgroup = 0;//group for loot tables! just for chests
	
	int max_stack_size = 1;
	
	String namestring="";

	String description="";
	
	int Icon_ID=-1;
	
	boolean purchasable;
	int value =0;
	
	//remove these and just use the ID to reference other variables!
	//Image2D image;
	//Image2D[] imageequippeddirectional = new Image2D[4];
	
}
