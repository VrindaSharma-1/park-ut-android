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
import android.content.Context
import android.text.Editable
import android.widget.Toast
import okhttp3.*



class RegisterFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.register_fragment, container, false)
        view.add_user.setOnClickListener {
            if (!isPasswordValid(password_register_text.text!!,password_confirm_text_register.text!!))
            {
                password_confirm_text_register.error = getString(R.string.error_conf)
                context?.let { it1 -> "Please enter correct password".toast(it1) }
            }
            else
            {
                doAsync { fetchregister() }
                context?.let { it1 -> "Registration Successful!".toast(it1) }
               (activity as NavigationHost).navigateTo(LoginFragment(), false)
            }
        }

        view.cancel_button_r.setOnClickListener{
            // Navigate to the Registration Fragment.
            (activity as NavigationHost).navigateTo(RegisterFragment(), false)

        }
        view.back_to_login.setOnClickListener{
            // Navigate to the Login Fragment.
            (activity as NavigationHost).navigateTo(LoginFragment(), false)

        }
        return view
    }
    private fun isPasswordValid(text: Editable?,text1: Editable?): Boolean {
        return text != null && text.isNotEmpty() && text.toString().equals(text1.toString())
    }

    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_LONG): Toast {
        return Toast.makeText(context, this.toString(), duration).apply { show() }
    }

    private fun fetchregister()
    {
        val email = email_register_text.text
        val password = password_register_text.text
        val password_conf = password_confirm_text_register.text
        val name = name_register_text.text
        val client = OkHttpClient()

        val url = "https://park-ut.appspot.com/signup"
        val json = """
{"name":"$name", "email":"$email", "password":"$password", "passwordDuplicate":"$password_conf"}
""".trimIndent()

        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
        val request = Request.Builder()
                .url(url)
                .post(body)
                .build()
        val response = client.newCall(request).execute()
        val jsonString = response?.body()?.string()

        val result = JSONObject(jsonString)



    }
}

