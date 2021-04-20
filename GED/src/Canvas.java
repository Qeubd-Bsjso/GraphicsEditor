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
	
	private int imgWidth,imgHeight;
	
	private BottomBar bottomBar;
	private OptionPlate optionPlate;
	private ColorPalette palette;
	private MyFrame frame;
	
	private BufferedImage backgroundImg;
	private Graphics2D  bggraphics;
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
		this.setBackground(new Color(255,255,255));
		mousePointerX=-1;
		mousePointerY=-1;
		imgWidth = 1200;
		imgHeight = 600;
		img = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
	    graphics = img.createGraphics();
	    graphics.setBackground(new Color(255,255,255,0));
	    backgroundImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
	    bggraphics = backgroundImg.createGraphics();
	    bggraphics.setColor(Color.white);
	    bggraphics.fillRect(0, 0, imgWidth, imgHeight);
	    object = null;
	    objectCreated = false;
	    objectDrawn = false;
	}
	public void bindBottomBar(BottomBar b) {
		bottomBar = b;
		bottomBar.setImageSize(imgWidth, imgHeight);
	}
	public void bindOptionPlate(OptionPlate p) {
		optionPlate = p;
	}
	public void bindColorPalette(ColorPalette p) {
		palette = p;
	}
	public void bindFrame(MyFrame f) {
		frame = f;
	}
	
	public void createNewFile(int type,int x,int y) {
		imgWidth = x;
		imgHeight = y;
		backgroundImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
		bggraphics = backgroundImg.createGraphics();
		switch(type) {
		case 0:
			//white sheet
			bggraphics.setColor(Color.white);
			bggraphics.fillRect(0, 0, imgWidth, imgHeight);
			break;
		case 1:
			//black sheet
			bggraphics.setColor(Color.black);
			bggraphics.fillRect(0, 0, imgWidth, imgHeight);
			break;
		case 2:
			//wide ruled
			bggraphics.setColor(Color.white);
			bggraphics.fillRect(0, 0, imgWidth, imgHeight);
			bggraphics.setColor(new Color(0x2030f0));
			bggraphics.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
			
			for(int i=100;i<imgHeight;i+=50) {
				bggraphics.drawLine(0,i ,imgWidth ,i );
			}
			break;
		case 3:
			//graph
			bggraphics.setColor(Color.white);
			bggraphics.fillRect(0, 0, imgWidth, imgHeight);
			bggraphics.setColor(new Color(0xb81164));
			bggraphics.setStroke(new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
			
			for(int i=10;i<imgHeight;i+=10) {
				bggraphics.drawLine(0,i ,imgWidth ,i );
			}
			for(int i=10;i<imgWidth;i+=10) {
				bggraphics.drawLine(i,0 ,i ,imgHeight );
			}
			
			bggraphics.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
			
			for(int i=50;i<imgHeight;i+=50) {
				bggraphics.drawLine(0,i ,imgWidth ,i );
			}
			for(int i=50;i<imgWidth;i+=50) {
				bggraphics.drawLine(i,0 ,i ,imgHeight );
			}
			break;
		case 4:
			//dot-grid
			bggraphics.setColor(Color.white);
			bggraphics.fillRect(0, 0, imgWidth, imgHeight);
			bggraphics.setColor(new Color(0x332323));
			bggraphics.setStroke(new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND, 1.0f,new float[] {1.0f,20.0f},0.0f));
			
			for(int i=20;i<imgHeight;i+=20) {
				bggraphics.drawLine(0,i ,imgWidth ,i );
			}
			break;
		case 5:
			//lined
			bggraphics.setColor(Color.white);
			bggraphics.fillRect(0, 0, imgWidth, imgHeight);
			bggraphics.setColor(new Color(0x203030));
			bggraphics.setStroke(new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
			
			for(int i=150;i<imgHeight;i+=40) {
				bggraphics.drawLine(0,i ,imgWidth ,i );
			}
			
			bggraphics.setColor(new Color(0xd43030));
			bggraphics.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
			bggraphics.drawLine(0,110 ,imgWidth ,110 );
			bggraphics.drawLine(0,105 ,imgWidth ,105 );
			bggraphics.drawLine(100, 0, 100, imgHeight);
			bggraphics.drawLine(95, 0, 95, imgHeight);
			break;
		case 6:
			//notebook
			bggraphics.setColor(new Color(0xded3a0));
			bggraphics.fillRect(0, 0, imgWidth, imgHeight);
			bggraphics.setColor(new Color(0x203030));
			bggraphics.setStroke(new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
			
			for(int i=150;i<imgHeight;i+=40) {
				bggraphics.drawLine(0,i ,imgWidth ,i );
			}
			
			bggraphics.setColor(new Color(0xd43030));
			bggraphics.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
			bggraphics.drawLine(0,110 ,imgWidth ,110 );
			bggraphics.drawLine(0,105 ,imgWidth ,105 );
			bggraphics.drawLine(100, 0, 100, imgHeight);
			bggraphics.drawLine(95, 0, 95, imgHeight);
			break;
		case 7:
			//blueprint
			bggraphics.setColor(new Color(0x4053e3));
			bggraphics.fillRect(0, 0, imgWidth, imgHeight);
			bggraphics.setColor(new Color(0xe6e8f7));
			bggraphics.setStroke(new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
			
			for(int i=20;i<imgHeight;i+=20) {
				bggraphics.drawLine(0,i ,imgWidth ,i );
			}
			for(int i=20;i<imgWidth;i+=20) {
				bggraphics.drawLine(i,0 ,i ,imgHeight );
			}
			break;
		case 8:
			//transparent
			
			break;
		}
		
		img = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
		graphics = img.createGraphics();
		graphics.setBackground(new Color(255,255,255,0));
		frame.updateCanvasScale();
	    this.repaint();
	    bottomBar.setImageSize(imgWidth, imgHeight);
	}
	
	public BufferedImage getSaveImage() {
		BufferedImage temp = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = temp.createGraphics();
		g.drawImage(backgroundImg, 0, 0, imgWidth, imgHeight, null);
		g.drawImage(img, 0, 0, imgWidth, imgHeight, null);
		return temp;
	}
	
	public void clear() {
		graphics.clearRect(0, 0, imgWidth, imgHeight);
		this.repaint();
	}
	
	public void loadImage(BufferedImage i) { 
		imgWidth = i.getWidth();
		imgHeight = i.getHeight();
		bottomBar.setImageSize(imgWidth, imgHeight);
		img = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
	    graphics = img.createGraphics();
	    graphics.drawImage(i, 0, 0, imgWidth, imgHeight, null);
	    this.repaint();
	}
	
	
	public void flipVertical() { 
		BufferedImage tempImg = img;
		BufferedImage tempBackgroundImg = backgroundImg;
		img = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
		graphics = img.createGraphics();
		backgroundImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
		bggraphics = backgroundImg.createGraphics();
		graphics.drawImage(tempImg, 0, imgHeight, imgWidth, -imgHeight, null);
		bggraphics.drawImage(tempBackgroundImg, 0, imgHeight, imgWidth, -imgHeight, null);
		this.repaint();
	}
	
	public void flipHorizontal() { 
		BufferedImage tempImg = img;
		BufferedImage tempBackgroundImg = backgroundImg;
		img = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
		graphics = img.createGraphics();
		backgroundImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
		bggraphics = backgroundImg.createGraphics();
		graphics.drawImage(tempImg, imgWidth, 0, -imgWidth, imgHeight, null);
		bggraphics.drawImage(tempBackgroundImg, imgWidth, 0, -imgWidth, imgHeight, null);
		this.repaint();
	}
	
	public void rotateRight() { 
		
		int newImgWidth = imgHeight;
		int newImgHeight = imgWidth;
		
		BufferedImage tempImg = img;
		BufferedImage tempBackgroundImg = backgroundImg;
	
		img = new BufferedImage(newImgWidth, newImgHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		backgroundImg = new BufferedImage(newImgWidth, newImgHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D bg = backgroundImg.createGraphics();
		
		g.translate((newImgWidth-imgWidth)/2, (newImgHeight-imgHeight)/2);
		bg.translate((newImgWidth-imgWidth)/2, (newImgHeight-imgHeight)/2);
		
		g.rotate(Math.toRadians(90), imgWidth/2, imgHeight/2);
		bg.rotate(Math.toRadians(90), imgWidth/2, imgHeight/2);
		
		g.drawRenderedImage(tempImg, null);
		bg.drawRenderedImage(tempBackgroundImg, null);
		
		g.dispose();
		bg.dispose();
		
		graphics = img.createGraphics();
		bggraphics = backgroundImg.createGraphics();
		
		imgWidth = newImgWidth;
		imgHeight = newImgHeight;
		
		bottomBar.setImageSize(imgWidth, imgHeight);
		
		frame.updateCanvasScale();
		this.repaint();
	}
	
	public void rotateLeft() { 
		
		int newImgWidth = imgHeight;
		int newImgHeight = imgWidth;
		
		BufferedImage tempImg = img;
		BufferedImage tempBackgroundImg = backgroundImg;
	
		img = new BufferedImage(newImgWidth, newImgHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		backgroundImg = new BufferedImage(newImgWidth, newImgHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D bg = backgroundImg.createGraphics();
		
		g.translate((newImgWidth-imgWidth)/2, (newImgHeight-imgHeight)/2);
		bg.translate((newImgWidth-imgWidth)/2, (newImgHeight-imgHeight)/2);
		
		g.rotate(Math.toRadians(-90), imgWidth/2, imgHeight/2);
		bg.rotate(Math.toRadians(-90), imgWidth/2, imgHeight/2);
		
		g.drawRenderedImage(tempImg, null);
		bg.drawRenderedImage(tempBackgroundImg, null);
		
		g.dispose();
		bg.dispose();
		
		graphics = img.createGraphics();
		bggraphics = backgroundImg.createGraphics();
		
		imgWidth = newImgWidth;
		imgHeight = newImgHeight;
		
		bottomBar.setImageSize(imgWidth, imgHeight);
		
		frame.updateCanvasScale();
		this.repaint();
	}

	private int canvasToImageWidth(int x) {
		return (int)((double)x*imgWidth/this.getWidth());
	}
	private int canvasToImageHeight(int x) {
		return x*imgHeight/this.getHeight();
	}
	
	public int getImgWidth() {
		return imgWidth;
	}
	
	public int getImgHeight() {
		return imgHeight;
	}
	
	public double getAspectRatio() {
		return (double)imgWidth / imgHeight;
	}
	
	public void writeWithPen(int a,int b) {
		graphics.setColor(palette.getPrimaryColor());
		graphics.setStroke(new BasicStroke(optionPlate.getPenSize(),BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		graphics.drawLine(canvasToImageWidth(mousePointerX), canvasToImageHeight(mousePointerY), canvasToImageWidth(a), canvasToImageHeight(b));
		this.repaint();
	}
	
	public void erase() {
		graphics.clearRect(canvasToImageWidth(mousePointerX-optionPlate.getEraserSize()/2),canvasToImageWidth(mousePointerY-optionPlate.getEraserSize()/2),canvasToImageHeight(optionPlate.getEraserSize()), canvasToImageHeight(optionPlate.getEraserSize()));		
		this.repaint();
	}
	
	public void paintComponent(Graphics g_temp) {
		super.paintComponent(g_temp);
		Graphics2D g = (Graphics2D) g_temp;
		g.drawImage(backgroundImg, 0, 0, this.getWidth(), this.getHeight(), null);
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
					object = new DrawableObject("line",canvasToImageWidth(a),canvasToImageHeight(b),optionPlate.getLineWidth(),palette.getPrimaryColor(),null,imgWidth,imgHeight);
					this.repaint();
					objectCreated = true;
					objectDrawn = false;
				}
				break;
		case 4:
				if(SwingUtilities.isLeftMouseButton(e)&&mousePointerX != -1&&objectCreated == false) {
					object = new DrawableObject("rectangle",canvasToImageWidth(a),canvasToImageHeight(b),optionPlate.getRectangleLineWidth(),palette.getPrimaryColor(),optionPlate.getRectangleFillColor(),imgWidth,imgHeight);
					this.repaint();
					objectCreated = true;
					objectDrawn = false;
				}
				break;
		case 5:
				if(SwingUtilities.isLeftMouseButton(e)&&mousePointerX != -1&&objectCreated == false) {
					object = new DrawableObject("ellipse",canvasToImageWidth(a),canvasToImageHeight(b),optionPlate.getEllipseLineWidth(),palette.getPrimaryColor(),optionPlate.getEllipseFillColor(),imgWidth,imgHeight);
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
					graphics.drawImage(object.image, 0, 0, imgWidth, imgHeight, null);
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
					graphics.drawImage(object.image, 0, 0, imgWidth, imgHeight, null);
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
					object.updateP2(canvasToImageWidth(a),canvasToImageHeight(b));
					this.repaint();
				}
				break;
		}
		mousePointerX = a;
		mousePointerY = b;
		bottomBar.setCoordinates(mousePointerX,mousePointerY);
		
	}
}
