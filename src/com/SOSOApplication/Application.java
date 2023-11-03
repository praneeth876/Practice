package com.SOSOApplication;

import java.io.IOException;
import java.util.Scanner;
import com.LoginPage.CreateUsernameAndPassword;
import com.LoginPage.EnterLoginDetails;
import com.LoginPage.ForgetPassword;
import com.RegistrationPage.RegistrationPage;
import com.RegistrationPage.UpdateRegistrationDetails;

public class Application {
	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("MelCoww to SOSO Application");
		Scanner scan = new Scanner(System.in);
		System.out.println("Select option :\n1)Registration Page\n2)Login Page\n3)Update User Details\n4)Forgot Password");
		int select = scan.nextInt();
		switch (select) {
		case 1: {
			RegistrationPage regPage = new RegistrationPage();
			System.out.println("Registration Page of SOSO Application");
			System.out.println("Enter name : ");
			regPage.setName(scan.next());
			System.err.println("Date of Birth should be in this format DD/MM/YYYY ");
			System.out.println("Enter Date of birth : ");
			scan.nextLine();
			regPage.setDOB(scan.next());
			System.out.println("Enter Gmail : ");
			regPage.setGmail(scan.next());
			System.out.println("Enter Mobile number : ");
			regPage.setMobNum(scan.next());
			System.out.println("type submit to register");
			regPage.clicOnRegisterButton(scan.next());

			// System.out.println("Please Create Username and Password for SOSO ");
			CreateUsernameAndPassword userAndPass = new CreateUsernameAndPassword();
			System.out.println("Create User name");
			System.err.println("Username should be Alpha numeric");
			userAndPass.setUsername(scan.next());
			// System.out.println(a.userName);

			System.out.println("Create Password :");
			System.err.println(
					"Password should be strong,it should contain atleast one uppercase,\nlowercase,number and special character");
			userAndPass.setPassword(scan.next());
			// System.out.println(userAndPass.password);
			System.out.println("Enter Confirm Password :");
			userAndPass.setCnfmPassword(scan.next());
			break;
		}
		case 2: {
			EnterLoginDetails login = new EnterLoginDetails();
			System.out.println("Enter username");
			String user = scan.next();
			login.LoggingIn(user);
			break;
		}
		case 3: {		
			System.out.println("Please login to update your details");
			UpdateRegistrationDetails update=new UpdateRegistrationDetails();
			System.out.println("\nEnter UserName");
			String usernamE = scan.next();
			System.out.println("Enter Password");
			String passworD = scan.next();
			update.updateUserDetails(usernamE, passworD);
			scan.nextLine();
			break;
		}
		case 4: {		
			ForgetPassword newPASS = new ForgetPassword();
			System.out.println("Please enter username to reset new password :");
			//System.out.println("Enter username :");
			newPASS.resetPassword(scan.next());
			System.out.println("Enter confirm Password");
			newPASS.setCnfmPassword(scan.next());
			scan.nextLine();
			break;
		}default : {
			System.out.println("Please select correct Option");
		}

		}
	}

}
