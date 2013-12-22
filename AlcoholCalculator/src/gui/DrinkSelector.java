package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import backEnd.Cocktail;
import backEnd.Drink;
import fileIO.LoadDrinks;

public class DrinkSelector extends JDialog {

	private final JPanel contentPanel = new JPanel();
	boolean ok = false;
	/**
	 * Create the dialog.
	 */
	JComboBox list;
	JSpinner spinner;
	SpinnerNumberModel volume;
	JPanel tpanel;
	public DrinkSelector(ResourceBundle resourceBundle, JPanel panel, final LoadDrinks ld, final Cocktail c) {
		this.tpanel = panel;
		//setBounds(100, 100, 450, 300);
		list = new JComboBox();
		setSize(300,150);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(resourceBundle.getString("okButton"));
				okButton.setActionCommand(resourceBundle.getString("okButton"));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int selected = list.getSelectedIndex();
						Drink d = ld.getDrink(selected);
						c.addDrink( volume.getNumber().doubleValue(), d);
						ok = true;
						//tpanel = new EditCocktailPanel(ld, c);
						//makeBoard(panel, ld, c, volumeSpinners, comboBox, gbc_lblDrink, removes);
						//dispose();
						//	setVisible(false);
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton(resourceBundle.getString("cancelButton"));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand(resourceBundle.getString("cancelButton"));
				buttonPane.add(cancelButton);
			}
		}
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 430, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{getHeight()/2, getHeight()/2};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblWhatDrinkWould = new JLabel(resourceBundle.getString("selectQuestion"));
			GridBagConstraints gbc_lblWhatDrinkWould = new GridBagConstraints();
			gbc_lblWhatDrinkWould.gridwidth = 4;
			gbc_lblWhatDrinkWould.insets = new Insets(0, 0, 5, 0);
			gbc_lblWhatDrinkWould.gridx = 0;
			gbc_lblWhatDrinkWould.gridy = 0;
			contentPanel.add(lblWhatDrinkWould, gbc_lblWhatDrinkWould);
		}
		{
			list = new JComboBox(ld.getDrinksStrings());
			//list.setMaximumRowCount(1);
			list.setEnabled(true);
			GridBagConstraints gbc_list = new GridBagConstraints();
			gbc_list.insets = new Insets(0, 0, 0, 5);
			gbc_list.fill = GridBagConstraints.HORIZONTAL;
			gbc_list.gridx = 1;
			gbc_list.gridy = 1;
			contentPanel.add(list, gbc_list);
		}
		{
			volume = new SpinnerNumberModel(0.0, 0.0, 1000.0, 10.0);
			spinner = new JSpinner(volume);
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.insets = new Insets(0, 0, 0, 5);
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 1;
			contentPanel.add(spinner, gbc_spinner);
		}
	}

}
