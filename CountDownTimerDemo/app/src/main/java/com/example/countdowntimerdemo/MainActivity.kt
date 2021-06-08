package com.example.countdowntimerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.countdowntimerdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    // Variable for Timer which will be initialized later.
    private var countDownTimer: CountDownTimer? = null

    // The Duration of the timer in milliseconds
    private var timerDuration: Long = 60000

    // pauseOffset = timerDuration - time left
    private var pauseOffset: Long = 0

    /**
     * onCreate()
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)   //making the layout visible for us to access.
        setContentView(binding.root)
        // setSupportActionBar(toolbar)

        binding.tvTimer.text = (timerDuration / 1000).toString()

        binding.startButton.setOnClickListener {
            startTimer(pauseOffset)
        }

        binding.pauseButton.setOnClickListener {
            pauseTimer()
        }

        binding.resetButton.setOnClickListener {
            resetTimer()
        }

    }


    /**
     * Function is used to start the timer of 60 Seconds.
     */
    private fun startTimer(pauseOffsetL: Long) {

        // Passing pauseOffsetL will help us in restarting the timer(cuz every time we restart/resume we're creating
        // a new instance of the countDownTimer)
        countDownTimer = object : CountDownTimer(timerDuration - pauseOffsetL, 1000) {

            // onTick() - it is called on every countDownInterval (1 sec = 1000)
            override fun onTick(millisUntilFinished: Long) {
                pauseOffset = timerDuration - millisUntilFinished
                //pauseOffset is how many ms are over, millisUntilFinished is how many ms are left
                binding.tvTimer.text = (millisUntilFinished / 1000).toString()
            }

            // onFinish()
            override fun onFinish() {
                Toast.makeText(this@MainActivity, "Timer is finished", Toast.LENGTH_SHORT).show()
            }

        }.start()

    }


    /**
     * Function is used to pause the count down timer which is running
     */
    private fun pauseTimer() {
        Toast.makeText(this, "$timerDuration : $pauseOffset", Toast.LENGTH_SHORT).show()
        countDownTimer?.cancel()
    }

    /**
     *  Function is used to reset the count down timer which is running
     */
    private fun resetTimer() {
        if (countDownTimer != null) {

            // Cancel the current timer
            countDownTimer!!.cancel()

            // Set tv_timer's text back to 60 seconds
            binding.tvTimer.text = (timerDuration / 1000).toString()

            // Set it back to null
            countDownTimer = null

            // Set pauseOffset back to 0, otherwise we may face confusing issues in the app
            pauseOffset = 0
        }
    }


}