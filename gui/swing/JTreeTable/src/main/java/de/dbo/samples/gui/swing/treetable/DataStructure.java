package de.dbo.samples.gui.swing.treetable;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
public class DataStructure  {
	 
    public static DataNode instance() {
    	
        final List<DataNode> children1 = new ArrayList<DataNode>();
        children1.add(new DataNode("N12", "C12", new Date(), Integer.valueOf(50), null));
        children1.add(new DataNode("N13", "C13", new Date(), Integer.valueOf(60), null));
        children1.add(new DataNode("N14", "C14", new Date(), Integer.valueOf(70), null));
        children1.add(new DataNode("N15", "C15", new Date(), Integer.valueOf(80), null));
 
        final List<DataNode> children2 = new ArrayList<DataNode>();
        children2.add(new DataNode("N12", "C12", new Date(), Integer.valueOf(10), null));
        children2.add(new DataNode("N13", "C13", new Date(), Integer.valueOf(20), children1));
        children2.add(new DataNode("N14", "C14", new Date(), Integer.valueOf(30), null));
        children2.add(new DataNode("N15", "C15", new Date(), Integer.valueOf(40), null));
        
        final List<DataNode> children3 = new ArrayList<DataNode>();
        children3.add(new DataNode("N312", "C312", new Date(), Integer.valueOf(10), null));
        children3.add(new DataNode("N313", "C313", new Date(), Integer.valueOf(20), children1));
        children3.add(new DataNode("N314", "C314", new Date(), Integer.valueOf(30), null));
        children3.add(new DataNode("N315", "C315", new Date(), Integer.valueOf(40), null));
         
        final List<DataNode> rootNodes = new ArrayList<DataNode>();
        rootNodes.add(new DataNode("R01", "C01", new Date(), Integer.valueOf(10), children2));
        rootNodes.add(new DataNode("R02", "C02", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("R03", "C03", new Date(), Integer.valueOf(10), children2));
        rootNodes.add(new DataNode("R04", "C04", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("R05", "C05", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("R06", "C06", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("R07", "C07", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("R08", "C08", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("R09", "C09", new Date(), Integer.valueOf(10), children3));
        rootNodes.add(new DataNode("R10", "C10", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("R11", "C11", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("R12", "C07 again", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("R13", "C08 again", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("R14", "C09 again", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("R15", "C10 again", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("R16", "C11 again", new Date(), Integer.valueOf(10), children1));
        
        final DataNode root = new DataNode("ROOT", null, null, null, rootNodes);
        return root;
    }
 
    
}