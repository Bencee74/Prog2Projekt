import java.io.*;
import java.util.StringTokenizer;


class ManageFile {

    File logFile;

    public void addProduct(int budget, int expense, String date, String name, int cost) {

        try{
            logFile = new File("kiadasok_" + currentYearAndMonth(date) + ".txt");

            FileWriter fileWriter = new FileWriter(logFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            if (logFile.length() == 0)
                bufferedWriter.write("keret \tkiadás\tdátum\t\ttermék\tár\n");

            bufferedWriter.append(budget + "\t" + expense + "\t" + date + "\t" + name + "\t" + cost);
            bufferedWriter.newLine();
            bufferedWriter.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public String budgetOfDate(String date) throws IOException {

        logFile = new File("kiadasok_" + currentYearAndMonth(date) + ".txt");
        if (!logFile.exists()) {
            logFile.createNewFile();
        }
        FileReader fileReader = new FileReader(logFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String currentBudgetAndExpense = null;
        String line, last = null;
        if (bufferedReader.readLine() == null) {
            currentBudgetAndExpense = null;
        } else {
            while ((line = bufferedReader.readLine()) != null) {
                last = line;
            }

            String[] arr = new String[5];
            int i = 0;
            StringTokenizer stringTokenizer = new StringTokenizer(last, "\t");
            while (stringTokenizer.hasMoreTokens()) {
                arr[i] = stringTokenizer.nextToken();
                i++;
            }
            currentBudgetAndExpense = arr[0].toString().concat(" " + arr[1].toString());
        }
        bufferedReader.close();
        return currentBudgetAndExpense;

    }

    private String currentYearAndMonth(String date) {

        String yearMonth = "";
        int i = 0;

        if (date.length() == 7)
            date = date.concat(".00");

        StringTokenizer stringTokenizer = new StringTokenizer(date, ".");

        String[] arr = new String[3];
        while (stringTokenizer.hasMoreTokens()) {
            arr[i] = stringTokenizer.nextToken();
            i++;
        }
        if (date.length() == 10)
            yearMonth = arr[0].toString().concat(arr[1].toString());
        return yearMonth;
    }
}