package com.RegistrationPage;

import java.util.HashSet;
import java.util.Set;

import utilities.ExcelWork;

public class UpdateRegistrationDetails extends RegistrationPage {

	// int nameCharsCount = 0;
    Set<String> userNameData = new HashSet<>();
	String nameValidation;
	String dateOfBirthValidation;
	String gmailValidation;
	String mobNumValidation;

	public void registeredDetails() throws InterruptedException {
		Thread.sleep(2000);

		System.out.println("\nCheck your Registration Details :" + "\nName : " + name + "\nDate of Birth  : " + DOB
				+ "\nGmail : " + gmail + "\nMobile Number : " + mobNo);
	}

	public void updatedDetails() {
		System.out.println("\nCheck your Updated Registration Details :" + "\nName : " + name + "\nDate of Birth  : "
				+ DOB + "\nGmail : " + gmail + "\nMobile Number : " + mobNo);
	}

	public void clickOnUpdate(String update) {
		// updatedDetails();

		if (update.equalsIgnoreCase("update")) {
			
			System.err.println("OTP is successfully sent to " + mobNo);
			System.out.println("Enter OTP to update details");
			String otp=scan.next();
			if (otp.length() == 6) {
				excelUtil.writeData();
				System.out.println("Details are successfully updated");
				
			} else {
				System.out.println("Please try again. Entered OTP is wrong");
			}
		}else {
			System.err.println("Unable to update please Type Update and try again");
			clickOnUpdate(scan.next());
		}
	}

	Set<String> updateData;

	public void getLoginData(String userName) throws InterruptedException {
		//excelUtil = new ExcelWork("sheet3");

		int rowsCount = excelUtil.getLastRowNumber();
		for (int i = 0; i <= rowsCount - 1; i++) {
			int cellsCount = excelUtil.getLastCellNumber(i + 1);
			for (int j = 0; j <= cellsCount; j++) {
				updateData = new HashSet<>();
				updateData.add(excelUtil.getData(i + 1, j));
				for (String details : updateData) {
					if (userName.equals(details)) {
						// password = excelUtil.getData(i + 1, 1);
						name = excelUtil.getData(i + 1, 2);
						DOB = excelUtil.getData(i + 1, 3);
						gmail = excelUtil.getData(i + 1, 4);
						mobNo = excelUtil.getData(i + 1, 5);

						registeredDetails();

					}
					break;
				}
			}
		}
	}

	public void checkName() {
		String Name = scan.next();
		scan.nextLine();
		int nameCharsCount = 0;
		if (Name.length() > 16) {
			System.err.println("Name should not be more than 10 characters : ");
			checkName();
		} else {
			char[] nameChars = Name.toCharArray();
			for (int i = 0; i < nameChars.length; i++) {
				if (((Name.charAt(i) >= 'A' && Name.charAt(i) <= 'Z')
						|| (Name.charAt(i) >= 'a' && Name.charAt(i) <= 'z') || (Name.charAt(i) == ' '))) {
				} else {
					nameCharsCount++;
					System.err.println("Name should contain only characters");
					System.out.println("Enter valid name : ");
					checkName();
					break;
				}
			}
			if (nameCharsCount == 0) {
				name = Name;
				nameValidation = "valid";
			}
		}
	}

//================================================================================================================
	public void checkDOB() {
		// System.out.println("Update Date of birth :");
		String dob = scan.next();
		// scan.nextLine();
		String[] dobSplit = dob.split("/");
		int Day = Integer.parseInt(dobSplit[0]);
		int Month = Integer.parseInt(dobSplit[1]);
		int Year = Integer.parseInt(dobSplit[2]);
		if (dob.charAt(2) == '/' && dob.charAt(5) == '/') {
			if ((Day < 01 || Day > 31) || (Month < 01 || Month > 12) || (Year < 0000 || Year > 2023)) {
				System.out.println(Day + "/" + Month + "/" + Year);
				System.err.println("Enter Valid Date of Birth : ");
				checkDOB();
			} else {
				DOB = dob;
				dateOfBirthValidation = "valid";
			}
		} else {
			System.err.println("Enter Valid Date of Birth format : ");
			checkDOB();
		}
	}

	public void checkGmail() {
		String gMAIL = scan.next();
		// scan.nextLine();
		if (!gMAIL.contains("gmail.com") && !gMAIL.contains("gmail.in") && !gMAIL.contains("yahoo.in")
				&& !gMAIL.contains("yahoo.com")) {
			System.err.println("Enter valid gmail : ");
			checkGmail();
		} else if (!gMAIL.contains("@")) {
			System.err.println("Gmail should contain '@'");
			System.out.println("Enter valid gmail : ");
			checkGmail();
		} else if (!(gMAIL.endsWith("com") || gMAIL.endsWith("in"))) {
			System.err.println("Enter valid gmail : ");
			checkGmail();
		} else {
			gmail = gMAIL;
			gmailValidation = "valid";
		}
	}

