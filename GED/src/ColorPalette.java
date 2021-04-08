import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

interface ColorChangeListener{
	void colorChanged();
}

@SuppressWarnings("serial")
public class ColorPalette extends JPanel{
	
	private List<ColorChangeListener> listeners = new ArrayList<ColorChangeListener>();
	
	private int pointer = 0;
	
	private int total = 14;
	
	private Color [] myColors;
	
	private JLabel[] myColorsDisplay;
	
	private Border border;
	
	private JButton intensity;
	
	private int alpha ;
	
	public ColorPalette() {
		
		border = BorderFactory.createBevelBorder(1, Color.white, Color.black, Color.black, Color.white);
		
		this.setPreferredSize(new Dimension(80,220));
		this.setBackground(new Color(50,50,50));
		this.setOpaque(true);
		this.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 2));
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		intensity = new JButton("Alpha");
		intensity.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		intensity.setPreferredSize(new Dimension(70,20));
		intensity.setFocusable(false);
		intensity.setBackground(new Color(255,0,0,150));
		intensity.setCursor(new Cursor(Cursor.HAND_CURSOR));
		intensity.setForeground(Color.white);
		intensity.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new IntensitySlider();				
			}
			
		});
		createMyColors();
		
		myColorsDisplay = new JLabel[total];
		for(int i=0;i<total;i++) {
			myColorsDisplay[i] = new JLabel();
			myColorsDisplay[i].setPreferredSize(new Dimension(30,30));
			myColorsDisplay[i].setBackground(myColors[i]);
			myColorsDisplay[i].setOpaque(true);
			myColorsDisplay[i].repaint();
		}
		addMouseListeners();
		
		alpha = 255;
	
		this.add(intensity);
		
		myColorsDisplay[pointer].setBorder(border);
		for(int i=0;i<total;i++) {
			this.add(myColorsDisplay[i]);
		}
		
	}
	
	public void addColorChangerListener(ColorChangeListener toAdd) {
        listeners.add(toAdd);
    }
	
	public Color getPrimaryColor() {
		Color temp = myColors[pointer];
		return new Color(temp.getRed(),temp.getGreen(),temp.getBlue(),alpha);
	}
	
	private void createMyColors() { 
		myColors = new Color[total];
		myColors[0] = Color.black;
		myColors[1] = Color.white;
		myColors[2] = new Color(53,182,202);
		myColors[3] = new Color(1,145,180);
		myColors[4] = new Color(248,217,15);
		myColors[5] = new Color(211,221,224);
		myColors[6] = new Color(254,122,21);
		myColors[7] = new Color(126,150,128);
		myColors[8] = new Color(121,97,111);
		myColors[9] = new Color(174,99,120);
		myColors[10] = new Color(216,127,129);
		myColors[11] = new Color(234,181,149);
		myColors[12] = Color.red;
		myColors[13] = Color.green;
	}
	
	public void setAlpha(int x) {
		alpha = x;
		for (ColorChangeListener hl : listeners)
            hl.colorChanged();
	}
	
	public void setColorPointer(int x) {
		if(x>=0 && x<14) {
			myColorsDisplay[pointer].setBorder(null);
			pointer = x;
			myColorsDisplay[pointer].setBorder(border);
			for (ColorChangeListener hl : listeners)
	            hl.colorChanged();
		}
	}
	
	private void addMouseListeners() {
		myColorsDisplay[0].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				setColorPointer(0);
			}
		});
		myColorsDisplay[1].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				setColorPointer(1);
			}
		});
		myColorsDisplay[2].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				setColorPointer(2);
			}
		});
		myColorsDisplay[3].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				setColorPointer(3);
			}
		});
		myColorsDisplay[4].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				setColorPointer(4);
			}
		});
		myColorsDisplay[5].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				setColorPointer(5);
			}
		});
		myColorsDisplay[6].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				setColorPointer(6);
			}
		});
		myColorsDisplay[7].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				setColorPointer(7);
			}
		});
		myColorsDisplay[8].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				setColorPointer(8);
			}
		});
		myColorsDisplay[9].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				setColorPointer(9);
			}
		});
		myColorsDisplay[10].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				setColorPointer(10);
			}
		});
		myColorsDisplay[11].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				setColorPointer(11);
			}
		});
		myColorsDisplay[12].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				setColorPointer(12);
			}
		});
		myColorsDisplay[13].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				setColorPointer(13);
			}
		});
	}
	
	private class IntensitySlider extends JFrame{
		JPanel panel;
		JSlider slider;
		JLabel label;
		public IntensitySlider() {
			this.setTitle("Alpha");
			label = new JLabel() {
	            @Override
	            protected void paintComponent(Graphics grphcs) {
	                super.paintComponent(grphcs);
	                Graphics2D g2d = (Graphics2D) grphcs;
	                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	                Color temp = getPrimaryColor();
	                g2d.setColor(new Color(temp.getRed(),temp.getGreen(),temp.getBlue(),alpha));
	                g2d.fillOval(50, 0,50,50);
	                g2d.dispose();
	            }

	            @Override
	            public Dimension getPreferredSize() {
	                return new Dimension(50, 50);
	            }
	        };
	        label.setBackground(new Color(50,50,50));
			panel = new JPanel();
			panel.setLayout(new BorderLayout(10,10));
			panel.setBackground(new Color(50,50,50));
            slider = new JSlider(0,255,alpha);
            slider.setBackground(new Color(50,50,50));
            slider.setForeground(Color.red);
            slider.setFocusable(false);
            slider.setUI(new GEDSliderUI(slider));
            slider.setPaintTicks(true);
            slider.setMinorTickSpacing(25);
    		slider.setPaintTrack(true);
    		slider.setMajorTickSpacing(255);
    		slider.setPaintLabels(true);
    		slider.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					// TODO Auto-generated method stub
					alpha = slider.getValue();
					label.repaint();
					for (ColorChangeListener hl : listeners)
			            hl.colorChanged();
				}
            });
    		panel.add(label,BorderLayout.NORTH);
            panel.add(slider,BorderLayout.SOUTH);
            panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            this.add(panel);
            this.setResizable(false);
            this.pack();
            this.setLocation(ColorPalette.this.getLocationOnScreen().x,ColorPalette.this.getLocationOnScreen().y+50);
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
