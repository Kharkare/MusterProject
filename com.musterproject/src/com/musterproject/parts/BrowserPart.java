package com.musterproject.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.musterproject.controller.Links;
import com.musterproject.controller.Portals;
import com.musterproject.custom.ui.ApplicationBrowser;

public class BrowserPart {

	private ApplicationBrowser browser;

	@Inject
	private MPart part;
	
	@Inject
	IEclipseContext context;
	
	@PostConstruct
	public void createComposite(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Links link = (Links)part.getObject();
		link = link==null ? getLinksObject() : link;
		part.setLabel(link.getLineName());
		browser = new ApplicationBrowser(parent, SWT.NONE, link);
		browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
	}
	
	public ApplicationBrowser getBrowser() {
		return this.browser;
	}
	
	@Persist
	public void save() {
		part.setDirty(false);
	}
	
	private Links getLinksObject() {
		TreeViewer viewer = (TreeViewer)this.context.get("tree");
		Portals portal = (Portals)viewer.getTree().getItem(1).getData();
		Links link = portal.getLinks().get(0);
		return link;
	}
	
}
