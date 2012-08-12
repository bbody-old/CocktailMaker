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
	private LoadDrinks ld;
	EditCocktailPanel ecp;
	JScrollPane jsp;
	
	/**
	 * Create the frame. 852
	 */
	public EditCocktail(final LoadDrinks ld, final Cocktail c, final JEditorPane lblP, final int selected, final JList jList, final LoadCocktails lc, Image iconImage) {
		this.ld = ld;
		setTitle("Edit");
		setIconImage(iconImage);
		
		setSize(Const.width, Const.height);
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{getWidth()/16, getWidth()/16*14, getWidth()/16};
		gbl_contentPane.rowHeights = new int[]{38, 238, 35, 78, 43, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		// 
		//------------------------------------------
				
		
		//GridBagLayout gb2_contentPane = new GridBagLayout();

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(lblNewLabel);
		
		textField = new JTextField(c.getCocktailName());
		panel.add(textField);
		textField.setColumns(15);
		
		//JPanel panel = new JPanel();
		//GridBagLayout gb_contentPane = new GridBagLayout();
		
		
		ecp = new EditCocktailPanel(ld, c);
		
		
		jsp = new JScrollPane(ecp);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		GridBagConstraints gbc_jsp = new GridBagConstraints();
		gbc_jsp.fill = GridBagConstraints.BOTH;
		gbc_jsp.insets = new Insets(0, 0, 5, 5);
		gbc_jsp.gridx = 1;
		gbc_jsp.gridy = 1;
		contentPane.add(jsp, gbc_jsp);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.EAST;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.VERTICAL;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 2;
		contentPane.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*for (int i = 0; i < c.getSize(); i++){
					panel_1.remove(0);
					panel_1.remove(0);
					panel_1.remove(0);
					panel_1.remove(0);
				}*/				
				final DrinkSelector ds = new DrinkSelector(ecp, ld, c);
				ds.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		        
				// Override it with a set of actions before it closes
				ds.addWindowListener(new WindowAdapter() {
		            public void windowClosed(WindowEvent e) {
		            	
		                System.out.println("Reset");
		                ecp.replace(c);
		                ds.setVisible(false);
		            }
		        });
				ds.setVisible(true);
				
				//panel_1.remove(i*3 + j);
				//panel_1.removeAll();
			}
		});
		panel_1.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ecp.removeSelected();
				ecp.removeSelected();
				ecp.replace(ecp.cocktail);
				//jsp = new JScrollPane(ecp);
			}
		});
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ecp.removeSelected();
				System.out.println("Resetty");
				ecp.defaultDrinks();
				//ecp.removeAllDrinks();
				//ecp.replace(ecp.oc);
				//c.copy(ecp.oc);
				//jsp = new JScrollPane(ecp);
			}
		});
		panel_1.add(btnReset);
		panel_1.add(btnRemove);
		
		JButton btnRemoveAll = new JButton("Remove All");
		btnRemoveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ecp.removeAllDrinks();
				ecp.replace(ecp.cocktail);
				//jsp = new JScrollPane(ecp);
			} 
		});
		panel_1.add(btnRemoveAll);
		//contentPane.add(jsp, ecp);
		
		//ecp.setLayout(gb2_contentPane);
		
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);
		//
		
		
		final JTextArea textPane = new JTextArea(c.getComment());
		scrollPane.setViewportView(textPane);
		textPane.setLineWrap(true);
		textPane.setColumns(80);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.anchor = GridBagConstraints.EAST;
		gbc_panel_2.fill = GridBagConstraints.VERTICAL;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 4;
		contentPane.add(panel_2, gbc_panel_2);
		
		
		JButton btnNewButton = new JButton("Save and Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.setCocktailName(textField.getText());
				c.setComment(textPane.getText());
				for (int i = 0; i < c.getSize(); i++){
					if (ecp.comboBox[i].getSelectedIndex() >= 0){
						Drink d = ld.getDrink(ecp.comboBox[i].getSelectedIndex());
						double volume = ecp.volumeSpinners[i].getNumber().doubleValue();
						c.setDrink(i, d, volume);
					}
				}
				setVisible(false);
				lc.setCocktail(selected, c);
				//System.out.println(c.getCocktailName());
				jList.setListData(lc.getStrings());
				jList.setSelectedIndex(selected);
				//if (selected >= 0){
		        	lblP.setText(c.toGuiStringPreview());
		        	//System.out.println(c.toGuiStringPreview());
		        	//lblP.repaint();
		        	jList.setSelectedIndex(selected);
			}
		});
		panel_2.add(btnNewButton);
		
		JButton btnCloseWithoutSave = new JButton("Close without Save");
		btnCloseWithoutSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.copy(ecp.oc);
				setVisible(false);
			}
		});
		panel_2.add(btnCloseWithoutSave);
	}

	

}
