package com.logisticsmanagementcr.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.logisticsmanagementcr.android.databinding.ActivityCompanyWayBillJsonactivityBinding
import com.logisticsmanagementcr.android.network.JsonBillRecord
import com.logisticsmanagementcr.android.network.JsonBillService
import com.logisticsmanagementcr.android.network.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompanyWayBillJSONActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCompanyWayBillJsonactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyWayBillJsonactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.backButton.setOnClickListener {
            finish()
        }
        var responseData = ""
        val jsonBillService = ServiceCreator.create<JsonBillService>()
        jsonBillService.getAppData().enqueue(object : Callback<JsonBillRecord> {
            override fun onResponse(
                call: Call<JsonBillRecord>,
                response: Response<JsonBillRecord>
            ) {
                val jsonBillList = response.body()
                if (jsonBillList != null) {
                    for (bill in jsonBillList.waybillRecord) {
                        responseData += "${bill.waybillNo} ${bill.consignee}\n"
                    }
                    showResponse(responseData)
                }
            }

            override fun onFailure(call: Call<JsonBillRecord>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }

    private fun showResponse(response: String) {
        runOnUiThread {
            binding.responseText.text = response
        }
    }
}