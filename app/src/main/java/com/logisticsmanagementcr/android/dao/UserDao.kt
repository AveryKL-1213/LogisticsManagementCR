package com.logisticsmanagementcr.android.dao

import androidx.room.*

//用户表操作
@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(newUser: User)

    @Query("select * from User")
    fun loadAllUsers(): List<User>

    @Query("select * from User where user_login = :user_login")
    fun loadUsersByLogin(user_login: String): List<User>

    @Delete
    fun deleteUser(user: User)

    @Query("delete from User where user_name = :user_name")
    fun deleteUserByName(user_name: String): Int
}