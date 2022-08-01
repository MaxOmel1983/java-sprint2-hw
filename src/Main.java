import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DataProcessing dataProcessing = new DataProcessing();

        boolean isYearReportDownload = false;
        boolean isMonthReportDownload = false;

        while (true) {
            printMenu();
            int command = scanner.nextInt();

            if (command == 1) {
                int monthsNumber = 3;

                dataProcessing.makeMonthlyReport(monthsNumber);

                System.out.println("Месячные отчеты загружены.");
                isMonthReportDownload = true;
            } else if (command == 2) {
                int yearNumber = 2021;
                dataProcessing.makeYearlyReport(yearNumber);

                System.out.println("Годовые отчеты загружены.");
                isYearReportDownload = true;

            } else if (command == 3) {
                if ((isYearReportDownload == true) && (isMonthReportDownload == true)) {
                    dataProcessing.compareReports();
                } else {
                    System.out.println("Загрузите годовые и месячные отчеты.");
                }
            } else if (command == 4) {
                if (isMonthReportDownload == true) {
                    dataProcessing.printMonthReport();
                } else {
                    System.out.println("Загрузите месячные отчеты.");
                }
            } else if (command == 5) {
                if (isYearReportDownload == true) {
                    dataProcessing.printYearReport();
                } else {
                    System.out.println("Загрузите годовые отчеты.");
                }
            } else if (command == 0) {
                break;
            } else {
                System.out.println("Такой команды не существует.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что Вы хотитете сделать:");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выйти из меню");
    }
}

