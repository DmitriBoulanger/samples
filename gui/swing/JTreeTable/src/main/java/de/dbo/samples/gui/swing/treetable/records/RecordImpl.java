package de.dbo.samples.gui.swing.treetable.records;

import de.dbo.samples.gui.swing.treetable.records.api.Path;
import de.dbo.samples.gui.swing.treetable.records.api.RecordAbstraction;

import java.util.UUID;

public class RecordImpl extends RecordAbstraction {
	
	private final String uuid;
	
	public RecordImpl() {
		this(null);
	}
	
	public RecordImpl(final Path path) {
		super(path);
		this.uuid = UUID.randomUUID().toString().replaceAll("_", "");
	}
	
	public String getUuid() {
		return uuid;
	}

	
	@Override
	public String treename() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0,6);
	}

	private static final String DF = "000";
    @Override
	public StringBuilder print() {
    	final StringBuilder sb = new StringBuilder();
//    	sb.append(" seq=" + new DecimalFormat(DF).format(sequence) );
//    	sb.append(" uuid=" + uuid);
    	sb.append(" path=" + getPath().canonicalValue());
    	return sb;
    }
    
    @Override
    public String toString() {
    	return uuid;
    }
    
}
