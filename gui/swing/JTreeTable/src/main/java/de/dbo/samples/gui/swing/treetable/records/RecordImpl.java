package de.dbo.samples.gui.swing.treetable.records;

import de.dbo.samples.gui.swing.treetable.records.api.Path;
import de.dbo.samples.gui.swing.treetable.records.api.Record;

import java.text.DecimalFormat;
import java.util.UUID;

public class RecordImpl implements Record {
	
	// /A/B/C/D or anything other as a non-path
	private final Path path;
	
	private final Long timestamp;
	
	private final String uuid;
	
	private Object contents = null;
	
	private Long sequence = null;
	
	public RecordImpl(final Long timestamp) {
		this((Path) null, timestamp);
	}
	
	public RecordImpl(final Path path, final Long timestamp) {
		this.path = path;
		this.timestamp = timestamp;
		this.uuid = UUID.randomUUID().toString().replaceAll("_", "");
	}
	
	@Override
	public boolean isData(final int depth) {
		 return depth == getPath().depth();
	}

	@Override
	public Path getPath() {
		return path;
	}
	

	public String getUuid() {
		return uuid;
	}

	@Override
	public int compareTo(Record another) {
		return sequence.compareTo(another.getSequence());
	}
	
	@Override
	public Long getTimestamp() {
		return timestamp;
	}

	@Override
	public Long getSequence() {
		return sequence;
	}

	@Override
	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	@Override
	public Object getContents() {
		return contents;
	}

	@Override
	public void setContents(Object contents) {
		this.contents = contents;
	}
	
	
	private static final String DF = "000";
	
	@Override
	public String name() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0,6);
	}

    @Override
	public StringBuilder print() {
    	final StringBuilder sb = new StringBuilder();
//    	sb.append(" seq=" + new DecimalFormat(DF).format(sequence) );
//    	sb.append(" uuid=" + uuid);
    	sb.append(" path=" + path.canonicalValue());
    	return sb;
    }
    
    @Override
    public String toString() {
    	return print().toString();
    }
}
