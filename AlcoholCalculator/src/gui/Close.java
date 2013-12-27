package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import fileIO.LoadCocktails;

@SuppressWarnings("serial")
public class Close extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	/**
	 * Dialog box for when the program is close for saving the program.
	 */
	public Close(ResourceBundle resourceBundle, final LoadCocktails lc) {
		// Set details
		setTitle(resourceBundle.getString("closeDialog"));
		setSize(Const.closeWidth, Const.closeHeight);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		
		// Override exit 
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
            @Override
			public void windowClosing(WindowEvent e) {
            	// Close program
                setVisible(false);
                System.exit(0);
                
            }
        });
		
		// Add save dialog text
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			//
			JLabel warningText = new JLabel(resourceBundle.getString("closeText"));
			contentPanel.add(warningText);
		}
		
		// Add buttons
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				// Set Save button
				JButton saveButton = new JButton(resourceBundle.getString("saveButton"));
				saveButton.setActionCommand(resourceBundle.getString("okButton"));
				saveButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// Save Cocktails back to file
						lc.save();
						// Close program
						setVisible(false);
						System.exit(0);
					}
				});
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			{
				// Set Don't Save Button
				JButton cancelButton = new JButton(resourceBundle.getString("dsaveButton"));
				cancelButton.setActionCommand(resourceBundle.getString("cancelButton"));
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// Close program
						setVisible(false);
						System.exit(0);	
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

}
