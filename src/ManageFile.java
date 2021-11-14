import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
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
}

class Password {

    Scanner scanner = new Scanner(System.in);
    String password;

    private String getPassword() throws IOException {
        FileReader fileReader = new FileReader("password.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String pass = bufferedReader.readLine();

        bufferedReader.close();
        return pass;
    }
    public boolean askForPassword() throws IOException {
        System.out.print("Add meg a jelszót: ");
        password = scanner.nextLine();
        checkPassword(password);

        return true;
    }

    private boolean checkPassword(String pass) throws IOException {
        while(!pass.equals(getPassword())){
            System.out.print("A megadott jelszó helytelen!\n");
            System.out.print("Adja meg újra a jelszót: ");
            pass = scanner.next();

            if (pass.equals(getPassword()))
                break;
        }
        return true;
    }

}