package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import backEnd.Cocktail;
import backEnd.Drink;
import fileIO.LoadCocktails;
import fileIO.LoadDrinks;

public class NewCocktailScreen2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private LoadDrinks ld;
	private JPanel panel_1;
	JScrollPane jsp;
	GridBagLayout gb2_contentPane;
	GridBagConstraints gbc_panel_1;
	
	private JPanel listDraw(Cocktail c){
		JPanel panel = new JPanel();
		volumeSpinners = new SpinnerNumberModel[c.getSize()];
		comboBox = new JComboBox[c.getSize()];
		remove= new JButton[c.getSize()];
		//
		
		 gb2_contentPane = new GridBagLayout();

		//JPanel panel = new JPanel();
		//GridBagLayout gb_contentPane = new GridBagLayout();
		int drinksWidth = (getWidth()/8*6);
		gb2_contentPane.columnWidths = new int[]{drinksWidth/8*2, drinksWidth/8*2, drinksWidth/8*2, drinksWidth/8};
		int rows [] = new int[c.getSize() + 1];
		double rowsW [] = new double[c.getSize() + 1];
		for (int i = 0; i < rows.length; i++){
			rows[i] = getHeight()*10/24/c.getSize();
			rowsW[i] = 1.0;
		}
		
		gb2_contentPane.rowHeights = rows;
		gb2_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0};
		gb2_contentPane.rowWeights = rowsW;
		panel.setLayout(gb2_contentPane);
		 gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		jsp = new JScrollPane(panel);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		panel.setLayout(gb2_contentPane);
		//System.out.println(c.getSize());
		//final JComboBox [] nameList = new JComboBox[c.getSize()];
		
		for (int i = 0; i < c.getSize(); i++){
			Drink d = c.getDrink(i);
			JLabel lblDrink = new JLabel(resourceBundle.getString("drink") + Const.space + i);
			GridBagConstraints gbc_lblDrink = new GridBagConstraints();
			//gbc_lblDrink.insets = new Insets(0, 0, 0, 5);
			gbc_lblDrink.gridx = 0;
			
			gbc_lblDrink.gridy = i;
			panel.add(lblDrink, gbc_lblDrink);
			
			comboBox[i] = new JComboBox(ld.getDrinksStrings());
			comboBox[i].setSelectedIndex(ld.getIndex(d));
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 0, 5);
			gbc_comboBox.fill = GridBagConstraints.WEST;
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = i;
			panel.add(comboBox[i], gbc_comboBox);
			volumeSpinners[i] = new SpinnerNumberModel(c.getVolume(i), 0.0, 1000.0, 10.0);
			JSpinner spinner = new JSpinner(volumeSpinners[i]);
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = i;
			
			
			panel.add(spinner, gbc_spinner);
			
			remove[i] = new JButton("X");
			gbc_comboBox.fill = GridBagConstraints.WEST;
			gbc_comboBox.gridx = 3;
			gbc_comboBox.gridy = i;
			panel.add(remove[i], gbc_comboBox);
		}
		//panel_1.repaint();
		//contentPane.add(panel_1, gbc_panel_1);
		
		//contentPane.add(jsp, gbc_panel_1);
		return panel;
	}
	SpinnerNumberModel[] volumeSpinners;
	JComboBox [] comboBox;
	JButton remove [];
	ResourceBundle resourceBundle;
	/**
	 * Create the frame.
	 */
	public NewCocktailScreen2(ResourceBundle rb, final LoadDrinks ld, final Cocktail c, final JEditorPane lblP, final JList jList, final LoadCocktails lc) {
		this.ld = ld;
		this.resourceBundle = rb;
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		setTitle(resourceBundle.getString("edit"));
		//final JPanel panel_1;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Const.width, Const.height);
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{getWidth()/8*6};
		gbl_contentPane.rowHeights = new int[]{getHeight()*2/24, getHeight()*12/24, getHeight()*4/24, getHeight()*2/24};
		gbl_contentPane.columnWeights = new double[]{0.0};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		
		//GridBagLayout gb2_contentPane = new GridBagLayout();

		JPanel panel = new JPanel();
		//GridBagLayout gb_contentPane = new GridBagLayout();
		/*
		gb2_contentPane.columnWidths = new int[]{getWidth()/8*6/2, getWidth()/8*6/4, getWidth()/8*6/3};
		int rows [] = new int[c.getSize()];
		double rowsW [] = new double[c.getSize()];
		for (int i = 0; i < rows.length; i++){
			rows[i] = getHeight()*10/24/c.getSize();
			rowsW[i] = 1.0;
		}
		
		gb2_contentPane.rowHeights = rows;
		gb2_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0};
		gb2_contentPane.rowWeights = rowsW;*/
		//panel.setLayout(gb2_contentPane);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		
		JLabel lblNewLabel = new JLabel(resourceBundle.getString("nameLabel"));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(lblNewLabel);
		
		textField = new JTextField(c.getCocktailName());
		panel.add(textField);
		textField.setColumns(10);
		
		panel_1 = listDraw(c);
		
		contentPane.add(jsp, gbc_panel_1);
		
		for (int i = 0; i < remove.length; i++){
			final int index = i;
			remove[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.removeDrink(index);
					panel_1 = listDraw(c);
					panel_1.repaint();
				}
			});
		}
		
		//JButton remove = new JButton("X");
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);
		//
		
		
		final JTextArea textPane = new JTextArea(c.getComment());
		scrollPane.setViewportView(textPane);
		textPane.setLineWrap(true);
		textPane.setColumns(80);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.EAST;
		gbc_panel_2.fill = GridBagConstraints.VERTICAL;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 3;
		contentPane.add(panel_2, gbc_panel_2);
		
		JButton btnNewButton = new JButton(resourceBundle.getString("saveCloseButton"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.setCocktailName(textField.getText());
				c.setComment(textPane.getText());
				for (int i = 0; i < c.getSize(); i++){
					Drink d = ld.getDrink(comboBox[i].getSelectedIndex());
					double volume = volumeSpinners[i].getNumber().doubleValue();
					c.setDrink(i, d, volume);
				}
				setVisible(false);
				lc.addCocktail(c);
				//System.out.println(c.getCocktailName());
				jList.setListData(lc.getStrings());
				//jList.setSelectedIndex(selected);
				//if (selected >= 0){
		        	lblP.setText(c.toGuiStringPreview(resourceBundle));
		        	//System.out.println(c.toGuiStringPreview());
		        	//lblP.repaint();
		        	jList.setSelectedIndex(lc.size()-1);
			}
		});
		panel_2.add(btnNewButton);
		
		JButton btnCloseWithoutSave = new JButton(resourceBundle.getString("closeNoSave"));
		btnCloseWithoutSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel_2.add(btnCloseWithoutSave);
	}


}
