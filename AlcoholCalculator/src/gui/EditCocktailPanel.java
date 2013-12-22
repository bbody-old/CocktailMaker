package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import backEnd.Cocktail;
import backEnd.Drink;
import fileIO.LoadDrinks;

public class EditCocktailPanel extends JPanel {
	LoadDrinks ld;
	//Cocktail cocktail;
	SpinnerNumberModel[] volumeSpinners;
	JComboBox[] comboBox;
	public GridBagConstraints gbc_lblDrink;
	JCheckBox [] removes;
	JPanel panel;
	GridBagLayout gb2_contentPane;
	//final Cocktail oc;
	/**
	 * Create the panel.
	 */
	public EditCocktailPanel(final LoadDrinks ld, final Cocktail c) {
		//System.out.println("Unique");
		//oc = new Cocktail();
		//System.out.println("Before:" + oc.getSize());
		//oc.copy(c);
		//System.out.println("After:" + oc.getSize());
		this.ld = ld;
		init(c);
	}
	
	void init(Cocktail c){
		int drinksWidth = (getWidth()/8*6);
		int rows [] = new int[c.getSize()];
		removes = new JCheckBox [c.getSize()];
		double rowsW [] = new double[c.getSize()];
		for (int i = 0; i < rows.length; i++){
			rows[i] = getHeight()*10/24/c.getSize();
			rowsW[i] = 1.0;
			removes[i] = new JCheckBox ();
			removes[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//setVisible(false);
				}
			});
		}
		panel = this;
		//this.cocktail = c;
		gb2_contentPane = new GridBagLayout();
		gb2_contentPane.columnWidths = new int[]{drinksWidth/8*2, drinksWidth/8*3, drinksWidth/8*3};

		gb2_contentPane.rowHeights = rows;
		gb2_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0};
		gb2_contentPane.rowWeights = rowsW;
		
		setLayout(gb2_contentPane);
		// Initialise variables
		comboBox = new JComboBox[c.getSize()];
		//removes = new JButton[c.getSize()];
		volumeSpinners = new SpinnerNumberModel[c.getSize()];
		gbc_lblDrink = new GridBagConstraints();
		
		System.out.println(c.getSize());
		
		for (int i = 0; i < c.getSize(); i++){
			System.out.println("OK");
			// Get drink for that row
			Drink d = c.getDrink(i);
			
			// Label Drink by number
			JLabel lblDrink = new JLabel("Drink " + i);
			
			//gbc_lblDrink.insets = new Insets(0, 0, 0, 5);
			gbc_lblDrink.gridx = 0;
			
			gbc_lblDrink.gridy = i;
			
			add(lblDrink, gbc_lblDrink);
			//removes[i] = new JButton("X");
			
			// Load all drinks for combo box
			comboBox[i] = new JComboBox(ld.getDrinksStrings());
			comboBox[i].setEnabled(true);
			// Set the selected as the drink
			comboBox[i].setSelectedIndex(ld.getIndex(d));
			
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 0, 5);
			gbc_comboBox.fill = GridBagConstraints.WEST;
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = i;
			
			add(comboBox[i], gbc_comboBox);
			
			volumeSpinners[i] = new SpinnerNumberModel(c.getVolume(i), 0.0, 1000.0, 10.0);
			
			JSpinner spinner = new JSpinner(volumeSpinners[i]);
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = i;
			add(spinner, gbc_spinner);
			GridBagConstraints remove = new GridBagConstraints();
			remove.gridx = 3;
			remove.gridy = i;
			add(removes[i], remove);
		}

	}
	
	public boolean [] getSelected(){
		boolean [] selected = new boolean[removes.length];
		for (int i = 0; i < selected.length; i++){
			if (removes[i].isSelected()){
				selected[i] = true;
			} else {
				selected[i] = false;
			}
		}
		return selected;
	}
	
	/*
	public void removeAllDrinks(){
		for (int i = c.getSize() - 1; i >= 0; i--){
			c.removeDrink(i);
		}
		init(c);
	}*/
	/*
	public void removeSelected(){
		for (int i = cocktail.getSize() - 1; i >= 0; i--){
			if (removes[i].isSelected()){
				cocktail.removeDrink(i);
			}
			
		}
		init(cocktail);
	}*/
	/*
	public void defaultDrinks(){
		System.out.println("L:" + oc.getSize());
		//cocktail.copy(oc);
		//replace(oc);
		//this.cocktail.copy(oc);// = oc;
		//init(cocktail);
		//init(oc);
		//replace(oc);
		//undraw();
		undraw();
		this.cocktail.copy(oc);
		//redraw();
	}*/
	
	public void replace(Cocktail nc){
		//this = new EditCocktailPanel();
		
		undraw();
		init(nc);
		redraw();
		//getRootPane().removeAll();
	    //JPanel newPanel=new JPanel();
	    //
		
	    //System.out.println("new panel created");//for debugging purposes
	    //validate();
	    //setVisible(true);
	}
	
	public void undraw(){
		removeAll();
		//invalidate();
	}
	
	public void redraw(){
		revalidate();
		repaint();
	}

}
