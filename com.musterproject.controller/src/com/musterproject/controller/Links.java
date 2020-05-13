package com.musterproject.controller;

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
		return linkName;
	}
	
	public Portals getParent() {
		return this.parent;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this==obj) return true;
		if(obj == null || obj.getClass()!= this.getClass()) return false;
		Links link = (Links)obj;
		return link.getLineName().equals(this.linkName) && link.getLinkURL().equals(this.linkURL);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int hash = 3;
		hash = 53 * (this.linkName != null ? this.linkName.hashCode() : 0);
		hash = 53 * (this.linkURL != null? this.linkURL.hashCode() : 0);
		return hash;
	}
}
