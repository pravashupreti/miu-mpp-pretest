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

	}

	public String getUUID() {
		return UUID;
	}

	public String getSummaryLine() {
		double balance = getBalance();
		if (balance >= 0) {
			return String.format("%s : $%.02f : %s", UUID, balance, name);
		} else {
			return String.format("%s : $(%.02f) : %s", UUID, balance, name);

		}
	}

	public double getBalance() {
		double balance = 0;

		for (Transaction t : transactions) {
			balance += t.getAmount();
		}
		return balance;
	}

	public void printTransactionHistory() {
		System.out.printf("\n Transaction history for account %s \n", UUID);
		for (int t = transactions.size(); t >= 0; t--) {
			System.out.printf(this.transactions.get(t).getSummaryLine());
		}
	}

	public void addTransaction(double amount, String memo) {
		Transaction transaction = new Transaction(amount, this, memo);
		this.transactions.add(transaction);

	}
}
