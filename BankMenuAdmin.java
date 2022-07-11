import java.io.BufferedWriter;
import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import org.w3c.dom.UserDataHandler;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author LENOVO
 *
 */
public class BankMenuAdmin {

	Scanner inputScan = new Scanner(System.in);
	private String input;
	private String adminChoice;
	private String dbUserLoc = "C://Users/Lenovo/eclipse-workspace/BankAtm/dbBankUser.txt";
	File dbUserFile = new File(dbUserLoc);
	Scanner dbUserScan = new Scanner(dbUserFile);
	private String barisFile;
	LinkedList<String> dbUserTemp = new LinkedList<String>();
	Scanner dataInputScan = new Scanner(System.in);
	private String userRole, userId, userPass, userName, userDob, userAddr, userMom, userJob, userRangeSlr, userStatus;
	private String userAccNum;
	private int userAccNo, userAccBalance;
	private AtomicInteger usrAccNo;
	boolean flagEmpty, flagSpace, flagCharCaps, flagCharSpecial, flagBothLN, flagNum;
	private String checkSpace, userNameJob;
	final String letterCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	final String letterSpec = "`~!@#$%^&*()_+-=[]\\\\{}|;'\\\":,./<>?";
	final String numbers = "1234567890";
	private String[] letterCapsEach = letterCaps.split("");
	private String[] letterSpecEach = letterSpec.split("");
	private String[] numbersEach = numbers.split("");
	
	private BankKuDat bkd = new BankKuDat();

	public BankMenuAdmin(String userName) throws FileNotFoundException, IOException {
		this.userName = userName;
		
		System.out.println("\nWelcome admin " + userName + "! What do you want to do today?");
		System.out.println("\n1) Check user database"
				+ "\n2) Create user data"
				+ "\n3) Edit user data"
				+ "\n4) Delete user data"
				+ "\n5) Logout");
		System.out.print("\nChoose one (in numeric): ");
		
		Scanner adminChoiceScan = new Scanner(System.in);
		adminChoice = adminChoiceScan.nextLine();

		System.out.println("Menu " + adminChoice + " is chosen");
		menuAdminInput(adminChoice);
		adminChoiceScan.close();
	}
	
	private void menuAdminInput(String adminChoice) throws FileNotFoundException, IOException {
		this.adminChoice = adminChoice;
		
		if (adminChoice.equals("1")) {
			menuAdmin1();
		} else if (adminChoice.equals("2")) {
			menuAdmin2();
		} else if (adminChoice.equals("3")) {
			menuAdmin3();
		} else if (adminChoice.equals("4")) {
			menuAdmin4();
		} else if (adminChoice.equals("5")) {
			menuAdmin5();
		} else {
			System.out.print("\nChoose right menu number!\nTry again: ");
			input = inputScan.nextLine();
			menuAdminInput(input);
			inputScan.close();
		}
	}
	
	private void menuAdmin1() throws IOException {
		System.out.println("\nUSER DATABASE\n=============");
		bkd.tampilBankKuDat();
		
		backToMainMenu();
	}
	
	private void menuAdmin2() throws FileNotFoundException, IOException {
		StringBuilder userData = new StringBuilder("");

		System.out.println("\nCREATE NEW USER\n===============");
		
		while (dbUserScan.hasNext()) {
			barisFile = dbUserScan.nextLine();
			dbUserTemp.add(barisFile);
		}
		
		//input ROLE
		System.out.print("Role (admin/cust): ");
		userRole = dataInputScan.nextLine();
		validateUserRole(userRole);
		userData.append(getUserRole());
		
		//input ACC-NO
		validateAccNo();
		userData.append(";" + getAccNo());
		
		//input USER-ID
		System.out.print("User ID (only low letters & numbers, no spacing): ");
		userId = dataInputScan.nextLine();
		validateUserId(userId);
		userData.append(";" + getUserId());
		
		//input PASSWORD
		System.out.print("Password (no space character): ");
		userPass = dataInputScan.nextLine();
		validatePassword(userPass);
		userData.append(";" + getUserPass());
		
		//input NAME
		System.out.print("Name (letters & spacing): ");
		userName = dataInputScan.nextLine();
		validateNameJob(userName);
		userData.append(";" + getUserNameJob());

		//input DATE of BIRTH
		System.out.print("Date of birth (dd/mm/yyyy): ");
		userDob = dataInputScan.nextLine();
		validateDob(userDob);
		userData.append(";" + getUserDob());
		
		//input ADDRESS
		System.out.print("Address: ");
		userAddr = dataInputScan.nextLine();
		validateAddr(userAddr);
		userData.append(";" + getUserAddr());
		
		//input MOM's NAME
		System.out.print("Mother's name (letters & spacing): ");
		userMom = dataInputScan.nextLine();
		validateNameJob(userMom);
		userData.append(";" + getUserNameJob());
		
		//input JOB
		System.out.print("Job (letters & spacing): ");
		userJob = dataInputScan.nextLine();
		validateNameJob(userJob);
		userData.append(";" + getUserNameJob());
		
		//input RANGE SALARY
		System.out.print("Range of Salary:"
				+ "\n1) 1-3 juta"
				+ "\n2) 3-7 juta"
				+ "\n3) 7-15 juta"
				+ "\n4) 15-25 juta"
				+ "\n5) > 25 juta"
				+ "\nChoose one of the above (in number): ");
		userRangeSlr = dataInputScan.nextLine();
		validateRangeSalary(userRangeSlr);
		userData.append(";" + getUserRangeSlr());
		
		//input ACCOUNT BALANCE
		System.out.print("Initial balance (numeric only & > 99999): ");
		validateAccBalance();
		userData.append(";" + getUserAccBalance());
		
		//input MEMBER STATUS
		validateStatus(getUserAccBalance());
		userData.append(";" + getUserStatus());
		
		System.out.println("\nuserData: " + userData);
		
		//ADDING NEW BANK USER
		bkd.addBankUser(userData);
		backToMainMenu();
	}
	
