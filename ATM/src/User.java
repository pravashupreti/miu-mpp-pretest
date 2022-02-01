import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {

	private String firstName;
	private String lastName;
	private String UUID;
	private byte pinHash[];
	private ArrayList<Account> accounts;

	public User(String firstName, String lastName, String pin, Bank bank) {

		this.firstName = firstName;
		this.lastName = lastName;

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());

		} catch (NoSuchAlgorithmException e) {
			System.err.println("No such algorithm");
			e.printStackTrace();
			System.exit(1);
		}

		UUID = bank.getNewUUID();

		accounts = new ArrayList<Account>();

		System.out.printf("New users %s %s with ID %s created. \n", firstName, lastName, UUID);

	}

	public String getFirstName() {
		return firstName;
	}

	public void addAccount(Account account) {
		this.accounts.add(account);
	}

	public String getUUID() {
		return UUID;
	}

	public boolean validatePIN(String pin) {

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");

			return MessageDigest.isEqual(md.digest(pin.getBytes()), pinHash);

		} catch (NoSuchAlgorithmException e) {
			System.err.println("No such algorithm");
			e.printStackTrace();
			System.exit(1);
		}

		return false;
	}

	public void printAccountSummary() {
		System.out.printf("\n%s accounts summary:\n", this.firstName);
		for (int i = 0; i < this.accounts.size(); i++) {
			System.out.printf("%d) %s \n", i + 1, this.accounts.get(i).getSummaryLine());
		}
	}

	public int numberOfAccounts() {
		return accounts.size();
	}

	public void printTransactionHistory(int accountIndex) {
		accounts.get(accountIndex).printTransactionHistory();
	}

	public double getActualBalance(int index) {
		return accounts.get(index).getBalance();
	}

	public String getAccountUUID(int index) {
		return accounts.get(index).getUUID();
	}

	public void addAccountTransaction(int index, double amount, String memo) {
		accounts.get(index).addTransaction(amount, memo);
	}
}
