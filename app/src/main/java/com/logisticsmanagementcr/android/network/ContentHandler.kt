package com.logisticsmanagementcr.android.network

import android.util.Log
import com.logisticsmanagementcr.android.adapter.WayBillDisplay
import org.xml.sax.helpers.DefaultHandler
import org.xml.sax.Attributes

class ContentHandler : DefaultHandler() {

    private var nodeName = ""
    private lateinit var waybillNo: StringBuilder //运单号
    private lateinit var consignor: StringBuilder //发货人
    private lateinit var consignorPhoneNumber: StringBuilder //发货电话
    private lateinit var consignee: StringBuilder //收货人
    private lateinit var consigneePhoneNumber: StringBuilder//收货电话
    private lateinit var transportationDepartureStation: StringBuilder//发站
    private lateinit var transportationArrivalStation: StringBuilder //到站
    private lateinit var goodsDistributionAddress: StringBuilder//配送地址
    private lateinit var goodsName: StringBuilder //货物名称
    private lateinit var numberOfPackages: StringBuilder //件数
    private lateinit var freightPaidByTheReceivingParty: StringBuilder //到付运费
    private lateinit var freightPaidByConsignor: StringBuilder //寄付运费

    private val billList = ArrayList<WayBillDisplay>()

    override fun startDocument() {
        waybillNo = StringBuilder()
        consignor = StringBuilder()
        consignorPhoneNumber = StringBuilder()
        consignee = StringBuilder()
        consigneePhoneNumber = StringBuilder()
        transportationDepartureStation = StringBuilder()
        transportationArrivalStation = StringBuilder()
        goodsDistributionAddress = StringBuilder()
        goodsName = StringBuilder()
        numberOfPackages = StringBuilder()
        freightPaidByTheReceivingParty = StringBuilder()
        freightPaidByConsignor = StringBuilder()
    }

    override fun startElement(
        uri: String,
        localName: String,
        qName: String,
        attributes: Attributes
    ) {
        // 记录当前节点名
        nodeName = localName
        Log.d("ContentHandlerParseLog", "uri is $uri")
        Log.d("ContentHandlerParseLog", "localName is $localName")
        Log.d("ContentHandlerParseLog", "qName is $qName")
        Log.d("ContentHandlerParseLog", "attributes is $attributes")
    }

    override fun characters(ch: CharArray, start: Int, length: Int) {
        // 根据当前的节点名判断将内容添加到哪一个StringBuilder对象中
        when (nodeName) {
            "waybillNo" -> waybillNo.append(ch, start, length)
            "consignor" -> consignor.append(ch, start, length)
            "consignorPhoneNumber" -> consignorPhoneNumber.append(ch, start, length)
            "consignee" -> consignee.append(ch, start, length)
            "consigneePhoneNumber" -> consigneePhoneNumber.append(ch, start, length)
            "transportationDepartureStation" -> transportationDepartureStation.append(
                ch,
                start,
                length
            )
            "transportationArrivalStation" -> transportationArrivalStation.append(ch, start, length)
            "goodsDistributionAddress" -> goodsDistributionAddress.append(ch, start, length)
            "goodsName" -> goodsName.append(ch, start, length)
            "numberOfPackages" -> numberOfPackages.append(ch, start, length)
            "freightPaidByTheReceivingParty" -> freightPaidByTheReceivingParty.append(
                ch,
                start,
                length
            )
            "freightPaidByConsignor" -> freightPaidByConsignor.append(ch, start, length)
        }
    }

    override fun endElement(uri: String, localName: String, qName: String) {
        if ("waybillRecord" == localName) {
//            response += "id: ${id.toString().trim()}, name: ${
//                name.toString().trim()
//            }, version: ${version.toString().trim()}\n"
//
            val billNo = "No: ${waybillNo.toString().trim()}"
            val billTrace =
                "${
                    transportationArrivalStation.toString().trim()
                } - ${transportationDepartureStation.toString().trim()}  ${
                    goodsName.toString().trim()
                } ${
                    numberOfPackages.toString().trim()
                }件  到付${freightPaidByTheReceivingParty.toString().trim()}元"
            val billName =
                "收货人：${consignee.toString().trim()}(${consigneePhoneNumber.toString().trim()})"
            billList.add(WayBillDisplay(billNo, billTrace, billName))
            // 最后要将StringBuilder清空掉
            waybillNo.setLength(0)
            consignor.setLength(0)
            consignorPhoneNumber.setLength(0)
            consignee.setLength(0)
            consigneePhoneNumber.setLength(0)
            transportationDepartureStation.setLength(0)
            transportationArrivalStation.setLength(0)
            goodsDistributionAddress.setLength(0)
            goodsName.setLength(0)
            numberOfPackages.setLength(0)
            freightPaidByTheReceivingParty.setLength(0)
            freightPaidByConsignor.setLength(0)

        }
    }

    override fun endDocument() {
    }

    fun getResponse() = billList

}