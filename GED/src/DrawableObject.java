import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public abstract class DrawableObject {
	protected int x0 ,y0;
	protected int x1 , y1;
	private Color color;
	private BasicStroke strokeStyle;
	public BufferedImage image;
	protected Graphics2D graphics;
	private Color fillColor;
	private int width;
	private int height;
	DrawableObject (int x,int y, int w, Color c, Color f ,int iw,int ih){	
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
	}
	public void update(int x,int y) {
		x1 = x;
		y1 = y;
	}
	
	protected void clean() {
		graphics.setBackground(new Color(255,255,255,0));
		graphics.clearRect(0, 0, width, height);
	}
	
	protected void outline() {
		graphics.setPaint(color);
	}
	
	protected void surface() {
		graphics.setPaint(fillColor);
	}
	
	
	
}
