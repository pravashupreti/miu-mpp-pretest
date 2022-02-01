import java.util.Scanner;

public class ATM {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Bank bank = new Bank("Fairfield Bank");
		User user = bank.addUser("Pravash", "Upreti", "1234");

		Account account = new Account("Checking Account", user, bank);
		user.addAccount(account);
		bank.addAccount(account);

		User curUser;
		while (true) {
			curUser = ATM.mainMenuPrompt(bank, sc);
			ATM.printUserMenu(curUser, sc);
		}
	}

	public static User mainMenuPrompt(Bank bank, Scanner sc) {

		String userId;
		String pin;
		User authUser;
		do {
			System.out.printf("\n\n Welcome to %s .", bank.getName());

			System.out.print("\n Enter user id:");
			userId = sc.nextLine();

			System.out.print("\n Enter pin:");
			pin = sc.nextLine();

			authUser = bank.userLogin(userId, pin);

			if (authUser == null) {
				System.out.println("Incorrect userid / pin. Please try again");
			}

		} while (authUser == null);

		return authUser;

	}

	public static void printUserMenu(User user, Scanner sc) {

		user.printAccountSummary();
		int choice;
		do {

			System.out.printf("Welcome %s. What you would like to do?\n", user.getFirstName());

			System.out.println(" 1. Show account transaction history");
			System.out.println(" 2. withdraw");
			System.out.println(" 3. Deposite");
			System.out.println(" 4. Transfer");
			System.out.println(" 5. Quit");

			System.out.println(" Enter choice: ");
			choice = sc.nextInt();

			if (choice < 1 || choice > 5) {
				System.out.println("Invalid choice! Please enter correct choice");
			}

		} while (choice < 1 || choice > 5);

		switch (choice) {
		case 1:
			ATM.showTransactionHistory(user, sc);
			break;
		case 2:
			ATM.withdrawFund(user, sc);
			break;
		case 3:
			ATM.depositeFund(user, sc);
			break;
		case 4:
			ATM.transferFund(user, sc);
			break;
		}

		if (choice != 5) {
			ATM.printUserMenu(user, sc);
		}

	}

	public static void depositeFund(User user, Scanner sc) {
		int toAccount;
		double amount;
		String memo;
		do {
			System.out.printf("Enter the number of account");
			toAccount = sc.nextInt() - 1;

			if (toAccount < 0 || toAccount >= user.numberOfAccounts()) {
				System.out.println("Invalid account, Please try again");

			}

		} while (toAccount < 0 || toAccount >= user.numberOfAccounts());

		do {
			System.out.printf("\n Enter the amount to deposite ");

			amount = sc.nextDouble();

			if (amount < 0) {
				System.out.println("Amount must be greater than 0");

			}

		} while (amount < 0);

		System.out.println("Enter the memo: ");
		memo = sc.nextLine();

		user.addAccountTransaction(toAccount, amount, memo);
	}

	public static void showTransactionHistory(User user, Scanner sc) {

		int theAccount;

		do {
			System.out.printf("Enter the number (1 - %d) of the account \n Whose transaction you want to see:",
					user.numberOfAccounts());

			theAccount = sc.nextInt() - 1;

			if (theAccount < 0 || theAccount >= user.numberOfAccounts()) {
				System.out.println("Invalid account, Please try again");

			}

		} while (theAccount < 0 || theAccount >= user.numberOfAccounts());

		user.printTransactionHistory(theAccount);

	}

	public static void withdrawFund(User user, Scanner sc) {
		int fromAccount;
		double amount;
		double actualBalance;
		String memo;
		do {
			System.out.printf("Enter the number of account");
			fromAccount = sc.nextInt() - 1;

			if (fromAccount < 0 || fromAccount >= user.numberOfAccounts()) {
				System.out.println("Invalid account, Please try again");

			}

		} while (fromAccount < 0 || fromAccount >= user.numberOfAccounts());

		actualBalance = user.getActualBalance(fromAccount);

		do {
			System.out.printf("\n Enter the amount to withdraw (Max $%.02f): ", actualBalance);

			amount = sc.nextDouble();

			if (amount < 0) {
				System.out.println("Amount must be greater than 0");

			}

			if (amount > actualBalance) {
				System.out.println("Amount cannot be greater than actual balance");

			}

		} while (amount < 0 || amount < actualBalance);

		System.out.println("Enter the memo: ");
		memo = sc.nextLine();

		user.addAccountTransaction(fromAccount, -1 * amount, memo);

	}

	public static void transferFund(User user, Scanner sc) {
		int fromAccount;
		int toAccount;
		double amount;
		double actualBalance;

		do {
			System.out.printf("Enter the number of account");
			fromAccount = sc.nextInt() - 1;

			if (fromAccount < 0 || fromAccount >= user.numberOfAccounts()) {
				System.out.println("Invalid account, Please try again");

			}

		} while (fromAccount < 0 || fromAccount >= user.numberOfAccounts());

		actualBalance = user.getActualBalance(fromAccount);

		do {
			System.out.printf("Enter the number of receiver account");
			toAccount = sc.nextInt() - 1;

			if (toAccount < 0 || toAccount >= user.numberOfAccounts()) {
				System.out.println("Invalid account, Please try again");

			}

		} while (toAccount < 0 || toAccount >= user.numberOfAccounts());

		do {
			System.out.printf("\n Enter the amount to transfer (Max $%.02f): ", actualBalance);

			amount = sc.nextDouble();

			if (amount < 0) {
				System.out.println("Amount must be greater than 0");

			}

			if (amount > actualBalance) {
				System.out.println("Amount cannot be greater than actual balance");

			}

		} while (amount < 0 || amount < actualBalance);

		user.addAccountTransaction(fromAccount, -1 * amount,
				String.format("Transfer to account %s", user.getAccountUUID(toAccount)));

		user.addAccountTransaction(toAccount, amount,
				String.format("Transfer from account %s", user.getAccountUUID(fromAccount)));

	}
}
