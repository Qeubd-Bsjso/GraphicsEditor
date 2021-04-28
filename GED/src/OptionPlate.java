import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OptionPlate extends JPanel{
	Canvas canvas;
	ColorPalette palette;
	
	Pen pen;
	Eraser eraser;
	Line line;
	Rectangle rectangle;
	Ellipse ellipse;
	AddImage addImage;
	
	int value ;
	
	
	OptionPlate(){
		this.setPreferredSize(new Dimension(80,180));
		this.setBackground(new Color(50,50,50));
		this.setOpaque(true);
		this.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 2));
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		value = 0;
		
		pen = new Pen();
		pen.bindOptionPlate(this);
		
		eraser = new Eraser();
		eraser.bindOptionPlate(this);

		line = new Line();		
		line.bindOptionPlate(this);
		
		rectangle = new Rectangle();		
		rectangle.bindOptionPlate(this);
		
		ellipse = new Ellipse();		
		ellipse.bindOptionPlate(this);
		
		addImage = new AddImage();
		addImage.bindOptionPlate(this);
		
		this.add(pen);
		this.add(eraser);
		this.add(line);
		this.add(rectangle);
		this.add(ellipse);
		this.add(addImage);
	}
	
	public void bindCanvas(Canvas c) {
		canvas = c;
		pen.bindCanvas(c);
		eraser.bindCanvas(c);
		line.bindCanvas(c);
		rectangle.bindCanvas(c);
		ellipse.bindCanvas(c);
		addImage.bindCanvas(c);
	}
	
	public void bindColorPalette(ColorPalette cp) {
		palette = cp;
		pen.bindColorPalette(cp);
		line.bindColorPalette(cp);
		rectangle.bindColorPalette(cp);
		ellipse.bindColorPalette(cp);
	}
	
	public int getTool() {
		return value;
	}
	
	public void resetTool() {
		value = 0;
	}
	
	public void setValue(int a) {
		value = a;
	}
	
	public void unselectAll() {
		pen.unselect();
		eraser.unselect();
		line.unselect();
		rectangle.unselect();
		ellipse.unselect();
		addImage.unselect();
	}
	
	public int getEraserSize() {
		return eraser.getEraserSize();
	}
	
	public int getPenSize() {
		return pen.getPenSize();
	}
	
	public int getLineWidth() {
		return line.getLineWidth();
	}
	
	public int getRectangleLineWidth() {
		return rectangle.getLineWidth();
	}
	
	public int getEllipseLineWidth() {
		return ellipse.getLineWidth();
	}
	
	public Color getRectangleFillColor() {
		return rectangle.getFillColor();
	}
	
	public Color getEllipseFillColor() {
		return ellipse.getFillColor();
	}
	
	public BufferedImage getAddImage() {
		return addImage.img;
	}
	
	public boolean isAddingImage() {
		return addImage.isAddingImage();
	}
	
	public void addImagePressed(int x,int y) {
		addImage.pressed(x, y);
	}
	
	public void addImageReleased() {
		addImage.released();
	}
	
	public void addImageDragged(int xp,int yp,int x,int y) {
		addImage.dragged(xp,yp,x, y);
	}
	
	public void confirmAddingImage() {
		addImage.confirm();
	}
	
	public void pasteImage(BufferedImage i) {
		addImage.pasteImage(i);
	}
	
}
