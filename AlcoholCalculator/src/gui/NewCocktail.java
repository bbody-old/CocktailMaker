package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import backEnd.Cocktail;
import backEnd.Drink;
import fileIO.LoadCocktails;
import fileIO.LoadDrinks;

public class NewCocktail extends JFrame {

	private JPanel contentPane;
	private LoadDrinks ld;
	private ResourceBundle resourceBundle;
	/**
	 * Create the frame.
	 */
	public NewCocktail(ResourceBundle rb, LoadDrinks ldr, final LoadCocktails lc, final JList mainList, final JEditorPane preview) {
		this.resourceBundle = rb;
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		final ArrayList<Drink> selectedDrinks = new ArrayList<Drink>();
		final ArrayList<String> selectedDrinksNames = new ArrayList<String>();
		this.ld = ldr;
		final JList selectionList =  new JList();
		final JScrollPane list_1 = new JScrollPane(selectionList);
		setTitle(resourceBundle.getString("newCtail"));
		setSize(Const.width, Const.height);
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{getWidth() / 12, getWidth() *4 / 12, getWidth() *2 / 12, getWidth() *4 / 12, getWidth() / 12};
		gbl_contentPane.rowHeights = new int[]{getHeight() /12, getHeight() * 8/12, getHeight() /12, getHeight() /12, getHeight() /12};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		
		final JList jList = new JList(ld.getDrinksStrings());
		JScrollPane list = new JScrollPane(jList);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 1;
		contentPane.add(list, gbc_list);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton button = new JButton(Const.left);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 0);
		gbc_button.gridx = 0;
		gbc_button.gridy = 1;
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indices[] = selectionList.getSelectedIndices();
				// TODO: Test
				for (int i = indices.length - 1; i >= 0; i--){
					if (indices[i] >= 0){
						selectedDrinks.remove(indices[i]);
						selectedDrinksNames.remove(indices[i]);
					}
				}
				selectionList.setListData(selectedDrinksNames.toArray());
			}
		});
		panel.add(button, gbc_button);
		
		JButton button_1 = new JButton(Const.right);
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 5, 0);
		gbc_button_1.gridx = 0;
		gbc_button_1.gridy = 2;
		button_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int indices[] = jList.getSelectedIndices();
				for (int i = 0; i < indices.length; i++){
					if (selectedDrinks.contains(ld.getDrink(indices[i])) == false){
						selectedDrinks.add(ld.getDrink(indices[i]));
						selectedDrinksNames.add(ld.getDrink(indices[i]).getDrinkName());
					}
				}
				
				selectionList.setListData(selectedDrinksNames.toArray());
			}
		});
		panel.add(button_1, gbc_button_1);
		
		JButton btnClear = new JButton(Const.doubleLeft);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedDrinksNames.clear();
				selectedDrinks.clear();
				selectionList.setListData(selectedDrinksNames.toArray());
			}
		});
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.gridx = 0;
		gbc_btnClear.gridy = 3;
		panel.add(btnClear, gbc_btnClear);
		
		//list_1 = new JList();
		GridBagConstraints gbc_list_1 = new GridBagConstraints();
		gbc_list_1.insets = new Insets(0, 0, 5, 5);
		gbc_list_1.fill = GridBagConstraints.BOTH;
		gbc_list_1.gridx = 3;
		gbc_list_1.gridy = 1;
		contentPane.add(list_1, gbc_list_1);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.EAST;
		gbc_panel_1.gridwidth = 5;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.VERTICAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 3;
		contentPane.add(panel_1, gbc_panel_1);
		
		JButton btnNewButton = new JButton(resourceBundle.getString("next"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cocktail c = new Cocktail(resourceBundle.getString("cocktailDefaultName"));
				for (int i = 0; i < selectedDrinks.size(); i++){
					c.addDrink(10.0, selectedDrinks.get(i));
				}
				NewCocktailScreen2 nc = new NewCocktailScreen2(resourceBundle, ld, c, preview, mainList, lc);
				//setVisible(false);
				nc.setVisible(true);
				setVisible(false);
			}
		});
		panel_1.add(btnNewButton);
		
		JButton btnClose = new JButton(resourceBundle.getString("close"));
		btnClose.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
				setVisible(false);
            }
        });
		panel_1.add(btnClose);
		
		
	}

}
