package com.example.gonnokami.roundedbandgraphsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        graphFull.positivePercentage = 100
        graphAlmost.positivePercentage = 98
        graphMost.positivePercentage = 70
        graphHalf.positivePercentage = 50
        graphSome.positivePercentage = 30
        graphLittle.positivePercentage = 2
        graphEmpty.positivePercentage = 0
    }
}
