package de.dbo.samples.gui.swing.treetable.impl.ref;
 
import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.impl.ref.RecordImpl;

import java.util.ArrayList;
import java.util.List;
 
public class Records2  {
	 
    public static List<Record> list() {

    	String error = "Error";
    	String warning = "Warning";
    	
    	final List<Record> records = new ArrayList<Record>();
    	
		records.add( new RecordImpl("/XX/b/c") );
		records.add( new RecordImpl("/XX/b/c/d/") );
		
		records.add( new RecordImpl("/XX/") );
		
		records.add( new RecordImpl("/XX/b/c/d/e") );
		
		records.add( new RecordImpl(error ));
		
		records.add( new RecordImpl("/XX/b/c/d/x") );
	
		records.add( new RecordImpl(error ));
		records.add( new RecordImpl(error ));
		records.add( new RecordImpl(warning ));
		
		records.add( new RecordImpl("/XXXX///B/C/f/k/f") );
		records.add( new RecordImpl("/XXXX/B/C/f/k") );
		
		records.add( new RecordImpl(warning ));
		
		records.add( new RecordImpl("/X/B/C/f/") );
		records.add( new RecordImpl("/X/B/C/f/") );
		records.add( new RecordImpl("/X") );
		
		records.add( new RecordImpl(warning ));
		
		records.add( new RecordImpl("/X/B/C/d") );
		
        return records;
    }
 
    
}