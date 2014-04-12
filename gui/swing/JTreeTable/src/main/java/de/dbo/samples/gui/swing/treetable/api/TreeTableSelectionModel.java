package de.dbo.samples.gui.swing.treetable.api;
 
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultTreeSelectionModel;
 
public class TreeTableSelectionModel extends DefaultTreeSelectionModel {
	private static final long serialVersionUID = -5304146615031760881L;

	public TreeTableSelectionModel() {
        super();
 
        getListSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                 
            }
        });
    }
     
    ListSelectionModel getListSelectionModel() {
        return listSelectionModel;
    }
}