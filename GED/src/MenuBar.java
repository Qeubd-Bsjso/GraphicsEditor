import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar implements ActionListener{
	JMenu fileMenu;
	JMenu editMenu;
	JMenu helpMenu;
	
	JMenuItem newItem;
	JMenuItem loadItem;
	JMenuItem saveItem;
	JMenuItem exitItem;
	MenuBar(){
		this.setFocusable(false);
		
		// creating  menus
		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");
		helpMenu = new JMenu("Help");
		
		//setting mnemonics(shortcuts) for menus
		fileMenu.setMnemonic(KeyEvent.VK_F);	//Alt+F
		editMenu.setMnemonic(KeyEvent.VK_E);	//Alt+E
		helpMenu.setMnemonic(KeyEvent.VK_H);	//Alt+H
		
		// creating items for file menu
		newItem = new JMenuItem("New");
		loadItem = new JMenuItem("Load");
		saveItem = new JMenuItem("Save");
		exitItem = new JMenuItem("Exit");

		// Setting Workings for the menuItems for file menu 
		newItem.addActionListener(this);
		loadItem.addActionListener(this);
		saveItem.addActionListener(this);
		exitItem.addActionListener(this);
		
		// setting mnemonics (shortcuts) for menuItems for file menu 
		newItem.setMnemonic(KeyEvent.VK_N);
		loadItem.setMnemonic(KeyEvent.VK_L);
		saveItem.setMnemonic(KeyEvent.VK_S);
		exitItem.setMnemonic(KeyEvent.VK_X);
		
		// Adding items to file menu 
		fileMenu.add(newItem);
		fileMenu.add(loadItem);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);

		// adding items to menu bar 
		this.add(fileMenu);
		this.add(editMenu);
		this.add(helpMenu);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==newItem) {
			System.out.print("New");
		}
		else if(e.getSource()==loadItem){
			System.out.println("Load");
		}
		else if(e.getSource()==saveItem){
			System.out.println("Save");
		}
		else if(e.getSource()==exitItem){
			System.exit(0);
		}
	}
	
}
