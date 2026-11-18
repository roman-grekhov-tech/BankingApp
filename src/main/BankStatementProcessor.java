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
            if (!isExpense(transaction)) continue;
            if (transaction.getAmount() > leastExpensive.getAmount()) {
                leastExpensive = transaction;
            }
        }

        return leastExpensive;
    }

    private boolean isExpense(BankTransaction transaction) {
        return transaction.getAmount() < 0;
    }
}
