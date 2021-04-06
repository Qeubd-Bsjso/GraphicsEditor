import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyFrame extends JFrame implements ComponentListener{
	
	MenuBar menuBar;
	Canvas canvas;
	OptionPlate options;
	BottomBar bottomBar;
	ColorPalette palette;
	
	JPanel canvasHolder;
	JPanel plate;
	
	double canvasAspectRatio;
	
	MyFrame(){
		this.setBackground(Color.black);
		//Title of frame
		this.setTitle("GeD");
		//initial size of frame
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//exit program on close operation
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(300,300));
		this.setLocationRelativeTo(null);	
		this.setLayout(new BorderLayout());
		this.addComponentListener(this);
		
		 
		ImageIcon icon = new ImageIcon("images/logo.png");
		this.setIconImage(icon.getImage());
		
		// creating menu bar , option plate , canvas , bottomBar
		canvasHolder = new JPanel();
		bottomBar = new BottomBar();
		canvas = new Canvas();
		menuBar = new MenuBar();
		options = new OptionPlate();
		palette = new ColorPalette();
		
		canvasHolder.setLayout(null);
		canvasHolder.setBackground(new Color(0x707070));
		canvasHolder.setOpaque(true);
		canvasHolder.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		canvasHolder.add(canvas,BorderLayout.CENTER);
		
		plate = new JPanel();
		plate.setPreferredSize(new Dimension(80,100));
		plate.setLayout(new BorderLayout());
		plate.setBackground(new Color(50,50,50));
		plate.add(options,BorderLayout.NORTH);
		plate.add(palette,BorderLayout.AFTER_LINE_ENDS);

		// binding elements
		canvas.bindBottomBar(bottomBar);
		menuBar.bindCanvas(canvas);
		canvas.bindOptionPlate(options);
		canvas.bindColorPalette(palette);
		options.bindCanvas(canvas);
		
		// adding menu bar , option plate , canvas , bottomBar 
		this.add(menuBar,BorderLayout.NORTH);
		//this.add(options,BorderLayout.EAST);
		//this.add(palette,BorderLayout.EAST);
		this.add(plate,BorderLayout.EAST);
		this.add(canvasHolder,BorderLayout.CENTER);
		this.add(bottomBar,BorderLayout.SOUTH);
		
		this.setVisible(true);
		
		canvasAspectRatio = 2;
	}
	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		if(((double)canvasHolder.getWidth()/canvasHolder.getHeight()) <= canvasAspectRatio) {
			int width = canvasHolder.getWidth()-20;
			int height = (int) ((double)width/canvasAspectRatio);
			canvas.setBounds(10,(Math.abs(canvasHolder.getHeight()-height))/2, width,height );
		}
		else {
			int height = canvasHolder.getHeight()-20;
			int width = (int) ((double)height*canvasAspectRatio);
			canvas.setBounds((Math.abs(canvasHolder.getWidth()-width))/2,10, width,height);
		}
	}
	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
