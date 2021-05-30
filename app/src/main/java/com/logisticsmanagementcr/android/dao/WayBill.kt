package com.logisticsmanagementcr.android.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WayBill(
    var waybillNo: String, //运单号
    var consignor: String, //发货人
    var consignorPhoneNumber: String, //发货电话
    var consignee: String, //收货人
    var consigneePhoneNumber: String,//收货电话
    var transportationArrivalStation: String, //到站
    var goodsName: String, //货物名称
    var numberOfPackages: Int, //件数
    var freightPaidByTheReceivingParty: Int, //到付运费
    var freightPaidByConsignor: Int //已付运费
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}