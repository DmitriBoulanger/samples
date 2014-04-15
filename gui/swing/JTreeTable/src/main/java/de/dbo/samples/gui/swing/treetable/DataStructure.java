package de.dbo.samples.gui.swing.treetable;
 
import java.util.ArrayList;
import java.util.List;
 
public class DataStructure  {
	 
    public static DataNode instance() {
    	
        final List<DataNode> children1 = new ArrayList<DataNode>();
        children1.add(new DataNode("Node 12", "C12", new Long(System.currentTimeMillis()), new Integer(50), null));
        children1.add(new DataNode("Node 13", "C13", new Long(System.currentTimeMillis()), new Integer(60), null));
        children1.add(new DataNode("Node 14", "C14", new Long(System.currentTimeMillis()), new Integer(70), null));
        children1.add(new DataNode("Node 15", "C15", new Long(System.currentTimeMillis()), new Integer(80), null));
 
        final List<DataNode> children2 = new ArrayList<DataNode>();
        children2.add(new DataNode("Node 12", "C12", new Long(System.currentTimeMillis()), new Integer(10), null));
        children2.add(new DataNode("Node 13", "C13", new Long(System.currentTimeMillis()), new Integer(20), children1));
        children2.add(new DataNode("Node 14", "C14", new Long(System.currentTimeMillis()), new Integer(30), null));
        children2.add(new DataNode("Node 15", "C15", new Long(System.currentTimeMillis()), new Integer(40), null));
        
        final List<DataNode> children3 = new ArrayList<DataNode>();
        children3.add(new DataNode("Node 312", "C312", new Long(System.currentTimeMillis()), new Integer(10), null));
        children3.add(new DataNode("Node 313", "C313", new Long(System.currentTimeMillis()), new Integer(20), children2));
        children3.add(new DataNode("Node 314", "C314", new Long(System.currentTimeMillis()), new Integer(30), null));
        children3.add(new DataNode("Node 315", "C315", new Long(System.currentTimeMillis()), new Integer(40), null));
         
        final List<DataNode> exTagNodes = new ArrayList<DataNode>();
        exTagNodes.add(new DataNode("Root 01", "C01", new Long(System.currentTimeMillis()), new Integer(10), children2));
        exTagNodes.add(new DataNode("Root 02", "C02", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("Root 03", "C03", new Long(System.currentTimeMillis()), new Integer(10), children2));
        exTagNodes.add(new DataNode("Root 04", "C04", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("Root 05", "C05", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("Root 06", "C06", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("Root 07", "C07", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("Root 07a", "C07", null, null, null));
        exTagNodes.add(new DataNode("Root 07", "C07x", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("Root 08", "C08", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("Root 09", "C09", new Long(System.currentTimeMillis()), new Integer(10), children3));
        exTagNodes.add(new DataNode("Root 10", "C10", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("Root 11", "C11", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("Root 12", "C07 again", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("Root 13", "C08 again", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("Root 14", "C09 again", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("Root 15", "C10 again", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("Root 16", "C11 again", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("Root 17", "CXXX bla bla", null, null, null));
        
        
        final DataNode root = new DataNode("Evaluation events", null, null, null, exTagNodes);
        return root;
    }
 
    
}