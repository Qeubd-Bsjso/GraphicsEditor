import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	Canvas canvas;
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
	JMenuItem cutItem;
	JMenuItem copyItem;
	JMenuItem pasteItem;
	
	// insert menu
	JMenuItem insertImageItem;
	JMenuItem insertTextItem;
	
	//help menu
	JMenuItem userGuideItem;
	JMenuItem aboutGeDItem;
	public MenuBar(){
		//this.setFocusable(false);
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
							canvas.createNewFile();
						});
		loadItem.addActionListener(e->{
							JFileChooser fileChooser = new JFileChooser("Choose a .png image");
							fileChooser.setCurrentDirectory(new File("./files"));
							fileChooser.setFileFilter(new FileNameExtensionFilter("*.png", "png"));
							int response = fileChooser.showOpenDialog(null);
							BufferedImage im = null;
							if(response == JFileChooser.APPROVE_OPTION) {
								try {
									File file = fileChooser.getSelectedFile();
									im = ImageIO.read(file); 
									canvas.loadImage(im);
								}
								catch(IOException e1){
									e1.printStackTrace();
								}
							}
							
					});
		saveItem.addActionListener(e->{
						JFileChooser fileChooser = new JFileChooser("Save Image");
						fileChooser.setCurrentDirectory(new File("./files"));
						fileChooser.setFileFilter(new FileNameExtensionFilter("*.png", "png"));
						int response = fileChooser.showSaveDialog(null);
						if(response == JFileChooser.APPROVE_OPTION) {
							File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
							if(file != null) {
								String name = file.getName();
							    String extension = name.substring(1+name.lastIndexOf(".")).toLowerCase();
							    try {
									ImageIO.write(canvas.getSaveImage(), extension, file);
								} catch (IOException e1) {
									e1.printStackTrace();
								}  catch (HeadlessException e2) {
								    e2.printStackTrace();
								} 
							}
						}
				});
		exitItem.addActionListener(e->{
							System.exit(0);
						});
		clearItem.addActionListener(e->{
							canvas.clear();
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
		cutItem = new JMenuItem("Cut");
		copyItem = new JMenuItem("Copy");
		pasteItem = new JMenuItem("Paste");
		
		// Setting working for Edit menu Items
		cutItem.addActionListener(e->{
							System.out.println("Cut");
						});
		copyItem.addActionListener(e->{
							System.out.println("Copy");						
					        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new ImageTransferable(canvas.getSaveImage()),null);					        
						});
		pasteItem.addActionListener(e->{
							System.out.println("Paste");
						});
		
		// adding items to Edit menu
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
	
	public void bindCanvas(Canvas c) {
		canvas = c;
	}
		
	static final class ImageTransferable implements Transferable{
	    final BufferedImage image;

	    public ImageTransferable(final BufferedImage image) {
	        this.image = image;
	    }

	    @Override
	    public DataFlavor[] getTransferDataFlavors() {
	        return new DataFlavor[] {DataFlavor.imageFlavor};
	    }

	    @Override
	    public boolean isDataFlavorSupported(final DataFlavor flavor) {
	        return DataFlavor.imageFlavor.equals(flavor);
	    }

	    @Override
	    public Object getTransferData(final DataFlavor flavor) throws UnsupportedFlavorException, IOException {
	        if (isDataFlavorSupported(flavor)) {
	            return image;
	        }
	        throw new UnsupportedFlavorException(flavor);
	    }
	};
	
}