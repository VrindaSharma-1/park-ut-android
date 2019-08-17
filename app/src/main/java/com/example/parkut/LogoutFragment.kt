package com.example.parkut

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.logout_fragment.view.*

/**
 * Fragment representing the login screen for Shrine.
 */
class LogoutFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.logout_fragment, container, false)
        // Set an error if the password is less than 8 characters.
        view.button.setOnClickListener({

                // Navigate to the next Fragment.
                (activity as NavigationHost).navigateTo(LoginFragment(), false)

        })

        // Clear the error once more than 8 characters are typed.

        return view
    }

    // "isPasswordValid"  method goes here
    // Currently checks for 8 characters but we could perform
    // an actual validation with a remote service like the Web version below
    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 2
    }

    private fun isPasswordValidWeb(text: Editable?): Boolean {
        //CHECK AGAINST AUTHORIZATION OF SERVICE TO SEE IF CREDENTIALS ARE CORRECT

        return true

    }
}
