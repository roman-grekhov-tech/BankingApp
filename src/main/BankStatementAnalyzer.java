package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {
    private static final String RESOURCES = "src/main/resources/";

    public static void main(String[] args) throws IOException {
        analyze(args[0], new BankStatementCSVParser());
    }

    public static void analyze(String fileName, BankStatementParser bankStatementParser) throws IOException {
        Path path = Paths.get(RESOURCES + fileName);
        List<String> lines = Files.readAllLines(path);

        List<BankTransaction> transactions = bankStatementParser.parseLinesFrom(lines);

        BankStatementProcessor processor = new BankStatementProcessor(transactions);

        collectSummary(processor);
    }

    private static void collectSummary(BankStatementProcessor processor) {
        System.out.println("The total for all transactions is "
                + processor.calculateTotalAmount());
        System.out.println("The total for transactions in January is "
                + processor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("The total for transactions in February is "
                + processor.calculateTotalInMonth(Month.FEBRUARY));
        System.out.println("The total salary received is "
                + processor.calculateTotalForCategory("Salary"));
    }
}
