import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.ArrayList;

public class MonthlyReport {
   ArrayList<MReport> mRows = new ArrayList<>();

   public void readMonthReport(String path){
      String content = readFileContentsOrNull(path);
      //String[] lines = content.split("\r?\n");
      String[] lines = content.split(System.lineSeparator());
      for(int i = 1; i < lines.length; i++){
         String[] part = lines[i].split(",");
         String itemName = part[0];
         boolean isExpense = Boolean.parseBoolean(part[1]);
         int quantity = Integer.parseInt(part[2]);
         int sumOfOne = Integer.parseInt(part[3]);
         MReport mReport = new MReport(itemName, isExpense, quantity, sumOfOne);

         mRows.add(mReport);
      }
   }

   // !!! поменять HashMap на класс  // 30.07
   public NameAmount maxExpense(){
      //public HashMap<String, Integer> maxExpense(){                         //30.07
      //HashMap<String, Integer> maxExpNameAndSum = new HashMap<>();          //30.07
      String maxExpenseName = "";
      int maxExpenseSum = 0;
      for(MReport mReport : mRows){
         if(mReport.isExpense){
            if(maxExpenseSum < (mReport.sumOfOne * mReport.quantity)){
                maxExpenseSum = (mReport.sumOfOne * mReport.quantity);
                maxExpenseName = mReport.itemName;
            }
         }
      }
      NameAmount nameAmount = new NameAmount(maxExpenseName, maxExpenseSum);
      //maxExpNameAndSum.put(maxExpenseName, maxExpenseSum);                  //30.07
      //return maxExpNameAndSum;                                              //30.07
      return nameAmount;
   }

   // !!! поменять HashMap на класс  // 30.07
   public NameAmount maxIncome(){
      //public HashMap<String, Integer> maxIncome(){                          // 30.07
      //HashMap<String, Integer> maxIncNameAndSum = new HashMap<>();          // 30.07

      String maxIncomeName = "";
      int maxIncomeSum = 0;
      for(MReport mReport : mRows){
         if(!mReport.isExpense){
            if(maxIncomeSum < (mReport.sumOfOne * mReport.quantity)){
               maxIncomeSum = (mReport.sumOfOne * mReport.quantity);
               maxIncomeName = mReport.itemName;
            }
         }
      }
      //maxIncNameAndSum.put(maxIncomeName, maxIncomeSum);                   // 30.07
      //return maxIncNameAndSum;                                             // 30.07
      NameAmount nameAmount = new NameAmount(maxIncomeName, maxIncomeSum);
      return nameAmount;
   }


   public int getSumExpenses(){
      int expensesSum = 0;
      for(MReport mReport : mRows){
         if(mReport.isExpense){
            expensesSum += (mReport.sumOfOne * mReport.quantity);
         }
      }
      return expensesSum;
   }

   public int getSumIncome(){
      int incomeSum = 0;
      for(MReport mReport : mRows){
         if(!mReport.isExpense){
            incomeSum += (mReport.sumOfOne * mReport.quantity);
         }
      }
      return incomeSum;
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
