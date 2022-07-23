import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean isYearReportDownload = false;
        boolean isMonthReportDownload = false;

        YearlyReport yearReport = new YearlyReport();
        HashMap<Integer, MonthlyReport> yearByMonthReport = new HashMap<>();

        while(true){
            printMenu();
            int command = scanner.nextInt();

            if(command == 1){
                int monthsNumber = 3;

                for(int i = 1; i <= monthsNumber; i++){
                    String dataPath = "resources/m.20210" + i + ".csv";
                    MonthlyReport monthlyReport = new MonthlyReport();
                    monthlyReport.readMonthReport(dataPath);
                    yearByMonthReport.put(i, monthlyReport);
                }

                System.out.println("Месячные отчеты загружены.");
                isMonthReportDownload = true;
            }else if(command == 2){

                int yearNumber = 2021;

                String yearPath = "resources/y." + yearNumber + ".csv";
                yearReport.year = yearNumber;
                yearReport.readYearReport(yearPath);

                System.out.println("Годовые отчеты загружены.");
                isYearReportDownload = true;

            }else if(command == 3){
                if((isYearReportDownload == true) && (isMonthReportDownload == true)){

                    boolean isReportCorrect = true;

                    for(int i = 1; i <= yearByMonthReport.size(); i++){
                        if((yearReport.expensesPerMonth(i) != yearByMonthReport.get(i).getSumExpenses()) ||
                                (yearReport.incomePerMonth(i) != yearByMonthReport.get(i).getSumIncome())){
                            isReportCorrect = false;
                            System.out.println("В " + fromNumToMonth(i) + " несоответсчтвие годового отчета месячному.");
                        }
                    }
                    if(isReportCorrect){
                        System.out.println("В результате проведения сверки годового и месячного отчета ошибок не обнаружено. Поздравляем!!!");
                    }

                }else{
                    System.out.println("Загрузите годовые и месячные отчеты.");
                }

            }else if(command == 4){
                if(isMonthReportDownload == true){

                    for (int i = 1; i <= yearByMonthReport.size(); i++) {
                        System.out.println("В " + fromNumToMonth(i) + " самым прибыльным товаром был " + yearByMonthReport.get(i).maxIncome().keySet()
                                + ", сумма прибыли по нему составила: " + yearByMonthReport.get(i).maxIncome().values() + ";");
                        System.out.println("В " + fromNumToMonth(i) + ", самаой большой тратой была " + yearByMonthReport.get(i).maxExpense().keySet()
                                + ", сумма трат по нему составила: " + yearByMonthReport.get(i).maxExpense().values() + ";");
                    }

                }else{
                    System.out.println("Загрузите месячные отчеты.");
                }

            }else if(command == 5){
                if(isYearReportDownload == true){

                    System.out.println("В " + yearReport.year + " году прибыль по каждому месяцу составила: ");
                    for(int i = 1; i <= yearReport.yRows.size(); i++) {
                        System.out.println("В " + fromNumToMonth(i) + " прибыль составила " + yearReport.getProfit(i) + ";");
                    }
                    System.out.println("Средний доход за все месяцы составил " + yearReport.getAverageIncome() + ";");
                    System.out.println("Средний расход за все месяцы составил " + yearReport.getAverageExpense() + ";");

                }else{
                    System.out.println("Загрузите годовые отчеты.");
                }

            }else if(command == 0){
                break;
            }else{
                System.out.println("Такой команды не существует.");
            }
        }
    }

    public static void printMenu(){
        System.out.println("Что Вы хотитете сделать:");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выйти из меню");
    }

    public static String fromNumToMonth(int number){
        if(number == 1){
            return "январе";
        }else if(number == 2){
            return "феврале";
        }else if(number == 3){
            return "марте";
        }else if(number == 4){
            return "апреле";
        }else if(number == 5){
            return "мае";
        }else if(number == 6){
            return "июне";
        }else if(number == 7){
            return "июле";
        }else if(number == 8){
            return "августе";
        }else if(number == 9){
            return "сентябре";
        }else if(number == 10){
            return "октябре";
        }else if(number == 11){
            return "ноябре";
        }else if(number == 12){
            return "декабре";
        }
        return null;
    }
}

