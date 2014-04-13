package de.dbo.samples.gui.swing.treetable;
 
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class DataNode {
	private static final Logger log = LoggerFactory.getLogger(DataNode.class);
 
    private final String name;
    private final String capital;
    
    private Long timestamp;
    private Object o;
    private String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    
    final private List<DataNode> children = new ArrayList<DataNode>();
 
    public DataNode(final String name, final String capital, Long timestamp, Object o, List<DataNode> children) {
        this.name = name;
        this.capital = capital;
        this.timestamp = timestamp;
        this.o = o;
        if (children != null) {
            this.children.addAll(children);
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
        log.debug("UUID=" + this.uuid);
    }
 
    public Long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        log.debug("Timestamp=" + this.timestamp);
    }
 
    public Object getObject() {
        return o;
    }
    
    public void setObject(Object o) {
    	 this.o = o;
    	 log.debug("Object=" + this.o);
    }
 
    public List<DataNode> getChildren() {
        return children;
    }
 
    /**
     * Node-path.
     */
    public String toString() {
        return name;
    }
}