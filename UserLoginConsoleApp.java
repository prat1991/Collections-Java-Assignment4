
/** Main Method (View Class) is the entry point for running the User Login Software Application */

public class UserLoginConsoleApp
{
	public static void main(String[] args)
	{
		System.out.println("----------STARTING THE USER LOGIN CONSOLE APP----------\n");
		UserService userService = new UserService();
		userService.readAndStoreUserData();
		userService.validateUserData();
		System.out.println("\n----------ENDING THE USER LOGIN CONSOLE APP----------");
	}
}
