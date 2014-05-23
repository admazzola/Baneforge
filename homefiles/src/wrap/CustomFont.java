package wrap;


import java.nio.DoubleBuffer;

import org.lwjgl.opengl.GL11;

public class CustomFont {

	
	   private float width, height;
	   private float centerX, centerY;
	   private float textureOffsetX, textureOffsetY;
	   private Texture texture;
	  
	   private int[][] lookuptable_parsed = new int[256][6];//the texture offsets for each char.  x,y,width,height,x offset, y offset

	   public CustomFont(Texture texture, int[][] lookuptable_parsed) {
	      this.texture = texture;
	      this.lookuptable_parsed = lookuptable_parsed;
	      this.width = texture.getImageWidth();
	      this.height = texture.getImageHeight();
	      this.centerX = width / 2f;
	      this.centerY = height / 2f;
	      
	      
	      
	   }
	   
	   
	   
	  
	   public void drawString(int x_orig, int y_orig, String S){//simple
		   
		   char[] ch = S.toCharArray();
		    
		      texture.bind();
		     GL11.glBegin(GL11.GL_QUADS);
			   
		   
		   
		   
		   int charwidth = 14;
		   int charheight = 14;
		   
		   int x=0;
		   int y=0;
		   int char_offset_x=0;
		   int char_offset_y=0;
		
		   
		   	for(int i=0;i<S.length();i++){
		   		
		   		
		   		
		   		
		      int char_id = ch[i];       
		      
		      
		      int tex_x = lookuptable_parsed[char_id][0];
			   int tex_y = lookuptable_parsed[char_id][1];
			   charwidth=lookuptable_parsed[char_id][2];
				charheight=lookuptable_parsed[char_id][3];
				char_offset_x=lookuptable_parsed[char_id][4];
				char_offset_y=lookuptable_parsed[char_id][5];
				
				x = x_orig + char_offset_x;
				y = y_orig + char_offset_y;  //attempts to fix the strange height bug
				
				
				
				
		   				   	 
				   float tex_min_x = tex_x / width;
				   float tex_max_x = (tex_x+charwidth) / width;
				   float tex_min_y = tex_y / height;
				   float tex_max_y = (tex_y+charheight) / height;
				   
			      
			      GL11.glTexCoord2f(tex_min_x, tex_min_y); //TOP LEFT
			      GL11.glVertex2f(x, y); 
			      GL11.glTexCoord2f(tex_min_x, tex_max_y); //BOTTOM LEFT
			      GL11.glVertex2f(x, y+charheight);         
			      GL11.glTexCoord2f(tex_max_x, tex_max_y); //BOTTOM RIGHT 
			      GL11.glVertex2f(x+charwidth, y+charheight); 
			      GL11.glTexCoord2f(tex_max_x, tex_min_y); //TOP RIGHT
			      GL11.glVertex2f(x+charwidth, y);
				  
			  
		   		
			      x_orig += (charwidth + char_offset_x);//make the next character be after whatever is there so far
		   	}	   
		   	
		   	
		    GL11.glEnd();
		    
		   
	   }
	   
	   
	   public void drawString(int x_orig, int y_orig, String S, int maxwidth, int maxheight, int verticalspacing){//complex, multilined
		   
		   int stored_orig_x = x_orig;
		   
		   char[] ch = S.toCharArray();
		   
		   int charwidth = 14;
		   int charheight = 14;
		   
		   int x=0;
		   int y=0;
		   int char_offset_x=0;
		   int char_offset_y=0;
		   
		   
		   
		   
		   int[] cutoff_spots = new int[100];//one for each line
		   int current_line=0;
		   int last_space=0;
		  // int current_char=0;
		   
		   cutoff_spots[0] = -1;//fixes the dumb bug
		  
			for(int i=0;i<S.length();i++){ //find the cutoff spots
		   		
		   		
			      int char_id = ch[i];       
			      
			      if(char_id==32){last_space=i;}//32 is for spacebar
			      
			      
			     // int tex_x = lookuptable_parsed[char_id][0];
				  // int tex_y = lookuptable_parsed[char_id][1];
				   charwidth=lookuptable_parsed[char_id][2];
					charheight=lookuptable_parsed[char_id][3];
					char_offset_x=lookuptable_parsed[char_id][4];
					char_offset_y=lookuptable_parsed[char_id][5];
					
					
					 x_orig += (charwidth + char_offset_x);
					
					if(x_orig - stored_orig_x > maxwidth - 12){  //System.out.println(current_line  + "." + last_space);
						cutoff_spots[current_line]=last_space;
						x_orig=stored_orig_x;
						current_line++;}
					
					//detect the |n combo to do a new line
					if(i>0){
					if(ch[i-1] == '|' && ch[i]=='n'){
						
						cutoff_spots[current_line]= i;
						x_orig=stored_orig_x;
						current_line++;
						
					}}
					
			   	}	   
		   
		   
		   
		   current_line=0;
			x_orig = stored_orig_x;
		   
		   
		   //actually do the draw
		   texture.bind();
		     GL11.glBegin(GL11.GL_QUADS);
		     
		     
		  
		 
		   
		   	for(int i=0;i<S.length();i++){
		   		
		   	  
		   		
		   		
		      int char_id = ch[i];    
		      
		      boolean draw_this_char = true;
		      
		      if(i<S.length()-1){
		      if(ch[i]=='|' && ch[i+1]=='n'){draw_this_char=false;}
		      }
		      if(i>0){
		    	if(ch[i-1]=='|' && ch[i]=='n'){draw_this_char=false;}
		    	if(ch[i-1]=='$'){
		    		draw_this_char=false;
		    		if(ch[i]=='0'){GL11.glColor3d(1,1,1);}
		    		if(ch[i]=='1'){GL11.glColor3d(0,0,0);}
		    		if(ch[i]=='2'){GL11.glColor3d(1,0,0);}
		    		if(ch[i]=='3'){GL11.glColor3d(0,1,0);}
		    		if(ch[i]=='4'){GL11.glColor3d(0,0,1);}
		    		if(ch[i]=='5'){GL11.glColor3d(0,1,1);}
		    		if(ch[i]=='6'){GL11.glColor3d(1,0,1);}
		    		if(ch[i]=='7'){GL11.glColor3d(1,1,0);}
		    	}
		      }
		      if(ch[i]=='$'){draw_this_char=false;}
		      
		      
		      
				
				if(current_line*verticalspacing > maxheight){draw_this_char = false;}
				
		      if(draw_this_char){
		      int tex_x = lookuptable_parsed[char_id][0];
			   int tex_y = lookuptable_parsed[char_id][1];
			   charwidth=lookuptable_parsed[char_id][2];
				charheight=lookuptable_parsed[char_id][3];
				char_offset_x=lookuptable_parsed[char_id][4];
				char_offset_y=lookuptable_parsed[char_id][5];
				
			
				x = x_orig + char_offset_x;
				y = y_orig + char_offset_y + current_line*verticalspacing; 
				
				
		   				   	 
				   float tex_min_x = tex_x / width;
				   float tex_max_x = (tex_x+charwidth) / width;
				   float tex_min_y = tex_y / height;
				   float tex_max_y = (tex_y+charheight) / height;
				   
			      
			      GL11.glTexCoord2f(tex_min_x, tex_min_y); //TOP LEFT
			      GL11.glVertex2f(x, y); 
			      GL11.glTexCoord2f(tex_min_x, tex_max_y); //BOTTOM LEFT
			      GL11.glVertex2f(x, y+charheight);         
			      GL11.glTexCoord2f(tex_max_x, tex_max_y); //BOTTOM RIGHT 
			      GL11.glVertex2f(x+charwidth, y+charheight); 
			      GL11.glTexCoord2f(tex_max_x, tex_min_y); //TOP RIGHT
			      GL11.glVertex2f(x+charwidth, y);
				  
			  
		   		
			      x_orig += (charwidth + char_offset_x);//make the next character be after whatever is there so far
		      }
			      
			      
			   /// i is currentchar
			      if(cutoff_spots[current_line]==i){x_orig = stored_orig_x;current_line++;}
			     
		   	}	   
		   	
		   	
		    GL11.glEnd();
		    GL11.glColor3d(1,1,1);
		 
	   }
	   
