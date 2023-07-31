# Electricity Billing System

The `Electricity Billing System` is a feature-rich Java project that revolutionizes the electricity department's operations by digitizing and streamlining the entire process, eliminating errors and enhancing efficiency.

## Tools Used

* Core Java (JDK 17)
* Java Swing and AWT for GUI
* MySQL Workbench for Database
* VS Code, Apache Netbeans as IDE

## Running the Project

* Download and install the latest version of JDK
* Also download an IDE of your choice. VS Code or NetBeans is preferable.
* Clone this repository on your system
* Download MySQL workbench and install it.
    > During installation, just remember the username and password. 
    Start the MySQL workbench and open a new tab for typing your SQL Queries. 
    Also, open the project in your IDE and copy the contents of `SQL Queries.sql` file and paste in the tab in your MySQL workbench. Start executing the queries by selecting one line at a time and then clicking the execute icon.
* Now, add the username and password of your database in the `src\electricity\billing\system\Conn.java` file by editing the `USERNAME` and `PASSWORD` fields. If are running the server on a different port, then you will also need to edit the connetion string and pass your PORT number. By default, it is `3306`. 
* Also, make sure that the jar files in the libs folder of the project are added in the classpath. In VS Code, you can do this by editing the `settings.json` file.
* You are all set. Click on `Run and Debug` menu on the left menu panel and then the `Run and Debug` button to run the project.

## Project Users
There are two main users - admin and customer. You can create either of them. 
First create an admin by executing a SQL query. Later, you can disallow this feature by editing the `Login.java`.
The other user is a customer. For a customer to login, the admin must have created a customer first and assign a `meter no` for identification. Then, the customer goes through a signup process. Once signuped, the customer can login using personal credentials.

## Project Features
* Admin can create new customers, view existing customers, see paid and unpaid bills, calculate bills for customers as well as print all these information

* Customer (after being created by admin) can signup with the department to generate login credentials.
Later, the customer can login and view its information, edit or update the information, view pending bills, pay pending bills and generate bills for paid and unpaid bills.

### Future Improvements
* Add a feature that tells whether a customer is receiving the electricity or not.
* If the customer has not paid bills from past n months, then allow admin to cutoff electricity supply for that customer
* Allow customer to send a request to the admin to deregister from the department.

Note - The above features are a lot more complex. As such, I will probably not add them any time soon. I am open for suggestions though.

# Steps to connect to database
> The following things are already implemented in the project. I have written this jsut for reference as to explain newbies about how the JDBC works. 

In Java, in order to connect to a database, we can use JDBC(Java Database Connectivity). The steps are as follows - 
1. ### Create a separate file called `Conn.java`. 
    Everytime you want to access/connect to the database, you will create an instance of this class.
2. ### Register the driver
    Download the jar files and put them in the libs folder of your project
    On the left hand side menu(VS Code), 
        click on JAVA PROJECTS
        click on Electricity Billing System
        click on Referenced Libraries
        select the jar file you downloaded
    This will add the jar files on your project's classpath. Alternatively, you can add the paths of the jar files in `settings.json` file present in `.vscode` directory.

3. ### Creating the connection
    Inside the `Conn.java` file, paste the following code - 
    ```
    import java.sql.*;

    public class Conn {
        Connection c;
        Statement s;
        private static final String USERNAME = "root";
        private static final String PASSWORD = "Sha2nk";

        Conn() {
            try {
                c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", USERNAME, PASSWORD);
                s = c.createStatement();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    ```

    Replace the `USERNAME` and `PASSWORD` with the one of yours.
    In the `getConnection()` method of `DriverManager` class, you need to pass the URL of your database, username password.
    If are using some other database, then you will need to download a different jar file and also edit the above connection string. 
    
    Now, in order to execute any query, create an instance of `Conn` class.
    Create your query of type `String` and pass it to the `executeQuery()`(if you are fetching data from DB) method or `executeUpdate()` (if you are updating the DB) of `s` (the `Statement` member in your class). The above steps are risky, so wrap all your code inside try-catch block. Here is an example from the `Login.java` file of fetching data from DB - 
    ```
    try {
        Conn c = new Conn();
        String query = "SELECT * FROM login WHERE username='" + susername + "' AND password='" + spassword
                + "' AND usertype='" + sloginas + "'";

        ResultSet rs = c.s.executeQuery(query);

        if (rs.next()) {
            String meter = rs.getString("meter_no");
            setVisible(false);
            new Home(sloginas, meter);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Login");
            username.setText("");
            password.setText("");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    ```
