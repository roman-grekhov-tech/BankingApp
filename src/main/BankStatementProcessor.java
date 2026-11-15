package main;

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
}
