package wrap;


//THIS WILL BE JUST LIKE UNITS, WHERE EACH ONE HAS AN ID

//when standing on ashes, the lootmenu comes up for it.
//when rightclicking an adjacent chets, the lootmenu comes up for it.

public class ItemContainer {
	


	//boolean isActive;
	
	int[] itemInSlot = new int[6];
	int number_of_slots_with_items(){
		int answer = 0;
		for(int i=0;i<6;i++){
			if(itemInSlot[i] > 0 && itemQuantity[i] > 0){answer++;}
		}
		
		return answer;
	};
	
	int[] itemQuantity = new int[6];
	
	//int x;
	//int y;
	
	
}
