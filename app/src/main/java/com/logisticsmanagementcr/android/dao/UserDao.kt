package com.logisticsmanagementcr.android.dao

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(newUser: User)

    @Query("select * from User")
    fun loadAllUsers(): List<User>

    @Query("select * from User where user_login = :user_login")
    fun loadUsersByLogin(user_login: Int): List<User>

    @Delete
    fun deleteUser(user: User)
}