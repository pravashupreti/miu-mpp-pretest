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

	public void addAccount(Account account) {
		this.accounts.add(account);
	}

	public String getUUID() {
		return UUID;
	}

}
