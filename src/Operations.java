import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

class Operations {

    Scanner scanner = new Scanner(System.in);
    ManageBudget manageBudget = new ManageBudget();
    ManageFile file = new ManageFile();


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

            if(manageBudget.budgetCheck(budget, expense, item.getCost()))
                System.out.println("\nA havi keret túllépve!");

            file.addProduct(budget, (expense+item.getCost()), item.getDate(), item.getName(), item.getCost());
        }catch(IOException e){
            System.out.println(e);
        }

    }
}

class ManageBudget {

    public boolean budgetCheck(int budget, int expense, int cost) {
        if (budget < (expense + cost)) return true;
        else return false;
    }

    public int getExpense(String temp) {
        StringTokenizer stringTokenizer = new StringTokenizer(temp, " ");
        int[] a = {0, 0};
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            a[i] = Integer.parseInt(stringTokenizer.nextToken());
            i++;
        }

        return a[1];
    }
    public int getBudget(String temp) {
        StringTokenizer stringTokenizer = new StringTokenizer(temp, " ");
        int[] a = {0, 0};
        int i = 0;
        while(stringTokenizer.hasMoreTokens())
        {
            a[i] = Integer.parseInt(stringTokenizer.nextToken());
            i++;
        }

        return a[0];
    }
}