	private void menuAdmin3() throws IOException {
		System.out.println("\nEDIT USER DATA");
		System.out.println("==============");
		
		System.out.println("Enter userId & bank account you want to edit:");
		System.out.print("User ID: ");
		userId = inputScan.nextLine();
		System.out.print("Bank account: ");
		userAccNum = inputScan.nextLine();
		
		editUser(userId, userAccNum);
		backToMainMenu();
	}
	
	private void menuAdmin4() throws IOException {
		System.out.println("\nDELETE USER DATA");
		System.out.println("==============");
		
		System.out.println("Enter userId & bank account you want to delete!");
		System.out.print("User ID: ");
		this.userId = inputScan.nextLine();
		System.out.print("Bank account: ");
		this.userAccNum = inputScan.nextLine();
		
		deleteUser(userId, userAccNum);
		backToMainMenu();
	}
	
	private void menuAdmin5() {
		//System.out.println("\nClear screen, thx u & greeed thank you for using the apps!");
		System.out.println("\nThank you for your work today! Have a nice day ;)");
		System.exit(0);
	}
	
	private void validateUserRole(String userRole) {
		// TODO Auto-generated method stub
		
		while (!userRole.equals("admin") && !userRole.equals("cust")) {
			System.out.println("Role input: " + userRole);
			System.out.print("Try again! Role must be filled with 'admin' or 'cust'\n\nRole (admin/cust): ");
			userRole = dataInputScan.nextLine();
		}
		this.userRole = userRole;
	}
	
	private void validateAccNo() {
		bkd.getLastAccNo();
		
		int accNoLast = 0;
		if (userRole.equals("admin")) {
			bkd.setLastAccNo(1);
			accNoLast = bkd.getLastAccNo();
			
			this.usrAccNo = new AtomicInteger(accNoLast);
			this.userAccNo = usrAccNo.incrementAndGet();
		} else if (userRole.equals("cust")) {
			bkd.setLastAccNo(2);
			accNoLast = bkd.getLastAccNo();
			
			this.usrAccNo = new AtomicInteger(accNoLast);
			this.userAccNo = usrAccNo.incrementAndGet();
		}
		System.out.println("Account Number (auto generated): " + this.userAccNo);
	}
	
