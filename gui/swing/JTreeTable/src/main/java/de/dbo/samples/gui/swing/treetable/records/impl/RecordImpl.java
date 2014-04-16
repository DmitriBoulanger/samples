package de.dbo.samples.gui.swing.treetable.records.impl;

import de.dbo.samples.gui.swing.treetable.records.api.Path;
import de.dbo.samples.gui.swing.treetable.records.api.RecordAbstraction;

import java.util.UUID;

public final class RecordImpl extends RecordAbstraction {
	
	private final String uuid;
	
	public RecordImpl(final String path) {
		this(new PathImpl(path));
	}
	
	public RecordImpl(final Path path) {
		super(path);
		this.uuid = UUID.randomUUID().toString().replaceAll("_", "");
	}
	
	@Override
	public String treename() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0,6);
	}
	
	@Override
    public final String toString() {
    	return uuid;
    }
	
    @Override
	public StringBuilder print() {
    	final StringBuilder sb = new StringBuilder();
//    	sb.append(" seq=" + new DecimalFormat("000").format(sequence) );
//    	sb.append(" uuid=" + uuid);
    	sb.append(" path=" + getPath().canonicalValue());
    	return sb;
    }
    
    
    
}
