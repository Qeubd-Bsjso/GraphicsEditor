import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class AddImage extends JLabel implements MouseListener {
	private String path = "./files/";
	private Canvas canvas;
	private OptionPlate options;
	private boolean selected;
	private boolean addingImage;
	
	public BufferedImage img;
	private Graphics2D graphics;
	
	private BufferedImage newImg;
	
	private int x0,y0,x1,y1;
	
	private int delx,dely;
	
	private ImageIcon del;
	
	private boolean _0changeSelected = false;
	private boolean _1changeSelected = false;
	private boolean _imgchangeSelected = false;
	
	public AddImage(){
		this.setIcon(new ImageIcon(getClass().getClassLoader().getResource("options/addImage.png")));
		del = new ImageIcon(getClass().getClassLoader().getResource("signs/del.png"));
		selected = false;
		addingImage = false;
		this.setBackground(Color.green);
		this.setPreferredSize(new Dimension(30,30));
		this.setFocusable(false);
		this.setToolTipText("Add Image");
		this.addMouseListener(this);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		img = null;
		graphics = null;
		newImg = null;
	}
	
	public void select() {
		if(!selected) {
			selected = true;
			this.setOpaque(true);
			this.repaint();
		}
	}
	
	public void unselect() {
		if(selected) {
			selected = false;
			if(addingImage) {
				this.confirm();
			}
			this.setOpaque(false);
			this.repaint();
		}
	}
	
	public void bindCanvas(Canvas c) {
		canvas = c;
	}
	
	public void bindOptionPlate(OptionPlate op) {
		options = op;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		options.setValue(6);
		canvas.setCursor(Cursor.getDefaultCursor());
		options.unselectAll();
		this.select();
		int response = this.loadImage();
		if(response == -1) {
			options.resetTool();
			options.unselectAll();
		}
		else {
			addingImage = true;
			render();
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public int loadImage() {
		JFileChooser fileChooser = new JFileChooser("Choose a .png image");
		fileChooser.setCurrentDirectory(new File(path));
		fileChooser.setFileFilter(new FileNameExtensionFilter("*.png", "png"));
		int response = fileChooser.showOpenDialog(null);
		if(response == JFileChooser.APPROVE_OPTION) {
			try {
				File file = fileChooser.getSelectedFile();
				
				/* create image */
				newImg = ImageIO.read(file);
				img = new BufferedImage(canvas.getImgWidth(), canvas.getImgHeight(), BufferedImage.TYPE_INT_ARGB);
				graphics = img.createGraphics();
				graphics.setBackground(new Color(255,255,255,0));
				if(img.getWidth() > img.getHeight()) {
					x0 = canvas.getImgWidth()/4;
					x1 = (canvas.getImgWidth()*3)/4;
					int h = (int)(((double)(x1-x0)*img.getHeight())/img.getWidth()); 
					y0 = canvas.getImgHeight()/2 - h/2;
					y1 = canvas.getImgHeight()/2 + h/2;
				}
				else {
					y0 = canvas.getImgHeight()/4;
					y1 = (canvas.getImgHeight()*3)/4;
					int w = (int)(((double)(y1-y0)*img.getWidth())/img.getHeight()); 
					y0 = canvas.getImgWidth()/2 - w/2;
					y1 = canvas.getImgWidth()/2 + w/2;
				}
				this.calculateDeletePosition();
				return 0;
			}
			catch(IOException e1){
				e1.printStackTrace();
				return -1;
			}
		}
		return -1;
	}
	
	public void render() {
		graphics.clearRect(0, 0, canvas.getImgWidth(), canvas.getImgHeight());
		graphics.drawImage(newImg, x0, y0, x1-x0, y1-y0, null);
		graphics.setPaint(new Color(30,230,10));
		graphics.fillOval(x0-5, y0-5, 10, 10);
		graphics.fillOval(x1-5, y1-5, 10, 10);
		graphics.drawImage(del.getImage(), delx  , dely , 30, 30, null);
		canvas.repaint();
	}
	
	public boolean isAddingImage() {
		return addingImage;
	}
	
	
	
	public void pressed(int x,int y) {
		if(x >= delx && y >= dely && x <= delx+30 && y <= dely+30) {
			deleteThisImage();
		}
		else if(x < x0 +5 && x > x0 -5 && y > y0 - 5 && y < y0 + 5) {
			_0changeSelected = true;
		}
		else if(x < x1 +5 && x > x1 -5 && y > y1 - 5 && y < y1 + 5) {
			_1changeSelected = true;
		}
		else if(x < x1 && y < y1 && x > x0 && y > y0) {
				_imgchangeSelected = true;
		}
		else {
			confirm();
		}
	}
	
	public void released() {
		_0changeSelected = false;
		_1changeSelected = false;
		_imgchangeSelected = false;
	}
	
	public void dragged(int xp,int yp,int x,int y) {
		if(_0changeSelected == true) {
			x0 = x;
			y0 = y;
			this.calculateDeletePosition();
		}
		else if(_1changeSelected == true) {
			x1 = x;
			y1 = y;
			this.calculateDeletePosition();
		}
		else if(_imgchangeSelected == true) {
			x0 += x-xp;
			y0 += y-yp;
			x1 += x-xp;
			y1 += y-yp;
			this.calculateDeletePosition();
		}
		render();
	}
	
	private void calculateDeletePosition() {
		if( y0 > 50 && x0 < x1 && y0 < y1 ) {
			delx = x0 + 10;
			dely = y0 - 40;
		}
		else if(y0 < 50 && x0 < 50) {
			delx = 50;
			dely = 10; 
		}
		else if(y0 < 50) {
			delx = x0+10;
			dely = 10; 
		}
		else if(x0 < 50) {
			delx = 50;
			dely = y0-040; 
		}
		else if(x0 > x1 && y0 < y1) {
			delx = x1 ;
			dely = y0 - 40;
		}
		else if(x0 < x1 && y0 > y1) {
			delx = x0 ;
			dely = y1 - 40;
		}
		else {
			delx = x1;
			dely = y1 -40;
		}
	}
	public void deleteThisImage() {
		img = null;
		graphics = null;
		newImg = null;
		addingImage = false;
		_0changeSelected = false;
		_1changeSelected = false;
		_imgchangeSelected = false;
	}
	
	public void confirm() {
		graphics.clearRect(0, 0, canvas.getImgWidth(), canvas.getImgHeight());
		graphics.drawImage(newImg, x0, y0, x1-x0, y1-y0, null);
		canvas.mergeAddingImage();
		addingImage = false;
		this.deleteThisImage();
	}
	
	public void pasteImage(BufferedImage im) {
		options.setValue(6);
		canvas.setCursor(new Cursor(Cursor.HAND_CURSOR));
		options.unselectAll();
		this.select();
		newImg = im;
		img = new BufferedImage(canvas.getImgWidth(), canvas.getImgHeight(), BufferedImage.TYPE_INT_ARGB);
		graphics = img.createGraphics();
		graphics.setBackground(new Color(255,255,255,0));
		if(img.getWidth() > img.getHeight()) {
			x0 = canvas.getImgWidth()/4;
			x1 = (canvas.getImgWidth()*3)/4;
			int h = (int)(((double)(x1-x0)*img.getHeight())/img.getWidth()); 
			y0 = canvas.getImgHeight()/2 - h/2;
			y1 = canvas.getImgHeight()/2 + h/2;
		}
		else {
			y0 = canvas.getImgHeight()/4;
			y1 = (canvas.getImgHeight()*3)/4;
			int w = (int)(((double)(y1-y0)*img.getWidth())/img.getHeight()); 
			y0 = canvas.getImgWidth()/2 - w/2;
			y1 = canvas.getImgWidth()/2 + w/2;
		}
		this.calculateDeletePosition();
		addingImage = true;
		render();
	}
	
}