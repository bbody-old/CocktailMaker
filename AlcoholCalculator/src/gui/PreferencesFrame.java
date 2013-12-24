package gui;

import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import resources.LocaleHandler;
import fileIO.PreferenceHandler;

public class PreferencesFrame extends JFrame {

	private JTextField textField_2;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 * 
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Preferences window = new
	 * Preferences(); window.frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */
	private PreferenceHandler preferenceHandler;
	private ResourceBundle resourceBundle;

	/**
	 * Create the application.
	 */
	public PreferencesFrame(ResourceBundle resourceBundle, PreferenceHandler ph) {
		super();
		this.resourceBundle = resourceBundle;
		this.preferenceHandler = ph;
		initialize();

	}

	JComboBox comboBox_3;
	JComboBox comboBox_2;
	JComboBox comboBox;
	// private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 315);
		setTitle(resourceBundle.getString("preferences"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel_3 = new JLabel(resourceBundle.getString("font"));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 0;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);

		JLabel lblNewLabel = new JLabel(resourceBundle.getString("fontname"));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 1;
		getContentPane().add(comboBox, gbc_comboBox);
		for (int i = 0; i < GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getAvailableFontFamilyNames().length; i++) {
			comboBox.addItem(GraphicsEnvironment.getLocalGraphicsEnvironment()
					.getAvailableFontFamilyNames()[i]);
		}
		comboBox.setSelectedItem(preferenceHandler.getFont().getName());

		JLabel lblNewLabel_2 = new JLabel(resourceBundle.getString("fontsize"));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 3;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

		comboBox_2 = new JComboBox();
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 2;
		gbc_comboBox_2.gridy = 3;
		getContentPane().add(comboBox_2, gbc_comboBox_2);
		for (int i = 1; i < 72; i++) { // TODO: Font size
			comboBox_2.addItem(new Integer(i).toString());
		}
		comboBox_2.setSelectedItem(new Integer(preferenceHandler.getFont()
				.getSize()).toString());

		JLabel lblNewLabel_4 = new JLabel(resourceBundle.getString("language"));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 4;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel(resourceBundle.getString("language"));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 5;
		getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);

		comboBox_3 = new JComboBox();
		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_3.gridx = 2;
		gbc_comboBox_3.gridy = 5;
		getContentPane().add(comboBox_3, gbc_comboBox_3);
		for (int i = 0; i < LocaleHandler.getStringList().length; i++) {
			comboBox_3.addItem(LocaleHandler.getStringList()[i]);
		}
		comboBox_3.setSelectedItem(preferenceHandler.getLocaleName());

		JLabel lblNewLabel_7 = new JLabel(resourceBundle.getString("files"));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 6;
		getContentPane().add(lblNewLabel_7, gbc_lblNewLabel_7);

		JLabel lblNewLabel_6 = new JLabel(resourceBundle.getString("cocktails"));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 7;
		getContentPane().add(lblNewLabel_6, gbc_lblNewLabel_6);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 7;
		getContentPane().add(panel_1, gbc_panel_1);

		textField_2 = new JTextField();
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(preferenceHandler.getCocktailsFilename());

		JButton btnNewButton_2 = new JButton(resourceBundle.getString("load"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoadCocktailsDialog lcd = new LoadCocktailsDialog(resourceBundle, preferenceHandler);
				if (lcd.getSelected().length() > 0)
					textField_2.setText(lcd.getSelected());
			}
		});
		panel_1.add(btnNewButton_2);

		JLabel lblNewLabel_8 = new JLabel(resourceBundle.getString("drinks"));
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 1;
		gbc_lblNewLabel_8.gridy = 8;
		getContentPane().add(lblNewLabel_8, gbc_lblNewLabel_8);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 2;
		gbc_panel_2.gridy = 8;
		getContentPane().add(panel_2, gbc_panel_2);

		textField_3 = new JTextField();
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setText(preferenceHandler.getDrinksFilename());

		JButton btnNewButton_3 = new JButton(resourceBundle.getString("load"));
		panel_2.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoadDrinksDialog ldd = new LoadDrinksDialog(resourceBundle, preferenceHandler);
				if (ldd.getSelected().length() > 0)
					textField_3.setText(ldd.getSelected());
			}
		});
		
		JLabel lblWarningProgramMust = new JLabel(resourceBundle.getString("prefWarning"));
		GridBagConstraints gbc_lblWarningProgramMust = new GridBagConstraints();
		gbc_lblWarningProgramMust.gridwidth = 3;
		gbc_lblWarningProgramMust.insets = new Insets(0, 0, 5, 0);
		gbc_lblWarningProgramMust.gridx = 0;
		gbc_lblWarningProgramMust.gridy = 9;
		getContentPane().add(lblWarningProgramMust, gbc_lblWarningProgramMust);
		// panel_1.add(btnNewButton_3);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 10;
		getContentPane().add(panel, gbc_panel);

		JButton btnNewButton = new JButton(resourceBundle.getString("saveButton"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					preferenceHandler.setCocktailsFilename(textField_2
							.getText());
					preferenceHandler.setDrinksFilename(textField_3.getText());
					preferenceHandler.setFontName(comboBox.getSelectedItem()
							.toString());
					preferenceHandler.setFontSize(comboBox_2.getSelectedItem()
							.toString());
					preferenceHandler.setLocaleName(LocaleHandler
							.getStringList()[comboBox_3.getSelectedIndex()]);
					preferenceHandler.save();
				} catch (BackingStoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);
			}
		});
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton(resourceBundle.getString("dsaveButton"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(btnNewButton_1);
	}

}
