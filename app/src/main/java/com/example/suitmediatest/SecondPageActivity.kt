package com.example.suitmediatest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.suitmediatest.databinding.ActivityMainBinding
import com.example.suitmediatest.databinding.ActivitySecondPageBinding

class SecondPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondPageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val username = intent.getStringExtra("USERNAME")

        binding.shwUsername.text = "$username!"
        binding.button.setOnClickListener {
            val getListIntent = Intent(this@SecondPageActivity, ThirdActivity::class.java)
            startActivity(getListIntent)
        }
    }
}