package tatoc_javascript;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utility {
	public static String getConfigValue(String key) {
		Properties prop = new Properties();
		try(InputStream ip = new FileInputStream(new File("main.config"))){
			if(ip!=null)
				prop.load(ip);
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		return (String)prop.getProperty(key);
	}
}
