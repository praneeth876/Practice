package com.LoginPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.HomePage.HomePage;
import com.RegistrationPage.RegistrationPage;

import utilities.ExcelUtility;
import utilities.ExcelWork;
import utilities.PropertyFile;

public class CreateUsernameAndPassword  {

	public String userName;
	public String password;
	public String cnfmPassword;
	Scanner scan = new Scanner(System.in);
	PropertyFile propfile;
	Set<String> usernameData;
	String user;
	int rowCount;
	HomePage homePage;
	
	ExcelWork excelUtil;	

	public void setUsername(String userName) throws IOException {
		validateUsername(userName);
		int userCharCount=0;
		if (user != "Valid") {
			if (userName.length() <= 16 && userName.length() >= 3) {
				char[] userNameChars = userName.toCharArray();
				for (int i = 0; i < userNameChars.length; i++) {
					if (!((userName.charAt(i) >= 'A' && userName.charAt(i) <= 'Z')
							|| (userName.charAt(i) >= 'a' && userName.charAt(i) <= 'z') || (userName.charAt(i) == ' ')
							|| (userName.charAt(i) >= '0' && userName.charAt(i) <= '9'))) {					
						userCharCount++;
//						System.out.println("In If"+userName.charAt(i));
//						System.out.println(userCharCount);
						
						System.err.println("Name can only be Alpha Numeric characters");
						System.out.println("Enter valid name : ");
						setUsername(scan.next());
						scan.nextLine();
					} 
				}		
					if(userCharCount==0) {
						this.userName=userName;
						excelUtil = new ExcelWork("sheet3");
						rowCount = excelUtil.getLastRowNumber();
						//System.out.println("Username"+rowCount);
						excelUtil.setDatas(rowCount , 0, userName);
					}
				
			} else {
				System.err.println("User name should contain atleast 3 char and should not be more than 16 chars");
				setUsername(scan.next());
			}
		} else {
			System.err.println("Username is already exist.Select different name");
			setUsername(scan.next());
			scan.nextLine();
		}
	}

	public void setPassword(String password) throws IOException {
		if (password.length() <= 16 && password.length() >= 3) {
			char[] passwordChars = password.toCharArray();
			int lowletterCount = 0;
			int UpletterCount = 0;
			int numCount = 0;
			int splCharCount = 0;
			for (int i = 0; i < passwordChars.length; i++) {
				if ((password.charAt(i) >= 'A' && password.charAt(i) <= 'Z')) {
					UpletterCount++;
				} else if ((password.charAt(i) >= 'a' && password.charAt(i) <= 'z')) {
					lowletterCount++;

				} else if ((password.charAt(i) >= '0' && password.charAt(i) <= '9')) {
					numCount++;
				} else if ((password.charAt(i) == ' ' || password.charAt(i) == '@' || password.charAt(i) == '#'
						|| password.charAt(i) == '$' || password.charAt(i) == '&' || password.charAt(i) == '*'
						|| password.charAt(i) == '_')) {
					splCharCount++;
				}
			}
			if (lowletterCount != 0 && UpletterCount != 0 && numCount != 0 && splCharCount != 0) {
				System.out.println("Password is very strong");
				this.password = password;
				//System.out.println("Password"+rowCount);
				excelUtil.setDatas(rowCount, 1, password);
			} else {
				System.err.println("Create strong password : ");
				setPassword(scan.next());
				scan.nextLine();
			}
		} else {
			System.err.println("Password should contain atleast 3 char and should not be more than 16 chars");
			setPassword(scan.next());
		}
	}

	public void setCnfmPassword(String cnfmPassword) {
		this.cnfmPassword = cnfmPassword;
		excelUtil.writeData();
		clickOnSignIn();
	}
	public void clickOnSignIn() {
		if (cnfmPassword.equals(password)) {
			System.out.println("Passwords are matched");
			System.out.println("Type signIn to Login to SOSO Application");
			if (scan.next().equalsIgnoreCase("SignIn")) {
				System.out.println("Successfully navigated to Home page of SOSO Application");
				homePage = new HomePage();
				homePage.allModules();
			} else {
				System.err.println("Unable to signIn. Please type correct signIn spelling");
			}
		} else {
			System.err.println("Confirm password is not matched with entered password");
			setCnfmPassword(scan.next());
		}
	}

	public void validateUsername(String UserName) {
		excelUtil = new ExcelWork("sheet3");
		int rowsCount = excelUtil.getLastRowNumber();
		for (int i = 0; i <= rowsCount - 1; i++) {
			usernameData = new HashSet<>();
			usernameData.add(excelUtil.getData(i + 1, 4));
			for (String usernameDetails : usernameData) {
				if (UserName.equals(usernameDetails)) {
					user = "Valid";
					break;
				}
			}
		}
	}
}
