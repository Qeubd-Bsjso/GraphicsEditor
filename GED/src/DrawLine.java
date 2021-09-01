import java.awt.Color;

public class DrawLine extends DrawableObject{

	DrawLine(int x, int y, int w, Color c, Color f, int iw, int ih) {
		super(x, y, w, c, f, iw, ih);
		// TODO Auto-generated constructor stub
		clean();
		draw();
	}

	public void update(int x,int y) {
		super.update(x,y);
		clean();
		draw();
	}
	
	private void draw() {
		outline();
		graphics.drawLine(x0, y0, x1, y1);
	}

}
