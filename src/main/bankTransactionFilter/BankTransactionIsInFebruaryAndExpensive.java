package main.bankTransactionFilter;

import main.BankTransaction;

import java.time.Month;

public class BankTransactionIsInFebruaryAndExpensive implements BankTransactionFilter {
    public boolean test(BankTransaction bankTransaction) {
        return bankTransaction.getDate().getMonth() == Month.FEBRUARY
                && bankTransaction.getAmount() >= 1000;
    }
}
