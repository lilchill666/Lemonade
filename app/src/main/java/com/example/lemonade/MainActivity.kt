package com.example.lemonade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var numberOfSqueezes = 0
    private var bounder = 0
    private var currentState = "tree"
    private var random = 0
    private var states = arrayOf("tree", "lemon", "cup", "empty")
    private var  indexer = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        grow()
    }
    private fun grow(){
        val image : ImageView = findViewById(R.id.imageView)
        image.setImageResource(R.drawable.lemon_tree)
        setBounder()
        random = (1..bounder).random()
        image.setOnClickListener {
            numberOfSqueezes += 1
            if (numberOfSqueezes == random){
                checkState()
                setBounder()
                numberOfSqueezes = 0
                random = (1..bounder).random()
            }
        }
    }

    private fun checkState(){
        val image : ImageView = findViewById(R.id.imageView)
        val text : TextView = findViewById(R.id.textView2)
        if (indexer == 3){
            indexer = -1
        }
        indexer++

        currentState = states[indexer]
        when (currentState){
            states[0] -> image.setImageResource(R.drawable.lemon_tree)
            states[1] -> image.setImageResource(R.drawable.lemon_squeeze)
            states[2] -> image.setImageResource(R.drawable.lemon_drink)
            states[3] -> image.setImageResource(R.drawable.lemon_restart)
        }
        when (currentState){
            states[0] -> text.setText(R.string.tap2grow)
            states[1] -> text.setText(R.string.tap2juice)
            states[2] -> text.setText(R.string.tap2drink)
            states[3] -> text.setText(R.string.tap2getNew)
        }
        if (indexer == 4){
            indexer = 0
        }
    }
    private fun setBounder(){
        when (currentState){
            states[0] -> bounder = 4
            states[1] -> bounder = 15
            states[2] -> bounder = 7
            states[3] -> bounder = 1
        }
    }
}