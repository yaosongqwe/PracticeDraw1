package com.hencoder.hencoderpracticedraw1.practice.util

import android.graphics.Color
import com.hencoder.hencoderpracticedraw1.practice.model.ChartData
import com.hencoder.hencoderpracticedraw1.practice.model.ChartDataItem
import java.util.ArrayList

/**
 * Demo for vlayout
 * Created by Song on 2017/10/20.
 */
class ChartUtil {
    companion object {
        @JvmStatic
        fun initChartData(chartData:ChartData) : ChartData {
            chartData.sumNum = chartData.dataList.sumBy { it.num }
            chartData.maxValue = chartData.dataList.maxBy { it.num }!!.num
            chartData.dataList.forEach {
                it.percent = it.num.toFloat()/chartData.sumNum.toFloat()
            }
            return chartData
        }

        @JvmStatic
        fun getDemoData() : ChartData{
            val chartDataItems = ArrayList<ChartDataItem>()
            chartDataItems.add(ChartDataItem("Froyo", 1, Color.BLUE, "2.2"))
            chartDataItems.add(ChartDataItem("GB", 2, Color.CYAN, "2.3"))
            chartDataItems.add(ChartDataItem("ICS", 2, Color.DKGRAY, "4.0"))
            chartDataItems.add(ChartDataItem("JB", 8, Color.GREEN, "4.2"))
            chartDataItems.add(ChartDataItem("KitKat", 16, Color.LTGRAY, "4.4"))
            chartDataItems.add(ChartDataItem("L", 22, Color.MAGENTA, "5.0"))
            chartDataItems.add(ChartDataItem("M", 11, Color.RED, "6.0"))
            val chartData = ChartData(chartDataItems, "demo")
            return ChartUtil.initChartData(chartData)
        }
    }
}