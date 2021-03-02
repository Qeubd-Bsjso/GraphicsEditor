import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class OptionPlate extends JPanel implements ActionListener{
	JButton pen;
	JButton eraser;
	JButton line;
	JButton rectangle;
	JButton eclipse;
	
	OptionPlate(){
		this.setPreferredSize(new Dimension(80,80));
		this.setBackground(new Color(0xeedfdd));
		this.setOpaque(true);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		pen = new JButton(new ImageIcon("src/images/pen.png"));
		pen.setPreferredSize(new Dimension(30,30));
		pen.setFocusable(false);
		pen.setToolTipText("Pen");
		
		eraser = new JButton(new ImageIcon("src/images/eraser.png"));
		eraser.setPreferredSize(new Dimension(30,30));
		eraser.setFocusable(false);
		eraser.setToolTipText("Eraser");
		
		line = new JButton(new ImageIcon("src/images/line.png"));
		line.setPreferredSize(new Dimension(30,30));
		line.setFocusable(false);
		
		
		rectangle = new JButton(new ImageIcon("src/images/rectangle.png"));
		rectangle.setPreferredSize(new Dimension(30,30));
		rectangle.setFocusable(false);
		eclipse = new JButton(new ImageIcon("src/images/eclipse.png"));
		
		eclipse.setPreferredSize(new Dimension(30,30));
		eclipse.setFocusable(false);
		
		this.add(pen);
		this.add(eraser);
		this.add(line);
		this.add(rectangle);
		this.add(eclipse);
	}
	public void actionPerformed(ActionEvent e) {
		
	}
}
