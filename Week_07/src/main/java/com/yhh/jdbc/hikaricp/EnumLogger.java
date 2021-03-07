package com.yhh.jdbc.hikaricp;

import java.io.File;
import java.io.FileWriter;


public enum EnumLogger {
	INSTANCE;
	private FileWriter writer;
	
	private EnumLogger(){
		File file = new File("log.txt");
		try {
			writer = new FileWriter(file,true);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void log(String message) {
		try {
			writer.write(message);
			writer.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EnumLogger logger = EnumLogger.INSTANCE;
		logger.log("AABBCCDD");
	}

}
