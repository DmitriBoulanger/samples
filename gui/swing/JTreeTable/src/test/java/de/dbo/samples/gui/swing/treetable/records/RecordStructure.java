package de.dbo.samples.gui.swing.treetable.records;
 
import de.dbo.samples.gui.swing.treetable.records.api.Node;
import de.dbo.samples.gui.swing.treetable.records.api.Path;
import de.dbo.samples.gui.swing.treetable.records.api.Record;
import de.dbo.samples.gui.swing.treetable.records.impl.PathImpl;
import de.dbo.samples.gui.swing.treetable.records.impl.RecordImpl;

import java.util.ArrayList;
import java.util.List;
 
public class RecordStructure  {
	 
    public static Node instance() {

    	Path error = new PathImpl("Error");
    	Path warning = new PathImpl("Warning");
    	
    	final List<Record> records = new ArrayList<Record>();
    	
		records.add( new RecordImpl(new PathImpl("/a/b/c") ));
		records.add( new RecordImpl(new PathImpl("/a/b/c/d/") ));
		
		records.add( new RecordImpl(new PathImpl("/a/") ));
		
		records.add( new RecordImpl(new PathImpl("/a/b/c/d/e") ));
		records.add( new RecordImpl(error ));
		records.add( new RecordImpl(new PathImpl("/a/b/c/d/x") ));
	
		records.add( new RecordImpl(error ));
		records.add( new RecordImpl(error ));
		records.add( new RecordImpl(warning ));
		records.add( new RecordImpl(new PathImpl("/A///B/C/f/k/f") ));
		records.add( new RecordImpl(new PathImpl("/A/B/C/f/k") ));
		records.add( new RecordImpl(warning ));
		records.add( new RecordImpl(new PathImpl("/A/B/C/f/") ));
		records.add( new RecordImpl(new PathImpl("/A/B/C/f/") ));
		records.add( new RecordImpl(new PathImpl("/A") ));
		
		records.add( new RecordImpl(warning ));
		
		
		records.add( new RecordImpl(new PathImpl("/A/B/C/d") ));
		
		final RecordTreeGenerator recordList = new RecordTreeGenerator(records);
		System.out.println("Tree-Structure: "
				 + "\nTotal records: " + recordList.size()  
	             + recordList.print());
        
        return recordList.tree();

    }
 
    
}