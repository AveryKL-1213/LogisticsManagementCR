package com.logisticsmanagementcr.android.network

class JsonWayBill(
    var waybillNo: String, //运单号
    var consignor: String, //发货人
    var consignorPhoneNumber: String, //发货电话
    var consignee: String, //收货人
    var consigneePhoneNumber: String,//收货电话
    var transportationDepartureStation: String,//发站
    var transportationArrivalStation: String, //到站
    var goodsDistributionAddress: String,//配送地址
    var goodsName: String, //货物名称
    var numberOfPackages: String, //件数
    var freightPaidByTheReceivingParty: String, //到付运费
    var freightPaidByConsignor: String //寄付运费
)