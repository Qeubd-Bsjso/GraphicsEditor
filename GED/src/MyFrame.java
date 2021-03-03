import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
	
	MenuBar menuBar;
	OptionPlate options;
	Canvas canvas;
	BottomBar bottomBar;
	MyFrame(){
		//Title of frame
		this.setTitle("GeD");
		//initial size of frame
		this.setSize(800,600);
		//exit program on close operation
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);	
		
		this.setLayout(new BorderLayout(1,1));
		
		 
		//ImageIcon icon = new ImageIcon("images/logo.png");
		//this.setIconImage(icon.getImage());
		
		// creating menu bar , option plate , canvas , bottomBar
		menuBar = new MenuBar();
		options = new OptionPlate();
		canvas = new Canvas();
		bottomBar = new BottomBar();
		
		// adding menu bar , option plate , canvas , bottomBar 
		this.add(menuBar,BorderLayout.NORTH);
		this.add(options,BorderLayout.EAST);
		this.add(canvas,BorderLayout.CENTER);
		this.add(bottomBar,BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
}
