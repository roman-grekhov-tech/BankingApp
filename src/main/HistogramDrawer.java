package main;

import java.time.Month;

public class HistogramDrawer {
    public static void drawHistogramByMonths(double[] expensesByMonth, int divisor) {
        for (int i = 0; i < expensesByMonth.length; i++) {
            int length = (int) (expensesByMonth[i] / divisor) + 1;

            System.out.printf("%-10s: %s%n", Month.of(i+1), "*".repeat(length));
        }
    }
}
