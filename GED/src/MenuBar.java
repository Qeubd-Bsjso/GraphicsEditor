import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuBar extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String path = "./files";

	Canvas canvas;
	JPanel holder;
	MyFrame frame;
	
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
		
		CreateNewFilePopup createNewFilePopup = new CreateNewFilePopup();
		
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
				createNewFilePopup.create();
			}
		});
		loadItem.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				loadItem.setForeground(new Color(0xabffd5));
				loadItem.repaint();
				JFileChooser fileChooser = new JFileChooser("Choose a .png image");
				fileChooser.setCurrentDirectory(new File(path));
				fileChooser.setFileFilter(new FileNameExtensionFilter("*.png", "png"));
				int response = fileChooser.showOpenDialog(null);
				BufferedImage im = null;
				if(response == JFileChooser.APPROVE_OPTION) {
					try {
						File file = fileChooser.getSelectedFile();
						im = ImageIO.read(file); 
						canvas.loadImage(im);
						frame.updateCanvasScale();
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
				fileChooser.setCurrentDirectory(new File(path));
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
				canvas.clear();
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
	
	public void bindFrame(MyFrame f) {
		frame = f;
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
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
			menuItems.show(holder, this.getX(),0);
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
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
	
	private class CreateNewFilePopup extends JPopupMenu{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private int type = 0;
		
		JPanel panel ;
		
		JPanel sizePanel;
		JPanel typePanel;
		JPanel submitPanel;
		
		JPanel widthPanel;
		JPanel heightPanel;
		
		JTextField widthField;
		JTextField heightField;
		
		JLabel submit;
		JLabel cancel;
		
		JPanel [] backgrounds;
		JLabel [] nameLabels;
		JPanel [] imgPanels;
		JLabel [] imgLabels;
		
		Color background = new Color(0xe4fa93);
		Color unitsBackground = new Color(0xdea704);
		
		@SuppressWarnings("serial")
		CreateNewFilePopup(){
			this.setBorder(BorderFactory.createEmptyBorder());
			
			panel = new JPanel();
			panel.setBorder(null);
			panel.setLayout(new BorderLayout());
			
			sizePanel = new JPanel();
			sizePanel.setBackground(background);
			sizePanel.setPreferredSize(new Dimension(200,50));
			
			typePanel = new JPanel();
			typePanel.setBackground(background);
			
			submitPanel = new JPanel();
			submitPanel.setBackground(background);
			
			widthField = new JTextField();
			widthField.setForeground(Color.RED);
			widthField.setFont(new Font(Font.MONOSPACED,Font.BOLD,15));
			widthField.setPreferredSize(new Dimension(60,20));
			widthField.setBackground(background);
			widthField.setBorder(BorderFactory.createEmptyBorder());
			
			heightField = new JTextField();
			heightField.setForeground(Color.RED);
			heightField.setFont(new Font(Font.MONOSPACED,Font.BOLD,15));
			heightField.setPreferredSize(new Dimension(60,20));
			heightField.setBackground(background);
			heightField.setBorder(BorderFactory.createEmptyBorder());
			
			widthPanel = new JPanel();
			widthPanel.setPreferredSize(new Dimension(170,30));
			widthPanel.setBackground(unitsBackground);
			widthPanel.add(new JLabel("Width : "));
			widthPanel.add(widthField);
			
			heightPanel = new JPanel();
			heightPanel.setPreferredSize(new Dimension(170,30));
			heightPanel.setBackground(unitsBackground);
			heightPanel.add(new JLabel("Height : "));
			heightPanel.add(heightField);
			
			sizePanel.add(widthPanel);
			sizePanel.add(heightPanel);
			
			
			submit = new JLabel("Create New File",JLabel.CENTER);
			submit.setBackground(new Color(0xffbc36));
			submit.setPreferredSize(new Dimension(250,30));
			submit.setFont(new Font(Font.SERIF,Font.BOLD,20));
			submit.setOpaque(true);
			submit.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			submit.setCursor(new Cursor(Cursor.HAND_CURSOR));
			submit.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					submit.setBackground(background);
					submit.repaint();
				}
				public void mouseExited(MouseEvent e) {
					submit.setBackground(new Color(0xffbc36));
					submit.repaint();
				}
				public void mouseClicked(MouseEvent e) {
					int x , y;
					try {
						x = Integer.parseInt(widthField.getText());
						y = Integer.parseInt(heightField.getText());
						if(x > 2500 || x < 500 || y > 2500 || y < 500)
							throw new IllegalArgumentException();
						else {
							canvas.createNewFile(type,x,y);
						}
					}
					catch(java.lang.NumberFormatException exp1) {
						 JFrame f= new JFrame();
					     Dialog d = new Dialog(f , "Error", true);  
					     d.setLayout( new FlowLayout() ); 
					     JButton b = new JButton ("OK"); 
					     b.setFocusable(false);
					     b.addActionListener ( new ActionListener(){  
					    	 public void actionPerformed( ActionEvent e ){  
					              f.dispose();  
					         }  
					     });  
					     d.add( new JLabel (new ImageIcon(getClass().getClassLoader().getResource("signs/error.png"))));
					     d.add( new JLabel ("Size must be Integer"));  
					     d.add(b);       
					     d.setLocationRelativeTo(null);
					     d.pack();
					     d.setVisible(true);
					}
					catch(IllegalArgumentException exp2) {
						 JFrame f= new JFrame();
					     Dialog d = new Dialog(f , "Error", true);  
					     d.setLayout( new FlowLayout() ); 
					     JButton b = new JButton ("OK"); 
					     b.setFocusable(false);
					     b.addActionListener ( new ActionListener(){  
					    	 public void actionPerformed( ActionEvent e ){  
					              f.dispose();  
					         }  
					     });  
					     d.add( new JLabel (new ImageIcon(getClass().getClassLoader().getResource("signs/error.png"))));
					     d.add( new JLabel ("Input must be in range 500-2500"));  
					     d.add(b);       
					     d.setLocationRelativeTo(null);
					     d.pack();
					     d.setVisible(true);
					}
					submit.setBackground(new Color(0xffbc36));
					submit.repaint();
					setVisible(false);
				}
			});
			
			
			cancel = new JLabel("Cancel",JLabel.CENTER);
			cancel.setBackground(new Color(0xffbc36));
			cancel.setPreferredSize(new Dimension(100,30));
			cancel.setFont(new Font(Font.SERIF,Font.BOLD,20));
			cancel.setOpaque(true);
			cancel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			cancel.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					cancel.setBackground(background);
					cancel.repaint();
				}
				public void mouseExited(MouseEvent e) {
					cancel.setBackground(new Color(0xffbc36));
					cancel.repaint();
				}
				public void mouseClicked(MouseEvent e) {
					cancel.setBackground(new Color(0xffbc36));
					cancel.repaint();
					setVisible(false);
				}
			});
			
			submitPanel.add(submit);
			submitPanel.add(cancel);
			
			//backgrounds
			int n = 9;
			String [] names= {"White","Black","Wide-Ruled","Graph","Dot-Grid","Lined","Notebook","Blueprint","Transparent"};
			String [] imagesNames = {"backgrounds/white.png",
					"backgrounds/black.png",
					"backgrounds/wideRuled.jpg",
					"backgrounds/graph.png",
					"backgrounds/dotgrid.jpg",
					"backgrounds/lined.png",
					"backgrounds/notebook.png",
					"backgrounds/blueprint.jpg",
					"backgrounds/transparent.jpg"
					};
			backgrounds = new JPanel [n];
			nameLabels = new JLabel[n];
			imgPanels = new JPanel[n];
			imgLabels = new JLabel[n];
			
			for(int i=0;i<n;i++) {
				backgrounds[i] = new JPanel(){
				    Color color = background;
					@Override
				    public void setBackground(Color c) {
				    	color = c;
				    	repaint();
				    }
					@Override
				     protected void paintComponent(Graphics g) {
				        super.paintComponent(g);
				        Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
				        int width = getWidth();
				        int height = getHeight();
				        Graphics2D graphics = (Graphics2D) g;
				        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				        //Draws the rounded panel with borders.
				        graphics.setColor(color);
				        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
				        graphics.setColor(Color.red);
				        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
				     }
				  };
				backgrounds[i].setBackground(background);
				backgrounds[i].setPreferredSize(new Dimension(200,100));
				backgrounds[i].setLayout(new BorderLayout());
				nameLabels[i] = new JLabel(names[i],JLabel.CENTER);
				backgrounds[i].add(nameLabels[i],BorderLayout.NORTH);
				imgLabels[i] = new JLabel();
				imgPanels[i] = new JPanel();
				imgPanels[i].setOpaque(false);
				imgLabels[i].setIcon(new ImageIcon(getClass().getClassLoader().getResource(imagesNames[i])));
				imgPanels[i].add(imgLabels[i]);
				backgrounds[i].add(imgPanels[i],BorderLayout.CENTER);
				JPanel temp = new JPanel();
				temp.setOpaque(false);
				JPanel templ = new JPanel();
				templ.setOpaque(false);
				JPanel tempr = new JPanel();
				tempr.setOpaque(false);
				backgrounds[i].add(temp,BorderLayout.SOUTH);
				backgrounds[i].add(templ,BorderLayout.WEST);
				backgrounds[i].add(tempr,BorderLayout.EAST);
			}
			
			addListeners();
			
			backgrounds[type].setBackground(Color.red);
			nameLabels[type].setForeground(Color.white);
			backgrounds[type].repaint();
			for(int i=0;i<n;i++) {
				typePanel.add(backgrounds[i]);
			}
			
			typePanel.setLayout(new GridLayout(3,3,20,20));
			typePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
			
			panel.add(typePanel,BorderLayout.CENTER);
			panel.add(sizePanel,BorderLayout.NORTH);
			panel.add(submitPanel,BorderLayout.SOUTH);
			
			this.add(panel);
		}
		
		public void addListeners() {
			backgrounds[0].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					changeType(0);
				}
			});
			backgrounds[1].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					changeType(1);
				}
			});
			backgrounds[2].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					changeType(2);
				}
			});
			backgrounds[3].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					changeType(3);
				}
			});
			backgrounds[4].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					changeType(4);
				}
			});
			backgrounds[5].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					changeType(5);
				}
			});
			backgrounds[6].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					changeType(6);
				}
			});
			backgrounds[7].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					changeType(7);
				}
			});
			backgrounds[8].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					changeType(8);
				}
			});
		}
		public void changeType(int t) {
			backgrounds[type].setBackground(background);
			nameLabels[type].setForeground(Color.black);
			backgrounds[type].repaint();
			type = t;
			backgrounds[type].setBackground(Color.red);
			nameLabels[type].setForeground(Color.white);
			backgrounds[type].repaint();
		}
		
		public void create() {
			widthField.setText(String.valueOf(canvas.getImgWidth()));
			heightField.setText(String.valueOf(canvas.getImgHeight()));
			panel.setPreferredSize(new Dimension((int)(holder.getHeight()*0.80),(int)(holder.getHeight()*0.99)));
			this.show(holder,30,0);
		}
		
	}
	
}