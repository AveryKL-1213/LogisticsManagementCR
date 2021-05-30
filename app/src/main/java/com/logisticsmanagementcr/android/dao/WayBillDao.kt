package com.logisticsmanagementcr.android.dao

import androidx.room.*

@Dao
interface WayBillDao {

    @Insert
    fun insertWayBill(wayBill: WayBill): Long

    @Update
    fun updateWayBill(newWayBill: WayBill)

    @Query("select * from WayBill")
    fun loadAllBills(): List<WayBill>

    @Delete
    fun deleteBills(wayBill: WayBill)
}