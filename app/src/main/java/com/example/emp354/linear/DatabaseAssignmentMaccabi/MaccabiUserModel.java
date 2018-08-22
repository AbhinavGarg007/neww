package com.example.emp354.linear.DatabaseAssignmentMaccabi;

public class MaccabiUserModel {

    public static final String TABLE_NAME="MaccabiUser";
    public static final String COLUMN_ID="ID";
    public static final String COLUMN_MAIL_ID="MailID";
    public static final String COLUMN_FIRST_NAME="FirstName";
    public static final String COLUMN_LAST_NAME="LastName";
    public static final String COLUMN_PHONE_NO="PhoneNo";
    public static final String COLUMN_PASSWORD="Password";


    private int id;
    private String emailId;
    private String firstName;
    private String lastName;
    private int phoneNo;
    private String password;

    //create table
    public static final String CREATE_TABLE="CREATE TABLE "+ TABLE_NAME + " ( " +COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "+ COLUMN_MAIL_ID + " TEXT , " + COLUMN_FIRST_NAME + " TEXT , " + COLUMN_LAST_NAME + " TEXT , " +
            COLUMN_PHONE_NO + " INTEGER , " + COLUMN_PASSWORD + " TEXT  " + ")";

    public MaccabiUserModel()
    {

    }

    public MaccabiUserModel(int id,String emailId,String firstName,String lastName,int phoneNo,String password)
    {
        this.id=id;
        this.emailId=emailId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.phoneNo=phoneNo;
        this.password=password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
