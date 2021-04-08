import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
public class Line extends JLabel implements MouseListener , ColorChangeListener{
	private int lineWidth;
	private Canvas canvas;
	private ColorPalette palette;
	private OptionPlate options;
	private Cursor cursor;
	private boolean selected;
	private SizeSlider sizeSlider;
	
	public Line(){
		this.setIcon(new ImageIcon("images/options/line.png"));
		selected = false;
		this.setBackground(Color.green);
		this.setPreferredSize(new Dimension(30,30));
		this.setFocusable(false);
		this.setToolTipText("Line");
		this.addMouseListener(this);
		this.lineWidth = 5;
		cursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
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
	
	public int getLineWidth() {
		return this.lineWidth;
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
		options.setValue(3);
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
			this.setTitle("Choose Line Width");
			label = new JLabel() {
	            @Override
	            protected void paintComponent(Graphics grphcs) {
	                super.paintComponent(grphcs);
	                Graphics2D g2d = (Graphics2D) grphcs;
	                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	                g2d.setColor(palette.getPrimaryColor());
	                g2d.setStroke(new BasicStroke(lineWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
	                g2d.drawLine(lineWidth, lineWidth, label.getWidth()-lineWidth, label.getHeight()-lineWidth);
	            }

	            @Override
	            public Dimension getPreferredSize() {
	                return new Dimension(200, 140);
	            }
	        };
	        label.setBackground(new Color(50,50,50));
			panel = new JPanel();
			panel.setLayout(new BorderLayout(10,10));
			panel.setBackground(new Color(50,50,50));
            slider = new JSlider(1,50,lineWidth);
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
					lineWidth = slider.getValue();
					canvas.setCursor(cursor);
					options.setValue(3);
					options.unselectAll();
					Line.this.select();
					label.repaint();
				}
            });
    		panel.add(label,BorderLayout.EAST);
            panel.add(slider,BorderLayout.SOUTH);
            panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            this.add(panel);
            this.setResizable(false);
            this.pack();
            this.setLocation(Line.this.getLocationOnScreen().x,Line.this.getLocationOnScreen().y+50);
            this.setVisible(true);
		}
	}

	@Override
	public void colorChanged() {
		// TODO Auto-generated method stub
		if(sizeSlider != null)
			sizeSlider.label.repaint();
	}
	
}

