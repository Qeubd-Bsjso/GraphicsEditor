import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class DrawableObject {
	private int type ;
	private int x0 ,y0;
	private int x1 , y1;
	private Color color;
	private BasicStroke strokeStyle;
	public BufferedImage image;
	private Graphics2D graphics;
	private Color fillColor;
	private int width;
	private int height;
	DrawableObject (String s,Dimension d,int x,int y, int w, Color c, Color f ,int iw,int ih){	
		if(s == "line")
			type = 1;
		else if(s == "rectangle") 
			type = 2;
		else if(s == "ellipse")
			type = 3;
		 
		if(f == null)
			fillColor = new Color(255,255,255,0);
		else
			fillColor = f;
		x0 = x1 = x;
		y0 = y1 = y;
		
		strokeStyle = new BasicStroke(w,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		
		color = c;
		
		width = iw;
		height = ih;
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		graphics = image.createGraphics();
		graphics.setStroke(strokeStyle);
		update();
	}
	
	
	public void updateP2(int x,int y) {
		x1 = x;
		y1 = y;
		update();
	}
	
	private void update() {
		graphics.setBackground(new Color(255,255,255,0));
		graphics.clearRect(0, 0, width, height);
		switch(type) {
		case 1 :
				line();
				break;
		case 2 :
				rectangle();
				break;
		case 3 :
				ellipse();
				break;
		}
	}
	
	private void line() {
		graphics.setPaint(color);
		graphics.drawLine(x0, y0, x1, y1);
	}
	
	private void rectangle(){
		if(x0<=x1) {
			if(y0<=y1) {
				graphics.setPaint(fillColor);
				graphics.fillRect(x0, y0, x1-x0, y1-y0);
				graphics.setPaint(color);
				graphics.drawRect(x0, y0, x1-x0, y1-y0);
			}
			else {
				graphics.setPaint(fillColor);
				graphics.fillRect(x0, y1, x1-x0, y0-y1);
				graphics.setPaint(color);
				graphics.drawRect(x0, y1, x1-x0, y0-y1);
			}
		}
		else {
			if(y0<=y1) {
				graphics.setPaint(fillColor);
				graphics.fillRect(x1, y0, x0-x1, y1-y0);
				graphics.setPaint(color);
				graphics.drawRect(x1, y0, x0-x1, y1-y0);
			}
			else {
				graphics.setPaint(fillColor);
				graphics.fillRect(x1, y1, x0-x1, y0-y1);
				graphics.setPaint(color);
				graphics.drawRect(x1, y1, x0-x1, y0-y1);
			}
		}
	}
	
	public void ellipse() {
		if(x0<=x1) {
			if(y0<=y1) {
				graphics.setPaint(fillColor);
				graphics.fillOval(x0, y0, x1-x0, y1-y0);
				graphics.setPaint(color);
				graphics.drawOval(x0, y0, x1-x0, y1-y0);
			}
			else {
				graphics.setPaint(fillColor);
				graphics.fillOval(x0, y1, x1-x0, y0-y1);
				graphics.setPaint(color);
				graphics.drawOval(x0, y1, x1-x0, y0-y1);
			}
		}
		else {
			if(y0<=y1) {
				graphics.setPaint(fillColor);
				graphics.fillOval(x1, y0, x0-x1, y1-y0);
				graphics.setPaint(color);
				graphics.drawOval(x1, y0, x0-x1, y1-y0);
			}
			else {
				graphics.setPaint(fillColor);
				graphics.fillOval(x1, y1, x0-x1, y0-y1);
				graphics.setPaint(color);
				graphics.drawOval(x1, y1, x0-x1, y0-y1);
			}
		}
	}
	
}
