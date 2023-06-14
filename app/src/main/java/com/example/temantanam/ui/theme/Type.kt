package com.example.temantanam.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.temantanam.R

val Poppins = FontFamily(
    Font(R.font.poppins_black),
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_extrabold, FontWeight.ExtraBold),
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_thin, FontWeight.Thin),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.poppins_extralight, FontWeight.ExtraLight),
    Font(R.font.poppins_blackitalic, style = FontStyle.Italic),
    Font(R.font.poppins_bolditalic, style = FontStyle.Italic, weight = FontWeight.Bold),
    Font(R.font.poppins_lightitalic, style = FontStyle.Italic, weight = FontWeight.Light),
    Font(R.font.poppins_semibolditalic, style = FontStyle.Italic, weight = FontWeight.SemiBold),
    Font(R.font.poppins_thinitalic, style = FontStyle.Italic, weight = FontWeight.Thin),
    Font(R.font.poppins_extralightitalic, style = FontStyle.Italic, weight = FontWeight.ExtraLight),
    Font(R.font.poppins_extrabolditalic, style = FontStyle.Italic, weight = FontWeight.ExtraBold),
    Font(R.font.poppins_mediumitalic, style = FontStyle.Italic, weight = FontWeight.Medium),
)

// Set of Material typography styles to start with
val Typography = Typography(
    titleSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

