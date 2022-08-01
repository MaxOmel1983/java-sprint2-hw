import java.util.HashMap;

public class DataProcessing {
    YearlyReport yearReport = new YearlyReport();
    HashMap<Integer, MonthlyReport> yearByMonthReport = new HashMap<>();

    public void makeMonthlyReport(int monthsNumber) {
        for (int i = 1; i <= monthsNumber; i++) {
            String dataPath = "resources/m.20210" + i + ".csv";
            MonthlyReport monthlyReport = new MonthlyReport();
            monthlyReport.readMonthReport(dataPath);
            yearByMonthReport.put(i, monthlyReport);
        }
    }

    public void makeYearlyReport(int yearNumber) {
        String yearPath = "resources/y." + yearNumber + ".csv";
        yearReport.year = yearNumber;
        yearReport.readYearReport(yearPath);
    }

    public void compareReports() {
        boolean isReportCorrect = true;
        for (int i = 1; i <= yearReport.yRows.size() / 2; i++) {
            if ((yearReport.expensesPerMonth(i) != yearByMonthReport.get(i).getSumExpenses()) ||
                    (yearReport.incomePerMonth(i) != yearByMonthReport.get(i).getSumIncome())) {
                isReportCorrect = false;
                System.out.println("В " + fromNumToMonth(i) + " несоответсчтвие годового отчета месячному.");
            }
        }
        if (isReportCorrect) {
            System.out.println("В результате проведения сверки годового и месячного отчета ошибок не обнаружено." +
                    " Поздравляем!!!");
        }
    }

    public void printMonthReport() {
        for (int i = 1; i <= yearByMonthReport.size(); i++) {
            System.out.println("В " + fromNumToMonth(i) + " самым прибыльным товаром был "
                    + yearByMonthReport.get(i).maxIncome().name
                    + ", сумма прибыли по нему составила: " + yearByMonthReport.get(i).maxIncome().amount + ";");
            System.out.println("В " + fromNumToMonth(i) + " самой большой тратой была "
                    + yearByMonthReport.get(i).maxExpense().name
                    + ", сумма трат по нему составила: " + yearByMonthReport.get(i).maxExpense().amount + ";");
        }
    }

    public void printYearReport() {
        System.out.println("В " + yearReport.year + " году прибыль по каждому месяцу составила: ");
        for (int i = 1; i <= yearReport.yRows.size() / 2; i++) {
            System.out.println("В " + fromNumToMonth(i) + " прибыль составила " + yearReport.getProfit(i) + ";");
        }
        System.out.println("Средний доход за все месяцы составил " + yearReport.getAverageIncome() + ";");
        System.out.println("Средний расход за все месяцы составил " + yearReport.getAverageExpense() + ";");
    }

    public String fromNumToMonth(int number) {
        if (number == 1) {
            return "январе";
        } else if (number == 2) {
            return "феврале";
        } else if (number == 3) {
            return "марте";
        } else if (number == 4) {
            return "апреле";
        } else if (number == 5) {
            return "мае";
        } else if (number == 6) {
            return "июне";
        } else if (number == 7) {
            return "июле";
        } else if (number == 8) {
            return "августе";
        } else if (number == 9) {
            return "сентябре";
        } else if (number == 10) {
            return "октябре";
        } else if (number == 11) {
            return "ноябре";
        } else if (number == 12) {
            return "декабре";
        }
        return null;
    }
}
