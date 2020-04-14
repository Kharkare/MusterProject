package com.musterproject.handlers;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class BrowserForwardHandler {
	@Inject
	private EModelService modelService;
	
	@Inject
	private MApplication application;
	
	@Execute
	public void execute(EPartService partService) {
		
	}
}
