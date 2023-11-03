package com.LoginPage;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.HomePage.HomePage;
import com.RegistrationPage.UpdateRegistrationDetails;

import utilities.ExcelWork;

public class ForgetPassword extends UpdateRegistrationDetails {

	 //Scanner scan=new Scanner(System.in);
	Set<String> userNames = new HashSet<>();
	String password;
	String cnfmPassword;
	String newPasswordValidation;
	String MobNumber;

	public void resetPassword(String username) {

		excelUtil = new ExcelWork("sheet3");
		// excelUtil.getLastRowNumber();
		for (int x = 0; x <= excelUtil.getLastRowNumber() - 1; x++) {
			userNames.add(excelUtil.getData(x + 1, 0));

		}
		//System.out.println(userNames);
      if(userNames.contains(username)) {
    	  System.out.println("Enter your Registered mobile number");
    	String  MobileNumber=scan.next();
		for (int y = 0; y <= userNames.size() - 1; y++) {
           // System.out.println(MobNumber=excelUtil.getData(y + 1, 5));
			if (userNames.contains(username)&& (excelUtil.getData(y + 1, 5).equals(MobileNumber))) {
                MobNumber=excelUtil.getData(y + 1, 5);
				System.out.println("Enter new Password");
				System.err.println(
						"Password should be strong,it should contain atleast one uppercase,\nlowercase,number and special character");
				checkNewPassword();

				if (newPasswordValidation == "valid") {
					excelUtil.setDatas(y + 1, 1, password);
					//System.out.println(password);
					break;
				}
			}
		}
      }else {
    	  System.err.println("Please Enter valid username to reset password");
    	  //System.out.println();
    	  resetPassword(scan.next());
    	  
      }
	}
	
	public void setCnfmPassword(String cnfmPassword) {
		this.cnfmPassword = cnfmPassword;
		
		clickOnReset();
	}
	
	public void clickOnReset() {
		
		if (cnfmPassword.equals(password)) {
			System.err.println("Passwords are matched");
			System.out.println("Type next to send OTP to "+MobNumber);
			if(scan.next().equalsIgnoreCase("next")) {
				System.out.println("Please Enter OTP to reset password");
			if(scan.next().length()==5) {
			System.out.println("OTP validation is successfull. Type reset to update password");
			//System.out.println("Type reset to update password");
			if (scan.next().equalsIgnoreCase("reset")) {
				excelUtil.writeData();
				System.out.println("Password is Successfully updated");
				
			} else {
				System.err.println("Unable to reset. Please type correct reset spelling");
			}
		} else {
			System.err.println("Entered OTP is Incorrect. Resend OTP");
			clickOnReset();
		}
	}else {
		System.out.println("Re-enter next text");
		clickOnReset();
	}
	}else {
		System.err.println("Confirm password is not matched with entered password");
		setCnfmPassword(scan.next());
	}
	
	}
	public void checkNewPassword() {
		String newpassword = scan.next();

		if (newpassword.length() <= 16 && newpassword.length() >= 3) {
			char[] passwordChars = newpassword.toCharArray();
			int lowletterCount = 0;
			int UpletterCount = 0;
			int numCount = 0;
			int splCharCount = 0;
			for (int i = 0; i < passwordChars.length; i++) {
				if ((newpassword.charAt(i) >= 'A' && newpassword.charAt(i) <= 'Z')) {
					UpletterCount++;
				} else if ((newpassword.charAt(i) >= 'a' && newpassword.charAt(i) <= 'z')) {
					lowletterCount++;

				} else if ((newpassword.charAt(i) >= '0' && newpassword.charAt(i) <= '9')) {
					numCount++;
				} else if ((newpassword.charAt(i) == ' ' || newpassword.charAt(i) == '@' || newpassword.charAt(i) == '#'
						|| newpassword.charAt(i) == '$' || newpassword.charAt(i) == '&' || newpassword.charAt(i) == '*'
						|| newpassword.charAt(i) == '_')) {
					splCharCount++;
				}
			}
			if (lowletterCount != 0 && UpletterCount != 0 && numCount != 0 && splCharCount != 0) {
				System.out.println("Password is very strong");
				password = newpassword;
				newPasswordValidation = "valid";

			} else {
				System.err.println("Create strong password : ");
				checkNewPassword();
				scan.nextLine();
			}
		} else {
			System.err.println("Password should contain atleast 3 char and should not be more than 16 chars");
			checkNewPassword();
		}
	}

}
