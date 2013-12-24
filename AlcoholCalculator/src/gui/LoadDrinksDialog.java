package gui;

import java.io.File;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import fileIO.PreferenceHandler;

public class LoadDrinksDialog {
	String filename = "";
	PreferenceHandler p;
	public LoadDrinksDialog(final ResourceBundle rb, PreferenceHandler p){
		this.p = p;
		JFileChooser openFile = new JFileChooser();
		openFile.setLocale(p.getLocale());
		FileFilter ff = new FileFilter() {

			@Override
			public boolean accept(File arg0) {
				// TODO Auto-generated method stub
				if (arg0.isDirectory()) {
					return true;
				}
				return arg0.getName().toLowerCase().endsWith(".drinks");
			}

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return rb.getString("drinksDialog");
			}

		};
		openFile.setFileFilter(ff);
		openFile.setLocale(p.getLocale());
		openFile.showOpenDialog(null);
		if (openFile.getSelectedFile() != null){
			filename = openFile.getSelectedFile().getAbsolutePath();
		}
	}

	public String getSelected() {
		return filename;
	}
}
