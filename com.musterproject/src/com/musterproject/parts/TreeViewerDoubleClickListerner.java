package com.musterproject.parts;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;

public class TreeViewerDoubleClickListerner implements IDoubleClickListener {
	
	@Inject
	private EPartService partService;
	
	@Inject
	private EModelService modelService;
	
	@Inject
	private MApplication application;

	@Override
	public void doubleClick(DoubleClickEvent event) {
		// TODO Auto-generated method stub
		TreeViewer viewer = (TreeViewer) event.getViewer();
		IStructuredSelection thisSelection = (IStructuredSelection) event
			            .getSelection();
			        
		Object selectedNode = thisSelection.getFirstElement();
		if(selectedNode instanceof Links) {
			Links selected_link = (Links)selectedNode;
			System.out.println("selected link="+selected_link);
			MPart part = partService.createPart("com.musterproject.partdescriptor.BrowserPart");
			part.setObject(selected_link);
			part.setLabel(selected_link.getLineName());
			MPartStack stack = (MPartStack)modelService.find("com.musterproject.partstack.browser", application);
			stack.getChildren().add(part);
			partService.showPart(part, PartState.ACTIVATE);
			//modelService.findElements(application,null,MPartStack.class,null);
			/*for(MPartStack stack:list) {
				System.out.println(stack.getElementId());
			}*/
			
			/*MPart part = MBasicFactory.INSTANCE.createPart();
			part.setLabel(selected_link.getLineName());
			part.setCloseable(true);
			part.setObject(selected_link);
			part.setContributionURI("bundleclass://com.musterproject/com.musterproject.parts.BrowserPart");
			part.setIconURI("platform:/plugin/com.musterproject/icons/icon_browse.png");
			//List<MPartStack> list = modelService.findElements(application,"com.musterproject.partstack.browser",MPartStack.class,null);
			
			partService.showPart(part, PartState.ACTIVATE);*/
			
		}
	}

}
