package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import backEnd.Cocktail;
import backEnd.Drink;
import fileIO.LoadCocktails;
import fileIO.LoadDrinks;

public class EditCocktail extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private LoadDrinks loadDrinks;
	EditCocktailPanel ecp;
	JScrollPane jsp;
	private Cocktail cocktail;
	private final Cocktail originalCocktail;
	private JTextArea textPane;
	private ResourceBundle resourceBundle;
	
	/**
	 * Create the frame. 852
	 */
	public EditCocktail(ResourceBundle resourceBundle, final LoadDrinks loadDrinks, final Cocktail cocktail, final JEditorPane lblP, final int selected, final JList jList, final LoadCocktails lc, Image iconImage) {
		this.loadDrinks = loadDrinks;
		this.cocktail = cocktail;
		this.originalCocktail = new Cocktail(resourceBundle.getString("cocktailDefaultName"));
		this.originalCocktail.copy(this.cocktail);
		this.ecp = new EditCocktailPanel(this.loadDrinks, cocktail);
		this.resourceBundle = resourceBundle;

		
		setupUI(iconImage);
		setupContentPane();
		setupTextPane();
		setupJPanel();
		setupJPanelButtons();
		
		setupSaveButtons(lc, jList, selected, lblP);
	}
	
	private void setupUI(Image iconImage){
		setTitle(resourceBundle.getString("edit"));
		setIconImage(iconImage);
		setSize(Const.width, Const.height);
	}
	
	private void setupJPanel(){
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		
		JLabel lblNewLabel = new JLabel(resourceBundle.getString("nameLabel")); //$NON-NLS-1$
		lblNewLabel.setFont(new Font("Tacoma", Font.PLAIN, 18)); //$NON-NLS-1$
		panel.add(lblNewLabel);
		
		textField = new JTextField(cocktail.getCocktailName());
		panel.add(textField);
		textField.setColumns(15);
		
	}
	
	private void setupContentPane(){
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{getWidth()/16, getWidth()/16*14, getWidth()/16};
		gbl_contentPane.rowHeights = new int[]{38, 238, 35, 78, 43, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		GridBagConstraints gbc_jsp = new GridBagConstraints();
		gbc_jsp.fill = GridBagConstraints.BOTH;
		gbc_jsp.insets = new Insets(0, 0, 5, 5);
		gbc_jsp.gridx = 1;
		gbc_jsp.gridy = 1;
		

		jsp = new JScrollPane(ecp);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		contentPane.add(jsp, gbc_jsp);
	}
	
	private JTextArea setupTextPane(){
		textPane = new JTextArea(cocktail.getComment());
		JScrollPane scrollPane = getScrollPane();
		scrollPane.setViewportView(textPane);
		textPane.setLineWrap(true);
		textPane.setColumns(80);
		return textPane;
	}
	
	private JScrollPane getScrollPane(){
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);
		return scrollPane;
	}
	
	private void setupSaveButtons(final LoadCocktails lc, final JList jList, final int selected, final JEditorPane lblP){
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.anchor = GridBagConstraints.EAST;
		gbc_panel_2.fill = GridBagConstraints.VERTICAL;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 4;
		contentPane.add(panel_2, gbc_panel_2);
		
		
		JButton btnNewButton = new JButton(resourceBundle.getString("saveCloseButton")); //$NON-NLS-1$
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cocktail.setCocktailName(textField.getText());
				cocktail.setComment(textPane.getText());
				for (int i = 0; i < cocktail.getSize(); i++){
					if (ecp.comboBox[i].getSelectedIndex() >= 0){
						Drink d = loadDrinks.getDrink(ecp.comboBox[i].getSelectedIndex());
						double volume = ecp.volumeSpinners[i].getNumber().doubleValue();
						cocktail.setDrink(i, d, volume);
					}
				}
				setVisible(false);
				lc.setCocktail(selected, cocktail);
				jList.setListData(lc.getStrings());
				jList.setSelectedIndex(selected);
		        	lblP.setText(cocktail.toGuiStringPreview(resourceBundle));
		        	jList.setSelectedIndex(selected);
			}
		});
		panel_2.add(btnNewButton);
		
		JButton btnCloseWithoutSave = new JButton(resourceBundle.getString("closeNoSave")); //$NON-NLS-1$
		btnCloseWithoutSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//cocktail.copy(originalCocktail);
				cocktail.resetDrinks();
				cocktail.copy(originalCocktail);
				cocktail.setCocktailName(originalCocktail.getCocktailName());
				cocktail.setComment(originalCocktail.getComment());
				setVisible(false);
			}
		});
		panel_2.add(btnCloseWithoutSave);
	}
	
	private void setupJPanelButtons(){
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.EAST;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.VERTICAL;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 2;
		contentPane.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JButton btnAdd = new JButton(resourceBundle.getString("add")); //$NON-NLS-1$
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*for (int i = 0; i < c.getSize(); i++){
					panel_1.remove(0);
					panel_1.remove(0);
					panel_1.remove(0);
					panel_1.remove(0);
				}*/				
				final DrinkSelector ds = new DrinkSelector(resourceBundle, ecp, loadDrinks, cocktail);
				ds.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		        
				// Override it with a set of actions before it closes
				ds.addWindowListener(new WindowAdapter() {
		            public void windowClosed(WindowEvent e) {
		                ecp.replace(cocktail);
		                ds.setVisible(false);
		            }
		        });
				ds.setVisible(true);
				
				//panel_1.remove(i*3 + j);
				//panel_1.removeAll();
				//System.out.println("Add");
			}
		});
		panel_1.add(btnAdd);
		
		JButton btnRemove = new JButton(resourceBundle.getString("remove")); //$NON-NLS-1$
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ecp.removeSelected();
				for (int i = ecp.getSelected().length - 1; i >= 0; i--){
					/*if (removes[i].isSelected()){
						cocktail.removeDrink(i);
					}*/
					if (ecp.getSelected()[i]){
						cocktail.removeDrink(i);
					}
					
				}
				ecp.replace(cocktail);
			}
		});
		
		JButton btnReset = new JButton(resourceBundle.getString("reset")); //$NON-NLS-1$
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Replace panel with original cocktail
				ecp.replace(originalCocktail);
				// Reset Cocktail
				cocktail.reset();
				// Copy all the drinks from the original Cocktail
				cocktail.copy(originalCocktail);
				// Change comment and title back to original
				textPane.setText(cocktail.getComment());
				textField.setText(cocktail.getCocktailName());
				
			}
		});
		
		// Remove All Button
		JButton btnRemoveAll = new JButton(resourceBundle.getString("removeAll")); //$NON-NLS-1$
		btnRemoveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Remove all the drinks and replace panel
				cocktail.resetDrinks();
				ecp.replace(cocktail);
			} 
		});
		
		panel_1.add(btnReset);
		panel_1.add(btnRemove);
		panel_1.add(btnRemoveAll);
	}

	

}