	public void checkMobNum() {
		String mobNUM = scan.next();
		// scan.nextLine();
		int count = 0;
		if (!(mobNUM.length() == 10)) {
			System.err.println("Mobile number should be 10 digits : ");
			checkMobNum();
		} else {
			char[] MobChars = mobNUM.toCharArray();
			for (int i = 0; i < MobChars.length; i++) {
				if (mobNUM.charAt(i) > 'A' && mobNUM.charAt(i) < 'Z'
						|| mobNUM.charAt(i) > 'a' && mobNUM.charAt(i) < 'z') {
					count++;
					System.err.println("Mobile number should be in number format");
					System.out.println("Enter valid Mobile number : ");
					checkMobNum();
				}
			}
			if (count == 0) {
				mobNo = mobNUM;
				mobNumValidation = "valid";
			}
		}

	}
//====================================================================================================================	

	public void updateUserDetails(String UserName, String Password) throws InterruptedException {

		
		excelUtil = new ExcelWork("sheet3");

		int rowsCounts = excelUtil.getLastRowNumber();
		for (int a = 0; a <= rowsCounts - 1; a++) {
			userNameData.add(excelUtil.getData(a + 1, 0));
		}
		if (userNameData.contains(UserName)) {
			for (int p = 0; p <= userNameData.size() - 1; p++) {
				if ((userNameData.contains(UserName)) && ((excelUtil.getData(p + 1, 1).equals(Password)))) {
					getLoginData(UserName);
					Thread.sleep(2000);
					System.out.println(
							"\nSelect below options to update your details:\n1)Name\n2)Date of birth\n3)Gmail\n4)Mobile Number\n5)Update all");
					
					switch (scan.nextInt()) {
					case 1: {

						System.out.println("Update name :");
						checkName();
						if (nameValidation == "valid") {
							excelUtil.setDatas(p + 1, 2, name);
						}
						System.out.println("Name is successfully updated");
						Thread.sleep(3000);
						updatedDetails();
						System.out.println("\nType Update to update your details");
						clickOnUpdate(scan.next());
						break;
					}
					case 2: {
						System.out.println("Update Date of Birth");
						checkDOB();
						if (dateOfBirthValidation == "valid") {
							excelUtil.setDatas(p + 1, 3, DOB);
						}
						System.out.println("Date of birth is successfully updated");
						Thread.sleep(3000);
						updatedDetails();
						System.out.println("Type Update to update your details");
						clickOnUpdate(scan.next());
						break;
					}
					case 3: {
						System.out.println("Update gmail");
						checkGmail();
						if (gmailValidation == "valid") {
							excelUtil.setDatas(p + 1, 4, gmail);
						}
						System.out.println("Gmail is successfully updated");
						Thread.sleep(3000);
						updatedDetails();
						System.out.println("Type Update to update your details");
						clickOnUpdate(scan.next());
						break;
					}
					case 4: {
						System.out.println("Update mobile number");
						checkMobNum();
						if (mobNumValidation == "valid") {
							excelUtil.setDatas(p + 1, 5, mobNo);
						}
						System.out.println("Mobile number is successfully updated");
						Thread.sleep(3000);
						updatedDetails();
						System.out.println("Type Update to update your details");
						clickOnUpdate(scan.next());
						break;
					}
					case 5: {
						System.out.println("Update name");
						checkName();
						if (nameValidation == "valid") {
							excelUtil.setDatas(p + 1, 2, name);
						}
						System.out.println("Update Date of Birth");
						checkDOB();
						if (dateOfBirthValidation == "valid") {
							excelUtil.setDatas(p + 1, 3, DOB);
						}
						System.out.println("Update gmail");
						checkGmail();
						if (gmailValidation == "valid") {
							excelUtil.setDatas(p + 1, 4, gmail);
							// break;
						}
						System.out.println("Update mobile number");
						checkMobNum();
						if (mobNumValidation == "valid") {
							excelUtil.setDatas(p + 1, 5, mobNo);
						}
						Thread.sleep(3000);
						updatedDetails();
						System.out.println("Type Update to update your details");
						clickOnUpdate(scan.next());
						break;
					}
					default:
						System.out.println("Please select correct option");
					}
					
	//==================================				
					
				}
			
			
			
	//=======================================		
			
			
			}

		} else {
			System.err.println("Either Username or Password is incorrect");
			System.out.println("Re-Enter Username");
			String userN = scan.next();
			System.out.println("Re-Enter Password");
			String PassW = scan.next();
			updateUserDetails(userN, PassW);
		}
	}
}
