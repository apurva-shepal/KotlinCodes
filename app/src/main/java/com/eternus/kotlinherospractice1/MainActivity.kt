package com.eternus.kotlinherospractice1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import kotlin.math.roundToLong
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), View.OnClickListener {


    var andrew by Delegates.notNull<Boolean>()
    var dmitry by Delegates.notNull<Boolean>()
    var michel by Delegates.notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        submit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.submit -> {
                andrew = false
                michel = false
                dmitry = false
                create2DArray()
            }
        }
    }

    private fun create2DArray() {

        val array2d = arrayOf(
            intArrayOf(
                Andrew.text.toString().toInt(),
                Dmitry.text.toString().toInt(),
                Michal.text.toString().toInt()
            )
            , intArrayOf(
                green_grapes.text.toString().toInt(),
                purple_grapes.text.toString().toInt(),
                black_grapes.text.toString().toInt()
            )
        )
        if (checkNegativeValues(array2d)) {
            val startTime = System.nanoTime()
            for (person in 0..2) {

                when (person) {
                    0 -> {
                        if (array2d[0][0] <= array2d[1][0]) {
                            array2d[1][0] = array2d[1][0] - array2d[0][0]
                            andrew = true
                        }
                    }
                    1 -> {
                        if (array2d[0][1] <= (array2d[1][0] + array2d[1][1])) {
                            if (array2d[0][1] <= array2d[1][0]) {
                                array2d[1][0] = array2d[1][0] - array2d[0][1]
                                dmitry = true
                            } else {
                                array2d[0][1] = array2d[0][1] - array2d[1][0]
                                array2d[1][0] = 0
                                if (array2d[0][1] <= array2d[1][1]) {
                                    dmitry = true
                                }
                            }
                        }
                    }
                    2 -> {
                        if (array2d[0][2] < (array2d[1][0] + array2d[1][1] + array2d[1][2])) {
                            michel = true
                        }
                    }
                }
            }
            calculateResult(startTime)
        } else {
            output.text = "Negative values not alllowed"
        }
    }

    private fun checkNegativeValues(array2d: Array<IntArray>): Boolean {
        for (row in 0..1) {
            for(column in 0..2) {
                if (array2d[row][column] < 0) {
                    return false
                }
            }

        }
        return true
    }

    private fun calculateResult(startTime: Long) {
        if (andrew and dmitry and michel) {
            output.text = "Yes"
        } else {
            output.text = "No"
        }
        val endTime = System.nanoTime()
        val elapsedTime = (endTime.toDouble() - startTime)
        output.append("\n ${TimeUnit.SECONDS.convert(elapsedTime.roundToLong(), TimeUnit.NANOSECONDS)} sec")
    }
}
