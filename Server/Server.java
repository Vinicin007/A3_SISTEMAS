package Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Aguardando conexões de clientes...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado!");
                

                handleClient(clientSocket);
                 clientSocket.close();
                System.out.println("Conexão com o cliente encerrada.");
            }
            
        } catch (ConnectException e) {
            System.out.println("Erro: " + e.getMessage());
        } 
    }

    private static void handleClient(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String clientChoice;
        String serverChoice;
        String result;

        while (true) {
            out.println("Pedra, Papel ou Tesoura? Digite sua escolha: (ou 'sair' para encerrar)");
            clientChoice = in.readLine();
        
            if (clientChoice == null || clientChoice.equalsIgnoreCase("sair")) {
                break;
            }
        
            serverChoice = getServerChoice();
            result = getResult(clientChoice, serverChoice);
        
            out.println("Você escolheu: " + clientChoice);
            out.println("A maquína escolheu: " + serverChoice);
            out.println("Resultado: " + result);
        }

        
    }

    private static String getServerChoice() {
        int choice = (int) (Math.random() * 3);

        switch (choice) {
            case 0:
                return "Pedra";
            case 1:
                return "Papel";
            default:
                return "Tesoura";
        }
    }

    private static String getResult(String clientChoice, String serverChoice) {
        if (clientChoice.equalsIgnoreCase(serverChoice)) {
            return "Empate!";
        } else if ((clientChoice.equalsIgnoreCase("pedra") && serverChoice.equalsIgnoreCase("tesoura")) ||
                (clientChoice.equalsIgnoreCase("papel") && serverChoice.equalsIgnoreCase("pedra")) ||
                (clientChoice.equalsIgnoreCase("tesoura") && serverChoice.equalsIgnoreCase("papel"))) {
            return "Você venceu!";
        } else {
            return "A máquina venceu!";
        }
    }
}
