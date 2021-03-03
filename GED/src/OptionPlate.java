import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class OptionPlate extends JPanel implements ActionListener{
	JButton pen;
	JButton eraser;
	JButton line;
	JButton rectangle;
	JButton elipse;
	
	OptionPlate(){
		this.setPreferredSize(new Dimension(80,100));
		this.setBackground(new Color(0xa0aaaa));
		this.setOpaque(true);
		this.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 2));
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		pen = new JButton(new ImageIcon("images/pen.png"));
		pen.setPreferredSize(new Dimension(30,30));
		pen.setFocusable(false);
		pen.setToolTipText("Pen");
		
		eraser = new JButton(new ImageIcon("images/eraser.png"));
		eraser.setPreferredSize(new Dimension(30,30));
		eraser.setFocusable(false);
		eraser.setToolTipText("Eraser");
		
		line = new JButton(new ImageIcon("images/line.png"));
		line.setPreferredSize(new Dimension(30,30));
		line.setFocusable(false);
		line.setToolTipText("Line");
		
		
		rectangle = new JButton(new ImageIcon("images/rectangle.png"));
		rectangle.setPreferredSize(new Dimension(30,30));
		rectangle.setFocusable(false);
		rectangle.setToolTipText("Rectangle");
		
		elipse = new JButton(new ImageIcon("images/elipse.png"));
		elipse.setPreferredSize(new Dimension(30,30));
		elipse.setFocusable(false);
		elipse.setToolTipText("Elipse");
		
		this.add(pen);
		this.add(eraser);
		this.add(line);
		this.add(rectangle);
		this.add(elipse);
	}
	public void actionPerformed(ActionEvent e) {
		
	}
}
