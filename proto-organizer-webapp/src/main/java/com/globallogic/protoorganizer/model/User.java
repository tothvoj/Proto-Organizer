/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globallogic.protoorganizer.model;

/**
 *
 * @author magyapa
 */
public class User
{
    private String firstName;
    private String lastName;
    private String email;       
    private String barcode;
    private boolean rights;
    private long id;
    private String password;
    
    /**
     * Construct.
     * 
     * @param firstName
     *            firstName
     * @param lastName
     *            lastName
     * @param email
     *            email
     * @param barcode
     *            barcode
     * @param rights
     *            rights
     */
    
    public User(){
    	
    }
    
    
    public User(long id, String firstName, String lastName, String email, String barcode, boolean rights, String password)
    {
    	this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rights = rights;
        this.barcode = barcode;
        this.email = email;
        this.password = password;       
    }

    /**
     * Gets the first name.
     * 
     * @return firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Sets the first name.
     * 
     * @param firstName
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    /**
     * Gets the last name.
     * 
     * @return name
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Sets the last name.
     * 
     * @param lastName
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * Gets the email.
     * 
     * @return email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Sets the email.
     * 
     * @param email
     *            email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    /**
     * Gets the barcode.
     * 
     * @return barcode
     */
    public String getBarcode()
    {
        return barcode;
    }

    /**
     * Sets the barcode.
     * 
     * @param barcode
     */
    public void setBarcode(String barcode)
    {
        this.barcode = barcode;
    }
    
    /**
     * Gets the rights.
     * 
     * @return rights
     */
    public boolean getRights()
    {
        return rights;
    }

    /**
     * Sets the rights.
     * 
     * @param rights
     */
    public void setRights(boolean rights)
    {
        this.rights = rights;
    }
       
    public long getId() {
		return id;
	}

	public void printAll(){
        //System.out.println(this.name + this.email + this.barcode + this.rights);
    }
	
	public String GetFullName()
	{
		return this.toString();
	}
	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return lastName + " " + firstName;
	}

}