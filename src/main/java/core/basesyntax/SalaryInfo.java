package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int amountOfDateIndex = 0;
    private static final int amountOfHourIndex = 2;
    private static final int amountOfMoneyIndex = 3;

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                                            + dateFrom + " - " + dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        System.out.println(localDateFrom + " and " + localDateTo);
        for (int i = 0, fullSalary = 0; i < names.length; i++) {
            for (String pieceOfData : data) {
                if (pieceOfData.contains(names[i])) {
                    String[] allInfo = pieceOfData.split(" ");
                    if (isDateIncludes(localDateFrom, localDateTo, allInfo[amountOfDateIndex])) {
                        fullSalary += Integer.parseInt(allInfo[amountOfHourIndex])
                                * Integer.parseInt(allInfo[amountOfMoneyIndex]);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator()).append(names[i])
                            .append(" - ").append(fullSalary);
            fullSalary = 0;
        }
        return stringBuilder.toString();
    }

    private boolean isDateIncludes(LocalDate dateFrom, LocalDate dateTo, String currentDateString) {
        LocalDate currentDate = LocalDate.parse(currentDateString, formatter);
        return (currentDate.isAfter(dateFrom) && currentDate.isBefore(dateTo))
                || (currentDate.isEqual(dateFrom) || currentDate.isEqual(dateTo));
    }
}
