import java.util.Scanner;

class Item {

    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    private int cost;
    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }

    private String date;
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}

class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Password password = new Password();



        try {
            if (password.askForPassword()) {
                char task = '1';
                System.out.println("Sikeres belépés!");
                do {
                    Operations operations = new Operations();

                    System.out.print("\nVálasszon műveletet:\n");
                    System.out.print("1. Új bejegyzés\n" +
                                    "2. Napi kiadások\n");

                    task = scanner.next().charAt(0);

                    switch (task) {
                        case '1':
                            operations.makeEntry();
                            break;
                        case '2':
                            operations.expensesOfDay();
                            break;

                    }
                    System.out.print("\nOpciók:\n1. További művelet\n2. Kilépés\n");
                    task = scanner.next().charAt(0);

                    switch (task) {
                        case '1':

                    }

                } while (task == '1');



            }

        } catch (Exception e) {
            System.out.println(e);
        }

        scanner.close();
    }
}
