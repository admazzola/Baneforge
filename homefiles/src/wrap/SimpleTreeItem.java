package wrap;

public class SimpleTreeItem {
String name;
boolean Is_Expanded = true;
int asset_ID;

public void ToggleExpanded() {
	if(Is_Expanded){Is_Expanded=false;}else{Is_Expanded=true;}
	
}

}
