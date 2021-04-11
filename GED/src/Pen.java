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
public class Pen extends JLabel implements MouseListener , ColorChangeListener{
	private int penSize;
	private Canvas canvas;
	private ColorPalette palette;
	private OptionPlate options;
	private Cursor cursor;
	private boolean selected;
	private SizeSlider sizeSlider;
	
	public Pen(){
		this.setIcon(new ImageIcon("images/options/pen.png"));
		selected = false;
		this.setBackground(Color.green);
		this.setPreferredSize(new Dimension(30,30));
		this.setFocusable(false);
		this.setToolTipText("Pen");
		this.addMouseListener(this);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.penSize = 5;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image pencilCursorImage = toolkit.getImage("images/cursors/pencil.png");
		cursor = toolkit.createCustomCursor(pencilCursorImage, new Point(0,19), "pen cursor");
		sizeSlider = null;
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
	
	public void bindColorPalette(ColorPalette cp) {
		palette = cp;
		palette.addColorChangerListener(this);
	}
	
	public int getPenSize() {
		return this.penSize;
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
            sizeSlider = new SizeSlider();
         }
		options.setValue(1);
		canvas.setCursor(cursor);
		options.unselectAll();
		this.select();
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
	                g2d.setColor(palette.getPrimaryColor());
	                g2d.fillOval(25-penSize/2,25-penSize/2,penSize,penSize);
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
            slider = new JSlider(1,50,penSize);
            slider.setBackground(new Color(50,50,50));
            slider.setForeground(Color.red);
            slider.setFocusable(false);
            slider.setUI(new GEDSliderUI(slider));
            slider.setPaintTicks(true);
            slider.setMinorTickSpacing(3);
    		slider.setPaintTrack(true);
    		slider.setMajorTickSpacing(49);
    		slider.setPaintLabels(true);
    		slider.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					// TODO Auto-generated method stub
					penSize = slider.getValue();
					canvas.setCursor(cursor);
					options.setValue(1);
					options.unselectAll();
					Pen.this.select();
					label2.repaint();
				}
            });
    		label1.setIcon(new ImageIcon("images/options/penLogo.png"));
    		panel.add(label1,BorderLayout.WEST);
    		panel.add(label2,BorderLayout.EAST);
            panel.add(slider,BorderLayout.SOUTH);
            panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            this.add(panel);
            this.setResizable(false);
            this.pack();
            this.setLocation(Pen.this.getLocationOnScreen().x,Pen.this.getLocationOnScreen().y+50);
            this.setVisible(true);
		}
	}

	@Override
	public void colorChanged() {
		// TODO Auto-generated method stub
		if(sizeSlider != null)
			sizeSlider.label2.repaint();
	}
	
}
