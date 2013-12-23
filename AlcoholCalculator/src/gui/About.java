package gui;

import java.awt.Image;
import java.util.ResourceBundle;

@SuppressWarnings("serial")
public class About extends CustomDialog {

	/**
	 * Creates a dialog to show the selected Cocktail's details
	 */
	public About(ResourceBundle resourceBundle, Image iconImage) {
		super(resourceBundle, iconImage, resourceBundle.getString("Name"), 
				resourceBundle.getString("aboutText"));
		
	}

}
