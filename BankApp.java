import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class BankApp {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		BankMenu bm = new BankMenu();
		bm.showKumpulanDataUser();
		System.out.println();
		
		System.out.println();
		new BankLogin();
	}
}

class BankMenu {	
	public void showKumpulanDataUser() throws FileNotFoundException {//READING Bank's File
		String bankDbEg = "dbbank/dbBankUser.txt";
		File bankDbEgFile = new File(bankDbEg);
		Scanner scan = new Scanner(bankDbEgFile);
		String[] tokens;
		
		LinkedList<String> fileContent = new LinkedList<String>();
		String barisFile;
		
		while (scan.hasNext()) {
			barisFile = scan.nextLine();
			fileContent.add(barisFile);
		}
		
		int spRole, spAccNo, spId, spPass;
		spRole = spAccNo = spId = spPass = 0;
		int spacRole, spacAccNo, spacId, spacPass;
		spacRole = spacAccNo = spacId = spacPass = 0;
		
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
			System.out.println("  |");
		}

		scan.close();
	}
}