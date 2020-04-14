package com.musterproject.handlers;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.viewers.TreeViewer;

import com.musterproject.custom.ui.ApplicationBrowser;
import com.musterproject.parts.BrowserPart;
import com.musterproject.parts.Links;
import com.musterproject.parts.Portals;

public class SaveLinkHandler {
	
	@Inject
	private EModelService modelService;
	
	@Inject
	private MApplication application;
	
	@Execute
	public void execute(EPartService partService) {
		MPartStack stack = (MPartStack)modelService.find("com.musterproject.partstack.browser", application);
		MPart part = (MPart) stack.getSelectedElement();
		BrowserPart browserComposite = (BrowserPart)part.getObject();
		ApplicationBrowser browser = browserComposite.getBrowser();
		IEclipseContext context = application.getContext();
		System.out.println("Saving URL:"+browser.getUrl());
		
		if(context.get("categories") != null) {
			System.out.println("Categories is not null");
		}
		
		Portals portalLink = browser.getLink().getParent();
		portalLink.addLink(new Links("My New Link",browser.getUrl().toString(),portalLink));
		TreeViewer viewer = (TreeViewer)context.get("tree");
		viewer.refresh();
	}

}
