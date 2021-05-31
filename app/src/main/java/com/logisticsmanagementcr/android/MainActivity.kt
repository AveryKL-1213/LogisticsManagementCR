package com.logisticsmanagementcr.android

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.logisticsmanagementcr.android.dao.AppDatabase
import com.logisticsmanagementcr.android.dao.User
import com.logisticsmanagementcr.android.databinding.ActivityMainBinding
import kotlin.concurrent.thread

//登陆界面，为主Activity
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // 退出按钮，退出程序杀掉进程
        binding.logoutButton.setOnClickListener {
            finish()
            android.os.Process.killProcess(android.os.Process.myPid())
        }
        // 登陆按钮
        binding.loginButton.setOnClickListener {
            val userDao = AppDatabase.getDatabase(this).userDao()
            val user = binding.nameText.text.toString()
            val pwd = binding.passwordText.text.toString()
            val user1 = User("程睿", "计算机1803", "20184409", "991207", "15366251992")
            val user2 = User("王有为", "计算机1803", "20184544", "123456", "15541951980")
            var tmpPwd: String
            lateinit var truePwd: List<User>
            thread {
                if (userDao.loadAllUsers().isEmpty()) { // 如果数据库存在，就不用再增加数据，否则增加，主要便于在新设备上进行调试
                    user1.id = userDao.insertUser(user1)
                    user2.id = userDao.insertUser(user2)
                    Log.d("Test", "Add User")
                }
                // 获取与user对应的密码
                truePwd = userDao.loadUsersByLogin(user)
                if (truePwd.isNotEmpty()) {// 不为空，user存在进行匹配
                    tmpPwd = truePwd[0].user_password
                    startLoggedActivity(tmpPwd, pwd, truePwd[0].user_name)
                } else {//为空，对startLoggedActivity直接给出不匹配的数据表示登陆失败
                    startLoggedActivity("0", "1", "")
                }
            }

        }
    }

    // 切换用户回到登陆界面后，清除密码
    override fun onRestart() {
        super.onRestart()
        binding.passwordText.setText("")
    }

    private fun startLoggedActivity(tmpPwd: String, pwd: String, user: String) {
        runOnUiThread { //在主线程中对UI进行操作
            if (tmpPwd == pwd) { // 用户名密码对应正确，跳转到下一界面，传递用户名及密码数据
                myStartActivity<LoggedActivity>(this) {
                    putExtra("username", user)
                    putExtra("password", pwd)
                }
            } else { // 不对应，使用Toast给出提示
                Toast.makeText(
                    this,
                    "Wrong Password or Username!\nPlease Try Again.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}


