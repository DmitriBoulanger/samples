package de.dbo.samples.gui.swing.treetable.data;
 
import java.util.UUID;
 
public class Data  {
	
    private final String name;
    private final String capital;
    private Long timestamp;
    private String uuid = UUID.randomUUID().toString().replaceAll("-", "");
     
    public Data(final String name) {
    	this(name, null);
    }
    
    public Data(final String name, final String capital) {
        this.name = name;
    	this.capital = capital;
        this.timestamp = System.currentTimeMillis();
    }
    
    public String treename() {
        return name;
    }
 
    public String getCapital() {
        return capital;
    }
    
    public String getUUID() {
        return uuid;
    }
    
    public void setUUID(String uuid) {
        this.uuid = uuid;
    }
 
    public Long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
    
    public final String toString() {
    	return uuid.substring(10,15);
    }
    
 
}