// 62050238 Siriya Saenkhom-or

import java.io.*;
import java.net.*;
import java.util.*;

public class EchoThread extends Thread {
   private Socket connectionSocket;

   public EchoThread(Socket connectionSocket) {
      this.connectionSocket = connectionSocket;
   }

   public void run() {
      Scanner inFromClient = null;
      DataOutputStream outToClient = null;
      try {
         inFromClient = new Scanner(connectionSocket.getInputStream());
         outToClient = new DataOutputStream(connectionSocket.getOutputStream());

         String num1 = "";
         String num2 = "";
         // receive num1
         if (inFromClient.hasNextLine()) { // for catch err No Line Found
            num1 = inFromClient.nextLine();
            if (num1.equals("")) {
               return;
            } else {
               outToClient.writeBytes("enter number 2 (to end just press enter): \n");
            }
         }

         // receive num2
         if (inFromClient.hasNextLine()) { // for catch err No Line Found
            num2 = inFromClient.nextLine();
            if (num2.equals("")) {
               return;
            } else {
               int result = Integer.parseInt(num1) + Integer.parseInt(num2);
               outToClient.writeBytes(String.valueOf(result) + "\n");
            }
         }
      } catch (IOException e) {
         System.err.println("Closing Socket connection");
      } finally {
         try {
            if (inFromClient != null)
               inFromClient.close();
            if (outToClient != null)
               outToClient.close();
            if (connectionSocket != null)
               connectionSocket.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
}