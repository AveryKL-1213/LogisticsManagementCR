package com.logisticsmanagementcr.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.logisticsmanagementcr.android.databinding.ActivityCompanyWayBillXmlactivityBinding
import com.logisticsmanagementcr.android.adapter.WayBillAdapter
import com.logisticsmanagementcr.android.adapter.WayBillDisplay
import com.logisticsmanagementcr.android.network.ContentHandler
import com.logisticsmanagementcr.android.network.HttpUtil
import okhttp3.*
import org.xml.sax.InputSource
import java.io.IOException
import java.io.StringReader
import java.lang.Exception
import javax.xml.parsers.SAXParserFactory


class CompanyWayBillXMLActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCompanyWayBillXmlactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyWayBillXmlactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.backButton.setOnClickListener {
            finish()
        }

        getXMLWithOkHttp()

    }

    private fun getXMLWithOkHttp() {
        HttpUtil.sendOkHttpRequest(
            "http://60.12.122.142:6080/simulated-Waybills-db.xml",
            object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val responseData = response.body?.string()
                    if (responseData != null) {
                        parseXMLWithSAX(responseData)
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                }
            })
    }

    private fun parseXMLWithSAX(xmlData: String) {
        try {
            val factory = SAXParserFactory.newInstance()
            val xmlReader = factory.newSAXParser().xmlReader
            val handler = ContentHandler()
            // 将ContentHandler的实例设置到XMLReader中
            xmlReader.contentHandler = handler
            // 开始执行解析
            xmlReader.parse(InputSource(StringReader(xmlData)))
            showResponse(handler.getResponse())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showResponse(billList: ArrayList<WayBillDisplay>) {
        runOnUiThread {
            val layoutManager = LinearLayoutManager(this)
            binding.recyclerView.layoutManager = layoutManager
            val adapter = WayBillAdapter(billList)
            binding.recyclerView.adapter = adapter
        }
    }
}