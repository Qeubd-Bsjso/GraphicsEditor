import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class BottomBar extends JPanel{
	private MyFrame frame;
	
	private int borderPercentage;
	
	private JPanel left;
	private JPanel right;
	
	private JLabel imageSize;
	private JLabel viewport;
	
	private JLabel coordinates;
	private JSlider slider;
	BottomBar (){
		
		borderPercentage = 2;
		
		this.setBackground(new Color(0x555555));
		this.setPreferredSize(new Dimension(50,25));
		this.setOpaque(true);
		//this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setLayout(new BorderLayout());
		
		left = new JPanel();
		left.setOpaque(false);
		left.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		right = new JPanel();
		right.setOpaque(false);
		right.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		imageSize = new JLabel();
		imageSize.setForeground(new Color(0xc0c040));
		imageSize.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		imageSize.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		viewport = new JLabel();
		viewport.setForeground(new Color(0xc0c040));
		viewport.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		viewport.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		coordinates = new JLabel();
		coordinates.setForeground(new Color(0xc0c040));
		coordinates.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		coordinates.setText("x:  y: ");
		
		slider = new JSlider(1,75,76-borderPercentage);
        slider.setBackground(new Color(0x555555));
        slider.setForeground(Color.red);
        slider.setFocusable(false);
        slider.setUI(new GEDSliderUI(slider));
        slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				borderPercentage = 76 - slider.getValue();
				frame.updateCanvasScale();
			}
        });
		
        left.add(slider);
		left.add(coordinates);
		
		right.add(imageSize);
		right.add(viewport);
		
		this.add(left,BorderLayout.WEST);
		this.add(right,BorderLayout.EAST);
	}
	
	public void setCoordinates(int x,int y) {
		if(x>=0) {
			coordinates.setText("x: "+x+" , y: "+y);
		}
		else {
			coordinates.setText("x:  y: ");
		}
		this.repaint();
		
	}
	
	public void bindFrame(MyFrame f) {
		frame = f;
	}
	
	public void setImageSize(int x,int y) {
		imageSize.setText("Image: "+x+"X"+y);
		this.repaint();
	}
	
	public void setViewportSize(int x,int y) {
		viewport.setText("ViewPort: "+x+"X"+y);
		this.repaint();
	}
	
	public int getBorderPercentage() {
		return borderPercentage;
	}
	
}
