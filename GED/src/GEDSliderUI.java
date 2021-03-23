import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicSliderUI;

public class GEDSliderUI extends BasicSliderUI{

	private BasicStroke stroke = new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	
	public GEDSliderUI(JSlider b) {
		super(b);
		// TODO Auto-generated constructor stub
	}

    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Stroke old = g2d.getStroke();
        g2d.setStroke(stroke);
        g2d.setPaint(new Color(247,133,52));
        if (slider.getOrientation() == SwingConstants.HORIZONTAL) {
            g2d.drawLine(trackRect.x, trackRect.y + trackRect.height / 2, 
                    trackRect.x + trackRect.width, trackRect.y + trackRect.height / 2);
        } else {
            g2d.drawLine(trackRect.x + trackRect.width / 2, trackRect.y, 
                    trackRect.x + trackRect.width / 2, trackRect.y + trackRect.height);
        }
        g2d.setStroke(old);
    }

    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Stroke old = g2d.getStroke();
        g2d.setStroke(new BasicStroke(2f));
        g2d.setPaint(new Color(8, 122, 203));
        g2d.fillOval(thumbRect.x, thumbRect.y + thumbRect.height/2 - thumbRect.width/2, thumbRect.width, thumbRect.width);
        g2d.setStroke(old);
    }


}
