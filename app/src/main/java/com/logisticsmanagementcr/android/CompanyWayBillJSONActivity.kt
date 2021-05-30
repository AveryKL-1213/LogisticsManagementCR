package com.logisticsmanagementcr.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.logisticsmanagementcr.android.databinding.ActivityCompanyWayBillJsonactivityBinding

class CompanyWayBillJSONActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCompanyWayBillJsonactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.backButton.setOnClickListener {
            finish()
        }

    }
}