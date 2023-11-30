package com.example.dataviewer.data.network.api

import android.util.Log
import com.squareup.moshi.Moshi
import java.net.HttpURLConnection
import java.net.URL

class ApiNetwork(
    private val apiUrl: String,
    private val moshi: Moshi
): Api {
    override fun getData(){
        val url = URL(apiUrl)
        val con = url.openConnection() as HttpURLConnection
        val data = con.inputStream.bufferedReader().use { it.readText() }

        Log.d("MyLog", data)

//        val jsonAdapter = moshi.adapter(ShipmentsResponse::class.java)
//        val response = jsonAdapter.fromJson(json) as ShipmentsResponse
    }
}
/**
 * A group of *members*.
 *
 * This class has no useful logic; it's just a documentation example.
 *
 * @param T the type of a member in this group.
 * @property name the name of this group.
 * @constructor Creates an empty group.
 */

//val testTering: String =

//<offer id="942850" available="true">
// <url>https://www.citilink.ru/product/kartridzh-cactus-cs-cn628ae-971xl-zheltyi-942850/</url>
// <price>1790</price><oldprice>1980</oldprice>
// <currencyId>RUR</currencyId>
// <categoryId>77</categoryId>
// <market_category>Компьютеры/Аксессуары и расходные материалы/Картриджи, тонеры, фотобарабаны</market_category>
// <picture>https://cdn.citilink.ru/HP0VdKKyXtXAO78GFcFCXItyhvAzwWNV2k4YR4oN-JA/background:255:255:255/extend:1/gravity:sm/height:500/resizing_type:fit/width:500/plain/items/942850_v01_b.jpg?version=1700265600</picture>
// <delivery>true</delivery>
// <name>Картридж Cactus CS-CN628AE, №971XL, желтый / CS-CN628AE</name>
// <vendor>CACTUS</vendor>
// <vendorCode>CS-CN628AE</vendorCode>
// <description>желтый, объем 110 мл, для DJ Pro X476dw/X576dw/X451dw</description><sales_notes>Клубные цены ещё ниже. Присоединяйтесь!</sales_notes>
// <manufacturer_warranty>true</manufacturer_warranty>
// <cpa>false</cpa>
// <delivery-options>
// <option cost="390" days="1-2" order-before="22">
// </option>
// </delivery-options>
// <pickup-options>
// <option cost="0" cost="0" days="34"></option>
// </pickup-options>
// <barcode>4690207259804</barcode>
// <param name="Цвет">желтый</param>
// <param name="Group">Group9</param>
// <url2>https://www.citilink.ru/catalog/computers_and_notebooks/monitors_and_office/cartridges/942850/</url2>
// <model>CS-CN628AE</model>
// <typePrefix>Картриджи</typePrefix>
// </offer>

// поиск тэкга элемента
// признаки </

// признаки <

// if < 