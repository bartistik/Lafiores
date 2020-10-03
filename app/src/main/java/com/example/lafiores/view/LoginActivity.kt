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
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailTextInputLayout: TextInputLayout
    private lateinit var passwordTextInputLayout: TextInputLayout
    private lateinit var confirmPasswordTextInputLayout: TextInputLayout
    private var loginMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        emailTextInputLayout = binding.emailTextInputLayout
        passwordTextInputLayout = binding.passwordTextInputLayout
        confirmPasswordTextInputLayout = binding.confirmPasswordTextInputLayout

        auth = Firebase.auth
//        val currentUser = auth.currentUser

        binding.switchAuthRegister.setOnClickListener(View.OnClickListener {

            when (loginMode) {
                true -> {
                    binding.switchAuthRegister.text = resources.getText(R.string.switch_to_auth)
                    binding.authButton.text = resources.getText(R.string.register_button)
                    binding.confirmPasswordTextInputLayout.visibility = View.VISIBLE
                    binding.authTextView.text = resources.getText(R.string.switch_auth_register)
                    loginMode = false
                }
                false -> {
                    binding.switchAuthRegister.text = resources.getText(R.string.switch_auth_register)
                    binding.authButton.text = resources.getText(R.string.login_button)
                    binding.confirmPasswordTextInputLayout.visibility = View.GONE
                    binding.authTextView.text = resources.getText(R.string.welcome_auth)
                    loginMode = true
                }
            }
        })

        binding.authButton.setOnClickListener(View.OnClickListener {

            //      проверяем интернет-соединение
            if (!Constant.checkNetworkConnection(this)) {
                Snackbar.make(findViewById(R.id.emailEditText), R.string.error_network_connection, Snackbar.LENGTH_SHORT)
                        .show()
                return@OnClickListener
            }

            val emailUser = binding.emailEditText.text.toString().trim()
            val passwordUser = binding.passwordEditText.text.toString().trim()
            val confirmPassword = binding.confirmPasswordEditText.text.toString().trim()

            when (loginMode) {
                true -> {
                    if (validateEmail(emailUser) and validatePassword(passwordUser)) {
                        loginSingUpUser(emailUser, passwordUser)
                    }
                }
                false -> {
                    if (validateEmail(emailUser) and validatePassword(passwordUser) and validateConfirmPassword(passwordUser, confirmPassword)) {
                        registrationUser(emailUser, passwordUser)
                    }
                }
            }
        })
    }

    //Создание юзера
    private fun registrationUser(emailUser: String, passwordUser: String) {
        auth.createUserWithEmailAndPassword(emailUser, passwordUser)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("LOGIN", "createUserWithEmail:success")
                        val user = auth.currentUser

                        Log.d("Load", "" + user?.uid.toString())
                        createUser(user, emailUser)
                        (startActivity(Intent(this, ListProductActivity::class.java)))
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("LOGIN", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, R.string.create_user_error,
                                Toast.LENGTH_SHORT).show()
                    }
                }

    }

    //Авторизация
    private fun loginSingUpUser(emailUser: String, passwordUser: String) {
        auth.signInWithEmailAndPassword(emailUser, passwordUser)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
//                        val user = auth.currentUser
                        (startActivity(Intent(this, ListProductActivity::class.java)))
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Email и пароль не совпадают",
                                Toast.LENGTH_SHORT).show()
                        //                        updateUI(null)
                    }
                }
    }

    private fun validateEmail(emailUser: String): Boolean {
        if (emailUser.isEmpty()) {
            emailTextInputLayout.error = resources.getText(R.string.email_edit_text_empty_error)
            return false
        } else if (!emailUser.contains("@", false) ||
                !emailUser.contains(".", false) ||
                emailUser.endsWith(".")) {
            emailTextInputLayout.error = resources.getText(R.string.email_edit_text_error)
            return false
        } else if (emailUser.length < 5) {
            emailTextInputLayout.error = resources.getText(R.string.email_edit_text_short_error)
            return false
        } else if (emailUser.length > 30) {
            emailTextInputLayout.error = resources.getText(R.string.email_edit_text_long_error)
            return false
        } else {
            emailTextInputLayout.isErrorEnabled = false
            return true
        }
    }

    private fun validatePassword(passwordUser: String): Boolean {
        when {
            passwordUser.isEmpty() -> {
                passwordTextInputLayout.error = resources.getText(R.string.password_edit_text_empty_error)
                return false
            }
            passwordUser.length < 8 -> {
                passwordTextInputLayout.error = resources.getText(R.string.password_edit_text_short_error)
                return false
            }
            passwordUser.length > 20 -> {
                passwordTextInputLayout.error = resources.getText(R.string.password_edit_text_long_error)
                return false
            }
            else -> {
                passwordTextInputLayout.isErrorEnabled = false
                return true
            }
        }
    }

    private fun validateConfirmPassword(passwordUser: String, confirmPassword: String): Boolean {
        return when {
            confirmPassword.isEmpty() -> {
                confirmPasswordTextInputLayout.error = resources.getText(R.string.confirm_password_edit_text_empty_error)
                false
            }
            passwordUser != confirmPassword -> {
                confirmPasswordTextInputLayout.error = resources.getText(R.string.password_edit_text_not_match_error)
                false
            }
            else -> true
        }
    }

    private fun createUser(user: FirebaseUser?, emailUser: String) {
        val database = Firebase.database
        val myRef = database.getReference("users")
        myRef.child(user?.uid.toString()).child("email").setValue(emailUser)
        myRef.child(user?.uid.toString()).child("id").setValue(user?.uid.toString())
    }
}

