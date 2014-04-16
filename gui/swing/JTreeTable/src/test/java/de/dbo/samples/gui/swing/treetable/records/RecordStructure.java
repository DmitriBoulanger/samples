package de.dbo.samples.gui.swing.treetable.records;
 
import de.dbo.samples.gui.swing.treetable.records.api.Node;
import de.dbo.samples.gui.swing.treetable.records.api.Path;
import de.dbo.samples.gui.swing.treetable.records.api.Record;

import java.util.ArrayList;
import java.util.List;
 
public class RecordStructure  {
	 
    public static Node instance() {

    	Path error = new PathImpl("Error");
    	Path warning = new PathImpl("Warning");
    	
    	final List<Record> records = new ArrayList<Record>();
    	
		records.add( new RecordImpl(new PathImpl("/a/b/c"),System.currentTimeMillis() ));
		records.add( new RecordImpl(new PathImpl("/a/b/c/d/"),System.currentTimeMillis() ));
		
		records.add( new RecordImpl(new PathImpl("/a/"),System.currentTimeMillis() ));
		
		records.add( new RecordImpl(new PathImpl("/a/b/c/d/e"),System.currentTimeMillis() ));
		records.add( new RecordImpl(error,System.currentTimeMillis() ));
		records.add( new RecordImpl(new PathImpl("/Processing/Main/Opening/d/x"),System.currentTimeMillis() ));
	
		records.add( new RecordImpl(error,System.currentTimeMillis() ));
		records.add( new RecordImpl(error,System.currentTimeMillis() ));
		records.add( new RecordImpl(warning,System.currentTimeMillis() ));
		records.add( new RecordImpl(new PathImpl("/Optimization/Main/Opening/f/k/f"),System.currentTimeMillis() ));
		records.add( new RecordImpl(new PathImpl("/Optimization/Main/Opening/f/k"),System.currentTimeMillis() ));
		records.add( new RecordImpl(new PathImpl("/Optimization/Main/Opening/f/"),System.currentTimeMillis() ));
		records.add( new RecordImpl(new PathImpl("/Optimization/Main/Opening/f/"),System.currentTimeMillis() ));
		records.add( new RecordImpl(new PathImpl("/Optimization"),System.currentTimeMillis() ));
		
		records.add( new RecordImpl(warning,System.currentTimeMillis() ));
		
		
		records.add( new RecordImpl(new PathImpl("/Optimization/Main/Opening/d"),System.currentTimeMillis() ));
		
		final RecordList recordList = new RecordList(records);
		System.out.println("Tree-Structure: "
				 + "\nTotal records: " + recordList.size()  
	             + recordList.print());
        
        
        return recordList.tree();

    }
 
    
}