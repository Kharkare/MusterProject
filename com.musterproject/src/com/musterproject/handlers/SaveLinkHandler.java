package com.musterproject.handlers;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Shell;

import com.musterproject.controller.Links;
import com.musterproject.controller.Portals;
import com.musterproject.custom.ui.ApplicationBrowser;
import com.musterproject.parts.BrowserPart;
import com.musterproject.toolbar.control.SaveLinkDialog;

public class SaveLinkHandler {
	
	@Inject
	private EModelService modelService;
	
	@Inject
	private MApplication application;
	
	@Execute
	public void execute(EPartService partService, @Named(IServiceConstants.ACTIVE_SHELL) Shell shell) {
		MPartStack stack = (MPartStack)modelService.find("com.musterproject.partstack.browser", application);
		MPart part = (MPart) stack.getSelectedElement();
		BrowserPart browserComposite = (BrowserPart)part.getObject();
		ApplicationBrowser browser = browserComposite.getBrowser();
		IEclipseContext context = application.getContext();
		System.out.println("Saving URL:"+browser.getUrl());
		Portals portalLink = browser.getLink().getParent();
		SaveLinkDialog dialog = new SaveLinkDialog(shell,portalLink,context,browser.getUrl().toString());
		dialog.open();
	}

}
