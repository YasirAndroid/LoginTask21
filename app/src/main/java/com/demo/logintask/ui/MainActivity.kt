package com.demo.logintask.ui

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.demo.logintask.R
import com.demo.logintask.databinding.ActivityMainBinding
import com.demo.logintask.model.LoginModel
import com.demo.logintask.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var signInCheck = MutableLiveData<Boolean?>()
    var flag = true
    lateinit var loggedInIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        createRegisterObserver(viewModel)
        createLoginObserver(viewModel)

        viewModel.loginComplete.observeForever {
            if (it == true) {
                loggedInIntent = Intent(this, HomeActivity::class.java)
                startActivity(loggedInIntent)
            }
        }

        binding.btnRegister.setOnClickListener {
            viewModel.registerUser(
                binding.etName.text.toString(),
                binding.etEmail.text.toString(),
                binding.etMobile.text.toString(),
                binding.etPassword.text.toString(),
                binding.etConPassword.text.toString()
            )
        }

        binding.btnLogin.setOnClickListener {
            Log.d("data", binding.etEmailLogin.text.toString())
            viewModel.login(
                binding.etEmailLogin.text.toString(),
                binding.etPassLogin.text.toString()
            )
        }

        binding.btnChange.setOnClickListener {
            binding.btnChange.text =
                if (flag) getString(R.string.got_to_register) else getString(R.string.go_to_login)
            binding.llSignup.visibility = if (flag) View.GONE else View.VISIBLE
            binding.llLogin.visibility = if (flag) View.VISIBLE else View.GONE
            flag = !flag
        }

    }

    private fun createRegisterObserver(viewModel: MainViewModel) {
        viewModel.registerData.observeForever {
            if (it.token != null) {
                signInCheck.value = true
                loggedInIntent = Intent(this, HomeActivity::class.java)
                    .putExtra("name", it.user?.name)
                    .putExtra("name", it.user?.email)
            } else if (it.errors != null) {
                if (it.errors.email != null) {
                    binding.tlEmail.error = it.errors.email[0]
                }
                if (it.errors.mobile != null) {
                    binding.tlMobile.error = it.errors.mobile[0]
                }
                if (it.errors.name != null) {
                    binding.tlName.error = it.errors.name[0]
                }
                if (it.errors.password != null) {
                    binding.tlPassword.error = it.errors.password[0]
                }
            }
        }
    }

    private fun createLoginObserver(viewModel: MainViewModel) {
        viewModel.loginData.observeForever {
            if (!it.token.isNullOrEmpty()) {
                signInCheck.value = true
                loggedInIntent = Intent(this, HomeActivity::class.java)
                    .putExtra("name", it.user?.name)
                    .putExtra("name", it.user?.email)
            } else if (it.errors != null) {
                if (it.errors.email != null) {
                    binding.tlEmailLogin.error = it.errors.email[0]
                }
                if (it.errors.password != null) {
                    binding.tlPassLogin.error = it.errors.password[0]
                }
                if (it.error != null) {
                    binding.tlPassLogin.error = it.error
                }
            }
        }
    }
}