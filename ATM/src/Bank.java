import java.util.ArrayList;
import java.util.Random;

public class Bank {
	private String name;
	private ArrayList<User> users;
	private ArrayList<Account> accounts;

	public String getNewUUID() {
		String uuid;
		Random rnd = new Random();
		int len = 6;
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
		int len = 6;
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

}
