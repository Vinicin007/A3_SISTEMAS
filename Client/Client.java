package Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            String serverResponse;

            while ((serverResponse = in.readLine()) != null) {
                System.out.println(serverResponse);

                if (serverResponse.startsWith("Resultado:")) {
                    break;
                }

                String choice = scanner.nextLine();
                out.println(choice);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


