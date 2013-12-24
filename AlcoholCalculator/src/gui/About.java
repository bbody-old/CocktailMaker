package gui;

import java.util.ResourceBundle;

@SuppressWarnings("serial")
public class About extends CustomDialog {

	/**
	 * Creates a dialog to show the selected Cocktail's details
	 */
	public About(ResourceBundle resourceBundle) {
		super(resourceBundle, resourceBundle.getString("Name"), 
				resourceBundle.getString("aboutText"));
		
	}

}
