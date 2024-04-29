package com.perfomax.dataviewer.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = mochiyPopOneFontFamily,
        fontSize = 36.sp,
        fontWeight = FontWeight.W400
    ),
    headlineMedium = TextStyle(
        fontFamily = poppinsFontFamily,
        fontSize = 32.sp,
        fontWeight = FontWeight.W600,
        lineHeight = 32.sp
    ),
    titleSmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontSize = 18.sp,
        fontWeight = FontWeight.W600
    ),
    labelLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.W600,
    ),
    labelMedium = TextStyle(
        fontFamily = poppinsFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.W600,
        lineHeight = 17.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontSize = 15.sp,
        fontWeight = FontWeight.W400
    ),
    bodySmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontSize = 10.sp,
        fontWeight = FontWeight.W400
    )
)

val bodyLargeBold = TextStyle(
    fontFamily = poppinsFontFamily,
    fontSize = 15.sp,
    fontWeight = FontWeight.W600
)

val bodyPromo = TextStyle(
    fontFamily = alfaSlabOneFontFamily,
    fontSize = 15.sp,
    fontWeight = FontWeight.W400
)