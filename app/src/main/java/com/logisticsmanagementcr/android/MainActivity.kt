package com.logisticsmanagementcr.android

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.logisticsmanagementcr.android.dao.AppDatabase
import com.logisticsmanagementcr.android.dao.User
import com.logisticsmanagementcr.android.databinding.ActivityMainBinding
import kotlin.concurrent.thread

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
            var tmpPwd: String = ""
            lateinit var truePwd: List<User>
            thread {
                if (userDao.loadAllUsers().isEmpty()) {
                    user1.id = userDao.insertUser(user1)
                    user2.id = userDao.insertUser(user2)
                    Log.d("Test", "Add User")
                }
                if (user != "")
                    truePwd = userDao.loadUsersByLogin(user.toInt())
                else
                    truePwd = userDao.loadUsersByLogin(0)
                if (truePwd.isNotEmpty()) {
                    tmpPwd = truePwd[0].user_password
                    startLoggedActivity(tmpPwd, pwd, truePwd[0].user_name)
                } else {
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
        runOnUiThread {
            if (tmpPwd == pwd) { // 用户名密码对应正确，跳转到下一界面，传递用户名及密码数据
                myStartActivity<LoggedActivity>(this) {
                    putExtra("username", user)
                    putExtra("password", pwd.toString())
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


