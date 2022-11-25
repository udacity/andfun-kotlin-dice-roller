/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.diceroller

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener  {

    lateinit var diceImage: ImageView
    lateinit var textLabel: TextView
    lateinit var text_one: TextView
    lateinit var text_two: TextView
    lateinit var seek: SeekBar


    var languages = arrayOf("Java", "PHP", "Kotlin", "Javascript", "Python", "Swift")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setTheme(R.style.AppTheme_Fullscreen)
        setContentView(R.layout.activity_main)


        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener {
            rollDice()
        }

        val resetButton: Button = findViewById(R.id.reset_button)
        resetButton.setOnClickListener {
            resetDice()
        }

        diceImage = findViewById(R.id.dice_image)
        textLabel = findViewById(R.id.text_label)
        text_one = findViewById(R.id.text_one)
        text_two = findViewById(R.id.text_two)

        text_one.text = "You're gay for "
        text_two.text = "0%"

        fillSpinner()
        useSeekBar()

    }

    private fun rollDice() {
        val randomInt = Random().nextInt(6) + 1
        textLabel.setText("Your digit is $randomInt")
        val drawableResource = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        diceImage.setImageResource(drawableResource)
    }

    private fun resetDice() {

        diceImage.setImageResource(R.drawable.empty_dice)
        textLabel.setText("HELLO")
        seek.progress = 0
        text_two.text = "0%"

    }

    fun fillSpinner() {

        var aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(test_spinner)
        {
            adapter = aa
            setSelection(0, false)
            onItemSelectedListener = this@MainActivity
            prompt = "Select your favourite language"
            gravity = Gravity.CENTER

        }

//        val spinner = Spinner(this)
//        spinner.id = NEW_SPINNER_ID
//
//        val ll = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//
//        ll.setMargins(10, 40, 10, 10)
//        linearLayout.addView(spinner)


    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                showToast(message = "language: ${languages[position]}")

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        showToast(message = "Nothing selected")
    }
    private fun showToast(context: Context = applicationContext, message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }


    fun useSeekBar() {
        seek = findViewById(R.id.seekbar)

        seek?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                // write custom code for progress is changed
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
//                Toast.makeText(
//                    this@MainActivity,
//                    "Progress is: " + seek.progress + "%",
//                    Toast.LENGTH_SHORT
//                ).show()
                text_two.text = "${seek.progress}%"

            }

        })
    }
}
