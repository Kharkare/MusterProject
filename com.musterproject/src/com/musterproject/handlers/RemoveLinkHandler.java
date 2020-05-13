package com.musterproject.handlers;



import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MStackElement;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Shell;

import com.musterproject.controller.Links;
import com.musterproject.controller.Portals;
import com.musterproject.database.DatabaseService;

public class RemoveLinkHandler {
	
	@Inject
	private EModelService modelService;
	
	@Inject
	private MApplication application;
	
	@Inject
	private EPartService service;
	
	@Inject
	IEclipseContext context;
	
	@Execute
	public void execute(EPartService partService, @Named(IServiceConstants.ACTIVE_SHELL) Shell shell) {
		
		TreeViewer viewer = (TreeViewer)this.context.get("tree");
		IStructuredSelection selection = (IStructuredSelection)viewer.getSelection();
		Object selectedObject = selection.getFirstElement();
		if(selectedObject instanceof Links) {
			Links selected_link = (Links)selectedObject;
			System.out.println("selected link to remove="+selected_link);
			MPartStack stack = (MPartStack)modelService.find("com.musterproject.partstack.browser", application);
			closeMPart(stack,selected_link);
			DatabaseService dbservice = (DatabaseService)this.context.get("DBInstance");
			dbservice.deleteLink(selected_link);
			Portals portal = selected_link.getParent();
			portal.removeLink(selected_link);
			viewer.refresh();
		}
		
		
		
	}
	
	private void closeMPart(MPartStack stack, Links selected_link) {
		List<MStackElement> mparts = stack.getChildren();
		MPart partToRemove = null;
		for(MStackElement element: mparts) {
			MPart part = (MPart)element;
			if(part.getLabel().trim().toLowerCase().equals(selected_link.getLineName().trim().toLowerCase())){
				System.out.println("Closing part");
				partToRemove = part;
				break;
			}
		}
		service.hidePart(partToRemove, true);
	}

}
