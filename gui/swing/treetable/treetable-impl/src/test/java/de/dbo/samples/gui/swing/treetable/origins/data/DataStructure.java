package de.dbo.samples.gui.swing.treetable.origins.data;
 
import de.dbo.samples.gui.swing.treetable.api.records.Node;

import java.util.ArrayList;
import java.util.List;
 
public class DataStructure  {
	 
    public static Node treeroot() {
    	
        final List<Node> children1 = new ArrayList<Node>();
        children1.add(new DataNode("Record 12", "C12",  null));
        children1.add(new DataNode("Record 13", "C13",  null));
        children1.add(new DataNode("Record 14", "C14",  null));
        children1.add(new DataNode("Record 15", "C15",  null));
 
        final List<Node> children2 = new ArrayList<Node>();
        children2.add(new DataNode("Record 12", "C12", null));
        children2.add(new DataNode("Node 13", "C13", children1));
        children2.add(new DataNode("Record 14", "C14",  null));
        children2.add(new DataNode("Record 15", "C15",  null));
        
        final List<Node> children3 = new ArrayList<Node>();
        children3.add(new DataNode("Record 312", "C312",  null));
        children3.add(new DataNode("Node 313", "C313", children2));
        children3.add(new DataNode("Record 314", "C314",  null));
        children3.add(new DataNode("Record 315", "C315",  null));
         
        final List<Node> nodes0 = new ArrayList<Node>();
        nodes0.add(new DataNode("Root 01", "C01", children2));
        nodes0.add(new DataNode("Root 02", "C02", children1));
        nodes0.add(new DataNode("Root 03", "C03", children2));
        nodes0.add(new DataNode("Root 04", "C04", children1));
        nodes0.add(new DataNode("Root 05", "C05", children1));
        nodes0.add(new DataNode("Root 06", "C06", children1));
        nodes0.add(new DataNode("Root 07", "C07", children1));
        nodes0.add(new DataNode("Record 07a", "C07", null));
        nodes0.add(new DataNode("Root 07", "C07x",children1));
        nodes0.add(new DataNode("Root 08", "C08", children1));
        nodes0.add(new DataNode("Root 09", "C09", children3));
        nodes0.add(new DataNode("Root 10", "C10", children1));
        nodes0.add(new DataNode("Root 11", "C11"		, children1));
        nodes0.add(new DataNode("Root 12", "C07 again"		, children1));
        nodes0.add(new DataNode("Root 13", "C08 again"		, children1));
        nodes0.add(new DataNode("Root 14", "C09 again"		, children1));
        nodes0.add(new DataNode("Root 15", "C10 again"		, children1));
        nodes0.add(new DataNode("Root 16", "C11 again"		, children1));
        nodes0.add(new DataNode("Root 17", "CXXX bla bla"	, null));
        
        
        return new DataNode("Data records", "",  nodes0);
    }
    
}