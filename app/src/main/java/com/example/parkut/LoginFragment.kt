package com.example.parkut

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.view.*
import okhttp3.*
import okhttp3.OkHttpClient
import okhttp3.Response
import org.jetbrains.anko.AnkoAsyncContext
import org.jetbrains.anko.doAsync

import org.json.JSONObject
import java.io.IOException

var editText: EditText? = null
class LoginFragment() : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.login_fragment, container, false)
        // Set an error if the password is less than 8 characters.
        view.login_button.setOnClickListener({
            if (!isPasswordValid(password_edit_text.text!!)) {
                password_text_input.error = getString(R.string.error_password)
            }
            else
            {
                doAsync {
                    fetchpost()
//                    val jsonarray = JSONArray(gotresponse)
//                    activityUiThread {
//                        //iterate through the returned array of JSON objects
//                        // and look for candiadate whose name we requested
//                        for (i in 0..(jsonarray.length() - 1)) {
//                            val user = jsonarray.getJSONObject(i)
//                            if(user.get("email").toString().equals("admin")) {
//                                (activity as NavigationHost).navigateTo(ListingFragment(), false)
//                            }
//                        }
//                    }
                }
            }
           // (activity as NavigationHost).navigateTo(ListingFragment(), false)

        })
        // Clear the error once more than 8 characters are typed.
        view.password_edit_text.setOnKeyListener({ _, _, _ ->
            if (isPasswordValid(password_edit_text.text!!)) {
                // Clear the error.
                password_text_input.error = null
            }
            false
        })

        view.register.setOnClickListener({
            // Navigate to the Registration Fragment.
            (activity as NavigationHost).navigateTo(RegisterFragment(), false)

        })

        view.cancel_button.setOnClickListener({
            // Navigate to the Registration Fragment.
            (activity as NavigationHost).navigateTo(LoginFragment(), false)

        })


        return view
    }

    private fun activityUiThread(function: () -> Unit) {

    }

    // "isPasswordValid"  method goes here
    // Currently checks for 8 characters but we could perform
    // an actual validation with a remote service like the Web version below
    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 1
    }

    private fun isPasswordValidWeb(text: Editable?): Boolean {
        //CHECK AGAINST AUTHORIZATION OF SERVICE TO SEE IF CREDENTIALS ARE CORRECT

        return true

    }

    private fun fetchpost()
    {
        val client = OkHttpClient()
        val request = OkHttpRequest(client)
        val map: HashMap<String, String> = hashMapOf("email" to "admin", "password" to "p")
        val url = "https://park-ut.appspot.com/auth?email=admin&password=p"
        val post = request.POST(
                url,
                map,
                object : Callback {
                    override fun onResponse(call: Call?, response: Response) {
                        val responseData = response.body()?.string()
                        val jsonarray = JSONObject(responseData)
                        if(jsonarray.get("email")=="admin")
                        {(activity as NavigationHost).navigateTo(ListingFragment(), false)}

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

    private fun runOnUiThread(function: () -> Unit) {

    }
}

private fun LoginFragment.fetchComplete() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}




        
