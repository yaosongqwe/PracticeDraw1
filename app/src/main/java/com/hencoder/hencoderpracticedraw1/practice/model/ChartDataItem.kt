package com.hencoder.hencoderpracticedraw1.practice.model

import android.graphics.Color

/**
 * Demo for CustomView
 * Created by Song on 2017/10/20.
 */
data class ChartDataItem(var name: String, var num: Int = 0, var color: Int = Color.GREEN, var mark: String) {
    var percent : Float = 0f
}