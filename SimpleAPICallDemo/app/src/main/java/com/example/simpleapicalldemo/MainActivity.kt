package com.example.simpleapicalldemo

import android.app.Dialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.*
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

        CallAPILoginAsyncTask("Amal4m41","123pwd").startApiCall()  //execute the background process
    }

    private inner class CallAPILoginAsyncTask(val username:String, val password:String){

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


                /**
                 * Sets whether HTTP redirects should be automatically followed by this instance.
                 * The default value comes from followRedirects, which defaults to true.
                 */
                connection.instanceFollowRedirects = false

                /**
                 * Set the method for the URL request, one of:
                 *  GET
                 *  POST
                 *  HEAD
                 *  OPTIONS
                 *  PUT
                 *  DELETE
                 *  TRACE
                 *  are legal, subject to protocol restrictions.  The default method is GET.
                 */
                connection.requestMethod = "POST"

                /**
                 * Sets the general request property. If a property with the key already
                 * exists, overwrite its value with the new value.
                 */
                connection.setRequestProperty("Content-Type", "application/json")
                connection.setRequestProperty("charset", "utf-8")
                connection.setRequestProperty("Accept", "application/json")

                /**
                 * Some protocols do caching of documents.  Occasionally, it is important
                 * to be able to "tunnel through" and ignore the caches (e.g., the
                 * "reload" button in a browser).  If the UseCaches flag on a connection
                 * is true, the connection is allowed to use whatever caches it can.
                 *  If false, caches are to be ignored.
                 *  The default value comes from DefaultUseCaches, which defaults to
                 * true.
                 */
                connection.useCaches = false

                /**
                 * Creates a new data output stream to write data to the specified
                 * underlying output stream. The counter written is set to zero.
                 */
                val wr = DataOutputStream(connection.outputStream)

                // Create JSONObject Request
                val jsonRequest = JSONObject()
                jsonRequest.put("username", username) // Request Parameter 1
                jsonRequest.put("password", password) // Request Parameter 2

                /**
                 * Writes out the string to the underlying output stream as a
                 * sequence of bytes. Each character in the string is written out, in
                 * sequence, by discarding its high eight bits. If no exception is
                 * thrown, the counter written is incremented by the
                 * length of s.
                 */
                wr.writeBytes(jsonRequest.toString())
                wr.flush() // Flushes this data output stream.
                wr.close() // Closes this output stream and releases any system resources associated with the stream



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

            //USING GSON library
            //This method deserializes the specified Json into an object of the specified class
            val responseData=Gson().fromJson(result,ResponseData::class.java) //use ResponseData as the model for the 'result' string json
            Log.i("Message",  "Name: ${responseData.Name}")
            Log.i("Message", "No: ${responseData.Dummy_no}")
            Log.i("Message", "Profile details: ${responseData.Profile_details}")
            Log.i("Message", "Profile details isProfileCompleted: ${responseData.Profile_details.is_profile_completed}")
            Log.i("Message", "Profile details rating: ${responseData.Profile_details.rating}")
            Log.i("Message", "dataList: ${responseData.data_list}")

            for(i in responseData.data_list.indices){
                Log.i("Message", "dataList item : ${responseData.data_list[i]}")
                Log.i("Message", "dataList id: ${responseData.data_list[i].id}")
                Log.i("Message", "dataList name: ${responseData.data_list[i].name}")
            }



//            val jsonObject=JSONObject(result) //create json object from string
//
//            val name=jsonObject.optString("Name") //getting the value using key from json object(return "" if key doesn't exist)
////            Log.i(TAG, "afterCallFinish: $name")
//// Returns the value mapped by {name} if it exists.
//            val dummyNo = jsonObject.optInt("Dummy_no")
//            Log.i(TAG, "$dummyNo")
//
//            // Returns the value mapped by {name} if it exists.
////            val Profile_details = jsonObject.optString("Profile_details")
////            Log.i("Name", "$Profile_details")
////
////            // Returns the value mapped by {name} if it exists.
////            val is_profile_completed = jsonObject.optBoolean("is_profile_completed")
////            Log.i("Email", "$is_profile_completed")
////
////            // Returns the value mapped by {name} if it exists.
////            val rating = jsonObject.optDouble("rating")
////            Log.i("Mobile", "$rating")
//
//            // Returns the value mapped by {name} if it exists.
//            val profileDetailsObject = jsonObject.optJSONObject("Profile_details")
//
//            val isProfileCompleted = profileDetailsObject.optBoolean("is_profile_completed")
//            Log.i(TAG, "$isProfileCompleted")
//
//            val rating = profileDetailsObject.optDouble("rating")
//            Log.i(TAG, "$rating")
//
//            // Returns the value mapped by {name} if it exists.
//            val dataListArray = jsonObject.optJSONArray("data_list")
//            Log.i(TAG, "${dataListArray.length()}")
//
//            for (item in 0 until dataListArray.length()) {
//                Log.i(TAG, "${dataListArray[item]}")
//
//                // Returns the value mapped by {name} if it exists.
//                val dataItemObject: JSONObject = dataListArray[item] as JSONObject
//
//                val id = dataItemObject.optInt("id")
//                Log.i(TAG, "$id")
//
//                val name_sub = dataItemObject.optString("name")
//                Log.i(TAG, "$name_sub")
//            }


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