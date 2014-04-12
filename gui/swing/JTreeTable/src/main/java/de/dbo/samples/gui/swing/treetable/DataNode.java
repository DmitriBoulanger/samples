package de.dbo.samples.gui.swing.treetable;
 
import java.util.Collections;
import java.util.Date;
import java.util.List;
 
public class DataNode {
 
    final private String name;
    final private String capital;
    final private Date declared;
    final private Integer area;
    final private List<DataNode> children;
 
    public DataNode(String name, String capital, Date declared, Integer area, List<DataNode> children) {
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
 
    public Date getDeclared() {
        return declared;
    }
 
    public Integer getArea() {
        return area;
    }
 
    public List<DataNode> getChildren() {
        return children;
    }
 
    /**
     * Knotentext vom JTree.
     */
    public String toString() {
        return name;
    }
}