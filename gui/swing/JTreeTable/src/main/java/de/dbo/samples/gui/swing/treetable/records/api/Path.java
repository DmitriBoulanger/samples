package de.dbo.samples.gui.swing.treetable.records.api;


public interface Path {
	
	public static final char PATH_SEPARATOR_CHAR = '/';
	public static final String PATH_SEPARATOR = String.valueOf(PATH_SEPARATOR_CHAR);

	public abstract boolean isChildOf(Path parent);

	public abstract boolean isParentOf(Path child);

	/**
	 * the same path is not a sibling
	 * @param sibling
	 * @return
	 */
	public abstract boolean isSiblingOf(Path sibling);

	
	public abstract String pathElement(int depth);

	public abstract String canonicalValue();

	public abstract String parentCanonicalValue();

	public abstract int depth();

	public abstract StringBuilder print();

}