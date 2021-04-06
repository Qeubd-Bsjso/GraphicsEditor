import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class ColorPalette extends JPanel{
	
	private int pointer = 0;
	
	private int total = 14;
	
	private Color [] myColors;
	
	private JLabel[] myColorsDisplay;
	
	Border border;
	
	public ColorPalette() {
		
		border = BorderFactory.createBevelBorder(1, Color.white, Color.black, Color.black, Color.white);
		
		this.setPreferredSize(new Dimension(80,220));
		this.setBackground(new Color(50,50,50));
		this.setOpaque(true);
		this.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 2));
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
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
		
		myColorsDisplay[pointer].setBorder(border);
		for(int i=0;i<total;i++) {
			this.add(myColorsDisplay[i]);
		}
		
	}
	
	public Color getPrimaryColor() {
		return myColors[pointer];
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
	
	private void addMouseListeners() {
		myColorsDisplay[0].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				myColorsDisplay[pointer].setBorder(null);
				pointer = 0;
				myColorsDisplay[pointer].setBorder(border);
			}
		});
		myColorsDisplay[1].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				myColorsDisplay[pointer].setBorder(null);
				pointer = 1;
				myColorsDisplay[pointer].setBorder(border);
			}
		});
		myColorsDisplay[2].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				myColorsDisplay[pointer].setBorder(null);
				pointer = 2;
				myColorsDisplay[pointer].setBorder(border);
			}
		});
		myColorsDisplay[3].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				myColorsDisplay[pointer].setBorder(null);
				pointer = 3;
				myColorsDisplay[pointer].setBorder(border);
			}
		});
		myColorsDisplay[4].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				myColorsDisplay[pointer].setBorder(null);
				pointer = 4;
				myColorsDisplay[pointer].setBorder(border);
			}
		});
		myColorsDisplay[5].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				myColorsDisplay[pointer].setBorder(null);
				pointer = 5;
				myColorsDisplay[pointer].setBorder(border);
			}
		});
		myColorsDisplay[6].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				myColorsDisplay[pointer].setBorder(null);
				pointer = 6;
				myColorsDisplay[pointer].setBorder(border);
			}
		});
		myColorsDisplay[7].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				myColorsDisplay[pointer].setBorder(null);
				pointer = 7;
				myColorsDisplay[pointer].setBorder(border);
			}
		});
		myColorsDisplay[8].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				myColorsDisplay[pointer].setBorder(null);
				pointer = 8;
				myColorsDisplay[pointer].setBorder(border);
			}
		});
		myColorsDisplay[9].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				myColorsDisplay[pointer].setBorder(null);
				pointer = 9;
				myColorsDisplay[pointer].setBorder(border);
			}
		});
		myColorsDisplay[10].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				myColorsDisplay[pointer].setBorder(null);
				pointer = 10;
				myColorsDisplay[pointer].setBorder(border);
			}
		});
		myColorsDisplay[11].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				myColorsDisplay[pointer].setBorder(null);
				pointer = 11;
				myColorsDisplay[pointer].setBorder(border);
			}
		});
		myColorsDisplay[12].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				myColorsDisplay[pointer].setBorder(null);
				pointer = 12;
				myColorsDisplay[pointer].setBorder(border);
			}
		});
		myColorsDisplay[13].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {	
				myColorsDisplay[pointer].setBorder(null);
				pointer = 13;
				myColorsDisplay[pointer].setBorder(border);
			}
		});
	}
}
