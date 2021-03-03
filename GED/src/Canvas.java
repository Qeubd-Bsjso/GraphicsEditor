import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Canvas extends JPanel{
	Canvas(){
		this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		this.setBackground(new Color(0xc0c0c0));
		this.setOpaque(true);
	}
}
