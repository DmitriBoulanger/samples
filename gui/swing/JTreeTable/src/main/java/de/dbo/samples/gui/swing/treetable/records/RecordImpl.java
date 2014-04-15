package de.dbo.samples.gui.swing.treetable.records;

import de.dbo.samples.gui.swing.treetable.records.api.Path;
import de.dbo.samples.gui.swing.treetable.records.api.Record;

import java.util.UUID;

public class RecordImpl implements Record {
	
	// /A/B/C/D or anything other as a non-path
	private final Path path;
	
	private final Long timestamp;
	
	private final String uuid;
	
	private Object contents = null;
	
	private Integer sequence = null;
	
	public RecordImpl(final Long timestamp) {
		this((Path) null, timestamp);
	}
	
	public RecordImpl(final Path path, final Long timestamp) {
		this.path = path;
		this.timestamp = timestamp;
		this.uuid = UUID.randomUUID().toString().replaceAll("_", "");
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
	public Integer getSequence() {
		return sequence;
	}

	@Override
	public void setSequence(Integer sequence) {
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
}