	private void validateUserId(String userId) throws FileNotFoundException {
		flagEmpty = true;
		flagSpace = true;
		flagCharCaps = true;
		flagCharSpecial = true;
		flagBothLN = true;
		boolean flagSameId = true;
		while (flagEmpty == true || flagSpace == true || flagCharCaps == true || flagCharSpecial == true || flagBothLN == false || flagSameId == true) {//salah 1 salah semua
			flagEmpty = true;
			flagSpace = true;
			flagCharCaps = true;
			flagCharSpecial = true;
			flagBothLN = false;
			flagSameId = true;
			String[] userIdeach = userId.split("");
			
			//check input EMPTY
			if (userId.isEmpty()) {
				flagEmpty = true; /*
				System.out.println("\nTyped user ID is: [empty]");
				System.out.println("User ID can not be empty! Please input correct user ID!");
				System.out.print("User ID (only low letters & number): ");
				userId = dataInputScan.nextLine(); */
			} else if (!userId.isEmpty()) {
				flagEmpty = false;
			}
			
			//CHECK input SPACING
			checkSpace = userId;
			if (!userId.equals(checkSpace.replace(" ", ""))) {
				flagSpace = true; /*
				System.out.println("\nTyped user ID is: " + checkSpace);
				System.out.println("Space character is not allowed! Please input correct user ID!");
				System.out.print("User ID (only low letters & number): ");
				userId = dataInputScan.nextLine(); */
			} else if (userId.equals(checkSpace.replace(" ", ""))) {
				flagSpace = false;
			}
			
			//CHECK input CAPS
			int flagCharCapsChecker = 0;
			for (int i = 0; i < userIdeach.length; i++) {
				for (int j = 0; j < letterCapsEach.length; j++) {
					if (userIdeach[i].equals(letterCapsEach[j])) {
						flagCharCapsChecker = 1;
					}
				}
			}
			if (flagCharCapsChecker == 1) {
				flagCharCaps = true;
			} else {
				flagCharCaps = false;
			}
			
			//CHECK input SPECIAL CHAR
			int flagCharSpecialChecker = 0;
			for (int i = 0; i < userIdeach.length; i++) {
				for (int j = 0; j < letterSpecEach.length; j++) {
					if (userIdeach[i].equals(letterSpecEach[j])) {
						flagCharSpecialChecker = 1;
					}
				}
			}
			if (flagCharSpecialChecker == 1) {
				flagCharSpecial = true;
			} else {
				flagCharSpecial = false;
			}
			
			//CHECK input BOTHLN
			int checkNum = 0;
			for (int i = 0; i < userIdeach.length; i++) {
				for (int j = 0; j < numbersEach.length; j++) {
					if (userIdeach[i].equals(numbersEach[j])) {
						checkNum = 1;
					}
				}
			}
			int checkChar = 0;
			for (int i = 0; i < userIdeach.length; i++) {
				for (int j = 0; j < letterCapsEach.length; j++) {
					if (userIdeach[i].equals(letterCapsEach[j].toLowerCase())) {
						checkChar = 1;
					}
				}
			}
			if (checkNum == 1 && checkChar == 1) {
				flagBothLN = true;
			} else {
				flagBothLN = false;
			}
			
			//CHECK input SAME ID
			boolean flagSameIdCheck;
			bkd.checkUserIdExist(userId);
			flagSameIdCheck = bkd.getCheckUserIdExist();
			
			if (flagSameIdCheck == true) {
				flagSameId = true;
			} else if (flagSameIdCheck == false) {
				flagSameId = false;
			}
			
			if (flagEmpty == true || flagSpace == true || flagCharCaps == true || flagCharSpecial == true || flagBothLN == false || flagSameId == true) {
				System.out.println();
				if (flagEmpty == true) {
					System.out.println("Detecting empty input!");
				}
				
				if (flagSpace == true) {
					System.out.println("Detecting spacing!");
				}
				
				if (flagCharCaps == true) {
					System.out.println("Detecting capital characters!");
				}
				
				if (flagCharSpecial == true) {
					System.out.println("Detecting special characters!");
				}
				
				if (flagBothLN == false) {
					System.out.println("Detecting no letters or no numbers! checkChar = " + checkChar + ", checkNum = " + checkNum + ", flagBothLN = " + flagBothLN);
				}
				
				if (flagSameId == true) {
					System.out.println("Detecting UNAVAILABLE user Id! Try others!");
				}
				
				System.out.println("Entered user ID is: '" + userId + "'");
				//System.out.println("flagEmpty = " + flagEmpty + ", flagSpace = " + flagSpace + ", flagCharCaps = " + flagCharCaps + ", flagCharSpecial = " + flagCharSpecial);
				System.out.println("Please enter a right formatted user ID!");
				System.out.print("User ID (only low letters & numbers): ");
				userId = dataInputScan.nextLine();
			}
		}
		this.userId = userId;
	}
	
	private void validatePassword(String userPass) {
		checkSpace = userPass;
		
		flagEmpty = true;
		flagSpace = true;
		while (flagEmpty == true || flagSpace == true) {
			flagEmpty = true;
			flagSpace = true;
			checkSpace = userPass;
			
			//CHECK input EMPTY
			if (userPass.isEmpty()) {
				flagEmpty = true;
			} else if (!userPass.isEmpty()) {
				flagEmpty = false;
			}
			
			//CHECK input SPACING
			checkSpace = userPass;
			if (!userPass.equals(checkSpace.replace(" ", ""))) {
				flagSpace = true;
			} else if (userPass.equals(checkSpace.replace(" ", ""))) {
				flagSpace = false;
			}
			
			if (flagEmpty == true || flagSpace == true) {
				System.out.println();
				if (flagEmpty == true) {
					System.out.println("Detecting empty input!");
				}
				
				if (flagSpace == true) {
					System.out.println("Detecting spacing!");
				}

				System.out.println("Entered password is: '" + userPass + "'");
				System.out.println("Please enter a right formatted password!");
				System.out.print("Password (no space character): ");
				userPass = dataInputScan.nextLine();
			}
		}
		this.userPass = userPass;
	}
	
