package com.param.user;

public class User 
{
	private int id;
	private String username;
	private String password;
	private String contact;
	private String email;
	private String address;
	
	public User(String username,String password,String contact, String email, String address) 
	{
		super();
		this.username = username;
		this.password=password;
		this.contact=contact;
		this.email = email;
		this.address=address;
	}

	public User(int id,String username,String password,String contact, String email, String address) 
	{
		super();
		this.id=id;
		this.username = username;
		this.password=password;
		this.contact=contact;
		this.email = email;
		this.address=address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPass(String password) 
	{
		this.password = password;
	}
	public String getContact() 
	{
		return contact;
	}
	public void setContact(String contact) 
	{
		this.contact = contact;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getAddress() 
	{
		return address;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}
	/*public void show()
	{
		System.out.println("SHOW");
	}*/
	
	
}
