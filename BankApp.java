import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class BankApp {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		BankMenu bm = new BankMenu();
		bm.showKumpulanDataUser();
		System.out.println();
		
		final AtomicInteger count1 = new AtomicInteger(100);
		int tesId = count1.incrementAndGet();
		System.out.println(tesId);
		tesId = count1.incrementAndGet();
		System.out.println(tesId);
		/*
		boolean fullNum = false;
		String tes = null;
		scan.close();
		Scanner scan2 = new Scanner(System.in);
		
		System.out.print("test fullNum: ");
		tes = scan2.nextLine();		
		while (fullNum == false) {
			try {
				Integer.parseInt(tes);
				fullNum = true;
				System.out.println("lewat");
			} catch (Exception e) {
				System.out.println("Input 'tes' is '" + tes + "' --> NOT FULL-NUM. Try again!");
				System.out.println("fullNum = " + fullNum);
				System.out.print("test fullNum lagi: ");
				tes = scan2.next();
				continue;
			}
		}
		System.out.println("Var 'tes': " + tes);
		
		File deleteFile = new File("untitled.txt");
		deleteFile.delete();
		if (deleteFile.delete()) {
			System.out.println("Delete " + deleteFile.getName() + " is succeed! status: " + deleteFile.delete());
		} else {
			System.out.println("Delete " + deleteFile.getName() + " is failed! status: " + deleteFile.delete());
		}
		System.out.println();
		*/
		
		System.out.println();
		new BankLogin();
		//scan2.close();

		//CALLING Bank Menu
		//new BankMenu();
	}
}

class BankMenu { /*
	public BankMenu() {
		System.out.println("Welcome to Bank ATM. Please type the menu number:"
				+ "\n1) Administration"
				+ "\n2) Internet Banking");
		
		System.out.print("\nChosen menu number: ");
		
		Scanner chosenNum = new Scanner(System.in);
		String chosenMenu = chosenNum.nextLine();
		//chosenMenu.toString();
		//System.out.println(chosenMenu);
		
		while (!chosenMenu.equals("1") && !chosenMenu.equals("2")) {
			System.out.print("Please input the correct menu: " );
			chosenMenu = chosenNum.nextLine();
		}
		if (chosenMenu.equals("1")) {
			System.out.println("\nDefine later");
		} else if (chosenMenu.equals("2")) {
			//System.out.println("\nMenu number 2");
			BankLogin bl = new BankLogin();
			System.out.println();
			bl.eBanking();
		}
	}
	*/
	
