package com.musterproject;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.musterproject.database.DatabaseService;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) {
		Activator.context = bundleContext;
		System.out.println("Starting RCP application");
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("Closing RCP application");
	}

}
