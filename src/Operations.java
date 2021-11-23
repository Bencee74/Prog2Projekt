import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Operations {

    Scanner scanner = new Scanner(System.in);
    ManageBudget manageBudget = new ManageBudget();
    ManageFile file = new ManageFile();
    File logFile;

    /* 1 ------------------------------------------------------------------- */

    public void makeEntry() {

        Item item = new Item();
        System.out.print("\nAdd meg a dátumot (éééé.hh.nn): ");
        item.setDate(scanner.next());
        System.out.print("\nAdd meg a terméket: ");
        scanner.nextLine();
        item.setName(scanner.nextLine());
        System.out.print("\nAdd meg az árát: HUF");
        item.setCost(scanner.nextInt());
        budgetManager(item);
    }



    private void budgetManager(Item item) {

        int budget;
        int expense = 0;
        try{
            String logBook = file.budgetOfDate(item.getDate());
            if(logBook == null){
                System.out.print("\nAdd meg a havi keretet (HUF): ");
                budget = scanner.nextInt();
            }

            else{
                budget = manageBudget.getBudget(logBook);
                expense = manageBudget.getExpense(logBook);
            }
            System.out.print("\nA bejegyzés megtörtént");

            if(manageBudget.checkBudget(budget, expense, item.getCost()))
                System.out.println("\nA havi keret túllépve!");

            file.addProduct(budget, (expense+item.getCost()), item.getDate(), item.getName(), item.getCost());
        }catch(IOException e){
            System.out.println(e);
        }

    }

    /* 2 ------------------------------------------------------------------- */

    public void expensesOfDay() throws IOException{

        System.out.print("\nAdd meg a dátumot (éééé.hh.nn): ");
        String date = scanner.next();

        ArrayList<Item> arrayList = file.dayDetails(date);
        int n = arrayList.size();

        if(n == 0)
            System.out.print("\nEzen a napon még nincs bejegyzés");
        else{
            int sum = 0;
            System.out.println("\n" + date + " nap kiadásainak adatai:\n");
            for(int i = 0; i < n; i++){
                Item item = arrayList.get(i);
                System.out.println(item.getDate() + ": " + item.getName() + " | " + item.getCost() + "HUF");
                sum += item.getCost();
            }
            System.out.println("\nÖsszesen: " + sum + "HUF");
        }
    }

    /* 3 ------------------------------------------------------------------- */

    public void expensesOfMonth() throws IOException {

        System.out.print("\nAdd meg az évet és a hónapot (éééé.hh): ");
        String date = scanner.next();

        ArrayList<Item> arrayList = file.currentMonthLog(date);
        int n = arrayList.size();

        if(n == 0)
            System.out.print("\nEbben a hónapban még nem történt bejegyzés");
        else{
            int sum = 0;
            System.out.println("\n" + date + ". hónap kiadásainak adatai:\n");
            for(int i = 0; i < n; i++){
                Item item = arrayList.get(i);
                System.out.println(item.getDate() + ": " + item.getName() + " | " + item.getCost() + "HUF");
                sum += item.getCost();
            }
            System.out.println("\nÖsszesen: " + sum + "HUF");
        }
    }

    /* 4 ------------------------------------------------------------------- */

    public void displayMonthExpense() throws IOException {

        System.out.print("Add meg az évet és a hónapot (éééé.hh): ");

        String date = scanner.next();
        date = date.concat(".00");

        String budgetData = file.budgetOfDate(date);

        int expense = manageBudget.getExpense(budgetData);
        int budget = manageBudget.getBudget(budgetData);

        System.out.print("\nKeret: " + budget + "HUF |  Kiadás: " + expense + "HUF");

        if(budget > expense)
            System.out.print("\nMég elkölthető: " + (budget - expense) + "HUF");
        else
            System.out.print("\nA keret " + (expense - budget) + "HUF -al túllépve");
    }

    /* 5 ------------------------------------------------------------------- */

    public void deleteMonthLog() {

        System.out.print("Biztos kiakarod törölni egy hónap teljes logját? (I/N): ");
        char choice = scanner.next().charAt(0);
        if(choice == 'I'){
            System.out.print("\nAdd meg az évet és a hónapot (éééé.hh): ");
            String date = scanner.next();

            logFile = new File("kiadasok_" + file.currentYearAndMonth(date) + ".txt");
            boolean bool = true;

            if(logFile.exists())
                bool  = logFile.delete();

            if(bool == true){
                System.out.print("\nLog sikeresen törölve !");
            }
            else
                System.out.print("\nAdott hónapra vonatkozó log nem létezik!");
        }
    }
}