	public void showKumpulanDataUser() throws FileNotFoundException {//READING Bank's File
		String bankDbEg = "C://Users/Lenovo/eclipse-workspace/BankAtm/dbBankUser.txt";
		File bankDbEgFile = new File(bankDbEg);
		Scanner scan = new Scanner(bankDbEgFile);
		String[] tokens;
		
		LinkedList<String> fileContent = new LinkedList<String>();
		String barisFile;
		
		while (scan.hasNext()) {
			barisFile = scan.nextLine();
			fileContent.add(barisFile);
		}
		
		int spRole, spAccNo, spId, spPass, spName, spDob, spAddr, spMom, spJob, spSalRan, spBal, spStat;
		spRole = spAccNo = spId = spPass = spName = spDob = spAddr = spMom = spJob = spSalRan = spBal = spStat = 0;
		int spacRole, spacAccNo, spacId, spacPass, spacName, spacDob, spacAddr, spacMom, spacJob, spacSalRan, spacBal, spacStat;
		spacRole = spacAccNo = spacId = spacPass = spacName = spacDob = spacAddr = spacMom = spacJob = spacSalRan = spacBal = spacStat = 0;
		
		//COUNT SPACING
		for (int i = 0; i < fileContent.size(); i++) {
			tokens = fileContent.get(i).split(";");
			
			//Spacing ROLE
			spacRole = tokens[0].length();
			if (spacRole > spRole) {
				spRole = spacRole;
			}

			//Spacing ACC-NO
			spacAccNo = tokens[1].length();
			if (spacAccNo > spAccNo) {
				spAccNo = spacAccNo;
			}
			
			//Spacing USER-ID
			spacId = tokens[2].length();
			if (spacId > spId) {
				spId = spacId;
			}

			//Spacing PASS
			spacPass = tokens[3].length();
			if (spacPass > spPass) {
				spPass = spacPass;
			}
			
			//Spacing NAME
			spacName = tokens[4].length();			
			if (spacName > spName) {
				spName = spacName;
			}
			
			//Spacing DOB
			spacDob = tokens[5].length();
			if (spacDob > spDob) {
				spDob = spacDob;
			}
			
			//Spacing ADDR
			spacAddr = tokens[6].length();
			if (spacAddr > spAddr) {
				spAddr = spacAddr;
			}
			
			//Spacing MOM
			spacMom = tokens[7].length();
			if (spacMom > spMom) {
				spMom = spacMom;
			}
			
			//Spacing JOB
			spacJob = tokens[8].length();
			if (spacJob > spJob) {
				spJob = spacJob;
			}
			
			//Spacing SALARY RANGE
			spacSalRan = tokens[9].length();
			if (spacSalRan > spSalRan) {
				spSalRan = spacSalRan;
			}
			
			//Spacing BALANCE
			spacBal = tokens[10].length();
			if (spacBal > spBal) {
				spBal = spacBal;
			}
			
			//Spacing STATUS
			spacStat = tokens[11].length();
			if (spacStat > spStat) {
				spStat = spacStat;
			}
		}
		
		//PRINTING HEAD TABLE UPPERLINE
		System.out.print("---+");

		//sp-Head ROLE
		for (int i = 0; i < spRole; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head ACC-NO
		for (int i = 0; i < spAccNo; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head USER-ID
		for (int i = 0; i < spId; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head PASS
		for (int i = 0; i < spPass; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head NAME
		for (int i = 0; i < spName; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//Head DOB
		for (int i = 0; i < spDob; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head ADDR
		for (int i = 0; i < spAddr; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head MOM
		for (int i = 0; i < spMom; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head JOB
		for (int i = 0; i < spJob; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head SALARY RANGE
		for (int i = 0; i < spSalRan; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head BALANCE
		for (int i = 0; i < spBal; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head STATUS
		for (int i = 0; i < spStat; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//PRINTING HEAD TABLE
		System.out.print("\nNO |");

		System.out.print("ROLE");
		for (int i = 0; i < spRole - "ROLE".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("ACCOUNT NO");
		for (int i = 0; i < spAccNo - "AACOUNT NO".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("USER-ID");
		for (int i = 0; i < spId - "USER-ID".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("PASSWORD");
		for (int i = 0; i < spPass - "PASSWORD".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("NAME");
		for (int i = 0; i < spName - "NAME".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("BIRTH DATE");
		for (int i = 0; i < spDob - "BIRTH DATE".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("ADDRESS");
		for (int i = 0; i < spAddr - "ADDRESS".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("MOM's NAME");
		for (int i = 0; i < spMom - "MOM's NAME".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("OCCUPATION");
		for (int i = 0; i < spJob - "OCCUPATION".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("SALARY");
		for (int i = 0; i < spSalRan - "SALARY".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("BALANCE");
		for (int i = 0; i < spBal - "BALANCE".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		System.out.print("ACC-STAT");
		for (int i = 0; i < spStat - "ACC-STAT".length(); i++) {
			System.out.print(" ");
		}
		System.out.print("  |");
		
		//PRINTING HEAD TABLE UNDERLINE
		System.out.print("\n---+");

		//sp-Head ROLE
		for (int i = 0; i < spRole; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head ACC-NO
		for (int i = 0; i < spAccNo; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head USER-ID
		for (int i = 0; i < spId; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head PASS
		for (int i = 0; i < spPass; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head NAME
		for (int i = 0; i < spName; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//Head DOB
		for (int i = 0; i < spDob; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head ADDR
		for (int i = 0; i < spAddr; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head MOM
		for (int i = 0; i < spMom; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head JOB
		for (int i = 0; i < spJob; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head SALARY RANGE
		for (int i = 0; i < spSalRan; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head BALANCE
		for (int i = 0; i < spBal; i++) {
			System.out.print("-");
		}
		System.out.print("--+");
		
		//sp-Head STATUS
		for (int i = 0; i < spStat; i++) {
			System.out.print("-");
		}
		System.out.print("--+");

		//PRINTING ROWS
		System.out.println();
		int z = 0;
		for (String string : fileContent) {
			tokens = string.split(";");
			z++;
			
			System.out.print(z + "  |");
			
			//Print Row ROLE
			spacRole = spRole - tokens[0].length();
			System.out.print(tokens[0]);
			for (int i = 0; i < spacRole; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			//Print Row ACC-NO
			spacAccNo = spAccNo - tokens[1].length();
			System.out.print(tokens[1]);
			for (int i = 0; i < spacAccNo; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			//Print Row USER-ID
			spacId = spId - tokens[2].length();
			System.out.print(tokens[2]);
			for (int i = 0; i < spacId; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");

			//Print Row PASS
			spacPass = spPass - tokens[3].length();
			System.out.print(tokens[3]);
			for (int i = 0; i < spacPass; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			//Print Row NAME
			spacName = spName - tokens[4].length();
			System.out.print(tokens[4]);
			for (int i = 0; i < spacName; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			//Print Row DOB
			spacDob = spDob - tokens[5].length();
			System.out.print(tokens[5]);
			for (int i = 0; i < spacDob; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			//Print Row ADDR
			spacAddr = spAddr - tokens[6].length();
			System.out.print(tokens[6]);
			for (int i = 0; i < spacAddr; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			//Print Row MOM
			spacMom = spMom - tokens[7].length();
			System.out.print(tokens[7]);
			for (int i = 0; i < spacMom; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			//Print Row JOB
			spacJob = spJob - tokens[8].length();
			System.out.print(tokens[8]);
			for (int i = 0; i < spacJob; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			//Print Row SALARY RANGE
			spacSalRan = spSalRan - tokens[9].length();
			System.out.print(tokens[9]);
			for (int i = 0; i < spacSalRan; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");
			
			//Print Row BALANCE
			spacBal = spBal - tokens[10].length();
			System.out.print(tokens[10]);
			for (int i = 0; i < spacBal; i++) {
				System.out.print(" ");
			}
			System.out.print("  |");

			//Print Row STATUS
			spacStat = spStat - tokens[11].length();
			System.out.print(tokens[11]);
			for (int i = 0; i < spacStat; i++) {
				System.out.print(" ");
			}
			System.out.println("  |");
		}
	}
}