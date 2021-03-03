import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UserGuide {
	UserGuide() {
		JFrame guide=new JFrame("User Guide");
		guide.setSize(420,380);
		guide.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		JTextArea textArea = new JTextArea("Can not load File !!! Sorry");
		textArea.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
		textArea.setForeground(new Color(0x201f14));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		
		File file = new File("docs/guide.txt");
		
		try{
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			textArea.read(input, "READING FILE :-)");
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
		
		panel.add(scrollPane,BorderLayout.CENTER);
		
		guide.add(panel);
		
		guide.setVisible(true);
	}
}