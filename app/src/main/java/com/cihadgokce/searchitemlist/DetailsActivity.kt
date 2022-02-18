package com.example.searchrecyclerviewexample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.cihadgokce.searchitemlist.MainViewModel
import com.cihadgokce.searchitemlist.core.adapter.SatelliteAdapter
import com.cihadgokce.searchitemlist.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val id = intent.extras!!.getInt("satelliteItemId")!!

        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.getSatelliteDetail(id)
        viewModel.satelliteDetail.observe(this, Observer {
            binding.tvCost.text = it.detail.costPerLaunch.toString()
            binding.tvHeight.text = it.detail.firstFlight.toString()
        }
        )

        //    binding.detailCountryText.text = intent.extras!!.getString("passselectedcountry")!!

    }
}
