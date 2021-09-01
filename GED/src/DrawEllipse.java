import java.awt.Color;

public class DrawEllipse extends DrawableObject{

	DrawEllipse(int x, int y, int w, Color c, Color f, int iw, int ih) {
		super(x, y, w, c, f, iw, ih);
		// TODO Auto-generated constructor stub
	}
	
	public void update(int x,int y) {
		super.update(x,y);
		clean();
		draw();
	}
	
	public void draw() {
		if(x0<=x1) {
			if(y0<=y1) {
				surface();
				graphics.fillOval(x0, y0, x1-x0, y1-y0);
				outline();
				graphics.drawOval(x0, y0, x1-x0, y1-y0);
			}
			else {
				surface();
				graphics.fillOval(x0, y1, x1-x0, y0-y1);
				outline();
				graphics.drawOval(x0, y1, x1-x0, y0-y1);
			}
		}
		else {
			if(y0<=y1) {
				surface();
				graphics.fillOval(x1, y0, x0-x1, y1-y0);
				outline();
				graphics.drawOval(x1, y0, x0-x1, y1-y0);
			}
			else {
				surface();
				graphics.fillOval(x1, y1, x0-x1, y0-y1);
				outline();
				graphics.drawOval(x1, y1, x0-x1, y0-y1);
			}
		}
	}

}
