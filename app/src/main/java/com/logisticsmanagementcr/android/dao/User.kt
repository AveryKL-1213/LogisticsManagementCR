package com.logisticsmanagementcr.android.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

//用户表实体
@Entity
data class User(
    var user_name: String, //姓名
    var user_department: String, //班级
    var user_login: String, //登陆id（学号）
    var user_password: String, //密码
    var user_tel: String //电话号码
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}