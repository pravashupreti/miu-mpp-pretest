import java.util.ArrayList;
import java.util.Random;

public class Bank {
	private String name;
	private ArrayList<User> users;
	private ArrayList<Account> accounts;

	public Bank(String name) {
		this.name = name;
		users = new ArrayList<User>();
		accounts = new ArrayList<Account>();

	}

	public String getName() {
		return name;
	}

	public String getNewUUID() {
		String uuid;
		Random rnd = new Random();
		int len = 3;
		boolean noUnique = false;

		do {

			uuid = "";
			for (int c = 0; c < len; c++) {
				uuid += ((Integer) rnd.nextInt(10)).toString();
			}

			noUnique = false;

			for (User u : this.users) {
				if (uuid.compareTo(u.getUUID()) == 0) {
					noUnique = true;
					break;
				}
			}

		} while (noUnique);

		return uuid;
	}

	public String getNewAccountUUID() {
		String uuid;
		Random rnd = new Random();
		int len = 3;
		boolean noUnique = false;

		do {

			uuid = "";
			for (int c = 0; c < len; c++) {
				uuid += ((Integer) rnd.nextInt(10)).toString();
			}

			noUnique = false;

			for (Account a : this.accounts) {
				if (uuid.compareTo(a.getUUID()) == 0) {
					noUnique = true;
					break;
				}
			}

		} while (noUnique);

		return uuid;

	}

	public void addAccount(Account account) {
		this.accounts.add(account);
	}

	public User addUser(String firstName, String lastName, String pin) {
		User user = new User(firstName, lastName, pin, this);
		this.users.add(user);

		Account account = new Account("Saving", user, this);

		user.addAccount(account);
		this.addAccount(account);

		return user;

	}

	public User userLogin(String userUUID, String pin) {
		for (User u : this.users) {
			if (u.getUUID().compareTo(userUUID) == 0 && u.validatePIN(pin)) {
				return u;
			}
		}

		return null;
	}
}
