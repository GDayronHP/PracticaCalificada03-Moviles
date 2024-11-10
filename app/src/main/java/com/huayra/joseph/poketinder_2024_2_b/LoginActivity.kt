package com.huayra.joseph.poketinder_2024_2_b

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.huayra.joseph.poketinder_2024_2_b.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginViewModel = LoginViewModel(this)
        observeViewModel()
        setListeners()
    }

    private fun observeViewModel() {
        loginViewModel.inputsError.observe(this) {
            if (it == true) {
                Toast.makeText(this, "Por favor, ingrese todos los datos", Toast.LENGTH_SHORT).show()
            }
        }

        loginViewModel.emailError.observe(this) {
            if (it == true) {
                Toast.makeText(this, "El correo electrónico no es válido", Toast.LENGTH_SHORT).show()
            }
        }

        loginViewModel.authError.observe(this) {
            if (it == true) {
                Toast.makeText(this, "Error: usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        loginViewModel.registerError.observe(this) {
            if (it == true) {
                Toast.makeText(this, "Usuario no registrado", Toast.LENGTH_SHORT).show()
            }
        }

        loginViewModel.loginSuccess.observe(this) {
            if (it == true) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun setListeners() {
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            loginViewModel.validateInputs(email, password)
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
