import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BottomBar extends JPanel{
	private JLabel label;
	BottomBar (){
		this.setBackground(new Color(0x555555));
		this.setPreferredSize(new Dimension(50,25));
		this.setOpaque(true);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		label = new JLabel();
		label.setForeground(new Color(0xc0c040));
		label.setText("x:  y: ");
		this.add(label);
	}
	
	public void setCoordinates(int x,int y) {
		if(x>=0) {
			label.setText("x:"+x+" y:"+y);
		}
		else {
			label.setText("x:  y: ");
		}
		super.update(this.getGraphics());
		
	}
}
