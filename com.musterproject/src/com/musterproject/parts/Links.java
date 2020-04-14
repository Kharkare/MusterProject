package com.musterproject.parts;

public class Links {
	private String linkName;
	private String linkURL;
	private Portals parent;
	public Links(String linkName, String linkURL, Portals parent) {
		super();
		this.linkName = linkName;
		this.linkURL = linkURL;
		this.parent = parent;
	}
	public String getLineName() {
		return linkName;
	}
	public String getLinkURL() {
		return linkURL;
	}
	@Override
	public String toString() {
		return "Link [lineName=" + linkName + "]";
	}
	
	public Portals getParent() {
		return this.parent;
	}
	
}
