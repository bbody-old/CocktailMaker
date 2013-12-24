package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import backEnd.Cocktail;
import fileIO.LoadCocktails;
import fileIO.LoadDrinks;
import fileIO.PreferenceHandler;

public class MainWindow {


	private JFrame frame;
	private LoadCocktails loadedCocktails;
	private LoadDrinks loadedDrinks;
	private String iconFileName;
	private Image iconImage;
	private ResourceBundle resourceBundle;
	private PreferenceHandler ph;

	public MainWindow(PreferenceHandler ph, LoadCocktails lc, LoadDrinks ld, String iconFileName, ResourceBundle bundle) {
		// Set Variables
		this.loadedCocktails = lc;
		this.loadedDrinks = ld;
		this.iconFileName = iconFileName;
		this.resourceBundle = bundle;
		this.ph = ph;
		
		// Initialize GUI
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Initialize Frame with name and version
		setFrame(new JFrame(this.resourceBundle.getString("Name") + Const.space + Const.version));
		// Set frame size
		getFrame().setSize(Const.width, Const.height);
		
		// Set program Icon
		iconImage = Toolkit.getDefaultToolkit().getImage(iconFileName);
		getFrame().setIconImage(iconImage);
		
		// Stop program exiting
		getFrame().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
		// Override it with a set of actions before it closes
		getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeDialog();
            }
        });
		
		// Configure Layout
		GridBagLayout mainWindowLayout = new GridBagLayout();
		// Columns
		mainWindowLayout.columnWidths = new int[]{getFrame().getWidth()/24, 
				getFrame().getWidth()*10/24, getFrame().getWidth()*2/24, 
				getFrame().getWidth()*10/24, getFrame().getWidth()/24};
		mainWindowLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0};
		// Rows
		mainWindowLayout.rowHeights = new int[]{getFrame().getHeight()/24, 
				getFrame().getHeight()*17/24, getFrame().getHeight()/24, 
				getFrame().getHeight()*3/24, getFrame().getHeight()/24};
		mainWindowLayout.rowWeights = new double[]{0.0, 2.0, 0.0, 1.0, 0.0};
		getFrame().getContentPane().setLayout(mainWindowLayout);
		
		// Set up editor pane
		final JEditorPane previewPane = new JEditorPane(Const.epMode, 
				resourceBundle.getString("preview"));
		// Do not allow editing
		previewPane.setEditable(false);
		// Set colour to make it look like a lable
		previewPane.setBackground(UIManager.getColor(Const.epColour));
		
		// Set up a list for the Cocktails
		final JList cocktailList = new JList(loadedCocktails.getStrings());
		// Set up an action listener so when the user changes selection it 
		// changes the preview window
		cocktailList.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent evt) {
		        if (!evt.getValueIsAdjusting()){
		        	// Check something is selected
					if (cocktailList.getSelectedIndex() >= 0){
						// Change preview window into new drink
			        	previewPane.setText(loadedCocktails.getCocktail(cocktailList.getSelectedIndex()).toGuiStringPreview(resourceBundle));
					}
					// Set size so it will wrap text
			        previewPane.setPreferredSize(new Dimension(1, 1));
		        }
		      }
		    });
		
		JScrollPane list = new JScrollPane(cocktailList);
		list.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 1;
		
		getFrame().getContentPane().add(list, gbc_list);
		
		JScrollPane scrollPane = new JScrollPane();
		//scrollPane.getVerticalScrollBar().setValue(0);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 3;
		gbc_scrollPane.gridy = 1;
		getFrame().getContentPane().add(scrollPane, gbc_scrollPane);
		
		scrollPane.setViewportView(previewPane);
		
		
		//lblP.setHorizontalAlignment(SwingConstants.LEFT);
		//lblP.setVerticalAlignment(SwingConstants.TOP);
		previewPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
		previewPane.setCaretPosition(0);
		previewPane.setText(resourceBundle.getString("preview"));
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		getFrame().getContentPane().add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAdd = new JButton(resourceBundle.getString("add"));
		panel.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
            	NewCocktail frame = new NewCocktail(resourceBundle, loadedDrinks, loadedCocktails, cocktailList, previewPane);
				frame.setVisible(true);
            }
        });   
		
		JButton btnView = new JButton(resourceBundle.getString("view"));
		panel.add(btnView);
		btnView.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
            	if (cocktailList.getSelectedIndex() >= 0){
            		View v = new View(resourceBundle, loadedCocktails.getCocktail(cocktailList.getSelectedIndex()).getCocktailName(), loadedCocktails.getCocktail(cocktailList.getSelectedIndex()).toGuiStringView(resourceBundle), iconImage);
            		v.setVisible(true);
            	}
            }
        }); 
		
		JButton btnEdit = new JButton(resourceBundle.getString("edit"));
		panel.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
            	int selected = cocktailList.getSelectedIndex();
            	if (selected >= 0){
            		Cocktail cocktail = loadedCocktails.getCocktail(selected);
	            	EditCocktail frame = new EditCocktail(resourceBundle, loadedDrinks, cocktail, previewPane, selected, cocktailList, loadedCocktails, iconImage);
					frame.setVisible(true);
					
					/*
					lc.setCocktail(selected, cocktail);
					System.out.println(cocktail.getCocktailName());
					jList.setListData(lc.getStrings());
					jList.setSelectedIndex(selected);
					//if (selected >= 0){
			        	lblP.setText(cocktail.toGuiStringPreview());
			        	System.out.println(cocktail.toGuiStringPreview());
			        	lblP.repaint();
					//}*/
            	}
            }
        });
		
		JButton btnRemove = new JButton(resourceBundle.getString("remove"));
		panel.add(btnRemove);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu(resourceBundle.getString("file"));
		menuBar.add(mnFile);
		
		JMenuItem mntmPreferences = new JMenuItem(resourceBundle.getString("preferences"));
		mntmPreferences.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PreferencesFrame preferencesWindow = new PreferencesFrame(resourceBundle, ph);
				//preferencesWindow.show();
				preferencesWindow.setVisible(true);
				//preferencesWindow.
				//gp.
			}
		});
		mnFile.add(mntmPreferences);
		
		JMenuItem mntmLoadCocktails = new JMenuItem(resourceBundle.getString("loadCocktailList"));
		mntmLoadCocktails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoadCocktailsDialog lcd = new LoadCocktailsDialog(resourceBundle, ph);
				if (lcd.getSelected().length() > 0){
					ph.setCocktailsFilename(lcd.getSelected());
				}
			}
		});
		mnFile.add(mntmLoadCocktails);
		
		JMenuItem mntmLoadDrinks = new JMenuItem(resourceBundle.getString("loadDrinkList"));
		mntmLoadDrinks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoadDrinksDialog ldd = new LoadDrinksDialog(resourceBundle, ph);
				if (ldd.getSelected().length() > 0){
					ph.setDrinksFilename(ldd.getSelected());
				}
			}
		});
		mnFile.add(mntmLoadDrinks);
		
		JMenuItem mntmAbout = new JMenuItem(resourceBundle.getString("about"));
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				About ab = new About(resourceBundle, iconImage);
				ab.setVisible(true);
			}
		});
		mnFile.add(mntmAbout);
		
		JMenuItem mntmExit = new JMenuItem(resourceBundle.getString("exit"));
		mntmExit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeDialog();
				
			}});
		mnFile.add(mntmExit);
		
		btnRemove.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
            	int [] selected = cocktailList.getSelectedIndices();
            	if (selected.length == 1){
	            	String name = loadedCocktails.getCocktail(cocktailList.getSelectedIndex()).getCocktailName();
	            	
	            	String[] options = {resourceBundle.getString("deleteButton"), resourceBundle.getString("cancelButton")};
	            	int n = JOptionPane.showOptionDialog(getFrame(),
	            			resourceBundle.getString("delete_dialog_1") + name + resourceBundle.getString("delete_dialog_2"),
	            			resourceBundle.getString("delete"),
	            			JOptionPane.YES_NO_CANCEL_OPTION,
	            			JOptionPane.QUESTION_MESSAGE,
	            			null,
	            			options,
	            			options[1]);
	            	if (n == 0){
	            		loadedCocktails.removeCocktail(selected[0]);
		            	cocktailList.setListData(loadedCocktails.getStrings());
		            	if (selected[0] > 0){
		            		cocktailList.setSelectedIndex(selected[0] - 1);
		            	} else if (selected[0] == 0){
		            		cocktailList.setSelectedIndex(0);
		            	}
	            	}
            	} else if (selected.length > 1){
            		// TODO: HTML format so everything can go in
            		/*String name = "";// = lc.getCocktail(jList.getSelectedIndex()).getCocktailName();
            		for (int i = 0; i < selected.length; i++){
            			if (i > 0){
            				if (i != selected.length-1){
            					name += ", ";
            				} else {
            					name += " and ";
            				}
            			}
            			name += lc.getCocktail(selected[i]).getCocktailName();
            			
            		
            		}
            		*/
            		String name = "these items";
	            	
	            	String[] options = {resourceBundle.getString("deleteButton"), resourceBundle.getString("cancelButton")};
	            	int n = JOptionPane.showOptionDialog(getFrame(),
	            			resourceBundle.getString("delete_dialog_1") + name + resourceBundle.getString("delete_dialog_2"),
	            			resourceBundle.getString("delete"),
	            			JOptionPane.YES_NO_CANCEL_OPTION,
	            			JOptionPane.QUESTION_MESSAGE,
	            			null,
	            			options,
	            			options[1]);
	            	if (n == 0){
	            		for (int i = selected.length - 1; i >= 0; i--){
	            			loadedCocktails.removeCocktail(selected[i]);
	            		}
	            		
		            	cocktailList.setListData(loadedCocktails.getStrings());
		            	if (selected[0] > 0){
		            		cocktailList.setSelectedIndex(selected[0] - 1);
		            	} else if (selected[0] == 0){
		            		cocktailList.setSelectedIndex(0);
		            	}
	            	}
            	}
            }
        });
	}



	public JFrame getFrame() {
		return frame;
	}



	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	private void closeDialog() {
		// Open Close Dialog
		Close c = new Close(resourceBundle, loadedCocktails);
		c.setVisible(true);
		
		// Set this frame invisible
		getFrame().setVisible(false);
	}

}
