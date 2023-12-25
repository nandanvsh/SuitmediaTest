package com.example.suitmediatest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.example.suitmediatest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputPalindrome.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                binding.checkButton.isEnabled = !s.isNullOrBlank()
            }
        })

        binding.checkButton.setOnClickListener {
            val inputText = binding.inputPalindrome.text.toString()

            if (isPalindrome(inputText)) {
                showMessage("isPalindrome")
            } else {
                showMessage("notPalindrome")
            }
        }

        binding.nextButton.setOnClickListener{
            val username = binding.inputName.text.toString().trim()
            if (username.isNotEmpty()){
                val intent = Intent(this@MainActivity, SecondPageActivity::class.java)
                intent.putExtra("USERNAME", username)
                startActivity(intent)
            }else{
                binding.inputName.error = "Username is required!"
            }
        }
    }
    private fun isPalindrome(input: String): Boolean {
        val cleanInput = input.replace("\\s".toRegex(), "") // Remove spaces
        return cleanInput == cleanInput.reversed()
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}