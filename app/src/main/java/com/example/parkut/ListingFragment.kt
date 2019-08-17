package com.example.parkut

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.listing_fragment.view.*
import okhttp3.*
import org.jetbrains.anko.doAsync
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.util.*

class ListingFragment : Fragment() {
    private val jsoncode = 1

    private var response: String? = null
    private var userlist: ListView? = null
    private var log_out: View? = null
    private var userArrayList: ArrayList<String>? = null
    private var userModelArrayList: ArrayList<Garage_Model>? = null
    private var customAdapter: CustomAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.listing_fragment, container, false)



        view.done.setOnClickListener {
            doAsync {
                fetchdetails()
            }

                //val jsonarray = JSONArray(gotresponse)
                //iterate through the returned array of JSON objects
                // and look for candiadate whose name we requested
               // for (i in 0..(jsonarray.length() - 1)) {
              //      val user = jsonarray.getJSONObject(i)
              //  }


        }
        view.register1.setOnClickListener({

            // Navigate to the next Fragment.
            (activity as NavigationHost).navigateTo(RegisterFragment(), false)

        })


        return view;
    }

    private fun getInfo(response: String): ArrayList<Garage_Model> {
        val userModelArrayList = ArrayList<Garage_Model>()
        try {
            val dataArray = JSONArray(response)
            for (i in 0 until dataArray.length()) {
                val usersModel = Garage_Model()
                val dataobj = dataArray.getJSONObject(i)
                usersModel.setNames(dataobj.getString("email"))
                usersModel.setAddresses(dataobj.getString("address"))
                userModelArrayList.add(usersModel)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return userModelArrayList
    }

    private fun fetchComplete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun activityUiThread(function: () -> Unit) {

    }

    fun loadJSONFromAssets(): String? {
        var json: String? = null
        try {
            val inputStream = this.context?.assets?.open("garages.json")
            val size = inputStream?.available()
            val buffer = ByteArray(size!!)
            inputStream.read(buffer)
            inputStream.close()

            json = String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return json
    }

private fun fetchdetails() {
    var client = OkHttpClient()
    var request = OkHttpRequest(client)
    val URL1 = "https://park-ut.appspot.com/details?user_id=1"

    request.GET(URL1, object : Callback {

        override fun onResponse(call: Call?, response: Response) {
            val responseData = response.body()?.string()
val status = response.code()
            var json = JSONArray(responseData)
//                            println("Request Successful!!")
//                            println(json)
            // val responseObject = json.getJSONObject("response")
            for (i in 0..(json.length() - 1)) {
                val user = json.getJSONObject(i)
                val userModelArrayList = ArrayList<Garage_Model>()
                 println(user)

//                val usersModel = Garage_Model()
//
//                usersModel.setNames(user.getString("email"))
//                usersModel.setAddresses(user.getString("address"))
//                userModelArrayList.add(usersModel)
            }
            //val docs = json.getJSONArray(i)
            this@ListingFragment.fetchComplete()

        }

        override fun onFailure(call: Call?, e: IOException?) {
            println("Request Failure.")
        }

    })

    //private fun runOnUiThread(functionset: () -> Unit) {

}
}



