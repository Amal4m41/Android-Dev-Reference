package com.example.texttospeechdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*


class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null // Variable for TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSpeak=findViewById<Button>(R.id.speakButton)
        val etEnteredText=findViewById<EditText>(R.id.editText)

        // Initialize the Text To Speech
        /*
        context is 'this' as it must be spoken in the current activity(main activity), also the listener is the
        main activity which is only possible since we're extending the OnInitListener interface.
         */
        tts = TextToSpeech(this, this)


        btnSpeak.setOnClickListener { view ->

            if (etEnteredText.text.isEmpty()) {
                Toast.makeText(this@MainActivity, "Enter a text to speak.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                speakOut(etEnteredText.text.toString())
            }
        }
    }

    /**
     * This the TextToSpeech override function(must be overridden since it's from the interface)
     *
     * Called to signal the completion of the TextToSpeech engine initialization.
     */
    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {  //if text-to-speech can be used
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            }

        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }

    /**
     * Here is Destroy function we will stop and shutdown the tts which is initialized above.
     */
    public override fun onDestroy() {

        if (tts != null) {
            tts!!.stop()  //Interrupts the current utterance (whether played or rendered to file) and discards other utterances in the queue.
            tts!!.shutdown() //Releases the resources used by the TextToSpeech engine
        }

        super.onDestroy()
    }

    /**
     * Function is used to speak the text what we pass to it.
     */
    private fun speakOut(text: String) {
        //Queue flush will flush the latest job(tts) instead of appending to the queue.
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}
