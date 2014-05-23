package pack;

import org.lwjgl.opengl.GL11;

public class TextBox {
         boolean hidechars=false;
          int x;
          int y;
          int width;
          int height;
          int cursor_pos;
          String text = "";
          boolean Has_Focus;
          int maxtextlength=20;
          boolean multilined=false;
          
          Image2D image = null;
          Image2D icon = null;
          
          boolean accept_alpha_characters = false;
          
          String label = "";
		
          int menu=-1;
          int menuscreen=-1;
}