 public int getStringPixelLength( String S){
		   
		   char[] ch = S.toCharArray();
		  
		   int x_orig = 0;
		   
		   int charwidth = 14;
		   int charheight = 14;
		   
		   int x=0;
		   int y=0;
		   int char_offset_x=0;
		   int char_offset_y=0;
		
		   
		   	for(int i=0;i<S.length();i++){
		   		
		   		
		   		
		   		
		      int char_id = ch[i];       
		      
		      
		      int tex_x = lookuptable_parsed[char_id][0];
			   int tex_y = lookuptable_parsed[char_id][1];
			   charwidth=lookuptable_parsed[char_id][2];
				charheight=lookuptable_parsed[char_id][3];
				char_offset_x=lookuptable_parsed[char_id][4];
				char_offset_y=lookuptable_parsed[char_id][5];
				
			
				
		   				   	 
				   float tex_min_x = tex_x / width;
				   float tex_max_x = (tex_x+charwidth) / width;
				   float tex_min_y = tex_y / height;
				   float tex_max_y = (tex_y+charheight) / height;
				   
			      
			      GL11.glTexCoord2f(tex_min_x, tex_min_y); //TOP LEFT
			      GL11.glVertex2f(x, y); 
			      GL11.glTexCoord2f(tex_min_x, tex_max_y); //BOTTOM LEFT
			      GL11.glVertex2f(x, y+charheight);         
			      GL11.glTexCoord2f(tex_max_x, tex_max_y); //BOTTOM RIGHT 
			      GL11.glVertex2f(x+charwidth, y+charheight); 
			      GL11.glTexCoord2f(tex_max_x, tex_min_y); //TOP RIGHT
			      GL11.glVertex2f(x+charwidth, y);
				  
			  
		   		
			      x_orig += (charwidth + char_offset_x);//make the next character be after whatever is there so far
		   	}	   
		   	
		   	
		   
		   return x_orig;
		   
	   }
	   
