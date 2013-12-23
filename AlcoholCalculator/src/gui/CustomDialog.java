package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class CustomDialog extends JDialog {
	public CustomDialog(ResourceBundle resourceBundle, Image iconImage, String title, String details) {
		// Set name
		setName(title);

		// Set icon
		setIconImage(iconImage);

		// Set Size
		setSize(Const.viewWidth, Const.viewHeight);

		// Set standard title
		setTitle(title);

		// Set up layout
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { getWidth() / 16 * 14 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		// Set up scroll pane for the text window
		JScrollPane textViewScrollPane = new JScrollPane();
		// Never let the horizontal bar appear
		textViewScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// Set up layout for the scroll pane
		GridBagConstraints scrollPaneGrid = new GridBagConstraints();
		scrollPaneGrid.fill = GridBagConstraints.BOTH;
		scrollPaneGrid.insets = new Insets(0, 0, 5, 0);
		scrollPaneGrid.gridx = 0;
		scrollPaneGrid.gridy = 0;
		getContentPane().add(textViewScrollPane, scrollPaneGrid);

		// Add a editor pane
		JEditorPane viewTextPanel = new JEditorPane(Const.epMode, details);
		// Set at top
		viewTextPanel.setCaretPosition(0);
		// Make sure it is not editable
		viewTextPanel.setEditable(false);
		// Set colour to mimic a JLabel
		viewTextPanel.setBackground(UIManager.getColor(Const.epColour));
		// Set the scroll pane so it is scrolling on the editor pane
		textViewScrollPane.setViewportView(viewTextPanel);

		// Ok button -> Exit program
		JButton okButton = new JButton(resourceBundle.getString("okButton"));
		// Set up position
		GridBagConstraints okButtonGrid = new GridBagConstraints();
		okButtonGrid.gridx = 0;
		okButtonGrid.gridy = 1;

		// Set up so when button is pressed it closes window
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		getContentPane().add(okButton, okButtonGrid);
	}
}
