import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OptionPlate extends JPanel{
	Canvas canvas;
	
	JLabel selectTool;
	
	Pen pen;
	
	Eraser eraser;
	
	JLabel line;
	JLabel rectangle;
	JLabel elipse;
	
	int value ;
	
	Color foregroundColor;
	
	Cursor pencilCursor;
	Cursor eraserCursor;
	OptionPlate(){
		this.setPreferredSize(new Dimension(80,200));
		this.setBackground(new Color(50,50,50));
		this.setOpaque(true);
		this.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 2));
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		value = 0;
		
		foregroundColor = Color.black;
		
		selectTool = new JLabel(new ImageIcon("images/options/select.png"));
		selectTool.setPreferredSize(new Dimension(30,30));
		selectTool.setFocusable(false);
		selectTool.setToolTipText("Select");
		selectTool.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				value = 0;
				canvas.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		pen = new Pen();
		pen.bindOptionPlate(this);
		
		eraser = new Eraser();
		eraser.bindOptionPlate(this);

		line = new JLabel(new ImageIcon("images/options/line.png"));
		line.setPreferredSize(new Dimension(30,30));
		line.setFocusable(false);
		line.setToolTipText("Line");
		line.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				value = 3;
				canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
		});
		
		
		rectangle = new JLabel(new ImageIcon("images/options/rectangle.png"));
		rectangle.setPreferredSize(new Dimension(30,30));
		rectangle.setFocusable(false);
		rectangle.setToolTipText("Rectangle");
		rectangle.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				value = 4;
				canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
		});
		
		elipse = new JLabel(new ImageIcon("images/options/elipse.png"));
		elipse.setPreferredSize(new Dimension(30,30));
		elipse.setFocusable(false);
		elipse.setToolTipText("Elipse");
		elipse.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				value = 5;
				canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
		});
		
		this.add(selectTool);
		this.add(pen);
		this.add(eraser);
		this.add(line);
		this.add(rectangle);
		this.add(elipse);
	}
	
	public void bindCanvas(Canvas c) {
		canvas = c;
		pen.bindCanvas(c);
		eraser.bindCanvas(c);
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
	}
	
	public int getEraserSize() {
		return eraser.getEraserSize();
	}
	
	public int getPenSize() {
		return pen.getPenSize();
	}
	
	public Color currentColor() {
		return foregroundColor;
	}
}
