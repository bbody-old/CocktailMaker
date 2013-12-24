package gui;

import java.util.ResourceBundle;

@SuppressWarnings("serial")
public class View extends CustomDialog {

	/**
	 * Creates a dialog to show the selected Cocktail's details
	 */
	public View(ResourceBundle resourceBundle, String name, String details) {
		super(resourceBundle, name, details);
	}

}
