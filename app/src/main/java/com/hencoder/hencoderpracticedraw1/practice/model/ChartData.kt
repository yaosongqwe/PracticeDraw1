package com.hencoder.hencoderpracticedraw1.practice.model

import java.util.*

/**
 * Demo for chart
 * Created by Song on 2017/10/20.
 */
data class ChartData(var dataList:List<ChartDataItem> = ArrayList<ChartDataItem>(), var name:String) {
    var sumNum:Int = 0
    var startY:Int = 0
    var widthUnit:Int = 30
    var widthSpan:Int = 10
    var valuePerSpanHeight:Int = 5
    var maxValue:Int = 0
    var textSize:Int = 10
    var titleTextSize:Int = 20
    var heightSpan = 30
}