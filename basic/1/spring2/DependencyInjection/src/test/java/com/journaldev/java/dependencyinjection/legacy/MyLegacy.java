package com.journaldev.java.dependencyinjection.legacy;

import com.journaldev.java.legacy.MyApplication;

public class MyLegacy {

	public static void main(String[] args) {
		MyApplication app = new MyApplication();
		app.processMessages("Hi Pankaj", "pankaj@abc.com");
	}

}
