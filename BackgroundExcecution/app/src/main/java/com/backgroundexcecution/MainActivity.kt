package com.backgroundexcecution

import android.app.Dialog
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        btn_execute.setOnClickListener { view ->
            ExecuteAsyncTask("Background task executed successfully.").execute()
        }
    }

    /**
     * “A nested class marked as inner can access the members of its outer class.
     * Inner classes carry a reference to an object of an outer class:”
     * source: https://kotlinlang.org/docs/reference/nested-classes.html
     *
     * This is the background class is used to execute background task.
     *
     * For Background we have used the AsyncTask
     *
     * Asynctask : Creates a new asynchronous task. This constructor must be invoked on the UI thread.
     */
    private inner class ExecuteAsyncTask(val value: String) :
        AsyncTask<Any, Void, String>() {

        var customProgressDialog: Dialog? = null

        /**
         * This function is for the task which we wants to perform before background execution.
         * Here we have shown the progress dialog to user that UI is not freeze but executing something in background.
         */
        override fun onPreExecute() {
            super.onPreExecute()

            showProgressDialog()
        }

        /**
         * This function will be used to perform background execution.
         */
        override fun doInBackground(vararg params: Any): String {

            //TODO(You can code here what you wants to execute in background execution without freezing the UI thread.)

            // This is just an for loop which is executed for 1000 times.
            for (i in 1..1000) {
                Log.e("i : ", "" + i)
            }

            // You can notify with you custom message to onPostexecute.
            return value
        }

        /**
         * This function will be executed after the background execution is completed.
         */
        override fun onPostExecute(result: String) {
            super.onPostExecute(result)

            cancelProgressDialog()

            Toast.makeText(this@MainActivity, result, Toast.LENGTH_SHORT).show()
        }

        /**
         * Method is used to show the Custom Progress Dialog.
         */
        private fun showProgressDialog() {
            customProgressDialog = Dialog(this@MainActivity)

            /*Set the screen content from a layout resource.
            The resource will be inflated, adding all top-level views to the screen.*/
            customProgressDialog!!.setContentView(R.layout.dialog_custom_progress)

            //Start the dialog and display it on screen.
            customProgressDialog!!.show()
        }

        /**
         * This function is used to dismiss the progress dialog if it is visible to user.
         */
        private fun cancelProgressDialog() {
            if (customProgressDialog != null) {
                customProgressDialog!!.dismiss()
                customProgressDialog = null
            }
        }
    }
}