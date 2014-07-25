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
    private String name;
    private String email;       
    private String barcode;
    private boolean rights;
    private long id;
    
    /**
     * Construct.
     * 
     * @param name
     *            name
     * @param email
     *            email
     * @param barcode
     *            barcode
     * @param rights
     *            rights
     */
    
    public User(){
    	
    }
    
    
    public User(long id, String name, String email, String barcode, boolean rights)
    {
    	this.id = id;
        this.name = name;
        this.rights = rights;
        this.barcode = barcode;
        this.email = email;
       
    }

    /**
     * Gets the name.
     * 
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name.
     * 
     * @param name
     *            name
     */
    public void setName(String name)
    {
        this.name = name;
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
     *            barcode
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
     *            rights
     */
    public void setRights(boolean rights)
    {
        this.rights = rights;
    }
    /**
     * Gets the rights.
     * 
     * @return rights
     */
       
       
    public long getId() {
		return id;
	}

	public void printAll(){
        //System.out.println(this.name + this.email + this.barcode + this.rights);
    }
	
	@Override
	public String toString() {
		return name;
	}

}