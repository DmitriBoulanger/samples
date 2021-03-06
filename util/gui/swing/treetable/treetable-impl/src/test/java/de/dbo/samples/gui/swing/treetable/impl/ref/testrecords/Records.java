package de.dbo.samples.gui.swing.treetable.impl.ref.testrecords;
 
import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.impl.ref.RecordImpl;

import java.util.ArrayList;
 
public class Records extends ArrayList<Record>  {
	private static final long serialVersionUID = -2923241893698338344L;
	
	private String error = "Error";
	private String warning = "Warning";

	public Records()  {

		add( new RecordImpl("/a/b/c") );
		add( new RecordImpl("/a/b/c/d/") );
		
		add( new RecordImpl("/a/") );
		
		add( new RecordImpl("/a/b/c/d/e") );
		
		add( new RecordImpl(error ));
		
		add( new RecordImpl("/a/b/c/d/x") );
	
		add( new RecordImpl(error ));
		add( new RecordImpl(error ));
		add( new RecordImpl(warning ));
		
		add( new RecordImpl("/A///B/C/f/k/f") );
		add( new RecordImpl("/A/B/C/f/k") );
		
		add( new RecordImpl(warning ));
		
		add( new RecordImpl("/A/B/C/f/") );
		add( new RecordImpl("/A/B/C/f/") );
		add( new RecordImpl("/A") );
		
		add( new RecordImpl(warning ));
		
		add( new RecordImpl("/A/B/C/d") );
		 
    } 
}