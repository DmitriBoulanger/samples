package de.dbo.samples.gui.swing.treetable.records.impl.ref;
 
import de.dbo.samples.gui.swing.treetable.records.api.Node;
import de.dbo.samples.gui.swing.treetable.records.api.Record;
import de.dbo.samples.gui.swing.treetable.records.api.RecordTreeGenerator;
import de.dbo.samples.gui.swing.treetable.records.impl.ref.RecordImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class RecordStructure  {
	private static final Logger log = LoggerFactory.getLogger(RecordStructureTreeTableModel.class);
	
    public static Node instance() {

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
		
		final RecordTreeGenerator recordList = new RecordTreeGenerator(new FactoryImpl(), records);
		if (log.isDebugEnabled() ) {
			log.debug("Tree-Structure: "
					 + "\nTotal records: " + recordList.size()  
		             + recordList.print());	
		}
		
        return recordList.tree();
    }
 
    
}