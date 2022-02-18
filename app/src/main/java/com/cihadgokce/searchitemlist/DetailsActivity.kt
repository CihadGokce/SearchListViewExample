package com.example.searchrecyclerviewexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cihadgokce.searchitemlist.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val ext = intent.extras

        binding.tvName.text = intent.extras!!.getString("satelliteItemName")!!

    //    binding.detailCountryText.text = intent.extras!!.getString("passselectedcountry")!!

    }
}
