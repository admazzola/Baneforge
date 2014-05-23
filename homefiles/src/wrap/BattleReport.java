package wrap;

public class BattleReport {

	int initialstats[] = new int[SharedData.NUM_OF_UNITSTATS];
	
	
	
	
	Unit[] UnitsSlain = new Unit[10000];
	int num_units_slain = 0;
	int[] expgains = new int[10000];
	int[] goldgains = new int[10000];
	
	void AddKill(Unit unit_slain,int expgain,int goldgain){		
		UnitsSlain[num_units_slain] = unit_slain;	
		expgains[num_units_slain] = expgain;
		goldgains[num_units_slain] = goldgain;
		num_units_slain++;
	}
	
	
	
	
}
