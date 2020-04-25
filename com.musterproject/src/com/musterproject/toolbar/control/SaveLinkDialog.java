package com.musterproject.toolbar.control;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.musterproject.controller.Links;
import com.musterproject.controller.Portals;
import com.musterproject.database.DatabaseService;

public class SaveLinkDialog extends TitleAreaDialog {
	
	private Text link_name;
    private Text link;
    private Portals portal;
    private IEclipseContext context;
    private String browser_URL;

	public SaveLinkDialog(Shell parentShell,Portals portal,IEclipseContext context,String URL) {
		super(parentShell);
		// TODO Auto-generated constructor stub
		this.portal = portal;
		this.context = context;
		this.browser_URL = URL;
	}
	
	@Override
    public void create() {
        super.create();
        setTitle("Save Link");
        setMessage("Save this application", IMessageProvider.INFORMATION);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite area = (Composite) super.createDialogArea(parent);
        Composite container = new Composite(area, SWT.NONE);
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        GridLayout layout = new GridLayout(2, false);
        container.setLayout(layout);

        createLinkName(container);
        createLinkText(container);

        return area;
    }

    private void createLinkName(Composite container) {
        Label lbtFirstName = new Label(container, SWT.NONE);
        lbtFirstName.setText("Application Name");

        GridData dataFirstName = new GridData();
        dataFirstName.grabExcessHorizontalSpace = true;
        dataFirstName.horizontalAlignment = GridData.FILL;

        link_name = new Text(container, SWT.BORDER);
        link_name.setLayoutData(dataFirstName);
    }

    private void createLinkText(Composite container) {
        Label lbtLastName = new Label(container, SWT.NONE);
        lbtLastName.setText("Application Link");

        GridData dataLastName = new GridData();
        dataLastName.grabExcessHorizontalSpace = true;
        dataLastName.horizontalAlignment = GridData.FILL;
        link = new Text(container, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        link.setText(this.browser_URL);
        link.setLayoutData(dataLastName);
    }
    
    @Override
    protected boolean isResizable() {
        return true;
    }

    // save content of the Text fields because they get disposed
    // as soon as the Dialog closes
    private void saveInput() {
        String link_name_text = link_name.getText().trim();
        String link_text = link.getText().trim();
        DatabaseService dbservice = (DatabaseService)this.context.get("DBInstance");
        Links new_link = new Links(link_name_text, link_text, this.portal);
        dbservice.saveLink(new_link);
        this.portal.addLink(new_link);
		TreeViewer viewer = (TreeViewer)this.context.get("tree");
		viewer.refresh();
    }

    @Override
    protected void okPressed() {
        saveInput();
        super.okPressed();
    }
}