 public int GetStringNumLines(String S, int maxwidth){
	 
	 if(S == null){S="";}
	 if(S.length() < 1){S="";}
	 
	   int x_orig = 0 ;
	   
	   int y_orig = 0 ;
	   
	   int stored_orig_x = x_orig;
	   
	   
	   
	   char[] ch = S.toCharArray();
	   
	   int charwidth = 14;
	   int charheight = 14;
	   
	   int x=0;
	   int y=0;
	   int char_offset_x=0;
	   int char_offset_y=0;
	   
	   
	   
	   
	   int[] cutoff_spots = new int[100];//one for each line
	   int current_line=0;
	   int last_space=0;
	  // int current_char=0;
	   
	   cutoff_spots[0] = -1;//fixes the dumb bug
	  
		for(int i=0;i<S.length();i++){ //find the cutoff spots
	   		
	   		
		      int char_id = ch[i];       
		      
		      if(char_id==32){last_space=i;}//32 is for spacebar
		      
		      
		      
		      
		     // int tex_x = lookuptable_parsed[char_id][0];
			  // int tex_y = lookuptable_parsed[char_id][1];
			   charwidth=lookuptable_parsed[char_id][2];
				charheight=lookuptable_parsed[char_id][3];
				char_offset_x=lookuptable_parsed[char_id][4];
				char_offset_y=lookuptable_parsed[char_id][5];
				
				
				 x_orig += (charwidth + char_offset_x);
				
				if(x_orig - stored_orig_x > maxwidth - 12){  //System.out.println(current_line  + "." + last_space);
					cutoff_spots[current_line]=last_space;
					x_orig=stored_orig_x;
					current_line++;}
				
				//detect the |n combo to do a new line
				if(i>0){
				if(ch[i-1] == '|' && ch[i]=='n'){
					
					cutoff_spots[current_line]= i;
					x_orig=stored_orig_x;
					current_line++;
					
				}}
				
				
				
		   	}	   
	   
	   
	  
	    
	   return (current_line + 1);//returns the number of lines
 }
 
 
 int GetStringPixelIndexFromEnd(String s, int pixels){
	 int charcount =0;
	 
	 int pixelwidth = 0;
	 
	 char[] ch = s.toCharArray();
	 
	 for(int i=ch.length - 1; i >= 0; i--){
		 int char_id = ch[i];       
		 int    charwidth=lookuptable_parsed[char_id][2];
		 int	charheight=lookuptable_parsed[char_id][3];
		 int	char_offset_x=lookuptable_parsed[char_id][4];
		 int	char_offset_y=lookuptable_parsed[char_id][5];
		 
		 
		 pixelwidth += (charwidth + char_offset_x);
		 
		 
		 if(pixelwidth > pixels){ break;}else{charcount++;}
		 
	 }
	 
	 
	 
	 
	 return charcount;
 }
	   
	   
}
