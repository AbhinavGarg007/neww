package com.example.emp354.linear.Employee;

public class EmployeeModelClass {
    public static final String TABLE_NAME="Employee";
    public static final String COLUMN_ID="Id";
    public static final String COLUMN_NAME="Name";
    public static final String COLUMN_AGE="Age";
    public static final String COLUMN_PROFILE="Profile";
    public static final String COLUMN_ADDRESS="Address";
    public static final String COLUMN_SALARY="Salary";


    private int id;
    private String name;
    private int age;
    private String profile;
    private int salary;
    private String address;


    //Create table

    public static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ( "+ COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_NAME +" TEXT , " + COLUMN_AGE + " INTEGER , " +COLUMN_PROFILE+" TEXT , "+ COLUMN_SALARY +" INTEGER , "+ COLUMN_ADDRESS +" TEXT " +")";

    public EmployeeModelClass()
    {}
    public EmployeeModelClass(int id,String name,int age,String profile,int salary,String address)
    {
        this.id=id;
        this.name=name;
        this.age=age;
        this.profile=profile;
        this.salary=salary;
        this.address=address;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
