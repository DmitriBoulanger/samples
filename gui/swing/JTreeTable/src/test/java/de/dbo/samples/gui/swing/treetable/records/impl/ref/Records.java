package de.dbo.samples.gui.swing.treetable.records.impl.ref;
 
import de.dbo.samples.gui.swing.treetable.records.api.Record;

import java.util.ArrayList;
import java.util.List;
 
public class Records  {
	 
    public static List<Record> list() {

    	String error = "Error";
    	String warning = "Warning";
    	
    	final List<Record> records = new ArrayList<Record>();
    	
		records.add( new RecordImpl("/a/b/c") );
		records.add( new RecordImpl("/a/b/c/d/") );
		
		records.add( new RecordImpl("/a/") );
		
		records.add( new RecordImpl("/a/b/c/d/e") );
		
		records.add( new RecordImpl(error ));
		
		records.add( new RecordImpl("/a/b/c/d/x") );
	
		records.add( new RecordImpl(error ));
		records.add( new RecordImpl(error ));
		records.add( new RecordImpl(warning ));
		
		records.add( new RecordImpl("/A///B/C/f/k/f") );
		records.add( new RecordImpl("/A/B/C/f/k") );
		
		records.add( new RecordImpl(warning ));
		
		records.add( new RecordImpl("/A/B/C/f/") );
		records.add( new RecordImpl("/A/B/C/f/") );
		records.add( new RecordImpl("/A") );
		
		records.add( new RecordImpl(warning ));
		
		records.add( new RecordImpl("/A/B/C/d") );
		
        return records;
    }
 
    
}