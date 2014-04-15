package de.dbo.samples.gui.swing.treetable.records;

import java.util.UUID;



public class Record implements Comparable<Record> {
	
	// /A/B/C/D or anything other as a non-path
	private final Path path;
	
	private final Long timestamp;
	
	private final String uuid;
	
	private Object contents = null;
	
	private Integer sequence = null;
	
	public Record(final Long timestamp) {
		this((Path) null, timestamp);
	}
	
	public Record(final Path path, final Long timestamp) {
		this.path = null!=path? path : new Path();
		this.timestamp = timestamp;
		this.uuid = UUID.randomUUID().toString().replaceAll("_", "");
	}
	
	public Record(final String path, final Long timestamp) {
		this(new Path(path), timestamp);
	}

	public Path getPath() {
		return path;
	}
	
	public String getUuid() {
		return uuid;
	}

	@Override
	public int compareTo(Record another) {
		return timestamp.compareTo(another.timestamp);
	}
	
	public Long getTimestamp() {
		return timestamp;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		if (null!=this.sequence) {
			return;
		}
		this.sequence = sequence;
	}

	public Object getContents() {
		return contents;
	}

	public void setContents(Object contents) {
		this.contents = contents;
	}

}
