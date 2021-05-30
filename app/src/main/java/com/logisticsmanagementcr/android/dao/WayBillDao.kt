package com.logisticsmanagementcr.android.dao

import androidx.room.*

@Dao
interface WayBillDao {

    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(newUser: User)

    @Query("select * from User")
    fun loadAllBills(): List<User>

    @Delete
    fun deleteBills(user: User)
}