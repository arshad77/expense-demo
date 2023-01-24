package dev.expense.models.split;

import dev.expense.models.user.User;

public class ExactSplit extends Split {

	public ExactSplit(User user, double amount) {
		super(user);
		this.amount = amount;
	}
}