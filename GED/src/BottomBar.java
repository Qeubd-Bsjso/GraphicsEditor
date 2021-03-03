import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BottomBar extends JPanel{
	BottomBar (){
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setBackground(new Color(0x555555));
		this.setPreferredSize(new Dimension(50,25));
		this.setOpaque(true);
	}
}
