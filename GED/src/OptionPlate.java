import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OptionPlate extends JPanel implements ActionListener{
	Canvas canvas;
	JButton pen;
	int penSize;
	
	
	JButton eraser;
	int eraserSize;
	
	JButton line;
	JButton rectangle;
	JButton elipse;
	int value ;
	
	Color foregroundColor;
	
	Cursor pencilCursor;
	Cursor eraserCursor;
	OptionPlate(){
		this.setPreferredSize(new Dimension(80,100));
		this.setBackground(new Color(0xa0aaaa));
		this.setOpaque(true);
		this.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 2));
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		value = 0;
		
		foregroundColor = Color.black;
		
		pen = new JButton(new ImageIcon("images/options/pen.png"));
		pen.setPreferredSize(new Dimension(30,30));
		pen.setFocusable(false);
		pen.setToolTipText("Pen");
		pen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				value = 1;
				canvas.setCursor(pencilCursor);
			}
		});
		penSize = 1;
		
		eraser = new JButton(new ImageIcon("images/options/eraser.png"));
		eraser.setPreferredSize(new Dimension(30,30));
		eraser.setFocusable(false);
		eraser.setToolTipText("Eraser");
		eraser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				value = 2;
				canvas.setCursor(eraserCursor);
			}
		});
		eraserSize = 5;
		
		line = new JButton(new ImageIcon("images/options/line.png"));
		line.setPreferredSize(new Dimension(30,30));
		line.setFocusable(false);
		line.setToolTipText("Line");
		line.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				value = 3;
				canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
		});
		
		
		rectangle = new JButton(new ImageIcon("images/options/rectangle.png"));
		rectangle.setPreferredSize(new Dimension(30,30));
		rectangle.setFocusable(false);
		rectangle.setToolTipText("Rectangle");
		rectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				value = 4;
				canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
		});
		
		elipse = new JButton(new ImageIcon("images/options/elipse.png"));
		elipse.setPreferredSize(new Dimension(30,30));
		elipse.setFocusable(false);
		elipse.setToolTipText("Elipse");
		elipse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				value = 5;
				canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
		});
		
		this.add(pen);
		this.add(eraser);
		this.add(line);
		this.add(rectangle);
		this.add(elipse);
		
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image pencilCursorImage = toolkit.getImage("images/cursors/pencil.png");
		pencilCursor = toolkit.createCustomCursor(pencilCursorImage, new Point(0,10), "pencil cursor");
		Image eraserCursorImage = toolkit.getImage("images/cursors/eraser.png");
		eraserCursor = toolkit.createCustomCursor(eraserCursorImage, new Point(8,8), "pencil cursor");

	}
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void bindCanvas(Canvas c) {
		canvas = c;
	}
	public int getTool() {
		return value;
	}
	
	public void resetTool() {
		value = 0;
	}
	
	public int getEraserSize() {
		return eraserSize;
	}
	public Color currentColor() {
		return foregroundColor;
	}
}
