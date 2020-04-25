package com.musterproject.custom.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ProgressBar;

import com.musterproject.controller.Links;

public class ApplicationBrowser extends Browser {
	
	private Links link;

	public ApplicationBrowser(Composite parent, int style, Links link) {
		super(parent, style);
		// TODO Auto-generated constructor stub
		this.link = link;
		setUrl(this.link.getLinkURL());
		addProgressListenerToBrowser(parent);
		addLocationListener(new LocationListener() {
			
			@Override
			public void changing(LocationEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Location is changing");
				event.doit = true;
			}
			
			@Override
			public void changed(LocationEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Location has changed");
				event.doit = true;
				System.out.println("New location is:"+getUrl().toString());
			}
		});
	}
	
	private void addProgressListenerToBrowser(Composite parent) {
		// TODO Auto-generated method stub
		final ProgressBar progressBar = new ProgressBar(parent, SWT.NONE);
	    progressBar.setBounds(5,650,600,20);
		addProgressListener(new ProgressListener() {
			
			@Override
			public void completed(ProgressEvent event) {
				// TODO Auto-generated method stub
				progressBar.setSelection(0);
			}
			
			@Override
			public void changed(ProgressEvent event) {
				// TODO Auto-generated method stub
				if (event.total == 0) return;                            
		          int ratio = event.current * 100 / event.total;
		          progressBar.setSelection(ratio);
			}
		});
	}

	public Links getLink() {
		return this.link;
	}
	
	@Override
	public void addLocationListener(LocationListener listener) {
		// TODO Auto-generated method stub
		super.addLocationListener(listener);
	}
	
	@Override
	protected void checkSubclass() {
		// TODO Auto-generated method stub
		//super.checkSubclass();
		System.out.println("Allow Subclassing");
	}

}
