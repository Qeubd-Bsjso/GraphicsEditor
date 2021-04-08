import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
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
	
	private DrawableObject object;
	
	private boolean objectCreated ;
	private boolean objectDrawn;
	
	public Canvas(){
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setLayout(null);
		this.setVisible(true);
		mousePointerX=-1;
		mousePointerY=-1;
		img = new BufferedImage(1200, 600, BufferedImage.TYPE_INT_ARGB);
	    graphics = img.createGraphics();
	    graphics.setColor(Color.white);
	    graphics.fillRect(0, 0, 1200, 600);
	    object = null;
	    objectCreated = false;
	    objectDrawn = false;
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
		return img;
	}
	
	public void clear() {
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, 1200, 600);
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
		graphics.setStroke(new BasicStroke(optionPlate.getPenSize(),BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
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
		g.setPaint(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
		if(object!=null) {
			g.drawImage(object.image, 0, 0, this.getWidth(), this.getHeight(), null);
		}
		g.dispose();
	}
	
	public void mouseMoved(MouseEvent e) {
		mousePointerX=e.getX();
		mousePointerY=e.getY();
		bottomBar.setCoordinates(mousePointerX,mousePointerY);
	}
	
	public void mouseClicked(MouseEvent e){
		// button is clicked
		int a = e.getX();
		int b = e.getY();
		System.out.println(optionPlate.getTool());
		switch(optionPlate.getTool()) {
		case 1: if(SwingUtilities.isLeftMouseButton(e)&&mousePointerX != -1) {
					writeWithPen(a,b);
				}
				
				break;
		case 2:	if(SwingUtilities.isLeftMouseButton(e)&&mousePointerX != -1) {
					erase();
				}
				break;
		}
		mousePointerX = a;
		mousePointerY = b;
	}
	
	public void mousePressed(MouseEvent e){
		
		int a = e.getX();
		int b = e.getY();
		switch(optionPlate.getTool()) {
		case 1: 
				break;
		case 2:	
				break;
		case 3:	
				if(SwingUtilities.isLeftMouseButton(e)&&mousePointerX != -1&&objectCreated == false) {
					object = new DrawableObject("line",this.getSize(),canvasToImage(a),canvasToImage(b),optionPlate.getLineWidth(),palette.getPrimaryColor(),null);
					this.repaint();
					objectCreated = true;
					objectDrawn = false;
				}
				break;
		case 4:
				if(SwingUtilities.isLeftMouseButton(e)&&mousePointerX != -1&&objectCreated == false) {
					object = new DrawableObject("rectangle",this.getSize(),canvasToImage(a),canvasToImage(b),optionPlate.getRectangleLineWidth(),palette.getPrimaryColor(),optionPlate.getRectangleFillColor());
					this.repaint();
					objectCreated = true;
					objectDrawn = false;
				}
				break;
		case 5:
				if(SwingUtilities.isLeftMouseButton(e)&&mousePointerX != -1&&objectCreated == false) {
					object = new DrawableObject("ellipse",this.getSize(),canvasToImage(a),canvasToImage(b),optionPlate.getEllipseLineWidth(),palette.getPrimaryColor(),optionPlate.getEllipseFillColor());
					this.repaint();
					objectCreated = true;
					objectDrawn = false;
				}
				break;
		}
		mousePointerX = a;
		mousePointerY = b;
	}
	
	public void mouseReleased(MouseEvent e){
		//mouse button is released
		switch(optionPlate.getTool()) {
		case 1: 
				break;
		case 2:	
				break;
		case 3:	
		case 4:
		case 5:
				if(objectDrawn == false && objectCreated ==true) {
					objectDrawn = true;
					objectCreated = false;
					graphics.drawImage(object.image, 0, 0, 1200, 600, null);
					object = null;
				}
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
		switch(optionPlate.getTool()) {
		case 1: 
				break;
		case 2:	
				break;
		case 3:	
		case 4:
		case 5:
				if(objectDrawn == false && objectCreated == true) {
					objectDrawn = true;
					objectCreated = false;
					graphics.drawImage(object.image, 0, 0, 1200, 600, null);
					object = null;
				}
				break;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int a = e.getX();
		int b = e.getY();
		switch(optionPlate.getTool()) {
		case 1: if(SwingUtilities.isLeftMouseButton(e)&&mousePointerX != -1) {
					writeWithPen(a,b);
				}
				break;
		case 2:	if(SwingUtilities.isLeftMouseButton(e)&&mousePointerX != -1) {
					erase();
				}
				break;
		case 3:	
		case 4:
		case 5:
				if(SwingUtilities.isLeftMouseButton(e)&&mousePointerX != -1&&objectDrawn == false) {
					object.updateP2(canvasToImage(a),canvasToImage(b));
					this.repaint();
				}
				break;
		}
		mousePointerX = a;
		mousePointerY = b;
		bottomBar.setCoordinates(mousePointerX,mousePointerY);
		
	}
}
