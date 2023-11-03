package com.LoginPage;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.HomePage.HomePage;
import com.RegistrationPage.RegistrationPage;
import com.RegistrationPage.UpdateRegistrationDetails;

import utilities.ExcelUtility;
import utilities.ExcelWork;
import utilities.PropertyFile;

public class EnterLoginDetails {

	String userNAME;
	String passWORD;;
	public PropertyFile propFile;
	ExcelWork excelUtil;
	Set<String> data;
	Set<String> userNameData;
	String User;
	HomePage homePage;

	Scanner scan = new Scanner(System.in);

	CreateUsernameAndPassword userAndPass = new CreateUsernameAndPassword();

	public void getLoginData(String userName) {
		excelUtil = new ExcelWork("sheet3");
		userNAME = userName;
		int rowsCount = excelUtil.getLastRowNumber();
		for (int i = 0; i <= rowsCount - 1; i++) {
			int cellsCount = excelUtil.getLastCellNumber(i + 1);
			for (int j = 0; j <= cellsCount; j++) {
				data = new HashSet<>();
				data.add(excelUtil.getData(i + 1, j));
				for (String details : data) {
					if (userName.equals(details)) {
						passWORD = excelUtil.getData(i + 1, 1);
						break;
					}
				}
			}
		}
	}

	public void Login(String username, String password) {
		if (username.equals(userNAME) && password.equals(passWORD)) {
			System.out.println("SOSO application home page is displayed");
			homePage = new HomePage();
			homePage.allModules();
		} else {
			System.err.println("Either username or password is incorrect");
			System.out.println("Re-Enter Username");
			String uName = scan.next();
			System.out.println("Re-Enter Password");
			String pass = scan.next();
			Login(uName, pass);
		}
	}

	public void validateUser(String UserName) {
		excelUtil = new ExcelWork("sheet3");
		int rowsCount = excelUtil.getLastRowNumber();
		for (int i = 0; i <= rowsCount - 1; i++) {
			userNameData = new HashSet<>();
			userNameData.add(excelUtil.getData(i + 1, 0));
			for (String usernameDetails : userNameData) {
				// System.out.println(usernameDetails);
				if (UserName.equals(usernameDetails)) {
					//System.out.println("Valid User");
					User = "Valid";
					break;
				}
			}
			if(User=="Valid") {
				break;
			}
		}
	}

	public void LoggingIn(String UserName) throws InterruptedException, IOException {
		validateUser(UserName);
		if (User == "Valid") {
			System.out.println("Enter Password :");
			String PassWord = scan.next();
			getLoginData(UserName);
			Login(UserName, PassWord);
		} else {
			System.err.println("Not a valid user Please register and continue");
			// break;
			RegistrationPage regPage = new RegistrationPage();
			System.out.println("Registration Page of SOSO Application");
			System.out.println("Enter name : ");
			regPage.setName(scan.next());
			System.out.println("Enter Date of birth : ");
			scan.nextLine();
			regPage.setDOB(scan.next());
			System.out.println("Enter Gmail : ");
			regPage.setGmail(scan.next());
			System.out.println("Enter Mobile number : ");
			regPage.setMobNum(scan.next());
			
			System.out.println("Please Create Username and Password for SOSO ");
			CreateUsernameAndPassword userAndPass = new CreateUsernameAndPassword();
			System.out.println("Create User name");
			System.err.println("Username should be Alpha numeric");
			userAndPass.setUsername(scan.next());

			System.out.println("Create Password :");
			System.err.println(
					"Password should be strong,it should contain atleast one uppercase,\nlowercase,number and special character");
			userAndPass.setPassword(scan.next());
			System.out.println("Enter Confirm Password :");
			userAndPass.setCnfmPassword(scan.next());
		}
	}

}
