package com.example.dataviewer.data.network.api

fun main() {
    //- тэг открывается -> записывается первое значение после скобки "<" (это по сути 1 оффер/элемент)
    //- Идем дальше по тэгам
    //- Далее проверяется - если закрывающий тэг не равно "/>" то вродолжает идти дальше по строке
    // до следующего открывающего тэга "<"
    // -Если первое значение следующего открывающего тэга не равно записонному значению то идем дальше
    // по строке (записываем значение в модель).
    // -Если первое значение следующего открывающего тэга равно /+записанное значение, то тэг закрывается


    val mockFeed = "<offer id=\"982045\" available=\"true\">" +
                        "<url>https://www.citilink.ru/product/kartridzh-cactus-cs-ph7400-106r01080-chernyi-982045/</url>" +
                        "<price>3200</price>" +
                        "<oldprice>3712</oldprice>" +
                        "<currencyId>RUR</currencyId>" +
                        "<categoryId>77</categoryId>" +
                        "<market_category>Компьютеры/Аксессуары и расходные материалы/Картриджи, тонеры, фотобарабаны</market_category>" +
                        "<picture>https://cdn.citilink.ru/84n0N_wvMocuv4JBe42TNi2hJGWh2bCQ7Lg90jvJDj4/background:255:255:255/extend:1/gravity:sm/height:500/resizing_type:fit/width:500/plain/items/982045_v01_b.jpg?version=1700870400</picture>" +
                        "<delivery>true</delivery>" +
                        "<name>Картридж Cactus CS-PH7400, 106R01080, черный / CS-PH7400</name>" +
                        "<vendor>CACTUS</vendor>" +
                        "<vendorCode>CS-PH7400</vendorCode>" +
                        "<description>черный, примерный ресурс(страниц)- 15000, для Phaser 7400</description>" +
                        "<sales_notes>Клубные цены ещё ниже. Присоединяйтесь!</sales_notes>" +
                        "<manufacturer_warranty>true</manufacturer_warranty>" +
                        "<cpa>false</cpa>" +
                        "<delivery-options>" +
                            "<option cost=\"390\" days=\"2-3\" order-before=\"22\"></option>" +
                        "</delivery-options>" +
                        "<pickup-options>" +
                            "<option cost=\"0\" days=\"34\"></option>" +
                        "</pickup-options>" +
                        "<barcode>4690207263313</barcode>" +
                        "<param name=\"Цвет\">черный</param>" +
                        "<param name=\"Group\">Group9</param>" +
                        "<url2>https://www.citilink.ru/catalog/computers_and_notebooks/monitors_and_office/cartridges/982045/</url2>" +
                        "<model>CS-PH7400</model>" +
                        "<typePrefix>Картриджи</typePrefix>" +
                    "</offer>"
//        "<offer id=\"982046\" available=\"true\"><url>https://www.citilink.ru/product/kartridzh-cactus-cs-ph7400c-106r01150-goluboi-982046/</url><price>2350</price><oldprice>2679</oldprice><currencyId>RUR</currencyId><categoryId>77</categoryId><market_category>Компьютеры/Аксессуары и расходные материалы/Картриджи, тонеры, фотобарабаны</market_category><picture>https://cdn.citilink.ru/UvlyhrOp5PNsdO2-Man0mrRPsru0Msj76XQJdFe_0eI/background:255:255:255/extend:1/gravity:sm/height:500/resizing_type:fit/width:500/plain/items/982046_v01_b.jpg?version=1700870400</picture><delivery>true</delivery><name>Картридж Cactus CS-PH7400C, 106R01150, голубой / CS-PH7400C</name><vendor>CACTUS</vendor><vendorCode>CS-PH7400C</vendorCode><description>голубой, примерный ресурс(страниц)- 9000, для Phaser 7400</description><sales_notes>Клубные цены ещё ниже. Присоединяйтесь!</sales_notes><manufacturer_warranty>true</manufacturer_warranty><cpa>false</cpa><delivery-options><option cost=\"390\" days=\"2-3\" order-before=\"22\"></option></delivery-options><pickup-options><option cost=\"0\" days=\"34\"></option></pickup-options><barcode>4690207263306</barcode><param name=\"Цвет\">голубой</param><param name=\"Group\">Group9</param><url2>https://www.citilink.ru/catalog/computers_and_notebooks/monitors_and_office/cartridges/982046/</url2><model>CS-PH7400C</model><typePrefix>Картриджи</typePrefix></offer>" +
//        "<offer id=\"982047\" available=\"true\"><url>https://www.citilink.ru/product/kartridzh-cactus-cs-ph7400m-106r01151-purpurnyi-982047/</url><price>2410</price><oldprice>2675</oldprice><currencyId>RUR</currencyId><categoryId>77</categoryId><market_category>Компьютеры/Аксессуары и расходные материалы/Картриджи, тонеры, фотобарабаны</market_category><picture>https://cdn.citilink.ru/e3awP1V7dZaMK1ltMgh4FfA5t8ScXfXZwNeC6Qd9E2Q/background:255:255:255/extend:1/gravity:sm/height:500/resizing_type:fit/width:500/plain/items/982047_v01_b.jpg?version=1700870400</picture><delivery>true</delivery><name>Картридж Cactus CS-PH7400M, 106R01151, пурпурный / CS-PH7400M</name><vendor>CACTUS</vendor><vendorCode>CS-PH7400M</vendorCode><description>пурпурный, примерный ресурс(страниц)- 9000, для Phaser 7400</description><sales_notes>Клубные цены ещё ниже. Присоединяйтесь!</sales_notes><manufacturer_warranty>true</manufacturer_warranty><cpa>false</cpa><delivery-options><option cost=\"390\" days=\"2-3\" order-before=\"22\"></option></delivery-options><pickup-options><option cost=\"0\" days=\"34\"></option></pickup-options><barcode>4690207263290</barcode><param name=\"Цвет\">пурпурный</param><param name=\"Group\">Group10</param><url2>https://www.citilink.ru/catalog/computers_and_notebooks/monitors_and_office/cartridges/982047/</url2><model>CS-PH7400M</model><typePrefix>Картриджи</typePrefix></offer>" +
//        "<offer id=\"982048\" available=\"true\"><url>https://www.citilink.ru/product/kartridzh-cactus-cs-ph7400y-106r01152-zheltyi-982048/</url><price>3200</price><oldprice>3648</oldprice><currencyId>RUR</currencyId><categoryId>77</categoryId><market_category>Компьютеры/Аксессуары и расходные материалы/Картриджи, тонеры, фотобарабаны</market_category><picture>https://cdn.citilink.ru/j9pgHN4lbySOUDC1jFZ7PToy_nXIfe04jgj_m5K0Tqg/background:255:255:255/extend:1/gravity:sm/height:500/resizing_type:fit/width:500/plain/items/982048_v01_b.jpg?version=1700870400</picture><delivery>true</delivery><name>Картридж Cactus CS-PH7400Y, 106R01152, желтый / CS-PH7400Y</name><vendor>CACTUS</vendor><vendorCode>CS-PH7400Y</vendorCode><description>желтый, примерный ресурс(страниц)- 9000, для Phaser 7400</description><sales_notes>Клубные цены ещё ниже. Присоединяйтесь!</sales_notes><manufacturer_warranty>true</manufacturer_warranty><cpa>false</cpa><delivery-options><option cost=\"390\" days=\"2-3\" order-before=\"22\"></option></delivery-options><pickup-options><option cost=\"0\" days=\"34\"></option></pickup-options><barcode>4690207263283</barcode><param name=\"Цвет\">желтый</param><param name=\"Group\">Group9</param><url2>https://www.citilink.ru/catalog/computers_and_notebooks/monitors_and_office/cartridges/982048/</url2><model>CS-PH7400Y</model><typePrefix>Картриджи</typePrefix></offer>" +
//        "<offer id=\"982049\" available=\"true\"><url>https://www.citilink.ru/product/kartridzh-cactus-cs-ph7500-106r01446-chernyi-982049/</url><price>3300</price><oldprice>3762</oldprice><currencyId>RUR</currencyId><categoryId>77</categoryId><market_category>Компьютеры/Аксессуары и расходные материалы/Картриджи, тонеры, фотобарабаны</market_category><picture>https://cdn.citilink.ru/_qAawlhLP07SCHS9-zryztg0JPLHMaI9uc4Z9HluBaI/background:255:255:255/extend:1/gravity:sm/height:500/resizing_type:fit/width:500/plain/items/982049_v01_b.jpg?version=1700870400</picture><delivery>true</delivery><name>Картридж Cactus CS-PH7500, 106R01446, черный / CS-PH7500</name><vendor>CACTUS</vendor><vendorCode>CS-PH7500</vendorCode><description>черный, примерный ресурс(страниц)- 19800, для Phaser 7500</description><sales_notes>Клубные цены ещё ниже. Присоединяйтесь!</sales_notes><manufacturer_warranty>true</manufacturer_warranty><cpa>false</cpa><delivery-options><option cost=\"390\" days=\"2-3\" order-before=\"22\"></option></delivery-options><pickup-options><option cost=\"0\" days=\"34\"></option></pickup-options><barcode>4690207263276</barcode><param name=\"Цвет\">черный</param><param name=\"Group\">Group9</param><url2>https://www.citilink.ru/catalog/computers_and_notebooks/monitors_and_office/cartridges/982049/</url2><model>CS-PH7500</model><typePrefix>Картриджи</typePrefix></offer>" +
//        "<offer id=\"982050\" available=\"true\"><url>https://www.citilink.ru/product/kartridzh-cactus-cs-ph7500c-106r01443-goluboi-982050/</url><price>3300</price><oldprice>3927</oldprice><currencyId>RUR</currencyId><categoryId>77</categoryId><market_category>Компьютеры/Аксессуары и расходные материалы/Картриджи, тонеры, фотобарабаны</market_category><picture>https://cdn.citilink.ru/_71wQJgzTnr9QxVoG-mKf3wBPAfC5nltziM3JCkvOcY/background:255:255:255/extend:1/gravity:sm/height:500/resizing_type:fit/width:500/plain/items/982050_v01_b.jpg?version=1700870400</picture><delivery>true</delivery><name>Картридж Cactus CS-PH7500C, 106R01443, голубой / CS-PH7500C</name><vendor>CACTUS</vendor><vendorCode>CS-PH7500C</vendorCode><description>голубой, примерный ресурс(страниц)- 17800, для Phaser 7500</description><sales_notes>Клубные цены ещё ниже. Присоединяйтесь!</sales_notes><manufacturer_warranty>true</manufacturer_warranty><cpa>false</cpa><delivery-options><option cost=\"390\" days=\"2-3\" order-before=\"22\"></option></delivery-options><pickup-options><option cost=\"0\" days=\"34\"></option></pickup-options><barcode>4690207263269</barcode><param name=\"Цвет\">голубой</param><param name=\"Group\">Group9</param><url2>https://www.citilink.ru/catalog/computers_and_notebooks/monitors_and_office/cartridges/982050/</url2><model>CS-PH7500C</model><typePrefix>Картриджи</typePrefix></offer>" +
//        "<offer id=\"982051\" available=\"true\"><url>https://www.citilink.ru/product/kartridzh-cactus-cs-ph7500m-106r01444-purpurnyi-982051/</url><price>3300</price><oldprice>3762</oldprice><currencyId>RUR</currencyId><categoryId>77</categoryId><market_category>Компьютеры/Аксессуары и расходные материалы/Картриджи, тонеры, фотобарабаны</market_category><picture>https://cdn.citilink.ru/S3Jj2XQGK1jvr1VUXZewhv89YL2B6XVvJLA033SO0c0/background:255:255:255/extend:1/gravity:sm/height:500/resizing_type:fit/width:500/plain/items/982051_v01_b.jpg?version=1700870400</picture><delivery>true</delivery><name>Картридж Cactus CS-PH7500M, 106R01444, пурпурный / CS-PH7500M</name><vendor>CACTUS</vendor><vendorCode>CS-PH7500M</vendorCode><description>пурпурный, примерный ресурс(страниц)- 17800, для Phaser 7500</description><sales_notes>Клубные цены ещё ниже. Присоединяйтесь!</sales_notes><manufacturer_warranty>true</manufacturer_warranty><cpa>false</cpa><delivery-options><option cost=\"390\" days=\"2-3\" order-before=\"22\"></option></delivery-options><pickup-options><option cost=\"0\" days=\"34\"></option></pickup-options><barcode>4690207263252</barcode><param name=\"Цвет\">пурпурный</param><param name=\"Group\">Group9</param><url2>https://www.citilink.ru/catalog/computers_and_notebooks/monitors_and_office/cartridges/982051/</url2><model>CS-PH7500M</model><typePrefix>Картриджи</typePrefix></offer>" +
//        "<offer id=\"982052\" available=\"true\"><url>https://www.citilink.ru/product/kartridzh-cactus-cs-ph7500y-106r01445-zheltyi-982052/</url><price>3300</price><oldprice>3828</oldprice><currencyId>RUR</currencyId><categoryId>77</categoryId><market_category>Компьютеры/Аксессуары и расходные материалы/Картриджи, тонеры, фотобарабаны</market_category><picture>https://cdn.citilink.ru/MI4tqCzu_BtK2sBB6CmrmVSGCV-d7wTiLQIGjFP2hJE/background:255:255:255/extend:1/gravity:sm/height:500/resizing_type:fit/width:500/plain/items/982052_v01_b.jpg?version=1700870400</picture><delivery>true</delivery><name>Картридж Cactus CS-PH7500Y, 106R01445, желтый / CS-PH7500Y</name><vendor>CACTUS</vendor><vendorCode>CS-PH7500Y</vendorCode><description>желтый, примерный ресурс(страниц)- 17800, для Phaser 7500</description><sales_notes>Клубные цены ещё ниже. Присоединяйтесь!</sales_notes><manufacturer_warranty>true</manufacturer_warranty><cpa>false</cpa><delivery-options><option cost=\"390\" days=\"2-3\" order-before=\"22\"></option></delivery-options><pickup-options><option cost=\"0\" days=\"34\"></option></pickup-options><barcode>4690207263245</barcode><param name=\"Цвет\">желтый</param><param name=\"Group\">Group9</param><url2>https://www.citilink.ru/catalog/computers_and_notebooks/monitors_and_office/cartridges/982052/</url2><model>CS-PH7500Y</model><typePrefix>Картриджи</typePrefix></offer>" +
//        "<offer id=\"982068\" available=\"true\"><url>https://www.citilink.ru/product/kartridzh-struinyi-cactus-cs-lc529xlbk-chernyi-61ml-dlya-brother-dcp-j-982068/</url><price>450</price><oldprice>526</oldprice><currencyId>RUR</currencyId><categoryId>77</categoryId><market_category>Компьютеры/Аксессуары и расходные материалы/Картриджи, тонеры, фотобарабаны</market_category><picture>https://cdn.citilink.ru/kb0crMA1RZ1fIfOBVUuhgBNEGKWocaRV-r_Ccu2YlQk/background:255:255:255/extend:1/gravity:sm/height:500/resizing_type:fit/width:500/plain/items/982068_v01_b.jpg?version=1700870400</picture><delivery>true</delivery><name>Картридж Cactus CS-LC529XLBK, черный / CS-LC529XLBK</name><vendor>CACTUS</vendor><vendorCode>CS-LC529XLBK</vendorCode><description>черный, объем 61 мл, для DCP-J100/J105/J200</description><sales_notes>Клубные цены ещё ниже. Присоединяйтесь!</sales_notes><manufacturer_warranty>true</manufacturer_warranty><cpa>false</cpa><delivery-options><option cost=\"390\" days=\"2-3\" order-before=\"22\"></option></delivery-options><pickup-options><option cost=\"0\" days=\"34\"></option></pickup-options><barcode>4690207263207</barcode><param name=\"Цвет\">черный</param><param name=\"Group\">Group10</param><url2>https://www.citilink.ru/catalog/computers_and_notebooks/monitors_and_office/cartridges/982068/</url2><model>CS-LC529XLBK</model><typePrefix>Картриджи</typePrefix></offer>"

    //- тэг открывается -> записывается первое значение после скобки "<" (это по сути 1 оффер/элемент)
    //- Идем дальше по тэгам
    //- Далее проверяется - если закрывающий тэг не равно "/>" то вродолжает идти дальше по строке

    var firstIndexElement = ""
    var testIndex = ""
    val offerModel = arrayListOf<String>()

    if (mockFeed.contains("<") && mockFeed.contains(">")){
        testIndex += mockFeed.trim().split("<")[1].split(">")[0]
        firstIndexElement = testIndex.split(" ")[0]
    }

    mockFeed.split("><").forEach {offerTag ->
        var varTag = offerTag
        if (varTag.first().toString() == "<") varTag = varTag.replace("<", "")
        else if (varTag.last().toString() == ">") varTag = varTag.replace(">", "")
        println("<$varTag>")

    }


//    for (i in (mockFeed.contains("<") && mockFeed.contains(">")))
//    if (mockFeed.contains("<") && mockFeed.contains(">")){
//    println()

//    if (mockFeed.contains("<") && mockFeed.contains(">")){
//        testIndex += mockFeed.trim().split("<")[1].split(">")[0]
//        testIndex += mockFeed.trim().split("<")[1].split(">")[0]
//        println(testIndex)
//    }


//    val x1 = mockFeed.filter { i -> i == '<' }.length
//    for (i in 1..x1){
//        offerModel.add(mockFeed.split("<")[i].split(" ")[0])
//    }
//
//    for (i in offerModel.subList(1,offerModel.size)) {
//        if (!offerModel.last().contains(i)){
//         println(i)
//        }
//    }



//    offerModel.forEach{
//        println(offerModel.last().contains(it))
////        println(offerModel[0].contains(offerModel.last()))
//    }

//    while (offerModel[0])



//    println(offerModel)
//    val firstElement = mockFeed.split("<")[1].split(" ")[0]
//    val x2 = x1
//    print(x1)
//    println()
//    x1.forEach {
//        println(it)
//    }

}