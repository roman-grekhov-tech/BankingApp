import main.BankStatementCSVParser;
import main.BankStatementParser;
import main.BankTransaction;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementSCVParserTest {
    private final BankStatementParser parser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() {
        final String line = "30-01-2017,-100,Deliveroo";

        BankTransaction result = parser.parseFrom(line);

        BankTransaction expected = new BankTransaction(
                LocalDate.of(2017, Month.JANUARY, 30),
                -100,
                "Deliveroo"
        );

        final double tolerance = 0.0d;

        Assert.assertEquals(expected.getDate(), result.getDate());
        Assert.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        Assert.assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void shouldParseThreeCorrectLinesFromList() {
        List<String> lines = new ArrayList<>();
        lines.add("30-01-2017,-100,Deliveroo");
        lines.add("30-01-2017,-50,Tesco");
        lines.add("01-02-2017,6000,Salary");

        List<BankTransaction> result = parser.parseLinesFrom(lines);

        List<BankTransaction> expected = new ArrayList<>();
        expected.add(new BankTransaction(
                LocalDate.of(2017, Month.JANUARY, 30),
                -100,
                "Deliveroo")
        );

        expected.add(new BankTransaction(
                LocalDate.of(2017, Month.JANUARY, 30),
                -50,
                "Tesco")
        );

        expected.add(new BankTransaction(
                LocalDate.of(2017, Month.FEBRUARY, 1),
                6000,
                "Salary")
        );

        Assert.assertEquals(result.size(), expected.size());

        final double tolerance = 0.0d;

        for (int i = 0; i < expected.size(); i++) {
            Assert.assertEquals(result.get(i).getDate(), expected.get(i).getDate());
            Assert.assertEquals(result.get(i).getAmount(), expected.get(i).getAmount(), tolerance);
            Assert.assertEquals(result.get(i).getDescription(), expected.get(i).getDescription());
        }
    }
}
