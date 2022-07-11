import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class BankLogin {
	
	public BankLogin() throws FileNotFoundException, IOException {
		System.out.println("Object BankLogin is created!\n");
		
		checkUser(new BankKuDat().getKuDatLoc());
	}
	
	private void checkUser(String dbUserLocation) throws FileNotFoundException, IOException {
		String userRole, userAccNo, userId, userPass, userName, userDob, userAddr, userMom, userJob, userSalaryRange, userBalance, userStatus;
		BankKuDat bkd = new BankKuDat();
		String userInput;
		Scanner userInputScan = new Scanner(System.in);
		
		/// CHECKING VALIDITY of USER-ID and PASSWORD are VALID or NOT ///
		boolean matchLogin = false;
		while (matchLogin == false) {
			/// MATCHING UP User-ID & Password ///
			System.out.print("User ID: "); //asking User-id
			bkd.setUserId(userInputScan.nextLine());
			
			System.out.print("Password: "); //asking Password
			bkd.setUserPass(userInputScan.nextLine());
			
			bkd.checkLogin(); //VALIDATE entered USER-ID and PASSWORD
			matchLogin = bkd.getCheckLogin();
			
			if (matchLogin == false) {
				System.out.println("\nLogin doesn't match any data. Try again! ");
			}
		}
		System.out.println("\nLogin success!");
		
		/// SETTING ALL USER's DATA ///
		userRole = bkd.getUserRole();
		userAccNo = bkd.getUserAccNo();
		userId = bkd.getUserId();
		userPass = bkd.getUserPass();
		userName = bkd.getUserName();
		userDob = bkd.getUserDob();
		userAddr = bkd.getUserAddr();
		userMom = bkd.getUserMom();
		userJob = bkd.getUserJob();
		userSalaryRange= bkd.getUserSalaryRange();
		userBalance = bkd.getUserBalance();
		userStatus = bkd.getUserStatus();
		
		/// CHECKING USER's ROLE ///
		boolean correctRole = false;
		if (bkd.getUserRole().equals("admin")) {
			System.out.print("Which login role do you want choose: ");
			
			while (correctRole == false) {
				System.out.print("\n1) Admin"
						+ "\n2) Customer"
						+ "\nChoose one (1 or 2): ");
				userInput = userInputScan.nextLine();
				
				if (userInput.equals("1")) {
					correctRole = true;
					new BankMenuAdmin(userName);
				} else if (userInput.equals("2")) {
					correctRole = true;

					new BankMenuCust(userRole, userAccNo, userId, userPass, userName, userDob, userAddr, userMom, userJob, userSalaryRange, userBalance, userStatus);
				} else {
					System.out.println("\nWrong input '" + userInput + "'!");
					System.out.println("Please enter correct formatted input choice (1 or 2)");
				}
			}
			userInputScan.close();
		} else if (bkd.getUserRole().equals("cust")) {
			new BankMenuCust(userRole, userAccNo, userId, userPass, userName, userDob, userAddr, userMom, userJob, userSalaryRange, userBalance, userStatus);
		}
	}

	/*
	void eBanking() {
		LinkedHashMap<String, String> userAuth = new LinkedHashMap<String, String>();
		userAuth.put("Super Admin", "adm123");
		userAuth.put("user1", "pass");
		userAuth.put("Admin", "adm234");
		
		System.out.print("Existing user: ");
		for (String userId : userAuth.keySet()) {
			System.out.print(userId + ", ");
		}
		
		System.out.print("\nExisting pass: ");
		for (String pass : userAuth.values()) {
			System.out.print(pass + ", ");
		}
		
		System.out.println("\nTotal user is " + userAuth.size());
		
		//LOGIN
		System.out.println("\nPlease Login. Enter your ID and password.");
		
		Scanner inputLine = new Scanner(System.in);
		
		System.out.print("\nUser ID: ");
		String inputAuth = inputLine.nextLine();
		
		//CHECKING ID
		while (userAuth.get(inputAuth) == null) {
			System.out.println("User '" + inputAuth + "' not exist! Please enter the correct one.");
			System.out.print("\nUser ID: ");
			inputAuth = inputLine.nextLine();
		}
		
		String inputID = inputAuth;
		//System.out.println("inputID = " + inputID + ", pass should be '" + userAuth.get(inputID) + "'");
		System.out.print("Password: ");
		inputAuth = inputLine.nextLine();
		//System.out.println("You type: " + inputAuth + "; pass is '" + userAuth.get(inputID) + "'");
		
		//CHECKING PASSWORD
		while (!inputAuth.equals(userAuth.get(inputID))) {
			System.out.println("\nPassword incorrect! Please enter the correct one."); //Your 'inputAuth' is " + inputAuth);
			//System.out.println("\nUser ID = " + inputID + ", pass should be '" + userAuth.get(inputID) + "'");
			System.out.print("Password: ");
			inputAuth = inputLine.nextLine();
			/* System.out.print("Check pass validity: ");
			System.out.println(inputAuth == userAuth.get(inputID));
			System.out.println("Your input: " + inputAuth + "; validation: " + userAuth.get(inputID));
			*/
		/*
		}
		
		System.out.println("\nCorrect");
		
		LinkedHashMap<String, Object[]> userData = new LinkedHashMap<String, Object[]>();
		Object[] usrData = {10, "123456789", "miranda", "31/10/1969", 1000000};
		userData.put(inputID, usrData);
		usrData = null;
		
		System.out.println("Data of " + inputID + ": " + Arrays.toString(userData.get(inputID)));
		System.out.println("Initial balance = " + userData.get(inputID)[4]);
		int count = (int) userData.get(inputID)[4]; //taking user's balance data
		int finalBalance = count - 500000; //reducting user's balance
		userData.get(inputID)[4] = finalBalance;
		System.out.println("Last balance = " + userData.get(inputID)[4]);
		System.out.println("Data of " + inputID + ": " + Arrays.toString(userData.get(inputID)));
		System.out.println(inputID + " ID is " + userData.get(inputID)[0]);
		System.out.println("Users' data size is " + userData.size());
		
		inputLine.close();
	}
	*/
}
