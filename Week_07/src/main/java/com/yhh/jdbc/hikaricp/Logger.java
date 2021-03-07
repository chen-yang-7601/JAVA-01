package com.yhh.jdbc.hikaricp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class Logger{
	
	private FileWriter writer;
	
	private Logger() {
		File file = new File("log.txt");
		try {
			writer = new FileWriter(file,true);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static class Holder{
		private static final Logger instance = new Logger();
        public static Logger getInstance(){
            return instance;
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
		Logger logger = new Logger.Holder().getInstance();
		logger.log("eeetetete");
	}
}