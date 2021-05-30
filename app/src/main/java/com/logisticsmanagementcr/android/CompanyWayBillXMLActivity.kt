package com.logisticsmanagementcr.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.logisticsmanagementcr.android.databinding.ActivityCompanyWayBillXmlactivityBinding

class CompanyWayBillXMLActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCompanyWayBillXmlactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.backButton.setOnClickListener {
            finish()
        }

    }
}