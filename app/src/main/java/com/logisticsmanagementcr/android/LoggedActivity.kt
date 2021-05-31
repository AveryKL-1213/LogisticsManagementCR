package com.logisticsmanagementcr.android

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import com.logisticsmanagementcr.android.databinding.ActivityLoggedBinding

//登陆后的页面
class LoggedActivity : BaseActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoggedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // 取出intent中的传递的数据，并显示
        val user = intent.getStringExtra("username")
        val pwd = intent.getStringExtra("password")
        Toast.makeText(this, "Hello, $user!", Toast.LENGTH_SHORT).show()
        binding.nameView.text = "我是$user"
        binding.passwordView.text = "密码$pwd"

        // 切换用户按钮，直接销毁当前Activity，返回上一个界面
        binding.switchUserButton.setOnClickListener {
            finish()
        }

        // 退出按钮，直接退出程序，并杀掉进程
        binding.logoutButton.setOnClickListener {
            ActivityBox.finishAll()
            android.os.Process.killProcess(android.os.Process.myPid())
        }
        // 录入运单
        binding.enterWaybillButton.setOnClickListener {
            myStartActivity<EnterWayBillActivity>(this) {
            }
        }
        // 查询本机运单
        binding.queryNativeWaybillButton.setOnClickListener {
            myStartActivity<NativeWayBillActivity>(this) {
            }
        }
        //查询公司运单xml
        binding.queryCompanyWaybillXMLButton.setOnClickListener {
            myStartActivity<CompanyWayBillXMLActivity>(this) {
            }
        }
        //查询公司运单JSON
        binding.queryCompanyWaybillJSonButton.setOnClickListener {
            myStartActivity<CompanyWayBillJSONActivity>(this) {
            }
        }

    }
}