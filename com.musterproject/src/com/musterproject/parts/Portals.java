package com.musterproject.parts;

import java.util.ArrayList;

public class Portals {
	private ArrayList<Links> links = new ArrayList<Links>();
	private ArrayList<Portals> portalSubCategories = new ArrayList<Portals>();
	private String portalName;
	private Portals parent;
	public Portals(String portalName,Portals parent) {
		super();
		this.portalName = portalName;
		this.parent = parent;
	}
	public void addSubCategories(Portals portalSubCategories) {
		this.portalSubCategories.add(portalSubCategories);
	}
	public ArrayList getPortalSubCategories() {
	    return this.portalSubCategories;
	}
	public boolean hasPortalSubCategories() {
		return this.portalSubCategories.size()>0;
	}
	public Portals getParent() {
		return this.parent;
	}
	public ArrayList<Links> getLinks() {
		return links;
	}
	public void setLinks(ArrayList<Links> links) {
		this.links = links;
	}
	public String getPortalName() {
		return portalName;
	}
	public void setPortalName(String portalName) {
		this.portalName = portalName;
	}
	public void addAllLinks(ArrayList<Links> links) {
		this.links.addAll(links);
	}
	public void addLink(Links link) {
		this.links.add(link);
	}
	public boolean hasLinks() {
		return this.links.size()>0;
	}
	@Override
	public String toString() {
		return "Portal [portalName=" + portalName + "]";
	}
	
}
