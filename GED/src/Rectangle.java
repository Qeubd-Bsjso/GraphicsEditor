import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
public class Rectangle extends JLabel implements MouseListener , ColorChangeListener{
	private int lineWidth;
	private Canvas canvas;
	private ColorPalette palette;
	private OptionPlate options;
	private Cursor cursor;
	private ColorPalette pt;
	private boolean selected;
	
	private SizeSlider sizeSlider;
	
	public Rectangle(){
		this.setIcon(new ImageIcon(getClass().getClassLoader().getResource("options/rectangle.png")));
		selected = false;
		this.setBackground(Color.green);
		this.setPreferredSize(new Dimension(30,30));
		this.setFocusable(false);
		this.setToolTipText("Rectangle");
		this.addMouseListener(this);
		this.lineWidth = 5;
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
		sizeSlider = null;
		
		pt = new ColorPalette();
		pt.setAlpha(0);
		pt.setColorPointer(1);
		
		pt.addColorChangerListener(this);
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
	
	public int getLineWidth() {
		return this.lineWidth;
	}
	
	public Color getFillColor() {
		return pt.getPrimaryColor();
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
		options.setValue(4);
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
		JLabel label;
		public SizeSlider() {
			this.setTitle("Rectangle");
			label = new JLabel() {
	            @Override
	            protected void paintComponent(Graphics grphcs) {
	                super.paintComponent(grphcs);
	                Graphics2D g2d = (Graphics2D) grphcs;
	                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	                g2d.setColor(pt.getPrimaryColor());
	                g2d.fillRect(30,30,240,240);
	                g2d.setColor(palette.getPrimaryColor());
	                g2d.setStroke(new BasicStroke(lineWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
	                g2d.drawRect(30,30,240,240);
	            }

	            @Override
	            public Dimension getPreferredSize() {
	                return new Dimension(300, 300);
	            }
	        };
	        label.setBackground(new Color(50,50,50));
			panel = new JPanel();
			panel.setLayout(new BorderLayout(10,10));
			panel.setBackground(new Color(50,50,50));
            slider = new JSlider(0,50,lineWidth);
            slider.setBackground(new Color(50,50,50));
            slider.setForeground(Color.red);
            slider.setFocusable(false);
            slider.setUI(new GEDSliderUI(slider));
            slider.setPaintTicks(true);
            slider.setMinorTickSpacing(3);
    		slider.setPaintTrack(true);
    		slider.setMajorTickSpacing(50);
    		slider.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					// TODO Auto-generated method stub
					lineWidth = slider.getValue();
					canvas.setCursor(cursor);
					options.setValue(4);
					options.unselectAll();
					Rectangle.this.select();
					label.repaint();
				}
            });
    		panel.add(label,BorderLayout.EAST);
    		panel.add(pt,BorderLayout.WEST);
            panel.add(slider,BorderLayout.SOUTH);
            panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            this.add(panel);
            this.setResizable(false);
            this.pack();
            this.setLocation(Rectangle.this.getLocationOnScreen().x,Rectangle.this.getLocationOnScreen().y+50);
            this.setVisible(true);
		}
	}

	@Override
	public void colorChanged() {
		if(sizeSlider != null) {
			sizeSlider.label.repaint();
		}
	}
	
}