import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Canvas extends JPanel implements MouseListener , MouseMotionListener , Scrollable, ComponentListener{
	private int mousePointerX;
	private int mousePointerY;
	private BottomBar bottomBar;
	private OptionPlate optionPlate;
	BufferedImage img;
	Graphics2D  graphics;
	double width ,height;
	Canvas(){
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setVisible(true);
		this.addComponentListener(this);
		mousePointerX=-1;
		mousePointerY=-1;
		img = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
	    graphics = img.createGraphics();
	}
	public void bindBottomBar(BottomBar b) {
		bottomBar = b;
	}
	public void bindOptionPlate(OptionPlate p) {
		optionPlate = p;
	}
	
	public void writeWithPen(int a,int b) {
		graphics.setColor(optionPlate.currentColor());
		graphics.drawLine(mousePointerX, mousePointerY, a, b);
		this.repaint();
	}
	
	public void erase() {
		graphics.setColor(Color.white);
		graphics.fillRect(mousePointerX-optionPlate.getEraserSize(), mousePointerY-optionPlate.getEraserSize(),2*optionPlate.getEraserSize(), 2*optionPlate.getEraserSize() );
		this.repaint();
	}
	
	public void paintComponent(Graphics g_temp) {
		super.paintComponent(g_temp);
		Graphics2D g = (Graphics2D) g_temp;
		g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
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
		System.out.println("Clicked");
	}
	
	public void mousePressed(MouseEvent e){
		//when a mouse button is pressed
		System.out.println("Pressed");
	}
	
	public void mouseReleased(MouseEvent e){
		//mouse button is released
		System.out.println("Released");
		switch(optionPlate.getTool()) {
		case 2: optionPlate.resetTool();
				break;
		}
	}
	
	public void mouseEntered(MouseEvent e){
		//when mouse enters an area 
		mousePointerX=e.getX();
		mousePointerY=e.getY();
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
		}
		bottomBar.setCoordinates(mousePointerX,mousePointerY);
		
	}
	@Override
	public Dimension getPreferredScrollableViewportSize() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getScrollableBlockIncrement(Rectangle arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean getScrollableTracksViewportHeight() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean getScrollableTracksViewportWidth() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int getScrollableUnitIncrement(Rectangle arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void componentResized(ComponentEvent e) {
		BufferedImage resizedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
	    graphics = resizedImage.createGraphics();
	    graphics.setColor(Color.white);
	    graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
	    graphics.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
	    img = resizedImage;
	    this.repaint();
    }
	public void componentMoved(ComponentEvent e) {
        
    }
	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
