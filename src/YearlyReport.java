import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class YearlyReport {
    int year;
    ArrayList<YReport> yRows = new ArrayList<>();

    public void readYearReport(String path){
        String fileContents = readFileContentsOrNull(path);
        String[] lines = fileContents.split("\r?\n");
        for(int i = 1; i < lines.length; i++){
            String[] line = lines[i].split(",");
            int month = Integer.parseInt(line[0]);
            int amount = Integer.parseInt(line[1]);
            boolean isExpense = Boolean.parseBoolean(line[2]);
            YReport yReport = new YReport(month, amount, isExpense);

            yRows.add(yReport);
        }
    }

    public int getProfit(int month){
        int mExpense = 0;
        int mIncome = 0;
        for(YReport yReport : yRows) {
            if (yReport.month == month) {
                if (yReport.isExpense) {
                    mExpense = yReport.amount;
                } else {
                    mIncome = yReport.amount;
                }
            }
        }
        return mIncome - mExpense;
    }

    public double getAverageIncome(){
        Double exSum = 0.0;
        for(YReport yReport : yRows){
            if(yReport.isExpense){
                exSum += yReport.amount;
            }
        }
        return exSum/ yRows.size();
    }

    public double getAverageExpense(){
        Double inSum = 0.0;
        for(YReport yReport : yRows){
            if(!yReport.isExpense){
                inSum += yReport.amount;
            }
        }
        return inSum/ yRows.size();
    }

    public int expensesPerMonth(int month){
        int expInMonth = 0;
        for(YReport yReport : yRows){
            if(yReport.month == month){
                if(yReport.isExpense){
                    expInMonth = yReport.amount;
                    break;
                }
            }
        }
        return expInMonth;
    }

    public int incomePerMonth(int month){
        int incInMonth = 0;
        for(YReport yReport : yRows){
            if(yReport.month == month){
                if(!yReport.isExpense){
                    incInMonth = yReport.amount;
                    break;
                }
            }
        }
        return incInMonth;
    }

    public String readFileContentsOrNull(String path)
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}
