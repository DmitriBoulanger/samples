package de.dbo.samples.gui.swing.treetable.records.api;

import java.util.List;

public interface Node extends Comparable<Node>{
	
	public static final String ROOT = "ROOT";

	public abstract String treename();
	
	public abstract List<Node> getChildren();
	
	public abstract Long getSequence();
	
	public abstract void setSequence(Long o);
	
	public abstract StringBuilder print();
	
	public abstract Object getObject();
	
	public abstract void setObject(Object o);
	
}