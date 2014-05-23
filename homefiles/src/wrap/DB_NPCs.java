package wrap;


public class DB_NPCs {
	String imported_data[] = new String[100];
	
	static SharedData myshareddata = new SharedData();//causes some lag maybe? Its probably a millisecond and it makes my life easy..
	


	boolean enabled;
	//int[] stat = new int[myshareddata.NUM_OF_UNITSTATS];

	
	//int[] itemdropgroup = new int[10];
	String namestring;
	String description;
	int alignment;
	
    int model_ID;
	int sound_id = 11;
	int category;
	boolean invulnerable;
	boolean immobile;
	int expirytimer_on_spawn;
	
	int base_level=0;
	int base_endurance=0;
	int base_stamina=0;
	
	int base_meleepower=0;
	int base_rangedpower=0;
	int base_spellpower=0;
	
	int base_armor=0;
	int base_magres=0;
	int base_toxres=0;
	
	int base_initiative=0;
	int base_speed=0;
	
	int exp_reward=0;
	int gold_carried=0;
//	int ItemContainerType = ASHES;//on death
	
	
	int abilities[] = new int[5];
	


int[] items_held_default = new int[5];

int items_dropped[] = new int[5];
int item_drop_percent[] = new int[5];

	
	
	
}
