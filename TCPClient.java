// 62050238 Siriya Saenkhom-or

import java.io.*;
import java.net.*;
import java.util.*;

class TCPClient {
    public static void main(String argv[]) throws Exception {
        Scanner inFromUser = null;
        Socket clientSocket = null;
        DataOutputStream outToServer = null;
        Scanner inFromServer = null;
        try {
            inFromUser = new Scanner(System.in);
            clientSocket = new Socket("localhost", 1667);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new Scanner(clientSocket.getInputStream());

            // number 1
            System.out.print("enter number 1 (to end just press enter): ");
            String num1 = inFromUser.nextLine();
            outToServer.writeBytes(num1 + '\n');

            // number 2
            if (inFromServer.hasNextLine()) { // for catch err No Line Found
                String instruction2 = inFromServer.nextLine();
                System.out.print(instruction2);
                num1 = inFromUser.nextLine();
                outToServer.writeBytes(num1 + '\n');
            }

            // result
            if(inFromServer.hasNextLine()) { // for catch err No Line Found
                String result = inFromServer.nextLine();
                System.out.println("The result is " + result);
            }

        } catch (IOException e) {
            System.out.println("Error occurred: Closing the connection");
        } finally {
            try {
                if (inFromUser != null)
                    inFromUser.close();
                if (inFromServer != null)
                    inFromServer.close();
                if (outToServer != null)
                    outToServer.close();
                if (clientSocket != null)
                    clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}