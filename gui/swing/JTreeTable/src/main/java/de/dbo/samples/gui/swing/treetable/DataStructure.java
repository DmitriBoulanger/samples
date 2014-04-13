package de.dbo.samples.gui.swing.treetable;
 
import java.util.ArrayList;
import java.util.List;
 
public class DataStructure  {
	 
    public static DataNode instance() {
    	
        final List<DataNode> children1 = new ArrayList<DataNode>();
        children1.add(new DataNode("N12", "C12", new Long(System.currentTimeMillis()), new Integer(50), null));
        children1.add(new DataNode("N13", "C13", new Long(System.currentTimeMillis()), new Integer(60), null));
        children1.add(new DataNode("N14", "C14", new Long(System.currentTimeMillis()), new Integer(70), null));
        children1.add(new DataNode("N15", "C15", new Long(System.currentTimeMillis()), new Integer(80), null));
 
        final List<DataNode> children2 = new ArrayList<DataNode>();
        children2.add(new DataNode("N12", "C12", new Long(System.currentTimeMillis()), new Integer(10), null));
        children2.add(new DataNode("N13", "C13", new Long(System.currentTimeMillis()), new Integer(20), children1));
        children2.add(new DataNode("N14", "C14", new Long(System.currentTimeMillis()), new Integer(30), null));
        children2.add(new DataNode("N15", "C15", new Long(System.currentTimeMillis()), new Integer(40), null));
        
        final List<DataNode> children3 = new ArrayList<DataNode>();
        children3.add(new DataNode("N312", "C312", new Long(System.currentTimeMillis()), new Integer(10), null));
        children3.add(new DataNode("N313", "C313", new Long(System.currentTimeMillis()), new Integer(20), children2));
        children3.add(new DataNode("N314", "C314", new Long(System.currentTimeMillis()), new Integer(30), null));
        children3.add(new DataNode("N315", "C315", new Long(System.currentTimeMillis()), new Integer(40), null));
         
        final List<DataNode> exTagNodes = new ArrayList<DataNode>();
        exTagNodes.add(new DataNode("R01", "C01", new Long(System.currentTimeMillis()), new Integer(10), children2));
        exTagNodes.add(new DataNode("R02", "C02", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("R03", "C03", new Long(System.currentTimeMillis()), new Integer(10), children2));
        exTagNodes.add(new DataNode("R04", "C04", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("R05", "C05", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("R06", "C06", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("R07", "C07", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("R08", "C08", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("R09", "C09", new Long(System.currentTimeMillis()), new Integer(10), children3));
        exTagNodes.add(new DataNode("R10", "C10", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("R11", "C11", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("R12", "C07 again", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("R13", "C08 again", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("R14", "C09 again", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("R15", "C10 again", new Long(System.currentTimeMillis()), new Integer(10), children1));
        exTagNodes.add(new DataNode("R16", "C11 again", new Long(System.currentTimeMillis()), new Integer(10), children1));
        
        final DataNode root = new DataNode("Evaluation events", null, null, null, exTagNodes);
        return root;
    }
 
    
}