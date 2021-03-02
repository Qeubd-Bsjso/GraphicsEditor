import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
	
	MenuBar menuBar;
	OptionPlate options;
	MyFrame(){
		//Title of frame
		this.setTitle("GeD");
		//initial size of frame
		this.setSize(800,600);
		//exit program on close operation
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);	
		
		this.setLayout(new BorderLayout(1,1));
		
		
		// logo 
		//ImageIcon icon = new ImageIcon("images/logo.png");
		//this.setIconImage(icon.getImage());
		
		//creating menu bar
		menuBar = new MenuBar();
		
		//creating option plate
		options = new OptionPlate();
		
		
		//adding menu bar
		this.add(menuBar,BorderLayout.NORTH);
		//adding option plate
		this.add(options,BorderLayout.EAST);
		//
		
		this.setVisible(true);
	}
}
