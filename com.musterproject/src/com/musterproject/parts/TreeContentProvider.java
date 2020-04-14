package com.musterproject.parts;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;

public class TreeContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		return getChildren(inputElement);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
		Portals portal = (Portals)parentElement;
		System.out.println("Portal Name: "+portal.getPortalName());
		ArrayList children = new ArrayList();
	    children.addAll(portal.getPortalSubCategories());
	    children.addAll(portal.getLinks());
	    
	    if (children.size() == 0) {
	       return new Object[0];
	    }
	    return children.toArray();
		
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		if (element instanceof Portals) {
	         return ((Portals) element).getParent();
	      }
	       
	      return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		if (element instanceof Portals) {
			Portals category = (Portals) element;
	       
	      return category.hasPortalSubCategories() || category.hasLinks();
	    }
	       
	    return false;
	}

}
