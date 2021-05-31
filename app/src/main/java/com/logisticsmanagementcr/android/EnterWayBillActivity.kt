package com.logisticsmanagementcr.android

import android.os.Bundle
import android.widget.Toast
import com.logisticsmanagementcr.android.dao.AppDatabase
import com.logisticsmanagementcr.android.dao.WayBill
import com.logisticsmanagementcr.android.databinding.EnterwaybillBinding
import kotlin.concurrent.thread

//输入运单Activity
class EnterWayBillActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = EnterwaybillBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        // 返回按钮
        binding.returnButton.setOnClickListener {
            finish()
        }
        // 保存按钮
        binding.saveButton.setOnClickListener {
            //获取editText中的信息
            val arrivalStation = binding.nameText12.text.toString()
            val goodsName = binding.nameText62.text.toString()
            val number = binding.nameText72.text.toString()
            //对必填项进行判断
            if (arrivalStation != "" && goodsName != "" && number != "") {
                //自动生成运单号，为对象hashcode+随机数+当前时间
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
                thread { //添加数据到数据库中
                    wayBillDao.insertWayBill(wayBill)
                    //val billList = wayBillDao.loadAllBills()
                    //Log.d("Test", "${billList.size} ${billList[0].waybillNo}")
                }
                //运单录入成功，进行提示，并清空editText中的信息
                Toast.makeText(this, "运单录入成功", Toast.LENGTH_SHORT).show()
                binding.nameText12.setText("")
                binding.nameText22.setText("")
                binding.nameText32.setText("")
                binding.nameText42.setText("")
                binding.nameText52.setText("")
                binding.nameText62.setText("")
                binding.nameText72.setText("")
                binding.nameText82.setText("")
                binding.nameText92.setText("")

            } else {
                //提示未填写完整
                Toast.makeText(this, "红色框中为必填项，请填写完整", Toast.LENGTH_SHORT).show()
            }
        }


    }
}