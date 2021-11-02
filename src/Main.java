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
        char task = '1';

        try {
            while (task == '1') {
                Operations op = new Operations();
                op.makeEntry();

                System.out.print("\nOpciók:\n1. További művelet\n2. Kilépés");
                System.out.print("\nVálasszon műveletet: ");

                task = scanner.next().charAt(0);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        scanner.close();
    }
}
