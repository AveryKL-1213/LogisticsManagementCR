package com.logisticsmanagementcr.android

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.logisticsmanagementcr.android.dao.AppDatabase
import com.logisticsmanagementcr.android.dao.WayBill
import com.logisticsmanagementcr.android.databinding.EnterwaybillBinding
import java.sql.Time
import kotlin.concurrent.thread

class EnterWayBillActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = EnterwaybillBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        // 切换用户按钮，直接销毁当前Activity，返回上一个界面
        binding.returnButton.setOnClickListener {
            finish()
        }
        binding.saveButton.setOnClickListener {
            val arrivalStation = binding.nameText12.text.toString()
            val goodsName = binding.nameText62.text.toString()
            val number = binding.nameText72.text.toString()
            if (arrivalStation != "" && goodsName != "" && number != "") {
                val billNo =
                    (hashCode() + (1..10000000).random() + System.currentTimeMillis()).toString()
                val consignor = binding.nameText22.text.toString()
                val consignorPhone = binding.nameText32.text.toString()
                val consignee = binding.nameText42.text.toString()
                val consigneePhone = binding.nameText52.text.toString()
                val paidFee = binding.nameText82.text.toString()
                val unPaidFee = binding.nameText92.text.toString()
                val wayBill = WayBill(
                    billNo,
                    consignor,
                    consignorPhone,
                    consignee,
                    consigneePhone,
                    arrivalStation,
                    goodsName,
                    number,
                    unPaidFee,
                    paidFee
                )
                val wayBillDao = AppDatabase.getDatabase(this).waybillDao()
                thread {
                    wayBillDao.insertWayBill(wayBill)
                    //val billList = wayBillDao.loadAllBills()
                    //Log.d("Test", "${billList.size} ${billList[0].waybillNo}")
                }
            } else {
                Toast.makeText(this, "红色框中为必填项，请填写完整", Toast.LENGTH_SHORT).show()
            }
        }


    }
}