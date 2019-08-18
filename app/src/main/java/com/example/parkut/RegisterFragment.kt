package com.example.parkut

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.register_fragment.*
import kotlinx.android.synthetic.main.register_fragment.view.*
import kotlinx.android.synthetic.main.register_fragment.view.email_register_text
import kotlinx.android.synthetic.main.register_fragment.view.password_register
import org.jetbrains.anko.AnkoAsyncContext
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.io.IOException
import android.R.attr.data
import okhttp3.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    //private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.register_fragment, container, false)
        view.add_user.setOnClickListener{
doAsync { fetchregister() }
            // Navigate to the next Fragment.
           // (activity as NavigationHost).navigateTo(LoginFragment(), false)

        }

        view.cancel_button_r.setOnClickListener{
            // Navigate to the Registration Fragment.
            (activity as NavigationHost).navigateTo(RegisterFragment(), false)

        }
        view.back_to_login.setOnClickListener{
            // Navigate to the Registration Fragment.
            (activity as NavigationHost).navigateTo(LoginFragment(), false)

        }
        return view
    }

    private fun fetchregister()
    {
        val email = email_register_text.text
        val password = password_register_text.text
        val password_conf = password_confirm_text_register.text
        val name = name_register_text.text
        val client = OkHttpClient()
        val request = OkHttpRequest(client)
       // val map: HashMap<String, String> = hashMapOf("email" to "$email", "password" to "$password")
       //val json = mutableMapOf("name" to "$name", "email" to "$email","password" to "$password", "passwordDuplicate" to "$password_conf")
        val url = "https://park-ut.appspot.com/signup"
        val str = """
{"name":"$name", "email":"$email", "password":"$password", "passwordDuplicate":"$password_conf"}
""".trimIndent()
//val str =JSONObject(json)
        val post = request.POSTR(url, str, object : Callback {
                    override fun onResponse(call: Call?, response: Response) {
                        val responseData = response.body()?.string()
                        val jsonobj = JSONObject(responseData)

                        if(jsonobj.get("result")==true)
                        {(activity as NavigationHost).navigateTo(LoginFragment(), false)}

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

