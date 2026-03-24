package com.pi.recommendingmenu.recipes.presentation.utils

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object TypeConverter {

    fun fromListToString(list: List<String>): String {
        return list.joinToString(separator = ",") { it.encodeToPercent() }
    }

    fun fromStringToList(string: String): List<String> {
        return if (string.isEmpty()) emptyList()
        else string.split(",").map { it.decodeFromPercent() }
    }

    private fun String.encodeToPercent() =
        URLEncoder.encode(this, StandardCharsets.UTF_8.name())

    private fun String.decodeFromPercent() =
        URLDecoder.decode(this, StandardCharsets.UTF_8.name())
}