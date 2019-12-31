
/**
 * User Class is a POJO (Model Class) that will store the information that will
 * be read from the file. The User POJO should contain three properties: 1.
 * username, 2. password, 3. name
 */

public class User 
{
	private String username;
	private String password;
	private String name;
	private String role;

	public String getRole()
	{
		return this.role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	public String getUsername()
	{
		return this.username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
