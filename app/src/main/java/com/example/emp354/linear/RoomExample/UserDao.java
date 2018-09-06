package com.example.emp354.linear.RoomExample;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.emp354.linear.DatabaseAssignmentMaccabi.MaccabiUserModel;
import com.example.emp354.linear.Model.User;

import java.util.List;

@Dao
public interface UserDao {


    @Query("SELECT * FROM user")
    List<RoomUserModel> getAll();

    @Query("SELECT * FROM user where first_name LIKE  :firstName AND last_name LIKE  :lastName")
    RoomUserModel findByName(String firstName,String lastName);

    @Query("SELECT COUNT(*) from user")
    int countUsers();

    @Query("UPDATE user SET last_name= :lastName,salary= :salary,age= :age,phone_no= :phoneNo WHERE first_name= :firstName")
    void updateUser(String lastName,String salary,String age,String phoneNo,String firstName);

    @Insert
    void insertAll(RoomUserModel... users);

    @Delete
    void delete(RoomUserModel roomUserModel);

    @Query("DELETE FROM user" )
    void deleteAll();

}
