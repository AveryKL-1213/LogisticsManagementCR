package com.logisticsmanagementcr.android.dao

import androidx.room.*

//运单表操作
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