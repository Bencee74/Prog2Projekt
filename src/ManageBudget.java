import java.util.StringTokenizer;

class ManageBudget {

    public boolean checkBudget(int budget, int expense, int cost) {
        if (budget < (expense + cost)) return true;
        else return false;
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
}