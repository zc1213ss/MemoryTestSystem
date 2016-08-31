import javax.swing.*;
import java.awt.event.*;
public class Block extends JButton {
  ImageIcon openStateIcon;
  public ImageIcon getOpenStateIcon(){
	  return openStateIcon;
  }
  public void setOpenStateIcon(ImageIcon icon){
	  openStateIcon=icon;
  }
}
