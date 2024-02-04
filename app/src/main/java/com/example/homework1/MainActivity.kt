package com.example.homework1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.homework1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var counter: Int = 0
    private val maxSeats: Int = 50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        addButtonOnListener()
        updateBottomTextView()
        isEnableButton()
        freeSeatsOnTheBus()
    }

    private fun addButtonOnListener() {

        val resetButton = binding.resetButton
        val buttonPlus = binding.buttonPlus
        val buttonMinus = binding.buttonMinus

        resetButton.setOnClickListener {
            counter = 0
            updateBottomTextView()
            isEnableButton()
            freeSeatsOnTheBus()
        }

        buttonPlus.setOnClickListener {
            counter++
            updateBottomTextView()
            isEnableButton()
            freeSeatsOnTheBus()
        }

        buttonMinus.setOnClickListener {
            counter--
            updateBottomTextView()
            isEnableButton()
            freeSeatsOnTheBus()
        }
    }

    private fun updateBottomTextView() {
        binding.textViewBottom.text = counter.toString()
        binding.resetButton.visibility = if (counter >= 50) View.VISIBLE else View.INVISIBLE
    }

    private fun isEnableButton() {
        binding.buttonMinus.isEnabled = (counter != 0)
    }

    private fun freeSeatsOnTheBus() {
        val textViewCenter = binding.textViewCenter

        if (counter == 0) {
            textViewCenter.setText(R.string.string1)
            binding.textViewCenter.setTextColor(getColor(R.color.green))
        } else if (counter in 1..49) {
            val changeSits: Int = maxSeats - counter
            val text = getString(R.string.string2) + "\n" + changeSits.toString() + correctString(changeSits)
            textViewCenter.text = text
            binding.textViewCenter.setTextColor(getColor(R.color.blue))
        } else if (counter >= 50) {
            textViewCenter.setText(R.string.string3)
            binding.textViewCenter.setTextColor(getColor(R.color.red))
        }
    }

    private fun correctString(quantity: Int) : String {
        return if (quantity % 10 == 1 && quantity % 100 != 11) {
            " место"
        } else if (
            (quantity % 10 in 2..4) &&
            (quantity % 100 < 10 || quantity % 100 >= 20)) {
            " места"
        } else {
            " мест"
        }
    }

}

