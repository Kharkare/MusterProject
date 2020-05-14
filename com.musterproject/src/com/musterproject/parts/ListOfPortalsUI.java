package com.musterproject.parts;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.musterproject.controller.Links;
import com.musterproject.controller.Portals;

public class ListOfPortalsUI {

	private TreeViewer treeViewer;
	private Portals rootCategory;

	@Inject
	private MPart part;
	
	@Inject
	MApplication application;
	
	@Inject
	IEclipseContext context;

	@PostConstruct
	public void createComposite(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		
		rootCategory = (Portals) context.get("portals");
		
		treeViewer = new TreeViewer(parent);
        treeViewer.setContentProvider(new TreeContentProvider());
        treeViewer.getTree().setHeaderVisible(true);
        treeViewer.getTree().setLinesVisible(true);

        TreeViewerColumn viewerColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
        viewerColumn.getColumn().setWidth(300);
        viewerColumn.getColumn().setText("Application Portals");
        viewerColumn.setLabelProvider(new ColumnLabelProvider());
        treeViewer.setAutoExpandLevel(1);
        treeViewer.setInput(rootCategory);
        IDoubleClickListener doubleclick_listener =  new TreeViewerDoubleClickListerner();
        ContextInjectionFactory.inject(doubleclick_listener, application.getContext());
        treeViewer.addDoubleClickListener(doubleclick_listener);
        treeViewer.expandAll();
		treeViewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		application.getContext().set("categories", rootCategory);
		application.getContext().set("tree", treeViewer);
		
	}

	@Focus
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}

	@Persist
	public void save() {
		part.setDirty(false);
	}
	
}