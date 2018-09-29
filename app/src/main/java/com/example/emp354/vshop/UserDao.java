package com.example.emp354.vshop;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM vshopUser")
    List<VshopUserModel> getAll();

    @Insert
    long insert(VshopUserModel users);

    @Query("SELECT * FROM vshopUser WHERE email LIKE  :email AND password LIKE  :password")
    VshopUserModel isAccountExist(String email,String password);

    @Query("SELECT * FROM vshopUser WHERE email LIKE  :email")
    VshopUserModel isMailExist(String email);
}
