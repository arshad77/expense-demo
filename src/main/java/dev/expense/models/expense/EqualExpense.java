package dev.expense.models.expense;

import java.util.List;

import dev.expense.models.split.EqualSplit;
import dev.expense.models.split.Split;
import dev.expense.models.user.User;

public class EqualExpense extends Expense {
	public EqualExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
		super(amount, paidBy, splits, expenseMetadata);
	}

	@Override
	public boolean validate() {
		for (Split split : getSplits()) {
			if (!(split instanceof EqualSplit)) {
				return false;
			}
		}

		return true;
	}
}
