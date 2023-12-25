package com.example.suitmediatest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.suitmediatest.databinding.ActivityMainBinding
import com.example.suitmediatest.databinding.ActivitySecondPageBinding

class SecondPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondPageBinding

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK){
                binding.textView.text =  it.data?.getStringExtra(ThirdActivity.TAG_PAGE_THIRD)
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondPageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val username = intent.getStringExtra("USERNAME")
        val selectedUser = intent.getStringExtra("EXTRA_SELECTED_USER")


        binding.shwUsername.text = "$username!"
        binding.textView.text = "$selectedUser"
        binding.button.setOnClickListener {
            val getListIntent = Intent(this@SecondPageActivity, ThirdActivity::class.java)
            startActivity(getListIntent)
        }
    }
}