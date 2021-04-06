import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Canvas extends JPanel implements MouseListener , MouseMotionListener {
	private int mousePointerX;
	private int mousePointerY;
	
	private BottomBar bottomBar;
	private OptionPlate optionPlate;
	private ColorPalette palette;
	
	private BufferedImage img;
	private Graphics2D  graphics;
	private ArrayList <BufferedImage> objects;
	public Canvas(){
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setVisible(true);
		mousePointerX=-1;
		mousePointerY=-1;
		img = new BufferedImage(1200, 600, BufferedImage.TYPE_INT_ARGB);
	    graphics = img.createGraphics();
	    objects = new ArrayList<BufferedImage>();
	}
	public void bindBottomBar(BottomBar b) {
		bottomBar = b;
	}
	public void bindOptionPlate(OptionPlate p) {
		optionPlate = p;
	}
	public void bindColorPalette(ColorPalette p) {
		palette = p;
	}
	
	public void createNewFile() {
		img = new BufferedImage(1200, 600, BufferedImage.TYPE_INT_ARGB);
		graphics = img.createGraphics();
	    this.repaint();
	}
	
	public BufferedImage getSaveImage() {
		return this.img;
	}
	
	public void loadImage(BufferedImage i) { 
		img = new BufferedImage(1200, 600, BufferedImage.TYPE_INT_ARGB);
	    graphics = img.createGraphics();
	    graphics.drawImage(i, 0, 0, 1200, 600, null);
	    this.repaint();
	}

	private int canvasToImage(int x) {
		return x*1200/this.getWidth();
	}
	
	public void writeWithPen(int a,int b) {
		graphics.setColor(palette.getPrimaryColor());
		graphics.setStroke(new BasicStroke(canvasToImage(optionPlate.getPenSize()),BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		graphics.drawLine(canvasToImage(mousePointerX), canvasToImage(mousePointerY), canvasToImage(a), canvasToImage(b));
		this.repaint();
	}
	
	public void erase() {
		graphics.setColor(Color.white);
		graphics.fillRect(canvasToImage(mousePointerX-optionPlate.getEraserSize()/2),canvasToImage(mousePointerY-optionPlate.getEraserSize()/2),canvasToImage(optionPlate.getEraserSize()), canvasToImage(optionPlate.getEraserSize()));
		this.repaint();
	}
	
	public void paintComponent(Graphics g_temp) {
		super.paintComponent(g_temp);
		Graphics2D g = (Graphics2D) g_temp;
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
		for(BufferedImage i : objects) {
			g.drawImage(i,0,0,this.getWidth(),this.getHeight(),null);
		}
		g.dispose();
	}
	
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mousePointerX=e.getX();
		mousePointerY=e.getY();
		bottomBar.setCoordinates(mousePointerX,mousePointerY);
	}
	
	public void mouseClicked(MouseEvent e){
		// button is clicked
		switch(optionPlate.getTool()) {
		case 1: int a = e.getX();
				int b = e.getY();
				if(SwingUtilities.isLeftMouseButton(e)&&mousePointerX != -1) {
					writeWithPen(a,b);
				}
				mousePointerX = a;
				mousePointerY = b;
				break;
		case 2:	mousePointerX = e.getX();
				mousePointerY = e.getY();
				if(SwingUtilities.isLeftMouseButton(e)&&mousePointerX != -1) {
					erase();
				}
				break;
		case 3:
				break;
		}
	}
	
	public void mousePressed(MouseEvent e){
		//when a mouse button is pressed
	
	}
	
	public void mouseReleased(MouseEvent e){
		//mouse button is released
		switch(optionPlate.getTool()) {
		case 3: optionPlate.resetTool();
				break;
		}
	}
	
	public void mouseEntered(MouseEvent e){
		//when mouse enters an area 
		mousePointerX=e.getX();
		mousePointerY=e.getY();
		bottomBar.setCoordinates(mousePointerX,mousePointerY);
	}
	
	public void mouseExited(MouseEvent e){
		//when mouse leaves an area
		mousePointerX=-1;
		mousePointerY=-1;
		bottomBar.setCoordinates(mousePointerX,mousePointerY);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		switch(optionPlate.getTool()) {
		case 1: int a = e.getX();
				int b = e.getY();
				if(SwingUtilities.isLeftMouseButton(e)&&mousePointerX != -1) {
					writeWithPen(a,b);
				}
				mousePointerX = a;
				mousePointerY = b;
				break;
		case 2:	mousePointerX = e.getX();
				mousePointerY = e.getY();
				if(SwingUtilities.isLeftMouseButton(e)&&mousePointerX != -1) {
					erase();
				}
				break;
		case 3:
				break;
		}
		bottomBar.setCoordinates(mousePointerX,mousePointerY);
		
	}
}
