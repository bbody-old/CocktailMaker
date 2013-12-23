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

@SuppressWarnings("serial")
public class View extends CustomDialog {

	/**
	 * Creates a dialog to show the selected Cocktail's details
	 */
	public View(ResourceBundle resourceBundle, String name, String details, Image iconImage) {
		super(resourceBundle, iconImage, name, details);
	}

}
