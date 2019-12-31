import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * UserService (Controller Class) handles all the business logic of creating
 * users, storing username and password info into an array and checking the
 * entered values against the array containing the stored user and password
 * data.
 */

public class UserService
{

	User[] data = new User[20];

	/**
	 * method that creates a user and returns a user object
	 */

	public User createUser(String username, String password, String name, String role)
	{
		User user = new User(); // object creation
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
		user.setRole(role);
		return user;
	}

	/**
	 * method that reads the login data from a text file, stores the login data into
	 * a Users Array and returns that Users Array
	 */
	public User[] readAndStoreUserData()
	{
		BufferedReader br = null;

		try // code that throws exception goes here
		{
			// Specified a relative file path for the data.txt file because the data.txt
			// is present in the root of the Hw3 Folder
			br = new BufferedReader(new FileReader("data.txt")); // object creation

			// Local Variables
			String lines = "";
			int i = 0;

			while ((lines = br.readLine()) != null)
			{
				String[] Attributes = lines.split(", ");
				String username = Attributes[0];
				String password = Attributes[1];
				String name = Attributes[2];
				String role = Attributes[3];

				data[i] = createUser(username, password, name, role);
				// System.out.println("Database contains the following stored userlogin data: "
				// + UserDatabaseStorage);
				i++;
			}
			System.out.println("....Successfully stored the userData");
		} catch (FileNotFoundException e) // code that handles exception goes here
		{
			System.out.println("File Not Found Exception Occured during runtime.");
			e.printStackTrace();
		} catch (IOException e) // code that handles exception goes here
		{
			System.out.println("IO Exception Occured during runtime");
			e.printStackTrace();
		} finally
		{
			try
			{
				System.out.println(".....Successfully closed the file reader \n\n");
				br.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return data;
	}

	/**
	 * method that asks the user for login information and checks the entered into
	 * against a database. THis method does not have a return type
	 */

	public void validateUserData()
	{
		// Local Variables (Method Scope)
		String uName;
		String pWord;
		int tries = 0;
		boolean isMatchDB = false;

		Scanner userInputCollector = new Scanner(System.in); // Object Creation

		// --------------Design Consideration (For Loop Vs While
		// Loop)-----------------------
		// outer for loop kept on giving me a Index out of Bound error
		// so decided to wrap the entire logic inside of a while loop
		// i dont fully understand how this solved the issue but i found it on stack
		// overflow
		// so it must be right!

		// While loop handles updating the view in the User Login Console App
		while (tries < 5)
		{
			// skips the while loop if we find a matching login
			if (isMatchDB == true)
			{
				break;
			}

			// Prompting the user for login
			System.out.print("\nEnter your email to login --------> ");
			uName = userInputCollector.next();
			System.out.print("Enter your password to login ----------> ");
			pWord = userInputCollector.next();

			// Checking to see if the users entered info matches the database
			for (int i = 0; i < data.length; i++)
			{
				if (uName.equalsIgnoreCase(data[i].getUsername()) && pWord.equals(data[i].getPassword()))
				{	//Super Users Login Screen
					if (data[i].getRole().contentEquals("super_user"))
					{
						System.out.println("Welcome super user: " + data[i].getName());
						System.out.println("---------------");
						System.out.println("\n Please choose from the following options:");
						System.out.println("\t(0)Login as another user");
						System.out.println("\t(1)Update username");
						System.out.println("\t(2)Update password");
						System.out.println("\t(3)Update name");
						System.out.println("\t(4)Exit");

						isMatchDB = true;
						break; // gets out of while loop when the user enter info that matches the database
					} 
					else //Normal Users Login Screen
					{
						System.out.println("Welcome normal user: " + data[i].getName());
						System.out.println("---------------");
						System.out.println("\n Please choose from the following options:");
						System.out.println("\t(1)Update username");
						System.out.println("\t(2)Update password");
						System.out.println("\t(3)Update name");
						System.out.println("\t(4)Exit");
					}
				}
			}
			// Prompting the user to login again
			if (isMatchDB == false && tries != 5)
			{
				System.out.println("\nInvalid login, please try again");
				tries++; // updating user attempts
			}

			// Erroring out after 5 failed attempts
			if (tries == 5)
			{
				System.out.println("Too many failed login attempts you are now locked out.");
			}
		}
		// closing the scanner to prevent memory leaks
		userInputCollector.close();
	}
}
