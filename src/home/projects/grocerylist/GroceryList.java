package home.projects.grocerylist;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GroceryList {

    /**
     * If the fileNameWithpath does not exist it will create it else it will append to the file
     * @param fileNameWithPath - the file name with full path such as /Users/Kanchan/Documents/GroceryList.txt
     * @throws IOException - IOException when creating the file
     */
    public void createGroceryList(String fileNameWithPath) throws IOException {
        Scanner userinput = new Scanner (System.in);
        String yesornoanswer;
        String itemName;
        int quantityNum = 0;


        FileWriter thefile = new FileWriter(fileNameWithPath, true);
        PrintWriter writingtofile = new PrintWriter(thefile);
        System.out.println("Hi there! Would you like to add an item to do your do list?");
        String answer = userinput.next();

        if (answer.equalsIgnoreCase("yes")) {
            do {
                System.out.println("Enter the item that you would like to add.");
                System.out.println("Enter in this format: eggs 2, which means you want 2 eggs.");
                itemName = userinput.next();
                quantityNum = userinput.nextInt();
                writingtofile.print(itemName + " " + quantityNum);
                writingtofile.println();
                System.out.println("Do you have more items to add? Enter yes or no.");
                yesornoanswer = userinput.next();
            }
            while (yesornoanswer.equalsIgnoreCase("Yes"));
            thefile.close(); //whatever you're writing to, that option needs to be complete
            writingtofile.close();
        }

    }

    /**
     * This method generates the html content for the body of the email
     * @param fileNameWithPath - the file name with full path such as /Users/Kanchan/Documents/GroceryList.txt
     * @return the message body of the email
     */
    public static String generateHtmlContentForEmailBody(String fileNameWithPath) throws IOException {
        String messageBody = "";
        Scanner sc = new Scanner(new File(fileNameWithPath));
        //html content in the body of the email
        String htmlContent = "<table>"
                + "<tr>" //row
                + "<th style='border: 1px solid #dddddd; text-align: left; padding: 8px; width: 20%'>ItemName</th>"
                + "<th style='border: 1px solid #dddddd; text-align: left; padding: 8px; width: 75%'>Quantity</th>"
                + "</tr>";

        //while the file has content read it line by line and split the text by space
        while (sc.hasNext()) {
            String lineContent = sc.nextLine();
            String[] thesplitarray = lineContent.split(" ");
            htmlContent = htmlContent + "<tr>"
                    + "<td style='border: 1px solid #dddddd; text-align: left; padding: 8px;' th:text=>" + thesplitarray[0] + "</td>"
                    //the value of the column will contain the first element of the array
                    + "<td style='border: 1px solid #dddddd; text-align: left; padding: 8px;' th:text=>" + thesplitarray[1] + "</td>"
                    + "</tr>";
            messageBody = "<html><body><title>Grocery List</title><p style=\"font-family: Arial, sans-serif\">Hi, <br> Below are your grocery items:</p>"
                    + htmlContent + "</table></body></html>";

        }
        sc.close();

        return messageBody;
    }


    public static void main(String [] args) throws IOException {

        String fileNameWithPath = args[0];
        GroceryList toDoList = new GroceryList();
        toDoList.createGroceryList(fileNameWithPath);
        EmailClient emailobject = new EmailClient();
        String messageBody = generateHtmlContentForEmailBody(fileNameWithPath);
        emailobject.sendEmail(messageBody, "Grocery List") ;

        }
}



