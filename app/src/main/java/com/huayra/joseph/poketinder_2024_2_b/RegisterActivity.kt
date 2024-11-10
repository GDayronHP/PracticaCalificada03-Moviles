package com.huayra.joseph.poketinder_2024_2_b

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.huayra.joseph.poketinder_2024_2_b.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModel()
        setListeners()
    }

    private fun observeViewModel() {
        registerViewModel.inputsError.observe(this) {
            if (it == true) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        registerViewModel.emailError.observe(this) {
            if (it == true) {
                Toast.makeText(this, "El correo electrónico no es válido", Toast.LENGTH_SHORT).show()
            }
        }

        registerViewModel.passwordLengthError.observe(this) {
            if (it == true) {
                Toast.makeText(this, "La contraseña debe tener más de 8 caracteres", Toast.LENGTH_SHORT).show()
            }
        }

        registerViewModel.passwordsMismatchError.observe(this) {
            if (it == true) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }

        registerViewModel.userExistsError.observe(this) {
            if (it == true) {
                Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
            }
        }

        registerViewModel.registrationSuccess.observe(this) {
            if (it == true) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun setListeners() {
        binding.btnRegister.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val confirmPassword = binding.edtPassword2.text.toString()

            registerViewModel.registerUser(
                email = email,
                password = password,
                confirmPassword = confirmPassword
            )
        }

        binding.btnToLogin.setOnClickListener {
            val intentToLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentToLogin)
        }

        binding.btnBackClose.setOnClickListener {
            finish()
        }
    }
}
