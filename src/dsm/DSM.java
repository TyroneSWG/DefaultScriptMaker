package dsm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Properties;



public class DSM {

	private String core2Location;
	private String iffsToConvert;
	
	public static void main(String[] args) {

		Properties config = new Properties();
		try {
			FileInputStream inputStream = new FileInputStream("./config.cfg");
			config.load(inputStream);
			
			makeScripts(config.getProperty("IFFS"));
			System.out.println("None!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void makeScripts(String iffLocation) throws IOException {
		File iffs = new File(iffLocation);
		
		if(!iffs.isDirectory())
			return;
		
		FileInputStream inputStream = new FileInputStream("./config.cfg");
		Properties config = new Properties();
		
		config.load(inputStream);
		
		File exportFolder = new File(config.getProperty("EF"));
		exportFolder.mkdir();
		String ef = config.getProperty("EF");
		System.out.println("ef: " + ef + " " + exportFolder.getPath());
		for(File f : iffs.listFiles()) {
			//String[] s = 
			
			if (f.isDirectory()) {
				File outputFolder = new File(ef + f.getPath().replace("\\", "/").split(config.getProperty("IFFS"))[1]);
				System.out.println(f.getAbsolutePath());
				outputFolder.mkdir();
				//if(iffs.)
				makeSubScripts(f, config);
				continue;
			}
			
			System.out.println(ef + f.getPath().replace("\\", "/").split(config.getProperty("IFFS"))[1] + ".py");
			makeBaseScript(f, config);
		}
		
	}
	
	public static void makeSubScripts(File fileLocation, Properties config) throws IOException {
		for (File f : fileLocation.listFiles()) {
			makeBaseScript(f, config);
		}
	}

	public static void makeBaseScript(File f, Properties config) throws IOException{
		//System.out.println("." + f.getPath().replace("\\", "/").split(config.getProperty("IFFS"))[1] + ".py");
		String ef = config.getProperty("EF").replace("\\", "/");
		String path = ef + f.getPath().replace("\\", "/").split(config.getProperty("IFFS"))[1].replace("iff", "py").replace("shared_", "");
		
		//path = path + ".py";
		//System.out.println("Path string: " + path);
		File outputFile = new File(path);
		String filePath = outputFile.getAbsolutePath();
		//System.out.println("Line 85: " + filePath);
        //FileWriter fw = new FileWriter(outputFile.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile.getAbsolutePath()));
        
        bw.write("import sys");
        bw.newLine();
        bw.newLine();
        bw.write("def setup(core, object):");
        bw.write("	return");
        bw.close();
        System.out.println("Created file " + outputFile.getAbsolutePath());
	}
	public String getCore2Location() {
		return core2Location;
	}

	public void setCore2Location(String core2Location) {
		this.core2Location = core2Location;
	}

	public String getIffsToConvert() {
		return iffsToConvert;
	}

	public void setIffsToConvert(String iffsToConvert) {
		this.iffsToConvert = iffsToConvert;
	}
	
	
}
