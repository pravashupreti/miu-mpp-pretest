import java.util.Date;

public class Transaction {
	private double amount;
	private Date timestamp;
	private String memo;
	private Account inAccount;

	public Transaction(double amount, Account inAccount) {
		this.amount = amount;
		this.inAccount = inAccount;
		this.memo = "";
	}

	public Transaction(double amount, Account inAccount, String memo) {
		this(amount, inAccount);
		this.memo = memo;
	}

	public double getAmount() {
		return amount;
	}

	public String getSummaryLine() {
		if (amount > 0) {
			return String.format("%s : $%s.02f :%s ", timestamp.toString(), amount, memo);
		} else {
			return String.format("%s : $(%s.02f) :%s ", timestamp.toString(), amount, memo);
		}
	}

}
