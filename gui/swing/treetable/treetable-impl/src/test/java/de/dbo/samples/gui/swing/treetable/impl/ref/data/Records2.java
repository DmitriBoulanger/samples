package de.dbo.samples.gui.swing.treetable.impl.ref.data;
 
import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.impl.ref.RecordImpl;

import java.util.ArrayList;
import java.util.List;
 
public class Records2 extends ArrayList<Record>  {
	private static final long serialVersionUID = 5360421989194726803L;

	public Records2(final List<Record> records) {
	 
    	String error = "Error";
    	String warning = "Warning";
    	
		add( new RecordImpl("/XX/b/c") );
		add( new RecordImpl("/XX/b/c/d/") );
		
		add( new RecordImpl("/XX/") );
		
		add( new RecordImpl("/XX/b/c/d/e") );
		
		add( new RecordImpl(error ));
		
		add( new RecordImpl("/XX/b/c/d/x") );
	
		add( new RecordImpl(error ));
		add( new RecordImpl(error ));
		add( new RecordImpl(warning ));
		
		add( new RecordImpl("/XXXX///B/C/f/k/f") );
		add( new RecordImpl("/XXXX/B/C/f/k") );
		
		add( new RecordImpl(warning ));
		
		add( new RecordImpl("/X/B/C/f/") );
		add( new RecordImpl("/X/B/C/f/") );
		add( new RecordImpl("/X") );
		
		add( new RecordImpl(warning ));
		
		add( new RecordImpl("/X/B/C/d") );
		
		addAll(records);
    }
}