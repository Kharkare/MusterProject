package com.musterproject.parts;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
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

public class SamplePart {

	private TreeViewer treeViewer;
	private Portals rootCategory;

	@Inject
	private MPart part;
	
	@Inject
	MApplication application;

	@PostConstruct
	public void createComposite(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		rootCategory = new Portals("Jobs", null);
		Portals portalLinkedin = new Portals("LinkedIn",rootCategory);
		portalLinkedin.addLink(new Links("Accenture Software Engineer", "https://www.linkedin.com/jobs/view/1813655208",portalLinkedin));
		portalLinkedin.addLink(new Links("SDE II", "https://www.linkedin.com/jobs/view/1808746393",portalLinkedin));
		rootCategory.addSubCategories(portalLinkedin);
		Portals portalXing = new Portals("Xing",rootCategory);
		portalXing.addLink(new Links("Java Fullstack", "https://www.xing.com/jobs/hamburg-java-fullstack-entwickler-nps-57369044?paging_context=search&search_query%5Bkeywords%5D=java&search_query%5Blimit%5D=20&search_query%5Blocation%5D=&search_query%5Boffset%5D=0&search_query%5Bradius%5D=&ijt=jb_18",portalXing));
		portalXing.addLink(new Links("Java job", "Some URL", portalXing));
		rootCategory.addSubCategories(portalXing);
		treeViewer = new TreeViewer(parent);
        treeViewer.setContentProvider(new TreeContentProvider());
        treeViewer.getTree().setHeaderVisible(true);
        treeViewer.getTree().setLinesVisible(true);

        TreeViewerColumn viewerColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
        viewerColumn.getColumn().setWidth(300);
        viewerColumn.getColumn().setText("Names");
        viewerColumn.setLabelProvider(new ColumnLabelProvider());
        treeViewer.setAutoExpandLevel(1);
        treeViewer.setInput(rootCategory);
        IDoubleClickListener doubleclick_listener =  new TreeViewerDoubleClickListerner();
        ContextInjectionFactory.inject(doubleclick_listener, application.getContext());
        treeViewer.addDoubleClickListener(doubleclick_listener);
        
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
	
	/*private List<String> createInitialDataModel() {
		return Arrays.asList("Sample item 1", "Sample item 2", "Sample item 3", "Sample item 4", "Sample item 5");
	}*/
}