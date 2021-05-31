package com.logisticsmanagementcr.android.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//数据库使用Room，现为第三个版本，包含用户表和运单表两个表
@Database(version = 3, entities = [User::class, WayBill::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun waybillDao(): WayBillDao

    companion object {

//        private val MIGRATION_2_3 = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL(
//                    "create table WayBill (id integer primary key autoincrement not null, " +
//                            "consignor text not null" +
//                            "consignorPhoneNumber text not null" +
//                            "consignee text not null" +
//                            "consigneePhoneNumber text not null" +
//                            "transportationArrivalStation text not null" +
//                            "goodsName text not null" +
//                            "numberOfPackages text not null" +
//                            "freightPaidByTheReceivingParty text not null" +
//                            "freightPaidByConsignor text not null)"
//                )
//            }
//        }

        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            )
                .build().apply {
                    instance = this
                }
        }
    }

}