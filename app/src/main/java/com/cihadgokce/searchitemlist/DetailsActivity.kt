package com.example.searchrecyclerviewexample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.cihadgokce.searchitemlist.MainViewModel
import com.cihadgokce.searchitemlist.R
import com.cihadgokce.searchitemlist.core.adapter.SatelliteAdapter
import com.cihadgokce.searchitemlist.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notify

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

            var col1 = getString(R.string.satellite_detail_height_mass,it.detail.height.toString())
            var col2 = getString(R.string.satellite_detail_cost,it.detail.costPerLaunch.toString())
            var col3 = getString(R.string.satellite_detail_last_position,it.position.positions[0].posX,it.position.positions[0].posY)

                binding.tvHeight.setText(it.detail.height.toString())
                binding.tvCost.text = col2
                binding.tvPosition.text = col3


            }
        )


    }
}
