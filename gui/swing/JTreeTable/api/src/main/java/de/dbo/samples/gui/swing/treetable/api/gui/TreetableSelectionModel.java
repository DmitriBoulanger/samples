package de.dbo.samples.gui.swing.treetable.api.gui;
 
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultTreeSelectionModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
class TreetableSelectionModel extends DefaultTreeSelectionModel {
	private static final long serialVersionUID = -5304146615031760881L;
	private static final Logger log = LoggerFactory.getLogger(TreetableSelectionModel.class);

	TreetableSelectionModel() {
        super();
        
        listSelectionModel.addListSelectionListener(
        		new ListSelectionListener() {
        			@Override
        			public void valueChanged(ListSelectionEvent e) {
        				log.debug("valueChanged: " + e.toString() + " no action!");  
        			}
        });
    }
     
    ListSelectionModel getListSelectionModel() {
        return listSelectionModel;
    }
}