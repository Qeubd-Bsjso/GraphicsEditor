import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyFrame extends JFrame {
	
	MenuBar menuBar;
	Canvas canvas;
	OptionPlate options;
	BottomBar bottomBar;
	JPanel canvasHolder;
	MyFrame(){
		this.setBackground(Color.blue);
		//Title of frame
		this.setTitle("GeD");
		//initial size of frame
		this.setSize(800,600);
		//exit program on close operation
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);	
		this.setLayout(new BorderLayout(1,1));
		
		 
		ImageIcon icon = new ImageIcon("images/logo.png");
		this.setIconImage(icon.getImage());
		
		// creating menu bar , option plate , canvas , bottomBar
		canvasHolder = new JPanel();
		bottomBar = new BottomBar();
		canvas = new Canvas();
		menuBar = new MenuBar();
		options = new OptionPlate();
		
		canvasHolder.setLayout(new BorderLayout(20,20));
		canvasHolder.setBackground(new Color(0xc0c0c0));
		canvasHolder.setOpaque(true);
		canvasHolder.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		canvasHolder.add(canvas,BorderLayout.CENTER);

		// binding elements
		canvas.bindBottomBar(bottomBar);
		menuBar.bindCanvas(canvas);
		canvas.bindOptionPlate(options);
		options.bindCanvas(canvas);
		
		// adding menu bar , option plate , canvas , bottomBar 
		this.add(menuBar,BorderLayout.NORTH);
		this.add(options,BorderLayout.EAST);
		this.add(canvasHolder,BorderLayout.CENTER);
		this.add(bottomBar,BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
}
