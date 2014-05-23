package pack;

import org.lwjgl.opengl.GL11;

public class Image2D {
	   private float normalizedWidth, normalizedHeight;
	   private float width, height;
	   private float centerX, centerY;
	   private float textureOffsetX, textureOffsetY;
	   //private float rotation;
	   //boolean is_concentric;
	   private Texture texture;

	   public Image2D(Texture texture) {
	      this.texture = texture;
	      this.normalizedWidth = get2Fold((int) texture.getWidth()) /2;     //why did I have to cut this in half? Is too much data being stored per texture?
	      this.normalizedHeight = get2Fold((int) texture.getHeight()) /2;    //why did I have to cut this in half? Is too much data being stored per texture?
	      this.width = texture.getImageWidth();
	      this.height = texture.getImageHeight();
	      this.centerX = width / 2f;
	      this.centerY = height / 2f;
	   }

	   public Image2D copy() {
	      Image2D img = new Image2D(texture);
	      return img;
	   }

	   public Image2D getSubImage(float x, float y, float width, float height) {
	      float tx = ( x / this.width * normalizedWidth ) + textureOffsetX;
	      float ty = ( y / this.height * normalizedHeight ) + textureOffsetY;
	      float tw = width / this.width * normalizedWidth;
	      float th = height / this.height * normalizedHeight;
	      
	      Image2D img = copy();
	      img.textureOffsetX = tx;
	      img.textureOffsetY = ty;
	      img.width = width;
	      img.height = height;
	      img.normalizedWidth = tw;
	      img.normalizedHeight = th;
	      img.centerX = width / 2f;
	      img.centerY = height / 2f;
	      return img;
	   }
	 
	  
	  
	   
	   
	   private static int get2Fold(int fold) {
	        int ret = 2;
	        while (ret < fold) {
	            ret *= 2;
	        }
	        return ret;
	    }
	   

	   public void begin() {
	  //   GL11.glEnable(GL11.GL_TEXTURE_2D);
	    //  texture.bind();
	     GL11.glBegin(GL11.GL_QUADS);
	   }
	 
	

	   public void end() {
	        GL11.glEnd();
	     // GL11.glDisable(GL11.GL_TEXTURE_2D);
	   }
  
	   
	   public void bind() {
			      texture.bind();
			   }
	   
	   
	   public void draw(float x, float y) {  //draw the whole image
		   
		
		   
		  
		    bind();//too much lag!
		  begin();  
		 
		      
		  
	      GL11.glTexCoord2f(textureOffsetX, textureOffsetY); //TOP LEFT
	      GL11.glVertex2f(x, y); 
	      GL11.glTexCoord2f(textureOffsetX, textureOffsetY+normalizedHeight); //BOTTOM LEFT
	      GL11.glVertex2f(x, y+height);         
	      GL11.glTexCoord2f(textureOffsetX+normalizedWidth, textureOffsetY+normalizedHeight); //BOTTOM RIGHT 
	      GL11.glVertex2f(x+width, y+height); 
	      GL11.glTexCoord2f(textureOffsetX+normalizedWidth, textureOffsetY); //TOP RIGHT
	      GL11.glVertex2f(x+width, y);
	      
		  /*
		   GL11.glTexCoord2f(0, 0); //TOP LEFT
		      GL11.glVertex2f(x, y); 
		      GL11.glTexCoord2f(0, 1); //BOTTOM LEFT
		      GL11.glVertex2f(x, y+height);         
		      GL11.glTexCoord2f(1, 1); //BOTTOM RIGHT 
		      GL11.glVertex2f(x+width, y+height); 
		      GL11.glTexCoord2f(1, 0); //TOP RIGHT
		      GL11.glVertex2f(x+width, y);*/
		  
	     end();
	      
	  
	   
	      
	   }
	   
