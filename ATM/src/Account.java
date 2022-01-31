import java.util.ArrayList;

public class Account {
	private String name;
	private double balance;
	private String UUID;
	private User holder;
	private ArrayList<Transaction> transactions;

	public Account(String name, User holder, Bank bank) {
		this.name = name;
		this.holder = holder;
		this.UUID = bank.getNewAccountUUID();

		this.transactions = new ArrayList<Transaction>();

		holder.addAccount(this);
		bank.addAccount(this);

	}

	public String getUUID() {
		return UUID;
	}
}
