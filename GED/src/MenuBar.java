import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuBar extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Canvas canvas;
	JPanel holder;
	
	Menu fileMenu;
	Menu editMenu;
	Menu insertMenu;
	Menu helpMenu;
	
	// file menu
	MenuItem newItem;
	MenuItem loadItem;
	MenuItem saveItem;
	MenuItem clearItem;
	MenuItem exitItem;
	
	// edit menu
	MenuItem cutItem;
	MenuItem copyItem;
	MenuItem pasteItem;
	
	// insert menu
	MenuItem insertImageItem;
	MenuItem insertTextItem;
	
	//help menu
	MenuItem userGuideItem;
	MenuItem aboutGeDItem;
	
	MenuBar(){
		this.setBackground(new Color(0x555555));
		this.setBorder(null);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		// creating  menus
		fileMenu = new Menu("File");
				
		editMenu = new Menu("Edit");
		
		insertMenu = new Menu("Insert");
		
		helpMenu = new Menu("Help");
		 
		// creating items for file menu
		newItem = new MenuItem("New");
		loadItem = new MenuItem("Load");
		saveItem = new MenuItem("Save");
		clearItem = new MenuItem("Clear");
		exitItem = new MenuItem("Exit");

		// Setting Workings for the menuItems for file menu 
		newItem.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				newItem.setForeground(new Color(0xabffd5));
				newItem.repaint();
				canvas.createNewFile();
			}
		});
		loadItem.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				loadItem.setForeground(new Color(0xabffd5));
				loadItem.repaint();
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
			}
		});
		
		saveItem.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				saveItem.setForeground(new Color(0xabffd5));
				saveItem.repaint();
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
			}
		});
		
		exitItem.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				exitItem.setForeground(new Color(0xabffd5));
				exitItem.repaint();
				System.exit(0);
			}
		});
		
		clearItem.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				clearItem.setForeground(new Color(0xabffd5));
				clearItem.repaint();
				canvas.createNewFile();
			}
		});
		
		// Adding items to file menu 
		fileMenu.addMenuItem(newItem);
		fileMenu.addMenuItem(loadItem);
		fileMenu.addMenuItem(saveItem);
		fileMenu.addMenuItem(clearItem);
		fileMenu.addMenuItem(exitItem);
		
		// creating items for edit menu
		cutItem = new MenuItem("Cut");
		copyItem = new MenuItem("Copy");
		pasteItem = new MenuItem("Paste");
		
		// Setting working for Edit menu Items
		cutItem.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				cutItem.setForeground(new Color(0xabffd5));
				cutItem.repaint();
				System.out.println("Cut");
			}
		});
		
		copyItem.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				copyItem.setForeground(new Color(0xabffd5));
				copyItem.repaint();
				System.out.println("Copy");						
		        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new ImageTransferable(canvas.getSaveImage()),null);
			}
		});
		
		pasteItem.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				pasteItem.setForeground(new Color(0xabffd5));
				pasteItem.repaint();
				System.out.println("Paste");
			}
		});
		
		// adding items to Edit menu
		editMenu.addMenuItem(cutItem);
		editMenu.addMenuItem(copyItem);
		editMenu.addMenuItem(pasteItem);
		
		// creating items for insert menu
		insertImageItem = new MenuItem("Image");
		insertTextItem = new MenuItem("Text");
		
		// setting working for items in insert menu
		insertImageItem.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				insertImageItem.setForeground(new Color(0xabffd5));
				insertImageItem.repaint();
				System.out.println("Add Image");
			}
		});
		
		insertTextItem.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				insertTextItem.setForeground(new Color(0xabffd5));
				insertTextItem.repaint();
				System.out.println("Add Text");
			}
		});
		
		// Adding items in insert menu
		insertMenu.addMenuItem(insertImageItem);
		insertMenu.addMenuItem(insertTextItem);
		
		// creating items for help menu
		userGuideItem = new MenuItem("User Guide");
		aboutGeDItem = new MenuItem("About GeD");
		
		// setting working for items in help menu
		userGuideItem.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				userGuideItem.setForeground(new Color(0xabffd5));
				userGuideItem.repaint();
				new UserGuide();
			}
		});
		
		aboutGeDItem.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				aboutGeDItem.setForeground(new Color(0xabffd5));
				aboutGeDItem.repaint();
				new AboutGeD();
			}
		});
		
		// Adding items to help menu
		helpMenu.addMenuItem(userGuideItem);
		helpMenu.addMenuItem(aboutGeDItem);

		// adding items to menu bar 
		this.add(fileMenu);
		this.add(editMenu);
		this.add(insertMenu);
		this.add(helpMenu);
	}
	
	public void bindCanvas(Canvas c) {
		canvas = c;
	}
	
	public void bindCanvasHolder(JPanel p) {
		holder = p;		
	}
	
	private class Menu extends JLabel implements MouseListener{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		ArrayList<MenuItem> items;
		JPopupMenu menuItems ;

		Menu(String name){
			this.setText(name);
			this.setForeground(new Color(0xabffd5));
			this.setBorder(BorderFactory.createEmptyBorder(1,5,1,5));
			this.setFont(new Font(Font.MONOSPACED , Font.BOLD , 15));
			this.addMouseListener(this);
			items = new ArrayList<MenuItem>();
			menuItems = new JPopupMenu();
			menuItems.setBackground(new Color(0x555555));
			menuItems.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		}

		public void addMenuItem(MenuItem item){
			items.add(item);
			menuItems.add(item);
			menuItems.setLayout(new GridLayout(items.size(),1,0,5));
		}
		
		public void mouseClicked(MouseEvent e){	
			//menuItems.show(CustomMenu.this, this.getX(), CustomMenu.this.getInsets().top + this.getY()+this.getHeight());
		}

		public void mouseEntered(MouseEvent e){
			menuItems.show(holder, this.getX(),0);
			this.setForeground(new Color(0xff00ff));
			this.repaint();
		}

		public void mouseExited(MouseEvent e){
			this.setForeground(new Color(0xabffdf));
			this.repaint();
		}

		public void mousePressed(MouseEvent e){

		}

		public void mouseReleased(MouseEvent e){

		}

	}
	
	private class MenuItem extends JLabel implements MouseListener{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		MenuItem(String name){
			this.setText(name);
			this.setBackground(new Color(0x555555));
			this.setForeground(new Color(0xabffdf));
			this.addMouseListener(this);
			this.setFont(new Font(Font.SANS_SERIF, Font.BOLD , 15));
			this.setBorder(BorderFactory.createEmptyBorder(3,5,3,5));
		}

		public void mouseEntered(MouseEvent e){
			this.setForeground(new Color(0xff00ff));
			this.repaint();
		}

		public void mouseExited(MouseEvent e){
			this.setForeground(new Color(0xabffdf));
			this.repaint();
		}

		public void mousePressed(MouseEvent e){

		}

		public void mouseReleased(MouseEvent e){

		}

		public void mouseClicked(MouseEvent e){
			
		}
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