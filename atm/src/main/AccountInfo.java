package main;

public class AccountInfo {

	private Integer userId;
	private String name;
	private Double balance;

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("AccountInfo{");
		sb.append("userId=").append(userId);
		sb.append(", name='").append(name).append('\'');
		sb.append(", balance=").append(balance);
		sb.append('}');
		return sb.toString();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
}
