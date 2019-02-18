package com.eajy.materialdesigndemo.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import com.eajy.materialdesigndemo.R

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    // Keep track of the login task to ensure we can cancel it if requested.
    private var mAuthTask: UserLoginTask? = null

    private var mUserNameView: AutoCompleteTextView? = null
    private var input_user_name: TextInputLayout? = null
    private var input_password: TextInputLayout? = null
    private var mPasswordView: EditText? = null
    private var mProgressView: View? = null
    private var mLoginFormView: View? = null
    private var login_button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findView()
        initView()
    }

    private fun findView() {
        mLoginFormView = findViewById(R.id.form_login)
        mProgressView = findViewById(R.id.progress_login)
        mUserNameView = findViewById(R.id.tv_user_name)
        mPasswordView = findViewById(R.id.tv_password)
        input_user_name = findViewById(R.id.input_user_name)
        input_password = findViewById(R.id.input_password)
        login_button = findViewById(R.id.btn_login)
    }

    private fun initView() {

        mPasswordView!!.setOnEditorActionListener { v, id, event ->
            if (id == R.id.btn_login || id == EditorInfo.IME_NULL) {
                attemptLogin()
                true
            } else {
                false
            }
        }

        login_button!!.setOnClickListener(this)
        val forgot_password = findViewById<Button>(R.id.btn_forgot_password)
        forgot_password.setOnClickListener(this)
        val register = findViewById<Button>(R.id.btn_forgot_register)
        register.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login -> {
                attemptLogin()
            }

            R.id.btn_forgot_password -> {
                Snackbar.make(v, getString(R.string.snackbar_forgot_password), Snackbar.LENGTH_LONG)
                        .setAction("^_^", null).show()
            }

            R.id.btn_forgot_register -> {
                Snackbar.make(v, getString(R.string.snackbar_register), Snackbar.LENGTH_LONG)
                        .setAction("^_^", null).show()
            }
        }
    }

    /**
     * Attempts to sign in or register the account specified by the login form. If there are form errors
     * (invalid email, missing fields, etc.), the errors are presented and no actual login attempt is made.
     */
    private fun attemptLogin() {
        if (mAuthTask != null) {
            return
        }

        // Reset errors.
        input_user_name!!.error = null
        input_password!!.error = null

        val userName = mUserNameView!!.text.toString()
        val password = mPasswordView!!.text.toString()

        var cancel = false
        var focusView: View? = null

        if (TextUtils.isEmpty(userName)) {
            input_user_name!!.error = getString(R.string.error_no_name)
            focusView = mUserNameView
            cancel = true
        } else if (!isPhoneValid(userName) && !isEmailValid(userName)) {
            input_user_name!!.error = getString(R.string.error_invalid_name)
            focusView = mUserNameView
            cancel = true
        } else if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            input_password!!.error = getString(R.string.error_invalid_password)
            focusView = mPasswordView
            cancel = true
        } else if ((isPhoneValid(userName) || isEmailValid(userName)) && TextUtils.isEmpty(password)) {
            input_password!!.error = getString(R.string.error_no_password)
            focusView = mPasswordView
            cancel = true
        }

        if (cancel) {
            focusView!!.requestFocus()
        } else {
            hideInput(login_button)
            showProgress(true)
            mAuthTask = UserLoginTask(userName, password)
            mAuthTask!!.execute(null)
        }
    }

    private fun isPhoneValid(userName: String) =
            userName.length in 7..12 && userName.matches("[0-9]*".toRegex())

    private fun isEmailValid(userName: String) = '@' in userName

    private fun isPasswordValid(password: String) = password.length in 4..20

    private fun hideInput(view: View?) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

    private fun showProgress(show: Boolean) {
        val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)

        mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
        mLoginFormView!!.animate()
                .setDuration(shortAnimTime.toLong())
                .alpha(if (show) 0f else 1f)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
                    }
                })

        mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
        mProgressView!!.animate()
                .setDuration(shortAnimTime.toLong())
                .alpha(if (show) 1f else 0f)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
                    }
                })
    }

    private inner class UserLoginTask(
            private val mPhone: String,
            private val mPassword: String)
        : AsyncTask<Void, Void, Boolean>() {

        override fun doInBackground(vararg params: Void): Boolean? {
            // TODO: attempt authentication against a network service.

            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
                return false
            }

            for (credential in DUMMY_CREDENTIALS) {
                val pieces = credential.split(":".toRegex())
                        .dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                if (pieces[0] == mPhone) {
                    // Account exists, return true if the password matches.
                    return pieces[1] == mPassword
                }
            }

            // TODO: register the new account here.
            return true
        }

        override fun onPostExecute(success: Boolean?) {
            mAuthTask = null
            showProgress(false)

            if (success!!) {
                finish()
            } else {
                input_password!!.error = getString(R.string.error_incorrect_password)
                mPasswordView!!.requestFocus()
            }
        }

        override fun onCancelled() {
            mAuthTask = null
            showProgress(false)
        }
    }

    companion object {

        /**
         * A dummy authentication store containing known user names and passwords.
         * TODO: remove after connecting to a real authentication system.
         */
        private val DUMMY_CREDENTIALS = arrayOf("foo@example.com:hello", "bar@example.com:world")
    }
}
