package com.logisticsmanagementcr.android.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    var user_name: String,
    var user_department: String,
    var user_login: String,
    var user_password: String,
    var user_tel: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}