package dev.expense.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dev.expense.controllers.ExpenseController;
import dev.expense.models.expense.ExpenseType;
import dev.expense.models.split.*;
import dev.expense.models.user.User;

public class Main {
	public static void main(String[] args) {
		System.out.println("IN MAIN");
		System.out.println(Arrays.toString(args));
		ExpenseController expenseManager = new ExpenseController();
		expenseManager.addUser(new User("u1", "User1", "gaurav@workat.tech", "9876543210"));
		expenseManager.addUser(new User("u2", "User2", "sagar@workat.tech", "9876543210"));
		expenseManager.addUser(new User("u3", "User3", "hi@workat.tech", "9876543210"));
		expenseManager.addUser(new User("u4", "User4", "mock-interviews@workat.tech", "9876543210"));

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (true) {

			String command = sc.nextLine();
			String[] commands = command.split(" ");
			String commandType = commands[0];

			switch (commandType) {
			case "SHOW":
				if (commands.length == 1) {
					expenseManager.showBalances();
				} else {
					expenseManager.showBalance(commands[1]);
				}
				break;
			case "EXPENSE":
				// u1 1250 2 u2 u3 EXACT 370 880
				String paidBy = commands[1];
				Double amount = Double.parseDouble(commands[2]);
				int noOfUsers = Integer.parseInt(commands[3]);
				String expenseType = commands[4 + noOfUsers];
				List<Split> splits = new ArrayList<>();
				switch (expenseType) {
				case "EQUAL":
					for (int i = 0; i < noOfUsers; i++) {
						splits.add(new EqualSplit(expenseManager.userMap.get(commands[4 + i])));
					}
					expenseManager.addExpense(ExpenseType.EQUAL, amount, paidBy, splits, null);
					break;
				case "EXACT":
					for (int i = 0; i < noOfUsers; i++) {
						splits.add(new ExactSplit(expenseManager.userMap.get(commands[4 + i]),
								Double.parseDouble(commands[5 + noOfUsers + i])));
					}
					expenseManager.addExpense(ExpenseType.EXACT, amount, paidBy, splits, null);
					break;
				case "PERCENT":
					for (int i = 0; i < noOfUsers; i++) {
						splits.add(new PercentSplit(expenseManager.userMap.get(commands[4 + i]),
								Double.parseDouble(commands[5 + noOfUsers + i])));
					}
					expenseManager.addExpense(ExpenseType.PERCENT, amount, paidBy, splits, null);
					break;
				}
				break;
			}
		}
	}
}
