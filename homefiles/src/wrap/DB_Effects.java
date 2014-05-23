package wrap;

import java.awt.Image;

public class DB_Effects {
	String imported_data[] = new String[100];
	public static final int EFFECT_ACTION_MOVEUNIT = 0;
	public static final int EFFECT_ACTION_EDITSTATS = 1;
	public static final int EFFECT_ACTION_APPLYCONDITION = 2;//might be a tile-condition... not always unit-based!
	public static final int EFFECT_ACTION_DRAWPROJECTILE = 3;
	public static final int EFFECT_ACTION_CREATEUNIT = 4;
	public static final int EFFECT_ACTION_OVERLAYGFX = 5;
	public static final int EFFECT_ACTION_SOUND = 6;
	
	
	
	/*
		public static final int EFFECT_SHAPE_POINT = 0;
	public static final int EFFECT_SHAPE_LINE = 1;
	public static final int EFFECT_SHAPE_CONE = 2;
	public static final int EFFECT_SHAPE_SQUARE = 3;*/
	//the shape check comes right before the effect execution and just expands upon what I have. no replacing.
	
	boolean enabled;
	String namestring;
	
	public static final int NUM_OF_UNITSTATS = 45;
	
	boolean can_affect_caster=false;
	boolean can_affect_allies=false;
	boolean can_affect_enemies=false;
	
	
	boolean flip_cast_coords;//end
	boolean trajectory_collision;//caster
	boolean trajectory_endsontop;
	
	int shape_type;
	int shape_radius; //radius for circles and rects, and cones!
	int shape_angle_spread;
	
	int action;
	
	
	
	//IF ACTION==MOVEUNIT
	//int type_of_movement;
	int movement_animation;
	int movement_speed;
	
	//IF ACTION==EDITSTAT
	/*int stat_to_edit;
	int stat_change_amount;
	int stat_that_affects_change;
	int stat_that_affects_change_pct;
	boolean use_caster_stats;*/
	
	int[] stat_to_edit = new int[5];
	int[] stat_change_amount = new int[5];
	int[] stat_that_affects_change = new int[5];//implement!
	int[] stat_that_affects_change_pct = new int[5];//implement!!
	boolean[] sets_stat = new boolean[5];//as opposed to adding to it!
	boolean[] use_caster_stats = new boolean[5];
	//boolean[] stat_change_is_temp = new boolean[5];
	
	
	//int[] percent_factor_of_stat_increasing_editstat = new int[NUM_OF_UNITSTATS];
	
	//IF ACTION==APPLYCONDITION
	int condition_ID;
	int duration_base;
	int stat_that_affects_duration;
	int stat_that_affects_duration_pct;
	int intensity_base;
	int stat_that_affects_intensity;
	int stat_that_affects_intensity_pct;
	//int[] percent_factor_of_stat_increasing_duration = new int[NUM_OF_UNITSTATS];
	
	//IF ACTION==PROJECTILE
	int projectiletype;
	int projectile_R;
	int projectile_G;
	int projectile_B;
	int projectile_speed;
	int projectile_archeight;
	int projectile_luminescence;
	boolean projectile_requires_unit_on_tile=false;//used with 'draw projectile' and shapes
	int[] projectile_death_effect = new int[5];
	
	//IF ACTION==CREATEUNIT
	int unit_type;
	//death tiemr
	
	//if ACTION==CREATEREGIONALCONDITION
	int Overlay_GFX_ID=-1;
	int Overlay_GFX_R=100;
	int Overlay_GFX_G=100;
	int Overlay_GFX_B=100;
	int Overlay_GFX_Scale=100;
	
	int SOUND_ID=-1;
//	int regionalcondition_duration_base = 0;//this means that it is instant
	boolean force_negative_edit;
	
	
	/*
	int getEffectiveStatChange(int UnitID, Core passed_core){
		int answer = stat_change_amount;
		
		if(stat_that_affects_change>0 && stat_that_affects_change_pct>0){
			//affecting_stat_value =selected_unit.stat[stat_that_affects_change]
		int affecting_stat_value = passed_core.GetEffectiveUnitStat(UnitID, stat_that_affects_change, true);
		answer += ((affecting_stat_value)*stat_that_affects_change_pct)/100;
		}
		
		if(stat_change_amount<0 && answer>0){answer=0;}
		if(stat_change_amount>0 && answer<0){answer=0;}
		
		return answer;
		
	}*/
	
	int getEffectiveDuration(Unit selected_unit){
		int answer = duration_base;
		
		if(stat_that_affects_duration>0 && stat_that_affects_duration_pct>0){
		answer += (selected_unit.stat[stat_that_affects_duration]*stat_that_affects_duration_pct)/100;
		}
		
		if(answer<0){answer=0;}
		
		return answer;
		
	}
	
	int getEffectiveIntensity(Unit selected_unit){
		int answer = intensity_base;
		
		if(stat_that_affects_intensity>0 && stat_that_affects_intensity_pct>0){
		answer += (selected_unit.stat[stat_that_affects_intensity]*stat_that_affects_intensity_pct)/100;
		}
		
		if(answer<0){answer=0;}
		
		return answer;
		
	}
	
	
}
