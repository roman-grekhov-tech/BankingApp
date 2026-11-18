package main;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class BankStatementProcessor {

    private final List<BankTransaction> transactions;

    public BankStatementProcessor(List<BankTransaction> transactions) {
        this.transactions = transactions;
    }

    public double calculateTotalAmount() {
        double result = 0;
        for (BankTransaction transaction : transactions) {
            result += transaction.getAmount();
        }

        return result;
    }

    public double calculateTotalInMonth(Month month) {
        double result = 0;
        for (BankTransaction transaction : transactions) {
            if (transaction.getDate().getMonth() == month) {
                result += transaction.getAmount();
            }
        }

        return result;
    }

    public double calculateTotalForCategory(String category) {
        double result = 0;
        for (BankTransaction transaction : transactions) {
            if (transaction.getDescription().equals(category)) {
                result += transaction.getAmount();
            }
        }

        return result;
    }

    public BankTransaction findMostExpensiveTransactionInPeriod(LocalDate start, LocalDate end) {
        BankTransaction mostExpensive = transactions.getFirst();

        for (BankTransaction transaction : transactions) {
            if (transaction.getDate().isBefore(start) || transaction.getDate().isAfter(end)) continue;
            if (transaction.getAmount() < mostExpensive.getAmount()) {
                mostExpensive = transaction;
            }
        }

        return mostExpensive;
    }

    public BankTransaction findLeastExpensiveTransactionInPeriod(LocalDate start, LocalDate end) {
        BankTransaction leastExpensive = transactions.getFirst();

        for (BankTransaction transaction : transactions) {
            if (transaction.getDate().isBefore(start) || transaction.getDate().isAfter(end)) continue;
            if (!transaction.isExpense()) continue;
            if (transaction.getAmount() > leastExpensive.getAmount()) {
                leastExpensive = transaction;
            }
        }

        return leastExpensive;
    }

    public double[] calculateExpensesByMonth() {
        double[] expensesByMonth = new double[12];

        for (BankTransaction transaction : transactions) {
            if (!transaction.isExpense()) continue;
            int month = transaction.getDate().getMonthValue() - 1;
            expensesByMonth[month] += transaction.getAmount();
        }

        for (int i = 0; i < 12; i++) {
            expensesByMonth[i] *= -1;
        }

        return expensesByMonth;
    }

//    private double calculateTotalExpensesInMonth(Month month) {
//        double result = 0;
//        for (BankTransaction transaction : transactions) {
//            if (!transaction.isExpense()) continue;
//            if (transaction.getDate().getMonth() != month) continue;
//
//            result += transaction.getAmount();
//        }
//
//        return -result;
//    }
}
