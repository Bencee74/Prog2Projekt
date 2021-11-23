import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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