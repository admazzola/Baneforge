package wrap;

import java.awt.Image;

public class DB_Abilities {
	String imported_data[] = new String[100];
	/*
	public static final int ABILITYCASTTYPE_INSTANT= 1;
	public static final int ABILITYCASTTYPE_DIRECTIONAL= 2;
	public static final int ABILITYCASTTYPE_POINT= 3;
	public static final int ABILITYCASTTYPE_UNIT= 4;*/
	
	
	
	public static final int NUM_OF_UNITSTATS = 45;

    boolean enabled;
	String namestring;
	String description;
	int icon_ID;
	int cast_animation;//implement me
	
	int statrequired;//fully implement me!
	int statrequired_amt;

	
	boolean can_target_where_no_units_exist;
	boolean can_target_units;//living!
	boolean can_target_allies;//not in yet
	boolean can_target_self;//not in yet
	boolean can_target_enemies;//not in yet
	boolean can_target_corpses;//not in yet
	
	boolean counts_as_an_attack;//towards the units counter, per turn
	boolean counts_as_a_movement;//towards the units counter, per turn
	
	//boolean[] requires_weapontype_equipped = new boolean[99];//NEEDS TO BE IMPLEMENTED!
	int weapon_required;
	
	int required_level = 0;//NEEDS TO BE IMPLEMENTED!
	
	int cooldown_base=0;
	int stat_reducing_cooldown;
	int percent_factor_of_stat_reducing_cooldown;
	
	int cast_range_base=999;
	int stat_increasing_range;
	int percent_factor_of_stat_increasing_range;
	//int[] percent_factor_of_stat_modifying_cast_range = new int[NUM_OF_UNITSTATS];  //usually 0. max 100.  If 20/100 for a particular stat, range is increased by thatstat*0.2
	
	int energy_cost_base=0;
	int stat_reducing_cost;
	int percent_factor_of_stat_reducing_cost;
	//int[] percent_factor_of_stat_reducing_energy_cost = new int[NUM_OF_UNITSTATS];
	
	
	
	boolean requires_targetting;
	boolean constrain_to_cardinal_direction; //makes either the x or y value of the end coord equal to the caster's x/y 
	
	//boolean trajectory_collision;
	boolean require_targetpoint_walkable;
	//boolean require_targetpoint_walkable;  //just checks for end point to be walkable!
	
	int cardinal_direction_distance_override;//for directional abilities, overwrites the end point with (the start point shifted this amount in the cardinal direction of the end point)
	
	//int directional_distance = 1; //only for directional abilities
	//int[] prereq_abilities = new int[10];
	
	//boolean collision = false;
	
	
	
	
	int abilityspec; // 0=none, 1=brawn, 2= ....
	

	int[] effects = new int[10];


//for auras
	boolean passive;
	boolean can_affect_allies;
	boolean can_affect_self;
	boolean can_affect_enemies;
	int passive_condition_applied;
	
	
	
	
	

	//CHANGE ALL THINGS IN CORE TO REFERENCE THESE INSTEAD OF DOING THEIR OWN CALCS!!!
	int getEffectiveEnergyCost(Unit selected_unit){
		int answer = energy_cost_base;
		
		if(stat_reducing_cost>0 && percent_factor_of_stat_reducing_cost>0){
		answer += (selected_unit.stat[stat_reducing_cost]*percent_factor_of_stat_reducing_cost)/100;
		}
		
		if(answer<0){answer=0;}
		
		return answer;
		
	}
	int getEffectiveCooldown(Unit selected_unit){
		int answer = cooldown_base;
		
		if(stat_reducing_cooldown>0 && percent_factor_of_stat_reducing_cooldown>0){
		answer += (selected_unit.stat[stat_reducing_cooldown]*percent_factor_of_stat_reducing_cooldown)/100;
		}
		if(answer<0){answer=0;}
		return answer;
		
	}
	int getEffectiveRange(Unit selected_unit){
		int answer = cast_range_base;
		
		if(stat_increasing_range>0 && percent_factor_of_stat_increasing_range>0){
		answer += (selected_unit.stat[stat_increasing_range]*percent_factor_of_stat_increasing_range)/100;
		}
		if(answer<0){answer=0;}
		return answer;
		
	}
	
}
