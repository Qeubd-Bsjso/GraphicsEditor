import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


@SuppressWarnings("serial")
public class Eraser extends JLabel implements MouseListener{
	private int eraserSize;
	private Canvas canvas;
	private OptionPlate options;
	private Cursor []cursor;
	private boolean selected;
	
	public Eraser(){
		this.setIcon(new ImageIcon("images/options/eraser.png"));
		this.setBackground(Color.green);
		this.selected = false;
		this.setPreferredSize(new Dimension(30,30));
		this.setFocusable(false);
		this.setToolTipText("Pen");
		this.addMouseListener(this);
		this.eraserSize = 5;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		cursor = new Cursor[9];
		Image cursorImage = toolkit.getImage("images/cursors/eraser1.png");
		cursor[0] = toolkit.createCustomCursor(cursorImage, new Point(2,2), "eraser cursor");
		cursorImage = toolkit.getImage("images/cursors/eraser2.png");
		cursor[1] = toolkit.createCustomCursor(cursorImage, new Point(6,6), "eraser cursor");
		cursorImage = toolkit.getImage("images/cursors/eraser3.png");
		cursor[2] = toolkit.createCustomCursor(cursorImage, new Point(8,8), "eraser cursor");
		cursorImage = toolkit.getImage("images/cursors/eraser4.png");
		cursor[3] = toolkit.createCustomCursor(cursorImage, new Point(11,11), "eraser cursor");
		cursorImage = toolkit.getImage("images/cursors/eraser5.png");
		cursor[4] = toolkit.createCustomCursor(cursorImage, new Point(13,13), "eraser cursor");
		cursorImage = toolkit.getImage("images/cursors/eraser6.png");
		cursor[5] = toolkit.createCustomCursor(cursorImage, new Point(16,16), "eraser cursor");
		cursorImage = toolkit.getImage("images/cursors/eraser7.png");
		cursor[6] = toolkit.createCustomCursor(cursorImage, new Point(18,18), "eraser cursor");
		cursorImage = toolkit.getImage("images/cursors/eraser8.png");
		cursor[7] = toolkit.createCustomCursor(cursorImage, new Point(21,21), "eraser cursor");
		cursorImage = toolkit.getImage("images/cursors/eraser9.png");
		cursor[8] = toolkit.createCustomCursor(cursorImage, new Point(23,23), "eraser cursor");
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
	
	public int getEraserSize() {
		return this.eraserSize;
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
		if (e.getButton() == MouseEvent.BUTTON3) {
            new SizeSlider();
         }
		options.setValue(2);
		allocateCursor();
		options.unselectAll();
		this.select();
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private void allocateCursor() {
		if(eraserSize<10) {
			canvas.setCursor(cursor[0]);
		}
		else if(eraserSize<15) {
			canvas.setCursor(cursor[1]);
		}
		else if(eraserSize<20) {
			canvas.setCursor(cursor[2]);
		}
		else if(eraserSize<25) {
			canvas.setCursor(cursor[3]);
		}
		else if(eraserSize<30)	{
			canvas.setCursor(cursor[4]);
		}
		else if(eraserSize<35) {
			canvas.setCursor(cursor[5]);
		}
		else if(eraserSize<40) {
			canvas.setCursor(cursor[6]);
		}
		else if(eraserSize<45) {
			canvas.setCursor(cursor[7]);
		}
		else {
			canvas.setCursor(cursor[8]);
		}
	}
	
	private class SizeSlider extends JFrame{
		JPanel panel;
		JSlider slider;
		JLabel label1;
		JLabel label2;
		public SizeSlider() {
			label1 = new JLabel();
			label2 = new JLabel() {
	            @Override
	            protected void paintComponent(Graphics grphcs) {
	                super.paintComponent(grphcs);
	                Graphics2D g2d = (Graphics2D) grphcs;
	                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	                g2d.setColor(Color.white);
	                g2d.fillRect(25-eraserSize/2,25-eraserSize/2,eraserSize,eraserSize);
	            }

	            @Override
	            public Dimension getPreferredSize() {
	                return new Dimension(50, 50);
	            }
	        };
	        label2.setBackground(new Color(50,50,50));
			panel = new JPanel();
			panel.setLayout(new BorderLayout(10,10));
			panel.setBackground(new Color(50,50,50));
            slider = new JSlider(5,50,eraserSize);
            slider.setBackground(new Color(50,50,50));
            slider.setForeground(Color.red);
            slider.setFocusable(false);
            slider.setUI(new GEDSliderUI(slider));
            slider.setPaintTicks(true);
            slider.setMinorTickSpacing(3);
    		slider.setPaintTrack(true);
    		slider.setMajorTickSpacing(45);
    		slider.setPaintLabels(true);
    		slider.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					// TODO Auto-generated method stub
					eraserSize = slider.getValue();
					allocateCursor();
					options.setValue(2);
					options.unselectAll();
					Eraser.this.select();
					label2.repaint();
				}
            });
    		label1.setIcon(new ImageIcon("images/options/eraserLogo.png"));
    		panel.add(label1,BorderLayout.WEST);
    		panel.add(label2,BorderLayout.EAST);
            panel.add(slider,BorderLayout.SOUTH);
            panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            this.add(panel);
            this.setResizable(false);
            this.pack();
            this.setLocation(Eraser.this.getLocationOnScreen().x,Eraser.this.getLocationOnScreen().y+50);
            this.setVisible(true);
            this.addFocusListener(new FocusListener() {

            	private boolean gained = false;
				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub
					gained = true;
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					// TODO Auto-generated method stub
					if(gained) {
						dispose();
					}
				}});
		}
		
	}

}