	private void validateNameJob(String userNameJob) {
		flagCharSpecial = true;
		flagEmpty = true;
		flagNum = true;
		while (flagCharSpecial == true || flagEmpty == true || flagNum == true) {
			flagCharSpecial = true;
			flagEmpty = true;
			flagNum = true;
			
			//CHECK input EMPTY
			if (userNameJob.isEmpty()) {
				flagEmpty = true;
			} else if (!userNameJob.isEmpty()) {
				flagEmpty = false;
			}
			
			//CHECK input SPECIAL CHAR
			int flagCharSpecialCheck = 0;
			String[] userNameEach = userNameJob.split("");
			for (int i = 0; i < userNameEach.length; i++) {
				for (int j = 0; j < letterSpecEach.length; j++) {
					if (userNameEach[i].equals(letterSpecEach[j])) {
						flagCharSpecialCheck = 1;
					}
				}
			}
			if (flagCharSpecialCheck == 1) {
				flagCharSpecial = true;
			} else {
				flagCharSpecial = false;
			}
			
			//CHECK input NUM
			int flagNumCheck = 0;
			for (int i = 0; i < userNameEach.length; i++) {
				for (int j = 0; j < numbersEach.length; j++) {
					if (userNameEach[i].equals(numbersEach[j])) {
						flagNumCheck = 1;
					}
				}
			}
			if (flagNumCheck == 1) {
				flagNum = true;
			} else {
				flagNum = false;
			}
			
			if (flagEmpty == true || flagCharSpecial == true || flagNum == true) {
				System.out.println();
				
				if (flagEmpty == true) {
					System.out.println("Detecting empty input!");
				}
				
				if (flagCharSpecial == true) {
					System.out.println("Detecting special characters!");
				}
				
				if (flagNum == true) {
					System.out.println("Detecting numbers characters!");
				}
				
				System.out.println("Entered user name is: '" + userNameJob + "'");
				System.out.println("FLAGS -- flagEmpty = " + flagEmpty + ", flagCharSpecial = " + flagCharSpecialCheck + ", flagNum = " + flagNum);
				System.out.println("Please enter a right formatted user name!");
				System.out.print("Name (only letters & spacing): ");
				userNameJob = dataInputScan.nextLine();
			}
		}
		
		this.userNameJob = userNameJob;
	}
	
