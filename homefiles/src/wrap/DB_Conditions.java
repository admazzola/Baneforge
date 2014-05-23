package wrap;


public class DB_Conditions {
	String imported_data[] = new String[100];
	int Icon_ID;
	
	boolean enabled;
	
	String namestring;
	String description;
	
	int[] passive_effect = new int[5];
	int[] periodic_effect = new int[5];
	
	/*int[] stat_to_increase = new int[5];
	int[] stat_bonus_amount = new int[5];
	int[] stat_affecting_amount = new int[5];//implement!
	int[] stat_affecting_amount_factor = new int[5];//implement!!
	boolean[] use_caster_stats = new boolean[5];
	boolean[] stat_change_is_temp = new boolean[5];*/
	
	int Overlay_GFX_ID=-1;//the GFX animation to play at the beginning of each turn.
	int Overlay_GFX_R=100;
	int Overlay_GFX_G=100;
	int Overlay_GFX_B=100;
	int Overlay_GFX_Scale=100;
	
	int SOUND_ID=-1;
	
	boolean stacks_duration;//this must be implemented!
	boolean stacks_magnitude;
	
	boolean binds_to_units = false; //if true, the condition will 'bind' to the tile, not the unit!
}
