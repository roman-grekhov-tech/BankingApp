import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser {

    final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public BankTransaction parseFrom(String line) {
        String[] columns = line.split(",");

        final LocalDate date = LocalDate.parse(columns[0], DATE_FORMAT);
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];

        return new BankTransaction(date, amount, description);
    }

    public List<BankTransaction> parseLinesFrom(List<String> lines) {
        List<BankTransaction> transactions = new ArrayList<>();
        for (String line : lines) {
            transactions.add(parseFrom(line));
        }

        return transactions;
    }
}
