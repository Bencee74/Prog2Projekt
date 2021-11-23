import java.io.*;
import java.util.ArrayList;
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

        String currentBudgetAndExpense;
        String line, last  = null;

        if (bufferedReader.readLine() == null) {
            currentBudgetAndExpense = null;
        }
        else {
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
            currentBudgetAndExpense = arr[0].concat(" " + arr[1]);
        }
        bufferedReader.close();
        return currentBudgetAndExpense;

    }

    public String currentYearAndMonth(String date) {

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
            yearMonth = arr[0].concat(arr[1]);
        return yearMonth;
    }

    public ArrayList<Item> dayDetails(String date) throws IOException
    {

        logFile = new File("kiadasok_" + currentYearAndMonth(date) + ".txt");
        FileReader fileReader = new FileReader(logFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String temp;

        ArrayList<Item> arrayList = new ArrayList<>();
        while((temp = bufferedReader.readLine()) != null ){
            Item item = new Item();
            StringTokenizer stringTokenizer = new StringTokenizer(temp, "\t");
            String[] str = new String[5];
            int i = 0;
            while(stringTokenizer.hasMoreTokens()){
                str[i] = stringTokenizer.nextToken();
                i++;
            }

            if(str[2].equals(date))
            {
                item.setDate(str[2]);
                item.setName(str[3]);
                item.setCost(Integer.parseInt(str[4]));
                arrayList.add(item);
            }
        }
        fileReader.close();

        return arrayList;
    }


    public ArrayList<Item> currentMonthLog(String date) throws IOException {

        logFile = new File("kiadasok_" + currentYearAndMonth(date) + ".txt");
        FileReader fileReader = new FileReader(logFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.readLine();

        String temp;

        ArrayList<Item> arrayList = new ArrayList<>();

        while((temp = bufferedReader.readLine()) != null ){
            Item item = new Item();
            StringTokenizer stringTokenizer = new StringTokenizer(temp, "\t");
            String[] str = new String[5];
            int i = 0;
            while(stringTokenizer.hasMoreTokens()){
                str[i] = stringTokenizer.nextToken();
                i++;
            }

            item.setDate(str[2]);
            item.setName(str[3]);
            item.setCost(Integer.parseInt(str[4]));
            arrayList.add(item);
        }
        bufferedReader.close();
        return arrayList;
    }
}
