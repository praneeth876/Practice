package com.RegistrationPage;

import java.util.Scanner;

import utilities.ExcelWork;
import utilities.JavaUtility;

public class RegistrationPage {

	public String name;
	public String DOB;
	public String gmail;
	public String mobNo;
	public String OTP;

	JavaUtility javaUtil;
	public Scanner scan = new Scanner(System.in);
	public ExcelWork excelUtil;
	public int rowCount;
	
	
	
	public void setName(String name) {
		if (name.length() > 16) {
			System.err.println("Name should not be more than 10 characters : ");
			setName(scan.next());
		} else {
			int charsCount = 0;
			char[] nameChars = name.toCharArray();
			for (int i = 0; i < nameChars.length; i++) {
				if (!((name.charAt(i) >= 'A' && name.charAt(i) <= 'Z')
						|| (name.charAt(i) >= 'a' && name.charAt(i) <= 'z') || name.charAt(i) == ' ')) {
					charsCount++;
					//System.out.println(charsCount);
					System.err.println("Name should contain only characters");
					System.out.println("Enter valid name : ");
					setName(scan.next());
					scan.nextLine();
				}
			}
			if (charsCount == 0) {
				this.name = name;
				excelUtil = new ExcelWork("sheet3");
				rowCount = excelUtil.getLastRowNumber();
				System.out.println("name" + rowCount);
				excelUtil.setData(rowCount + 1, 2, name);
			}
		}
	}

	public void setDOB(String DOB) {
		String[] dobSplit = DOB.split("/");
		int Day = Integer.parseInt(dobSplit[0]);
		int Month = Integer.parseInt(dobSplit[1]);
		int Year = Integer.parseInt(dobSplit[2]);
		if (DOB.charAt(2) == '/' && DOB.charAt(5) == '/') {
			if ((Day < 01 || Day > 31) || (Month < 01 || Month > 12) || (Year < 0000 || Year > 2023)) {
				System.out.println(Day + "/" + Month + "/" + Year);
				System.err.println("Enter Valid Date of Birth : ");
				setDOB(scan.next());
			} else {
				this.DOB = DOB;
				// excelUtillity=new ExcelWork("sheet3");
				//System.out.println("DOB" + rowCount);
				excelUtil.setDatas(rowCount + 1, 3, DOB);

			}
		} else {
			System.err.println("Enter Valid Date of Birth format : ");
			setDOB(scan.next());
		}
	}

	public void setGmail(String gmail) {
		if (!gmail.contains("gmail.com") && !gmail.contains("gmail.in") && !gmail.contains("yahoo.in")
				&& !gmail.contains("yahoo.com")) {
			System.err.println("Enter valid gmail : ");
			setGmail(scan.next());
		} else if (!gmail.contains("@")) {
			System.err.println("Gmail should contain '@'");
			System.out.println("Enter valid gmail : ");
			setGmail(scan.next());
		} else if (!(gmail.endsWith("com") || gmail.endsWith("in"))) {
			System.err.println("Enter valid gmail : ");
			setGmail(scan.next());
		} else {
			this.gmail = gmail;
			//System.out.println("gmail" + rowCount);
			excelUtil.setDatas(rowCount + 1, 4, gmail);
		}
	}

	public void setMobNum(String mobNo) {
		int count = 0;
		if (!(mobNo.length() == 10)) {
			System.err.println("Mobile number should be 10 digits : ");
			setMobNum(scan.next());
		} else {
			char[] MobChars = mobNo.toCharArray();
			for (int i = 0; i < MobChars.length; i++) {
				if (mobNo.charAt(i) > 'A' && mobNo.charAt(i) < 'Z' || mobNo.charAt(i) > 'a' && mobNo.charAt(i) < 'z') {
					count++;
					System.err.println("Mobile number should be in number format");
					System.out.println("Enter valid Mobile number : ");
					setMobNum(scan.next());
				}
			}
			if (count == 0) {
				this.mobNo = mobNo;
				//System.out.println("mobNo" + rowCount);
				excelUtil.setDatas(rowCount + 1, 5, mobNo);
			}
		}
	}

	public void clicOnRegisterButton(String click) {
		excelUtil.writeData();
		if (click.equalsIgnoreCase("Submit")) {
			System.out.println("6 digit OTP is successfully sent to your mobile number");
			setOTP(scan.next());
			System.out
					.println("\nSuccessfully registered to SOSO application. Create UserName and Password to Sign In");
		} else {
			System.out.println("Type Submit to Register");
			clicOnRegisterButton(scan.next());
		}
	}

	public void setOTP(String oTP) {
		if (oTP.length() == 6) {
			this.OTP = oTP;
		} else if (oTP.length() != 6) {
			System.err.println("Enter valid 6 digit OTP : ");
			setOTP(scan.next());
		} else {
			char[] OTPChars = oTP.toCharArray();
			for (int i = 0; i < OTPChars.length; i++) {
				if (oTP.charAt(i) > 'A' && oTP.charAt(i) < 'Z' || oTP.charAt(i) > 'a' && oTP.charAt(i) < 'z') {
					System.err.println("OTP should be in number format");
					System.out.println("Enter valid OTP : ");
					setOTP(scan.next());
				}
			}
		}
	}

	

	
	
	
	
}
