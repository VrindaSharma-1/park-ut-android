package com.example.parkut

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_occupy.view.*
import kotlinx.android.synthetic.main.listing_fragment.*
import kotlinx.android.synthetic.main.listing_fragment.view.*
import kotlinx.android.synthetic.main.listing_fragment.view.garage_id
import okhttp3.*
import org.jetbrains.anko.AnkoAsyncContext
import org.jetbrains.anko.doAsync
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*
import java.util.logging.Logger.global

class ListingFragment : Fragment() {
    private val jsoncode = 1

    private var response: String? = null
    private var userlist: ListView? = null
    private var log_out: View? = null
    private var userArrayList: ArrayList<String>? = null
    private var userModelArrayList: ArrayList<Garage_Model>? = null
    private var customAdapter: CustomAdapter? = null

    companion object one{
        fun newInstance(position: String):ListingFragment {
            val fragment = ListingFragment()
            val args = Bundle()
           var user_id = args.putString("position", position)
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.listing_fragment, container, false)
        userlist = view.userlist
        val args=arguments
val user_id = args?.getString("position","position").toString()
        doAsync {
            fetchdetails(user_id)

        }
        view.reserve.setOnClickListener{
           doAsync {  reserve() }


            // Navigate to the next Fragment.
            // (activity as NavigationHost).navigateTo(RegisterFragment(), false)

        }

        view.occupy.setOnClickListener{
            doAsync {  occupy() }

            // Navigate to the next Fragment.
            // (activity as NavigationHost).navigateTo(RegisterFragment(), false)

        }
        view.logout.setOnClickListener{


            // Navigate to the next Fragment.
            (activity as NavigationHost).navigateTo(LoginFragment(), false)

        }
        view.checkout.setOnClickListener{

            doAsync {  checkout() }

        }
        view.done.setOnClickListener {
            userlist = view.userlist
            var jsonResponse = loadJSONFromAssets()
            userModelArrayList = getInfo(jsonResponse!!)

            // Create a Custom Adapter that gives us a way to "view" each user in the ArrayList
            customAdapter = CustomAdapter(view.context, userModelArrayList!!)
            // set the custom adapter for the userlist viewing
            userlist!!.adapter = customAdapter


        }
        return view;




    }

    private fun getInfo(response: String): ArrayList<Garage_Model> {
        val userModelArrayList = ArrayList<Garage_Model>()
        try {
            val dataArray = JSONArray(response)
            for (i in 0 until dataArray.length()) {
                val usersModel = Garage_Model()
                val dataobj = dataArray.getJSONObject(i)
                usersModel.setAddresses(dataobj.getString("address"))
                usersModel.setIds(dataobj.getString("id"))
                usersModel.setNames(dataobj.getString("name"))

                usersModel.setSpotss(dataobj.getString("spots"))

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

private fun fetchdetails(user_id:String) {

println(user_id)
    var client = OkHttpClient()
    var request = OkHttpRequest(client)
    val URL1 = "https://park-ut.appspot.com/details?user_id=$user_id"

    request.GET(URL1, object : Callback {

        override fun onResponse(call: Call?, response: Response) {
            val responseData = response.body()?.string()
val status = response.code()
            var json = JSONArray(responseData)
//                            println("Request Successful!!")
//                            println(json)
            // val responseObject = json.getJSONObject("response")
//            for (i in 0..(json.length() - 1)) {
   //(activity as NavigationHost).navigateTo(RegisterFragment(), false)
        }

        override fun onFailure(call: Call?, e: IOException?) {
            println("Request Failure.")
        }

    })

    //private fun runOnUiThread(functionset: () -> Unit) {
}


    private fun reserve()
    {
        val args=arguments
        val user_id = args?.getString("position","position").toString()
        val garage_id = garage_id.text
        val client = OkHttpClient()
        val request = OkHttpRequest(client)
        val map: HashMap<String, String> = hashMapOf("garage_id" to "$garage_id")
        val url = "https://park-ut.appspot.com/reserve/$garage_id?user_id=$user_id"
        val post = request.POST(
                url,
                map,
                object : Callback {
                    override fun onResponse(call: Call?, response: Response) {

                    }



                    override fun onFailure(call: Call?, e: IOException?) {
                        println("Request Failure.")
                    }
                })

     //  (activity as NavigationHost).navigateTo(OccupyFragment(), false)
        fun fetchComplete() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        fun runOnUiThread(function: () -> Unit) {

        }

        fun <T> AnkoAsyncContext<T>.activityUiThread(function: () -> Unit) {

        }

    }
    private fun occupy()
    {
        val args=arguments
        val user_id = args?.getString("position","position").toString()
        val client = OkHttpClient()
        val request = OkHttpRequest(client)
        val map: HashMap<String, String> = hashMapOf("user_id" to "$user_id")
        val url = "https://park-ut.appspot.com/occupy?user_id=$user_id"
        val post = request.POST(
                url,
                map,
                object : Callback {
                    override fun onResponse(call: Call?, response: Response) {

                        //(activity as NavigationHost).navigateTo(LogoutFragment(), false)

                    }

                    override fun onFailure(call: Call?, e: IOException?) {
                        println("Request Failure.")
                    }
                })

        fun fetchComplete() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        fun runOnUiThread(function: () -> Unit) {

        }

        fun <T> AnkoAsyncContext<T>.activityUiThread(function: () -> Unit) {

        }

    }






private fun checkout()
{
    val args=arguments
    val user_id = args?.getString("position","position").toString()
    val client = OkHttpClient()
    val request = OkHttpRequest(client)
    val map: HashMap<String, String> = hashMapOf("user_id" to "$user_id")
    val url = "https://park-ut.appspot.com/clear?user_id=$user_id"
    val post = request.POST(
            url,
            map,
            object : Callback {
                override fun onResponse(call: Call?, response: Response) {

                    //(activity as NavigationHost).navigateTo(LogoutFragment(), false)

                }

                override fun onFailure(call: Call?, e: IOException?) {
                    println("Request Failure.")
                }
            })

    fun fetchComplete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun runOnUiThread(function: () -> Unit) {

    }

    fun <T> AnkoAsyncContext<T>.activityUiThread(function: () -> Unit) {

    }

}





}