	private void validateDob(String userDob) {
		// TODO Auto-generated method stub
		
		flagEmpty = true;
		boolean flagDobDd = false;
		boolean flagDobMm = false;
		boolean flagDobYyyy = false;
		boolean flagDobCorrect = false;
		boolean flagDobLength = false;
		while (flagEmpty == true || flagDobDd == false || flagDobMm == false || flagDobYyyy == false || flagDobCorrect == false || flagDobLength == false) {
			flagEmpty = true;
			flagDobDd = false;
			flagDobMm = false;
			flagDobYyyy = false;
			flagDobCorrect = false;
			flagDobLength = false;
			
			String[] userDobEach = userDob.split("");
			/*
			System.out.println();
			System.out.println("Entered date of birth is '" + userDob + "'");
			for (int i = 0; i < userDobEach.length; i++) {
				System.out.println("userDobEach[" + i + "] = " + userDobEach[i]);
			}
			*/
			
			//CHECK input EMPTY
			if (userDob.isEmpty()) {
				flagEmpty = true;
			} else if (!userDob.isEmpty()) {
				flagEmpty = false;
			}
			
			//CHECK input LENGTH
			if (userDob.length() == 10) {
				flagDobLength = true;
				
				//CHECK input DATE FORMAT
				//System.out.println("userDobEach[1] = " + userDobEach[1]);
				//System.out.println("userDobEach[2] = " + userDobEach[2]);
				if (userDobEach[2].equals("/") && userDobEach[5].equals("/") ) {
					flagDobCorrect = true;
					
					//CHECK input DD FORMAT
					int flagDobDd1 = 0;
					int flagDobDd2 = 0;
					for (int i = 0; i <= 1; i++) {
						for (int j = 0; j < numbersEach.length; j++) {
							if (userDobEach[0].equals(numbersEach[j])) {
								flagDobDd1 = 1;
							}
							
							if (userDobEach[1].equals(numbersEach[j])) {
								flagDobDd2 = 1;
							}
						}
					}
					if (flagDobDd1 == 1 && flagDobDd2 == 1) {
						if (Integer.parseInt(userDob.split("/")[0]) <= 31) {
							flagDobDd = true;
						}
					} else {
						flagDobDd = false;
					}
					
					//CHECK input MM FORMAT
					int flagDobMm1 = 0;
					int flagDobMm2 = 0;
					//System.out.println("userDobEach[3] = " + userDobEach[3]);
					//System.out.println("userDobEach[4] = " + userDobEach[4]);
					for (int i = 3; i <= 4; i++) {
						for (int j = 0; j < numbersEach.length; j++) {
							if (userDobEach[3].equals(numbersEach[j])) {
								flagDobMm1 = 1;
							}
							
							if (userDobEach[4].equals(numbersEach[j])) {
								flagDobMm2 = 1;
							}
						}
					}
					if (flagDobMm1 == 1 && flagDobMm2 == 1) {
						if (Integer.parseInt(userDob.split("/")[1]) <= 12) {
							flagDobMm = true;
						}
					} else {
						flagDobMm = false;
					}
					
					//CHECK input YYYY FORMAT
					int flagDobYy1 = 0;
					int flagDobYy2 = 0;
					int flagDobYy3 = 0;
					int flagDobYy4 = 0;
					for (int i = 6; i <= 9; i++) {
						for (int j = 0; j < numbersEach.length; j++) {
							if (userDobEach[6].equals(numbersEach[j])) {
								flagDobYy1 = 1;
							}
							
							if (userDobEach[7].equals(numbersEach[j])) {
								flagDobYy2 = 1;
							}
							
							if (userDobEach[8].equals(numbersEach[j])) {
								flagDobYy3 = 1;
							}
							
							if (userDobEach[9].equals(numbersEach[j])) {
								flagDobYy4 = 1;
							}
						}
					}
					if (flagDobYy1 == 1 && flagDobYy2 == 1 && flagDobYy3 == 1 && flagDobYy4 == 1) {
						flagDobYyyy = true;
					} else {
						flagDobYyyy = false;
					}
				} else {
					flagDobCorrect = false;
				}
			} else {
				flagDobLength = false;
			}
			
			//System.out.println("/// KELUAR ///");
			if (flagEmpty == true || flagDobDd == false || flagDobMm == false || flagDobYyyy == false || flagDobCorrect == false || flagDobLength == false) {
				System.out.println();
				
				if (flagEmpty == true) {
					System.out.println("Detecting empty input!");
				}
				
				if (flagDobDd == false || flagDobMm == false || flagDobYyyy == false || flagDobCorrect == false || flagDobLength == false) {
					System.out.println("Wrong input characters!");
				}
				
				System.out.println("Entered date of birth is '" + userDob + "'");
				//System.out.println("flagEmpty = " + flagEmpty + ", flagDobDd = " + flagDobDd + ", flagDobMm = " + flagDobMm + ", flagDobYyyy = " + flagDobYyyy + ", flagDbCorrect = " + flagDobCorrect + ", flagDobLength = " + flagDobLength);
				System.out.println("Please enter a correct formatted date of birth!");
				System.out.print("Birth of Date (in format 'dd/mm/yyyy'): ");
				userDob = dataInputScan.nextLine();
			}
		}
		
		this.userDob = userDob;
	}
	
	private void validateAddr(String userAddr) {
		// TODO Auto-generated method stub
		
		flagEmpty = true;
		while (flagEmpty == true) {
			flagEmpty = true;
			
			if (userAddr.isEmpty()) {
				flagEmpty = true;
			} else if (!userAddr.isEmpty()) {
				flagEmpty = false;
			}
			
			if (flagEmpty == true) {
				System.out.println("\nDetecting empty input!");
				
				System.out.println("Entered address is: '" + userAddr + "'");
				System.out.println("Please enter a correct formatted address!");
				System.out.print("Address: ");
				userAddr = dataInputScan.nextLine();
			}
		}
		
		this.userAddr = userAddr;
	}
	
