package com.example.parkut


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.listing_fragment.*
import kotlinx.android.synthetic.main.listing_fragment.view.*
import kotlinx.android.synthetic.main.user.*
import kotlinx.android.synthetic.main.user.view.*
import okhttp3.*
import org.jetbrains.anko.AnkoAsyncContext
import org.jetbrains.anko.doAsync
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.util.*

class ListingFragment : Fragment() {

    private var userlist: ListView? = null
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

        val view =  inflater.inflate(R.layout.listing_fragment, container, false)
        userlist = view.userlist

        val args=arguments
        val user_id = args?.getString("position","position").toString()

        doAsync {
            fetchdetails(user_id)
        }

        view.reserve.setOnClickListener{
           doAsync {  reserve() }
            val g_id= garage_id.text
                    context?.let { it1 -> "Reserved spot at Garage - $g_id ".toast(it1) }
        }
        view.occupy.setOnClickListener{
            doAsync {  occupy()}
            val g_id= garage_id.text
            context?.let { it1 -> "Occupied spot at Garage - $g_id ".toast(it1) }
        }

        view.logout.setOnClickListener{

            context?.let { it1 -> "Logged Out!".toast(it1) }
            (activity as NavigationHost).navigateTo(LoginFragment(), false)
        }
        view.checkout.setOnClickListener{
            doAsync {  checkout() }
            context?.let { it1 -> "Checked Out! See You Again!!".toast(it1) }
        }
        view.done.setOnClickListener {
            userlist = view.userlist

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
               // usersModel.setSpotss(dataobj.getString("spots"))


                userModelArrayList.add(usersModel)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return userModelArrayList
    }

private fun fetchdetails(user_id:String) {
    var client = OkHttpClient()
    var request = OkHttpRequest(client)
    val URL1 = "https://park-ut.appspot.com/details?user_id=$user_id"

    request.GET(URL1, object : Callback {

        override fun onResponse(call: Call?, response: Response) {
            val responseData = response.body()?.string()
            val status = response.code()
            var json = JSONArray(responseData)

            userModelArrayList = getInfo(json.toString())
        }
        override fun onFailure(call: Call?, e: IOException?) {
            println("Request Failure.")
        }

    })
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
                    }
                    override fun onFailure(call: Call?, e: IOException?) {
                        println("Request Failure.")
                    }
                })

    }

    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_LONG): Toast {
        return Toast.makeText(context, this.toString(), duration).apply { show() }
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
                }
                override fun onFailure(call: Call?, e: IOException?) {
                    println("Request Failure.")
                }
            })
}
}


