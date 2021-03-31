package com.example.Preclaimupdate.common;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomMethods {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomMethods.class);
	
	public static List<String> getUploadType()
	{
		List<String> uploadType = new ArrayList<String>();
		uploadType.add("pdf1");
		uploadType.add("pdf2");
		uploadType.add("pdf3");
		uploadType.add("image");
		uploadType.add("audio");
		uploadType.add("video");
		uploadType.add("signature");
		uploadType.add("excel");
		return uploadType;
	}
	
	public static void logError(Exception e)
	{
		String error_message = "*************" + e.getClass() + "*************\n";
		LOGGER.error(e.getMessage());
		LOGGER.error("Error", e.getCause());
		StackTraceElement[] trace = e.getStackTrace();
	    for (StackTraceElement traceElement : trace)
	        error_message += "\tat " + traceElement + "\n";
		LOGGER.error(error_message);
		
	}

}
