package com.example.lafiores.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.lafiores.R
import com.example.lafiores.databinding.ActivityLoginBinding
import com.example.lafiores.service.Constant
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailTextInputLayout: TextInputLayout
    private lateinit var passwordTextInputLayout: TextInputLayout
    private lateinit var confrirmPasswordTextInputLayout: TextInputLayout
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private var loginMode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        emailTextInputLayout = binding.emailTextInputLayout
        passwordTextInputLayout = binding.passwordTextInputLayout
        confrirmPasswordTextInputLayout = binding.confirmPasswordTextInputLayout
        val database = Firebase.database
        val myRef = database.getReference("users")

        auth = Firebase.auth
        val currentUser = auth.currentUser

        binding.switchAuthRegister.setOnClickListener(View.OnClickListener {

            when (loginMode) {
                true -> {
                    binding.switchAuthRegister.text = resources.getText(R.string.switch_to_auth)
                    binding.authButton.setText(resources.getText(R.string.register_button))
                    binding.confirmPasswordTextInputLayout.visibility = View.VISIBLE
                    binding.authTextView.text = resources.getText(R.string.switch_auth_register)
                    loginMode = false

                }
                false -> {
                    binding.switchAuthRegister.text = resources.getText(R.string.switch_auth_register)
                    binding.authButton.setText(resources.getText(R.string.login_button))
                    binding.confirmPasswordTextInputLayout.visibility = View.GONE
                    binding.authTextView.text = resources.getText(R.string.welcome_auth)
                    loginMode = true
                }
            }
        })

        binding.authButton.setOnClickListener(View.OnClickListener {

            //      проверяем интернет-соединение
            if (!Constant.checkNetworkConnection(this)) {
                Snackbar.make(findViewById(R.id.emailEditText), R.string.error_network_connection, Snackbar.LENGTH_LONG)
                        .show()
                return@OnClickListener
            }

            val emailUser: String = binding.emailEditText.text.toString().trim()
            val passwordUser: String = binding.passwordEditText.text.toString().trim()
            val confirmPassword: String = binding.confirmPasswordEditText.text.toString().trim()

            if (loginMode) {
                //Авторизация
                validateEmail(emailUser)
                validatePassword(passwordUser)
                if (validateEmail(emailUser) || validatePassword(passwordUser))

                    auth.signInWithEmailAndPassword(emailUser, passwordUser)
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    val user = auth.currentUser
                                    (startActivity(Intent(this, ListProductActivity::class.java)))
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(baseContext, "Email и пароль не совпадают",
                                            Toast.LENGTH_SHORT).show()
//                        updateUI(null)
                                }
                            }

            } else {
//            Создание юзера
                validateConfirmPassword(passwordUser, confirmPassword)
                validateEmail(emailUser)
                validatePassword(passwordUser)
                if (validateEmail(emailUser) &&
                        validateConfirmPassword(passwordUser, confirmPassword) &&
                        validatePassword(passwordUser)) {
                    Log.d("Login", "YES")
                    auth.createUserWithEmailAndPassword(emailUser, passwordUser)
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("LOGIN", "createUserWithEmail:success")
                                    val user = auth.currentUser

                                    Log.d("Load", "" + user?.uid.toString())

                                    myRef.child(user?.uid.toString()).child("email").setValue(emailUser)
                                    myRef.child(user?.uid.toString()).child("id").setValue(user?.uid.toString())
                                    (startActivity(Intent(this, ListProductActivity::class.java)))
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("LOGIN", "createUserWithEmail:failure", task.exception)
                                    Toast.makeText(baseContext, R.string.create_user_error,
                                            Toast.LENGTH_SHORT).show()
                                }
                            }
                }
            }
        })
    }

    private fun validateEmail(emailUser: String): Boolean {

        var validateEmail: Boolean = true
        emailTextInputLayout.isErrorEnabled = false

        if (emailUser.isEmpty()) {
            emailTextInputLayout.error = resources.getText(R.string.email_edit_text_empty_error)
            validateEmail = false
        } else if (!emailUser.contains("@", false) || !emailUser.contains(".", false)) {
            emailTextInputLayout.error = resources.getText(R.string.email_edit_text_error)
            validateEmail = false
        } else if (emailUser.length < 5) {
            emailTextInputLayout.error = resources.getText(R.string.email_edit_text_short_error)
            validateEmail = false
        } else if (emailUser.length > 30) {
            emailTextInputLayout.error = resources.getText(R.string.email_edit_text_long_error)
            validateEmail = false
        }
        return validateEmail
    }

    private fun validatePassword(passwordUser: String): Boolean {

        var validatePassword: Boolean = true
        passwordTextInputLayout.isErrorEnabled = false

        if (passwordUser.isEmpty()) {
            passwordTextInputLayout.error = resources.getText(R.string.password_edit_text_empty_error)
            validatePassword = false
        } else if (passwordUser.length < 8) {
            passwordTextInputLayout.error = resources.getText(R.string.password_edit_text_short_error)
            validatePassword = false
        } else if (passwordUser.length > 20) {
            passwordTextInputLayout.error = resources.getText(R.string.password_edit_text_long_error)
            validatePassword = false
        }
        return validatePassword
    }

    private fun validateConfirmPassword(passwordUser: String, confirmPassword: String): Boolean {

        var validateEmail: Boolean = true
        passwordTextInputLayout.isErrorEnabled = false
        confrirmPasswordTextInputLayout.isErrorEnabled = false
        if (passwordUser != confirmPassword) {
            passwordTextInputLayout.error = resources.getText(R.string.password_edit_text_not_match_error)
            confrirmPasswordTextInputLayout.error = resources.getText(R.string.password_edit_text_not_match_error)
            validateEmail = false
        }
        return validateEmail
    }
}