   public void draw_BottomUp(float x, float y) {  //draw the whole image, shifted by the images dimensions!
		   
	   	
		  
		    bind();//too much lag!
		  begin();  
		 
		      
		  
	      GL11.glTexCoord2f(textureOffsetX, textureOffsetY); //TOP LEFT
	      GL11.glVertex2f(x, y-height); 
	      GL11.glTexCoord2f(textureOffsetX, textureOffsetY+normalizedHeight); //BOTTOM LEFT
	      GL11.glVertex2f(x, y+0);         
	      GL11.glTexCoord2f(textureOffsetX+normalizedWidth, textureOffsetY+normalizedHeight); //BOTTOM RIGHT 
	      GL11.glVertex2f(x+width, y+0); 
	      GL11.glTexCoord2f(textureOffsetX+normalizedWidth, textureOffsetY); //TOP RIGHT
	      GL11.glVertex2f(x+width, y-height);
	      
		  /*
		   GL11.glTexCoord2f(0, 0); //TOP LEFT
		      GL11.glVertex2f(x, y); 
		      GL11.glTexCoord2f(0, 1); //BOTTOM LEFT
		      GL11.glVertex2f(x, y+height);         
		      GL11.glTexCoord2f(1, 1); //BOTTOM RIGHT 
		      GL11.glVertex2f(x+width, y+height); 
		      GL11.glTexCoord2f(1, 0); //TOP RIGHT
		      GL11.glVertex2f(x+width, y);*/
		  
	     end();
	      
	  
	   }
	   
	   
	   
	   
	   
	   public void draw_subimage(float tex_x, float tex_y, float x, float y, float mywidth, float myheight) {
		   
		   //texcoordinates are in percents from 0 to 1 !!!! OH. WOW.
		   
		   float tex_min_x = tex_x / width;
		   float tex_max_x = (tex_x+mywidth) / width;
		   float tex_min_y = tex_y / height;
		   float tex_max_y = (tex_y+myheight) / height;
	
		   
	      
	      GL11.glTexCoord2f(tex_min_x, tex_min_y); //TOP LEFT
	      GL11.glVertex2f(x, y); 
	      GL11.glTexCoord2f(tex_min_x, tex_max_y); //BOTTOM LEFT
	      GL11.glVertex2f(x, y+myheight);         
	      GL11.glTexCoord2f(tex_max_x, tex_max_y); //BOTTOM RIGHT 
	      GL11.glVertex2f(x+mywidth, y+myheight); 
	      GL11.glTexCoord2f(tex_max_x, tex_min_y); //TOP RIGHT
	      GL11.glVertex2f(x+mywidth, y);
		  
	  
	   
	   }
	   
	   
	   //this is for sheets
 public void draw_subimage_stretch(float tex_x, float tex_y, float x, float y, float texwidth, float texheight, float mywidth, float myheight) {
		   
		   //texcoordinates are in percents from 0 to 1 !!!! OH. WOW.
		   
		   float tex_min_x = tex_x / width;
		   float tex_max_x = (tex_x+texwidth) / width;
		   float tex_min_y = tex_y / height;
		   float tex_max_y = (tex_y+texheight) / height;
		   
	
		   
	      
	      GL11.glTexCoord2f(tex_min_x, tex_min_y); //TOP LEFT
	      GL11.glVertex2f(x, y); 
	      GL11.glTexCoord2f(tex_min_x, tex_max_y); //BOTTOM LEFT
	      GL11.glVertex2f(x, y+myheight);         
	      GL11.glTexCoord2f(tex_max_x, tex_max_y); //BOTTOM RIGHT 
	      GL11.glVertex2f(x+mywidth, y+myheight); 
	      GL11.glTexCoord2f(tex_max_x, tex_min_y); //TOP RIGHT
	      GL11.glVertex2f(x+mywidth, y);
		  
	  
	   
	   }
 
 
 //this is dumb and redundant but oh well. this is not for sheets though.
 public void draw_FitToSize(float x, float y,int size) {  
 	  
	    bind();//too much lag!
	  begin();  
	 
	      
	  
    GL11.glTexCoord2f(textureOffsetX, textureOffsetY); //TOP LEFT
    GL11.glVertex2f(x, y); 
    GL11.glTexCoord2f(textureOffsetX, textureOffsetY+normalizedHeight); //BOTTOM LEFT
    GL11.glVertex2f(x, y+size);         
    GL11.glTexCoord2f(textureOffsetX+normalizedWidth, textureOffsetY+normalizedHeight); //BOTTOM RIGHT 
    GL11.glVertex2f(x+size, y+size); 
    GL11.glTexCoord2f(textureOffsetX+normalizedWidth, textureOffsetY); //TOP RIGHT
    GL11.glVertex2f(x+size, y);
    
	  
	  
   end();
    

 
 }
	   
	   public float getWidth() {
			return width;
		}

	

		public float getHeight() {
			return height;
		}

		
	   
	   
	}