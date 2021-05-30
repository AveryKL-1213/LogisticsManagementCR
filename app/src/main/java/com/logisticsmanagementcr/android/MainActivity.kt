package com.logisticsmanagementcr.android

import android.os.Bundle
import android.widget.Toast
import com.logisticsmanagementcr.android.databinding.ActivityMainBinding

val users = HashMap<String, String>()

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // 初始化用户map
        initUsers()
        // 退出按钮，退出程序杀掉进程
        binding.logoutButton.setOnClickListener {
            finish()
            android.os.Process.killProcess(android.os.Process.myPid())
        }
        // 登陆按钮
        binding.loginButton.setOnClickListener {
            val user = binding.nameText.text.toString()
            val pwd = binding.passwordText.text.toString()
            if (users[user] == pwd) { // 用户名密码对应正确，跳转到下一界面，传递用户名及密码数据
                myStartActivity<LogedActivity>(this) {
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

    // 切换用户回到登陆界面后，清除密码
    override fun onRestart() {
        super.onRestart()
        binding.passwordText.setText("")
    }
}

// 用户信息map初始化
fun initUsers() {
    users["程睿"] = "20184409"
    users["AveryKL"] = "991207"
    users["KiKi"] = "921030"
    users["管理员"] = "991213cr"
    users["用户1"] = "123456"
    users["职工9527"] = "password"
}

