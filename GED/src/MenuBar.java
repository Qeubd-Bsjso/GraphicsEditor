import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;

public class MenuBar extends JMenuBar implements ActionListener{
	JMenu fileMenu;
	JMenu editMenu;
	JMenu insertMenu;
	JMenu helpMenu;
	
	// file menu
	JMenuItem newItem;
	JMenuItem loadItem;
	JMenuItem saveItem;
	JMenuItem clearItem;
	JMenuItem exitItem;
	
	// edit menu
	JMenuItem undoItem;
	JMenuItem redoItem;
	JMenuItem cutItem;
	JMenuItem copyItem;
	JMenuItem pasteItem;
	
	// insert menu
	JMenuItem insertImageItem;
	JMenuItem insertTextItem;
	
	//help menu
	JMenuItem userGuideItem;
	JMenuItem aboutGeDItem;
	MenuBar(){
		this.setFocusable(false);
		
		// creating  menus
		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");
		insertMenu = new JMenu("Insert");
		helpMenu = new JMenu("Help");
		
		//setting mnemonics(shortcuts) for menus
		fileMenu.setMnemonic(KeyEvent.VK_F);	//Alt+F
		editMenu.setMnemonic(KeyEvent.VK_E);	//Alt+E
		helpMenu.setMnemonic(KeyEvent.VK_H);	//Alt+H
		
		// creating items for file menu
		newItem = new JMenuItem("New");
		loadItem = new JMenuItem("Load");
		saveItem = new JMenuItem("Save");
		clearItem = new JMenuItem("Clear");
		exitItem = new JMenuItem("Exit");

		// Setting Workings for the menuItems for file menu 
		newItem.addActionListener(e->{
							System.out.println("New");
						});
		loadItem.addActionListener(e->{
							JFileChooser fileChooser = new JFileChooser();
							fileChooser.setCurrentDirectory(new File("./files"));
							fileChooser.setFileFilter(new FileFilter() {
									    public String getDescription() {
									        return "jpg fiels (*.jpg)";
									    }
									 
									    public boolean accept(File f) {
									        if (f.isDirectory()) {
									            return true;
									        } else {
									            return f.getName().toLowerCase().endsWith(".jpg");
									        }
									    }
									});
							fileChooser.showOpenDialog(null);
						});
		saveItem.addActionListener(e->{
							JFileChooser fileChooser = new JFileChooser();
							fileChooser.setCurrentDirectory(new File("./files"));
							fileChooser.showSaveDialog(null);
						});
		exitItem.addActionListener(e->{
							System.exit(0);
						});
		clearItem.addActionListener(e->{
							System.out.println("Clear");
						});
		
			
		// setting mnemonics (shortcuts) for menuItems for file menu 
		newItem.setMnemonic(KeyEvent.VK_N);
		loadItem.setMnemonic(KeyEvent.VK_L);
		saveItem.setMnemonic(KeyEvent.VK_S);
		exitItem.setMnemonic(KeyEvent.VK_X);
		
		// Adding items to file menu 
		fileMenu.add(newItem);
		fileMenu.add(loadItem);
		fileMenu.add(saveItem);
		fileMenu.add(clearItem);
		fileMenu.add(exitItem);
		
		// creating items for edit menu
		undoItem = new JMenuItem("Undo");
		redoItem = new JMenuItem("Redo");
		cutItem = new JMenuItem("Cut");
		copyItem = new JMenuItem("Copy");
		pasteItem = new JMenuItem("Paste");
		
		// Setting working for Edit menu Items
		undoItem.addActionListener(e->{
							System.out.println("Undo");
						});
		redoItem.addActionListener(e->{
							System.out.println("Redo");
						});
		cutItem.addActionListener(e->{
							System.out.println("Cut");
						});
		copyItem.addActionListener(e->{
							System.out.println("Copy");
						});
		pasteItem.addActionListener(e->{
							System.out.println("Paste");
						});
		
		// adding items to Edit menu
		editMenu.add(undoItem);
		editMenu.add(redoItem);
		editMenu.add(cutItem);
		editMenu.add(copyItem);
		editMenu.add(pasteItem);
		
		// creating items for insert menu
		insertImageItem = new JMenuItem("Image");
		insertTextItem = new JMenuItem("Text");
		
		// setting working for items in insert menu
		insertImageItem.addActionListener(e->{
							System.out.println("Add Image");
						});
		insertTextItem.addActionListener(e->{
							System.out.println("Add Text");
						});
		
		// Adding items in insert menu
		insertMenu.add(insertImageItem);
		insertMenu.add(insertTextItem);
		
		// creating items for help menu
		userGuideItem = new JMenuItem("User Guide");
		aboutGeDItem = new JMenuItem("About GeD");
		
		// setting working for items in help menu
		userGuideItem.addActionListener(e->{
							new UserGuide();
						});
		aboutGeDItem.addActionListener(e->{
							new AboutGeD();
						});
		
		// Adding items to help menu
		helpMenu.add(userGuideItem);
		helpMenu.add(aboutGeDItem);

		// adding items to menu bar 
		this.add(fileMenu);
		this.add(editMenu);
		this.add(insertMenu);
		this.add(helpMenu);
	}
	
	public void actionPerformed(ActionEvent e){
		
	}
	
}