	private void validateRangeSalary(String userRangeSlr) {
		// TODO Auto-generated method stub
		
		flagEmpty = true;
		boolean flagRangeSlr = false;
		while (flagRangeSlr == false || flagEmpty == true) {
			flagEmpty = true;
			flagRangeSlr = false;
			
			//CHECK input EMPTY
			if (userRangeSlr.isEmpty()) {
				flagEmpty = true;
			} else if (!userRangeSlr.isEmpty()) {
				flagEmpty = false;
			}
			
			//CHECK input MATCH CHOICE
			if (userRangeSlr.equals("1")) {
				userRangeSlr = "1-3 juta";
				flagRangeSlr = true;
			} else if (userRangeSlr.equals("2")) {
				userRangeSlr = "3-7 juta";
				flagRangeSlr = true;
			} else if (userRangeSlr.equals("3")) {
				userRangeSlr = "7-15 juta";
				flagRangeSlr = true;
			} else if (userRangeSlr.equals("4")) {
				userRangeSlr = "15-25 juta";
				flagRangeSlr = true;
			} else if (userRangeSlr.equals("5")) {
				userRangeSlr = "> 25 juta";
				flagRangeSlr = true;
			} else {
				flagRangeSlr = false;
			}
			
			if (flagRangeSlr == false || flagEmpty == true) {
				System.out.println();
				
				if (flagEmpty == true) {
					System.out.println("Detecting empty input!");
				}
				
				if (flagRangeSlr == false) {
					System.out.println("Wrong choice input!");
				}
				
				//System.out.println("flagRangeSlr = " + flagRangeSlr + ", flagEmpty = " + flagEmpty);
				System.out.println("Entered menu choice  is '" + userRangeSlr + "'");
				System.out.println("Please enter correct choice of number!");
				System.out.print("Range of Salary:"
					+ "\n1) 1-3 juta"
					+ "\n2) 3-7 juta"
					+ "\n3) 7-15 juta"
					+ "\n4) 15-25 juta"
					+ "\n5) > 25 juta"
					+ "\nChoose one of the above (in number): ");
				userRangeSlr = dataInputScan.nextLine();
			}
		}
		
		this.userRangeSlr = userRangeSlr;
	}
	
	private void validateAccBalance() {
		boolean flagUserBalance = false;
		while (flagUserBalance == false) {
			flagUserBalance = false;
			
			try {
				this.userAccBalance = dataInputScan.nextInt();
				
				//CHECK input EMPTY
				if (userAccBalance < 100000) {
					flagUserBalance = false;
					
					System.out.println();
					System.out.println("flagUserBalance = " + flagUserBalance);
					System.out.println("Entered balance is '" + this.userAccBalance + "'");
					System.out.println("Input must be greater than '100000'");
					System.out.println("Please enter a correct formatted balance!");
					System.out.print("Initial balance (numeric only & > 99999): ");
				} else if (userAccBalance >= 100000) {
					flagUserBalance = true;
				}
			} catch (Exception e) {
				System.out.println();
				System.out.println("flagUserBalance = " + flagUserBalance);
				System.out.println("Entered balance is '" + this.userAccBalance + "'");
				System.out.println("Input must be greater than '100000'");
				System.out.println("Please enter a correct formatted balance!");
				System.out.print("Initial balance (numbers only): ");
				dataInputScan.next();
				continue;
			}
		}
	}
	
	private void validateStatus(int userAccBalance) {
		// TODO Auto-generated method stub
		
		if (userAccBalance <= 7000000 ) {
			userStatus = "silver";
		} else if (userAccBalance > 7000000 && userAccBalance <= 25000000) {
			userStatus = "gold";
		} else if (userAccBalance > 25000000) {
			userStatus = "platinum";
		}

		System.out.println("Member status (auto generate): " + userStatus);
	}

	public String getUserRole() {
		return userRole;
	}
	
