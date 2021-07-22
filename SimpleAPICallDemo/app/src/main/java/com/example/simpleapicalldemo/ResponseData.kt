package com.example.simpleapicalldemo

import org.json.JSONArray
import org.json.JSONObject
/**
 * A class for creating a data class which will be used to parse the Json response using GSON.
 */
data class ResponseData(
    val Name:String,
    val Dummy_no:Int,
    val Profile_details:ProfileDetails,
    val data_list:List<DataListDetail>
)

data class ProfileDetails(
    val is_profile_completed:Boolean,
    val rating:Double
)

data class DataListDetail(
    val id:Int,
    val name:String
)