import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class OptionPlate extends JPanel{
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
		pen.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
                    JFrame frame = new JFrame("Pen Size");
                    JPanel sizePanel = new JPanel();
                    JSlider slider = new JSlider(1,50,penSize);
                    slider.setPaintTicks(true);
                    slider.setMinorTickSpacing(3);
            		slider.setPaintTrack(true);
            		slider.setMajorTickSpacing(49);
            		slider.setPaintLabels(true);
            		slider.setPaintLabels(true);
                    slider.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent arg0) {
							// TODO Auto-generated method stub
							penSize = slider.getValue();
							canvas.setCursor(pencilCursor);
							value = 1;
						}
                    });
                    sizePanel.add(slider);
                    frame.add(sizePanel);
                    frame.setResizable(false);
                    frame.pack();
                    //frame.setLocationRelativeTo(pen);
                    frame.setLocation(pen.getLocationOnScreen().x,pen.getLocationOnScreen().y+50);
                    frame.setVisible(true);
                 }
				value = 1;
				canvas.setCursor(pencilCursor);
			}
		});
		penSize = 1;
		
		eraser = new JButton(new ImageIcon("images/options/eraser.png"));
		eraser.setPreferredSize(new Dimension(30,30));
		eraser.setFocusable(false);
		eraser.setToolTipText("Eraser");
		eraser.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
                    JFrame frame = new JFrame("Eraser Size");
                    JPanel sizePanel = new JPanel();
                    JSlider slider = new JSlider(5,50,eraserSize);
                    slider.setPaintTicks(true);
                    slider.setMinorTickSpacing(3);
            		slider.setPaintTrack(true);
            		slider.setMajorTickSpacing(5);
                    slider.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent arg0) {
							// TODO Auto-generated method stub
							eraserSize = slider.getValue();
							Toolkit toolkit = Toolkit.getDefaultToolkit();
							if(eraserSize<10) {
								Image eraserCursorImage = toolkit.getImage("images/cursors/eraser1.png");
								eraserCursor = toolkit.createCustomCursor(eraserCursorImage, new Point(eraserSize/2,eraserSize/2), "eraser cursor");
								canvas.setCursor(eraserCursor);
								value = 2;
							}
							else if(eraserSize<15) {
								Image eraserCursorImage = toolkit.getImage("images/cursors/eraser2.png");
								eraserCursor = toolkit.createCustomCursor(eraserCursorImage, new Point(eraserSize/2,eraserSize/2), "eraser cursor");
								canvas.setCursor(eraserCursor);
								value = 2;
							}
							else if(eraserSize<20) {
								Image eraserCursorImage = toolkit.getImage("images/cursors/eraser3.png");
								eraserCursor = toolkit.createCustomCursor(eraserCursorImage, new Point(eraserSize/2,eraserSize/2), "eraser cursor");
								canvas.setCursor(eraserCursor);
								value = 2;
							}
							else if(eraserSize<25) {
								Image eraserCursorImage = toolkit.getImage("images/cursors/eraser4.png");
								eraserCursor = toolkit.createCustomCursor(eraserCursorImage, new Point(eraserSize/2,eraserSize/2), "eraser cursor");
								canvas.setCursor(eraserCursor);
								value = 2;
							}
							else if(eraserSize<30)	{
								Image eraserCursorImage = toolkit.getImage("images/cursors/eraser5.png");
								eraserCursor = toolkit.createCustomCursor(eraserCursorImage, new Point(eraserSize/2,eraserSize/2), "eraser cursor");
								canvas.setCursor(eraserCursor);
								value = 2;
							}
							else if(eraserSize<35) {
								Image eraserCursorImage = toolkit.getImage("images/cursors/eraser6.png");
								eraserCursor = toolkit.createCustomCursor(eraserCursorImage, new Point(eraserSize/2,eraserSize/2), "eraser cursor");
								canvas.setCursor(eraserCursor);
								value = 2;
							}
							else if(eraserSize<40) {
								Image eraserCursorImage = toolkit.getImage("images/cursors/eraser7.png");
								eraserCursor = toolkit.createCustomCursor(eraserCursorImage, new Point(eraserSize/2,eraserSize/2), "eraser cursor");
								canvas.setCursor(eraserCursor);
								value = 2;
							}
							else if(eraserSize<45) {
								Image eraserCursorImage = toolkit.getImage("images/cursors/eraser8.png");
								eraserCursor = toolkit.createCustomCursor(eraserCursorImage, new Point(eraserSize/2,eraserSize/2), "eraser cursor");
								canvas.setCursor(eraserCursor);
								value = 2;
							}
							else {
								Image eraserCursorImage = toolkit.getImage("images/cursors/eraser9.png");
								eraserCursor = toolkit.createCustomCursor(eraserCursorImage, new Point(eraserSize/2,eraserSize/2), "eraser cursor");
								canvas.setCursor(eraserCursor);
								value = 2;
							}
						}
                    });
                    sizePanel.add(slider);
                    frame.add(sizePanel);
                    frame.setResizable(false);
                    frame.pack();
                    frame.setLocation(eraser.getLocationOnScreen().x,eraser.getLocationOnScreen().y+50);
                    frame.setVisible(true);
                 }
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
		pencilCursor = toolkit.createCustomCursor(pencilCursorImage, new Point(0,10), "pen cursor");
		Image eraserCursorImage = toolkit.getImage("images/cursors/eraser1.png");
		eraserCursor = toolkit.createCustomCursor(eraserCursorImage, new Point(eraserSize/2,eraserSize/2), "eraser cursor");

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
	
	public int getPenSize() {
		return penSize;
	}
	
	public Color currentColor() {
		return foregroundColor;
	}
}
