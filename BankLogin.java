import java.io.Console;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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
		
		/// CHECKING VALIDITY if USER-ID and PASSWORD are VALID or NOT ///
		boolean matchLogin = false;
		while (matchLogin == false) {
			/// MATCHING UP User-ID & Password ///
			System.out.print("User ID: "); //asking User-id
			bkd.setUserId(userInputScan.nextLine());
			
			try {
				userInput = passwordInput();
				bkd.setUserPass(userInput);
			} catch (Exception e) {
				System.out.print("Password: "); //asking Password
				bkd.setUserPass(userInputScan.nextLine());
			}
			
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
						+ "\n3) Logout"
						+ "\nChoose one (1 or 2, & 3 for logout): ");
				userInput = userInputScan.nextLine();
				
				if (userInput.equals("1")) {
					correctRole = true;
					new BankMenuAdmin(userName);
				} else if (userInput.equals("2")) {
					correctRole = true;

					new BankMenuCust(userRole, userAccNo, userId, userPass, userName, userDob, userAddr, userMom, userJob, userSalaryRange, userBalance, userStatus);
				} else if (userInput.equals("3")) {
					System.out.println("\nThank you for using this app! Have a nice day ;)");
					System.exit(0);
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
	
	private String passwordInput() {
		Console konsol = System.console();
		
		if (konsol == null) {
			System.out.println("PASSWORD will be UNMASKED! (Can NOT DETECT console/terminal)");
			System.out.println("-- Try to run in OS windows --");
		}
		
		char[] passArray = konsol.readPassword("Password: ");
				
		return new String(passArray);
	}
}