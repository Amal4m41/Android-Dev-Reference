package com.example.simpleapicalldemo

import android.app.Dialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CallAPILoginAsyncTask().execute()  //execute the background process
    }

    private inner class CallAPILoginAsyncTask(): AsyncTask<Any, Void, String>(){

        private lateinit var customProgressDialog:Dialog

        override fun onPreExecute() {
            super.onPreExecute()
            showProgressDialog()
        }

        override fun doInBackground(vararg params: Any?): String {
            var result:String
            var connection: HttpURLConnection?=null

            try{
                val url=URL("https://run.mocky.io/v3/d9db49ea-54ef-4be1-926b-b2a7b3f8da79")
                connection= url.openConnection() as HttpURLConnection?   //Returns a URLConnection instance that represents a connection to the remote object referred to by the URL.
                connection!!.doInput=true  //doInput tells if we get any data(by default doInput will be true and doOutput false)
                connection!!.doOutput=true //doOutput tells if we send any data with the api call

                val httpResult:Int=connection.responseCode
                if(httpResult==HttpURLConnection.HTTP_OK){
                    //now once we have established a successful connection, we want to read the data.

                    //Returns an input stream that reads from this open connection. A SocketTimeoutException can be thrown when
                    // reading from the returned input stream if the read timeout expires before data is available for read.
                    val inputStream=connection.inputStream

                    val reader=BufferedReader(InputStreamReader(inputStream))
                    val stringBuilder:StringBuilder=StringBuilder()
                    var line:String?
                    try{
                        while (reader.readLine().also { line=it }!=null) {
                                stringBuilder.append(line+"\n")
                            Log.i("TAG", "doInBackground: $line\n")
                        }
                    }
                    catch (e:IOException){
                        e.printStackTrace()
                    }
                    finally {
                        try {  //there could be some error while closing the inputStream
                            inputStream.close()
                        }
                        catch (e:IOException){
                            e.printStackTrace()
                        }
                    }
                    result=stringBuilder.toString()
                }
                else{  //if the response code is not OK
                    result=connection.responseMessage
                }
            }
            catch (e:SocketTimeoutException){
                result="Connection Timeout"
            }
            catch (e:Exception){
                result="Error + ${e.message}"
            }
            finally {
                connection?.disconnect()
            }

            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            cancelProgressDialog()

            Log.i("RESULT", result.toString())
        }

        private fun showProgressDialog(){
            customProgressDialog= Dialog(this@MainActivity)
            customProgressDialog.setContentView(R.layout.dialog_custom_progress)
            customProgressDialog.show()
        }

        private fun cancelProgressDialog(){
            customProgressDialog.dismiss()
        }

    }
}