package com.example.simpleapicalldemo

import android.app.Dialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
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

    private val TAG:String="MainActivityTag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CallAPILoginAsyncTask().startApiCall()  //execute the background process
    }

    private inner class CallAPILoginAsyncTask(){

        private lateinit var customProgressDialog:Dialog

        fun startApiCall() {
            showProgressDialog()
            lifecycleScope.launch(Dispatchers.IO) {
//                delay(5000L)
                val stringResult=makeApiCall()
                afterCallFinish(stringResult)
            }
        }

        fun makeApiCall(): String {
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

        fun afterCallFinish(result: String) {
            cancelProgressDialog()

            Log.i("JSON RESPONSE RESULT", result)
            /*
Output:
{
"Name":"testing simple api",
"Dummy_no":123,
"Profile_details":{
"is_profile_completed":true,
"rating":4.7
},
"data_list":[
{"id":1,"name":"Messi"},{"id":2,"name":"Neymar"},{"id":3,"name":"Zlatan"}]

}
 */

            val jsonObject=JSONObject(result) //create json object from string

            val name=jsonObject.optString("Name") //getting the value using key from json object(return "" if key doesn't exist)
//            Log.i(TAG, "afterCallFinish: $name")
// Returns the value mapped by {name} if it exists.
            val dummyNo = jsonObject.optInt("Dummy_no")
            Log.i(TAG, "$dummyNo")

            // Returns the value mapped by {name} if it exists.
//            val Profile_details = jsonObject.optString("Profile_details")
//            Log.i("Name", "$Profile_details")
//
//            // Returns the value mapped by {name} if it exists.
//            val is_profile_completed = jsonObject.optBoolean("is_profile_completed")
//            Log.i("Email", "$is_profile_completed")
//
//            // Returns the value mapped by {name} if it exists.
//            val rating = jsonObject.optDouble("rating")
//            Log.i("Mobile", "$rating")

            // Returns the value mapped by {name} if it exists.
            val profileDetailsObject = jsonObject.optJSONObject("Profile_details")

            val isProfileCompleted = profileDetailsObject.optBoolean("is_profile_completed")
            Log.i(TAG, "$isProfileCompleted")

            val rating = profileDetailsObject.optDouble("rating")
            Log.i(TAG, "$rating")

            // Returns the value mapped by {name} if it exists.
            val dataListArray = jsonObject.optJSONArray("data_list")
            Log.i(TAG, "${dataListArray.length()}")

            for (item in 0 until dataListArray.length()) {
                Log.i(TAG, "${dataListArray[item]}")

                // Returns the value mapped by {name} if it exists.
                val dataItemObject: JSONObject = dataListArray[item] as JSONObject

                val id = dataItemObject.optInt("id")
                Log.i(TAG, "$id")

                val name_sub = dataItemObject.optString("name")
                Log.i(TAG, "$name_sub")
            }


        }

        private fun showProgressDialog(){
            customProgressDialog= Dialog(this@MainActivity)
            customProgressDialog.setContentView(R.layout.dialog_custom_progress)
            customProgressDialog.setCancelable(false)
            customProgressDialog.show()
        }

        private fun cancelProgressDialog(){
            customProgressDialog.dismiss()
        }

    }
}