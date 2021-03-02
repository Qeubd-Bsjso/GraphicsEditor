import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AboutGeD {
	AboutGeD(){
		JFrame about=new JFrame("About GeD");
		about.setSize(420,380);
		about.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JTextArea textArea = new JTextArea("Can not load File !!! Sorry");
		textArea.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
		textArea.setForeground(new Color(0x201f14));
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		
		File file = new File("docs/about.txt");
		
		try{
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			textArea.read(input, "READING FILE :-)");
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
		
		panel.add(scrollPane,BorderLayout.CENTER);
		
		about.add(panel);
		
		about.setVisible(true);
	}
}