	public int getAccNo() {
		// TODO Auto-generated method stub
		return this.userAccNo;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public String getUserPass() {
		return userPass;
	}
	
	public String getUserNameJob() {
		return userNameJob;
	}
	
	public String getUserDob() {
		return userDob;
	}
	
	public String getUserAddr() {
		return userAddr;
	}
	
	public String getUserRangeSlr() {
		return userRangeSlr;
	}
	
	public int getUserAccBalance() {
		return userAccBalance;
	}
	
	public String getUserStatus() {
		return userStatus;
	}
	
	private void editUser(String userId, String userAccNum) throws FileNotFoundException {
		// TODO Auto-generated method stub
		this.userId = userId;
		this.userAccNum = userAccNum;

		String[] dbUserTempLineFragment = null;
		dbUserScan = new Scanner(dbUserFile);
		
		while (dbUserScan.hasNext()) {
			dbUserTemp.add(dbUserScan.nextLine());
		}

		boolean flagUserMatch = false;
		boolean editUserCheck = false;
		boolean menuEditAvail = false;
		int catchIndex = 0;
		
		while (flagUserMatch == false) {
			bkd.matchUserIdAccNo(userId, userAccNum);
			editUserCheck = bkd.getMatchUserIdAccNo();
			
			if (editUserCheck == true) {
				System.out.println("\nUser match found!");
				catchIndex = bkd.getMatchUserIdAccNoIndex();
			}
			
			if (editUserCheck == true) {
				flagUserMatch = true;
			} else if (editUserCheck == false) {
				flagUserMatch = false;
			}
			
			if (flagUserMatch == true) {
				while (menuEditAvail == false) {
					dbUserTempLineFragment = bkd.getBankUser().toString().split(";"); //dbUserTemp.get(catchIndex).split(";");
					
					System.out.println("1) Role: " + bkd.getUserRole() +
							"\n2) Bank account: " + bkd.getUserAccNo() + " *---UNEDITABLE---" +
							"\n3) User ID: " + bkd.getUserId() + " *---UNEDITABLE---" +
							"\n4) Password: " + bkd.getUserPass() +
							"\n5) Name: " + bkd.getUserName() +
							"\n6) Birthday: " + bkd.getUserDob() +
							"\n7) Address: " + bkd.getUserAddr() +
							"\n8) Mother's name: " + bkd.getUserMom() +
							"\n9) Occupation: " + bkd.getUserJob() +
							"\n10) Range salary: " + bkd.getUserSalaryRange() +
							"\n11) Balance: Rp. " + bkd.getUserBalance() +
							"\n12) Member status: " + bkd.getUserStatus() + " *---UNEDITABLE---");
					
					System.out.print("\nInput number menu of data you want to alter (1-12): ");
					String menuInput3Edit = inputScan.nextLine();
					
					if (menuInput3Edit.equals("1")) {
						System.out.println("\nAlter Role");
						System.out.println("==========");
						//System.out.println("BEFORE: " + dbUserTemp.get(catchIndex));
						System.out.println("Old role: " + bkd.getUserRole());
						System.out.print("New role (admin/cust): ");
						userRole = dataInputScan.nextLine();
						
						validateUserRole(userRole);
						bkd.setUserRole(getUserRole()); //dbUserTempLineFragment[0] = getUserRole();
						menuEditAvail = true;
					}
					else if (menuInput3Edit.equals("4")) {
						System.out.println("\nALTER PASSWORD");
						System.out.println("==============");
						System.out.println("Old password: " + bkd.getUserPass());
						System.out.print("New password (no spacing): ");
						userPass = dataInputScan.nextLine();
						
						validatePassword(userPass);
						bkd.setUserPass(getUserPass()); //dbUserTempLineFragment[3] = getUserPass();
						menuEditAvail = true;
					}
					else if (menuInput3Edit.equals("5")) {
						System.out.println("\nAlter Name");
						System.out.println("==========");
						System.out.println("Old name: " + bkd.getUserName());
						System.out.print("New name (only letters & spacing): " );
						userName = dataInputScan.nextLine();
						
						validateNameJob(userName);
						bkd.setUserJob(getUserNameJob()); //dbUserTempLineFragment[4] = getUserNameJob();
						menuEditAvail = true;
					}
					else if (menuInput3Edit.equals("6")) {
						System.out.println("\nALTER BIRTHDAY");
						System.out.println("==============");
						System.out.println("Old birthday: " + bkd.getUserDob());
						System.out.print("New birthday: ");
						userDob = dataInputScan.nextLine();
						
						validateDob(userDob);
						bkd.setUserDob(getUserDob()); //dbUserTempLineFragment[5] = getUserDob();
						menuEditAvail = true;
					}
					else if (menuInput3Edit.equals("7")) {
						System.out.println("\nALTER ADDRESS");
						System.out.println("=============");
						System.out.println("Old address: " + bkd.getUserAddr());
						System.out.print("New address: ");
						userAddr = dataInputScan.nextLine();
						
						validateAddr(userAddr);
						bkd.setUserAddr(getUserAddr()); //dbUserTempLineFragment[6] = getUserAddr();
						menuEditAvail = true;
					}
					else if (menuInput3Edit.equals("8")) {
						System.out.println("\nALTER MOTHER's NAME");
						System.out.println("===================");
						System.out.println("Old mother's name: " + bkd.getUserMom());
						System.out.print("New mother's name (only letters & spacing): ");
						userMom = dataInputScan.nextLine();
						
						validateNameJob(userMom);
						bkd.setUserMom(getUserNameJob()); //dbUserTempLineFragment[7] = getUserNameJob();
						menuEditAvail = true;
					}
					else if (menuInput3Edit.equals("9")) {
						System.out.println("\nALTER OCCUPATION");
						System.out.println("================");
						System.out.println("Old occupation: " + bkd.getUserJob());
						System.out.print("New occupation (only letters & spacing): ");
						userJob = dataInputScan.nextLine();
						
						validateNameJob(userJob);
						bkd.setUserJob(getUserNameJob()); //dbUserTempLineFragment[8] = getUserNameJob();
						menuEditAvail = true;
					}
					else if (menuInput3Edit.equals("10")) {
						System.out.println("\nALTER SALARY RANGE");
						System.out.println("==================");
						System.out.println("Old salary range: " + bkd.getUserSalaryRange());
						System.out.print("New salary range:"
								+ "\n1) 1-3 juta"
								+ "\n2) 3-7 juta"
								+ "\n3) 7-15 juta"
								+ "\n4) 15-25 juta"
								+ "\n5) > 25 juta"
								+ "\nChoose one of the above (in number): ");
						userRangeSlr = dataInputScan.nextLine();
						
						validateRangeSalary(userRangeSlr);
						bkd.setUserSalaryRange(getUserRangeSlr()); //dbUserTempLineFragment[9] = getUserRangeSlr();
						menuEditAvail = true;
					}
					else if (menuInput3Edit.equals("11")) {
						System.out.println("\nALTER BALANCE VALUE");
						System.out.println("===================");
						System.out.println("Old balance: " + bkd.getUserBalance());
						System.out.print("New balance: ");
						
						validateAccBalance();
						bkd.setUserBalance(Integer.toString(getUserAccBalance())); //dbUserTempLineFragment[10] = Integer.toString(getUserAccBalance());
						validateStatus(getUserAccBalance());
						bkd.setUserStatus(getUserStatus()); //dbUserTempLineFragment[11] = getUserStatus();
						menuEditAvail = true;
					}
					else if (menuInput3Edit.equals("2") || menuInput3Edit.equals("3") || menuInput3Edit.equals("12")) {
						menuEditAvail = false;
						System.out.print("This choice menu '" + menuInput3Edit + "' is uneditable!\nChoose another edit choice menu: ");
					} else {
						menuEditAvail = false;
						System.out.print("This choice menu '" + menuInput3Edit + "' is not available!\nPlease input correct edit choice menu: ");
					}
				}				
			}
			else if (flagUserMatch == false) {
				System.out.println("\nNo match found! Your inputs are userId: '" + userId + "', bankAcc: '" + userAccNum + "'");
				System.out.println("Please enter correct credentials!");
				System.out.print("User ID: ");
				userId = inputScan.nextLine();
				System.out.print("Bank account: ");
				userAccNum = inputScan.nextLine();
			}
		}

		System.out.print("BEFORE SET: ");
		for (int i = 0; i < dbUserTempLineFragment.length; i++) {
			System.out.print(dbUserTempLineFragment[i] + ";");
		}
		System.out.println("\nAFTER SET: " + bkd.getBankUser());
		bkd.editBankUser(bkd.getBankUser().toString());
		System.out.println("Editing file is done!");
	}
	
	private void deleteUser(String userId, String userAccNum) throws FileNotFoundException {
		System.out.println("\nInside deleteUser()");
		this.userId = userId;
		this.userAccNum = userAccNum;

		String[] dbUserTempLineFragment = null;
		dbUserScan = new Scanner(dbUserFile);
		
		while (dbUserScan.hasNext()) {
			dbUserTemp.add(dbUserScan.nextLine());
		}

		boolean flagUserMatch = false;
		boolean deleteUserCheck = false;
		int catchIndex = 0;

		while (flagUserMatch == false) {
			bkd.matchUserIdAccNo(userId, userAccNum);
			deleteUserCheck = bkd.getMatchUserIdAccNo();
			
			if (deleteUserCheck == true) {
				System.out.println("\nUser match found!");
				System.out.println("Deleting user with user ID:" + userId + " & acc no:" + userAccNum);
				catchIndex = bkd.getMatchUserIdAccNoIndex();
			}
			
			if (deleteUserCheck == true) {
				flagUserMatch = true;
			} else if (deleteUserCheck == false) {
				flagUserMatch = false;
			}
			
			if (flagUserMatch == true) {
				bkd.deleteBankUser(bkd.getBankUser().toString());
			} else if (flagUserMatch == false) {
				System.out.println("\nNo match found!");
				System.out.println("Your input is: user id = '" + userId + "', user account number = '" + userAccNum + "'");
				System.out.println("Please enter correct credentials:");
				System.out.print("User ID: ");
				userId = inputScan.nextLine();
				System.out.print("Bank account: ");
				userAccNum = inputScan.nextLine();
			}
		}
		System.out.println("DELETING SUCCEED!");
	}
	
	private void backToMainMenu() throws FileNotFoundException, IOException {
		boolean getMainMenu = false;
		
		while (getMainMenu == false) {
			System.out.print("\n((nPress 'Enter' to get back to main menu))");
			input = inputScan.nextLine();
			
			if (input.equals("")) {
				getMainMenu = true;
			} else {
				getMainMenu = false;
				System.out.println("Your input is '" + input + "'. Try again!");
			}
		}
		new BankMenuAdmin(userName);
	}
}
