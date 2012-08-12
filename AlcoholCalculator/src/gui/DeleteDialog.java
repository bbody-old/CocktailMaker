package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;

class DeleteDialog extends JDialog {
	boolean isDelete;
	  public DeleteDialog(JFrame parent, String drinkName, Image iconImage) {
	    super(parent, Const_En.deleteDialogName, true);
	    
	    setIconImage(iconImage);
	    isDelete = false;
	    GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{this.getWidth()/4, this.getWidth()/4, this.getWidth()/4};
		gridBagLayout.rowHeights = new int[]{this.getHeight()/2, this.getHeight()/2};
		gridBagLayout.columnWeights = new double[]{1.0, 0.5, 0.5};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0};
		this.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		JTextArea label = new JTextArea("Are you sure you want to delete " + drinkName + " ?");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);
		label.setLineWrap(true);
		label.setWrapStyleWord(true);
		label.setOpaque(false);
		label.setEditable(false);
		label.setFocusable(false);
	    JButton yesDelete = new JButton("Yes");
	    yesDelete.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	  //System.out.println("Delete the fucker!");
	    	  isDelete = true;
	    	  dispose();
	      }
	    });
	    
	    JButton noDelete = new JButton("No");
	    noDelete.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        dispose(); // Closes the dialog
	      }
	    });
	    
	    GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 0, 5);
		gbc_list.fill = GridBagConstraints.CENTER;
		gbc_list.gridx = 1;
		gbc_list.gridy = 1;
	    getContentPane().add(yesDelete, gbc_list);
	    
	    gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.CENTER;
		gbc_list.gridx = 2;
		gbc_list.gridy =1;
	    getContentPane().add(noDelete, gbc_list);
	    setSize(300, 100);
	  }
	  
	  public boolean isDelete(){
		  return isDelete;
	  }
	  
	}