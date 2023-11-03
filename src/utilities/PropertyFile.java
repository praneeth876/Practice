package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFile {
	public FileInputStream fis;
	public FileOutputStream fos;
	public Properties prop = new Properties();

	public void initializePropFile() {
		try {
			fis = new FileInputStream("./src/com/Files/propertiesFile.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setUsername(String user) {
		prop.setProperty("username", user);
	}

	public void setPassword(String pass) {
		prop.setProperty("password", pass);
	}

	public String getUserName() {
		return prop.getProperty("username");
	}

	public String getPassWord() {
		return prop.getProperty("password");
	}

	public void save()  {
		try {
			fos = new FileOutputStream("./src/com/Files/propertiesFile.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// prop=new Properties();
		try {
			prop.store(fos, "Data is stored Successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
