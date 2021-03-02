import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UserGuide {
	JPanel panel;
	UserGuide() {
		JFrame guide=new JFrame("User Guide");
		guide.setSize(420,380);
		guide.setLocationRelativeTo(null);
		
		panel = new JPanel();
		
		
		JTextArea textArea = new JTextArea("HY Fuck Man");
		textArea.setFont(new Font("Serif", Font.PLAIN, 16));
		new JScrollPane(textArea);
		textArea.setEditable(false);
		
		File file = new File("src/docs/guide.txt");
		
		try{
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			textArea.read(input, "READING FILE :-)");
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Shit");
		}
		
		
		
		panel.add(textArea);
		
		guide.add(panel);
		
		guide.setVisible(true);
	}
}