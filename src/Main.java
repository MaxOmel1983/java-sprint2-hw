import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        YearReport yearReport = new YearReport(2021, "resources/y.2021.csv");

        HashMap<Integer, MonthlyReport> yearByMonthReport = new HashMap<>();
        for(int i = 1; i < 4; i++){
            String dataPath = "resources/m.20210" + i + ".csv";
            MonthlyReport monthlyReport = new MonthlyReport(dataPath);
            yearByMonthReport.put(i, monthlyReport);
        }


        while(true){
            printMenu();
            int command = scanner.nextInt();

            if(command == 1){


            }else if(command == 2){


            }else if(command == 3){
                boolean isReportCorrect = true;

                for(int i = 0; i < yearByMonthReport.size(); i++){
                       if((yearReport.expensesPerMonth(i + 1) != yearByMonthReport.get(i + 1).getSumExpenses()) ||
                            (yearReport.incomePerMonth(i + 1) != yearByMonthReport.get(i + 1).getSumIncome())){
                        isReportCorrect = false;
                        System.out.println("В " + fromNumToMonth(i) + " несоответсчтвие годового отчета месячному.");
                    }
                }
                if(isReportCorrect){
                    System.out.println("В результате проведения сверки годового и месячного отчета ошибок не обнаружено. Поздравляем!!!");
                }
            }else if(command == 4){
                for (int i = 0; i < yearByMonthReport.size(); i++) {
                    System.out.println("В " + fromNumToMonth(i) + " самым прибыльным товаром был " + yearByMonthReport.get(i + 1).maxIncome().keySet()
                                + ", сумма прибыли по нему составила: " + yearByMonthReport.get(i + 1).maxIncome().values() + ";");
                    System.out.println("В " + fromNumToMonth(i) + ", самаой большой тратой была " + yearByMonthReport.get(i+1).maxExpense().keySet()
                                 + ", сумма трат по нему составила: " + yearByMonthReport.get(i+1).maxExpense().values() + ";");
                }

            }else if(command == 5){
                System.out.println("В " + yearReport.year + " году прибыль по каждому месяцу составила: ");
                for(int i = 0; i < yearReport.yRows.size(); i++) {
                    System.out.println("В " + fromNumToMonth(i) + " прибыль составила " + yearReport.getProfit(i + 1) + ";");
                }
                System.out.println("Средний доход за все месяцы составил " + yearReport.getAverageIncome() + ";");
                System.out.println("Средний расход за все месяцы составил " + yearReport.getAverageExpense() + ";");

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
        if(number == 0){
            return "январе";
        }else if(number == 1){
            return "феврале";
        }else if(number == 2){
            return "марте";
        }else if(number == 3){
            return "апреле";
        }else if(number == 4){
            return "мае";
        }else if(number == 5){
            return "июне";
        }else if(number == 6){
            return "июле";
        }else if(number == 7){
            return "августе";
        }else if(number == 8){
            return "сентябре";
        }else if(number == 9){
            return "октябре";
        }else if(number == 10){
            return "ноябре";
        }else if(number == 11){
            return "декабре";
        }
        return null; // не знаю, зачем это? поставила система 220720_2130
    }
}

