package wrap;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


//USED ONLY BY THE BANEFORGE ASSET EDITOR



public class ParameterOption {
	
	
	ParameterOption(){
		
		require_boolean_true[0] = -1;
		require_boolean_false[0] = -1;
		
	}
	
	boolean enabled;
    // String label;
     JLabel mylabel;
     
     
     int type_of_option;
     /* 0- boolean
      * 1-   Text Field, numeric only
      * 2- Text Field, alphanumeric
      * 3- Text Area, alphanumeric ...pops up 
      * 4- ComboBox   ...just drops right down
      * 5- Combobox with images
      */
     
     int[] require_boolean_true = new int[10];
     int[] require_boolean_false = new int[10];
     
     boolean myboolean;
     JTextField myalphatextfield = new JTextField();
     JTextArea mytextarea= new JTextArea();
     
     
     JComboBox mycombo = new JComboBox();
     //int combo_index;
     
     
     
     JFormattedTextField mynumbertextField = new NumberTextArea(NumberFormat.getInstance());
     
     
     
     class NumberTextArea extends JFormattedTextField implements KeyListener{
     
     public NumberTextArea(NumberFormat instance) {
			super(instance);
			this.addKeyListener(this);
		}

	@Override public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override public void keyReleased(KeyEvent arg0) {
		isValidChar(arg0.getKeyChar());
		System.out.println("hi");
	}

	@Override public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
	 private void isValidChar(char ch){
	        if(this.getText().length() == 1 && this.getText().equals("-") ){
	            this.setText("-");
	        }
	        else {
	            if(!isNumeric(Character.toString(ch))){
	                try{
	                    this.setText(removeNonnumericChar(this.getText(), ch));
	                }catch(Exception e){
	                }
	            }
	        }
	    }

	    private boolean isNumeric(String text) {
	    	if(text.equals(".")){return true;}
	    	
	        try {
	          
	            Integer.parseInt(text);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
	    //remove nonnumeric character
	    private String removeNonnumericChar(String text, char ch){
	        StringBuilder sBuilder = new StringBuilder();
	        for (int i = 0; i < text.length(); i++) {
	            if(text.charAt(i) != ch){
	                sBuilder.append(text.charAt(i));
	            }
	        }
	        return sBuilder.toString();
	    }
	
    	 
     }
     
     
     
     void HideOption(){
    	 mycombo.setVisible(false);
    	 myalphatextfield.setVisible(false);
    	 mynumbertextField.setVisible(false);
    	 //others
    	 myboolean=false;//???
    	 
     }
     
     
     void ShowOption(){
    	 
    	 mycombo.setVisible(true);
    	 myalphatextfield.setVisible(true);
    	 mynumbertextField.setVisible(true);
     }
     
     
    
     
     
}
