package de.dbo.samples.gui.swing.treetable;
 
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
 
public class DataNode {
 
    private final String name;
    private final String capital;
    private final Date declared;
    
    private Integer area;
    private String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    
    final private List<DataNode> children;
 
    public DataNode(final String name, final String capital, Date declared, Integer area, List<DataNode> children) {
        this.name = name;
        this.capital = capital;
        this.declared = declared;
        this.area = area;
        if (children == null) {
            this.children = Collections.emptyList();
        } else {
        	 this.children = children;
        }
    }
 
    public String getName() {
        return name;
    }
 
    public String getCapital() {
        return capital;
    }
    
    public String getUUID() {
        return uuid;
    }
    
    public void setUUID(String value) {
        this.uuid = value;
    }
 
    public Date getDeclared() {
        return declared;
    }
 
    public Integer getArea() {
        return area;
    }
    
    public void setArea(Integer value) {
    	 this.area = value;
    }
 
    public List<DataNode> getChildren() {
        return children;
    }
 
    /**
     * Knotentext from JTree.
     */
    public String toString() {
        return name;
    }
}