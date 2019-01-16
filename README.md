# GroceryList
This program allows you to specify the grocery list items along with its quantity and receive an email using the gmail server with those items.

Getting Started:
You can run the program from any IDE or the command line.
Run the program from command line as:
java GroceryList fileNameWithPath

Replace fileNameWithPath with a file name using the full path and using a path which has write permissions as it attempts to create a file.
Note: fileNameWithPath will be specific to your OS.

Example: java GroceryList /Users/kanchan/Documents/GroceryList.txt.
The above will create the GroceryList.txt file and append the grocery item along with the quantity.

Prerequisites
1. Java SDK 1.8
2. Latest javax.mail.jar
You will need to download the latest version of javax.mail jar file and add it to your classpath or as a dependent/external library in your IDE.
3. You will need to replace the values of email.username, email.password, and from.email in emailconfig.properties file with values associated with a gmail account that will be used to send the mail.
That gmail account needs to enable the "Allow less secure apps" setting. You need to specify the recipients email in recipient.email property.

email.username=********
email.password=*********
recipient.email=***********
from.email=***********

Author
Kanchan Krishna
