package com.example.conduit.extension

import java.sql.Timestamp
import java.text.SimpleDateFormat

/**
 * @author masker
 * @date 2021/12/5
 */

fun Timestamp?.format(): String {
    if (this == null) {
        return ""
    }
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return formatter.format(this)
}