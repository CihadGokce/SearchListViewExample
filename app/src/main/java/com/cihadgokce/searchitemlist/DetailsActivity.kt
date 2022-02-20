package com.cihadgokce.searchitemlist

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import com.cihadgokce.searchitemlist.core.BaseActivity
import com.cihadgokce.searchitemlist.core.extension.StringExtensions.convertDateFormat
import com.cihadgokce.searchitemlist.databinding.ActivityDetailsBinding
import com.cihadgokce.searchitemlist.model.PositionsList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : BaseActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityDetailsBinding
    private var positionData: PositionsList = PositionsList()

    lateinit var mainHandler: Handler
    private val updateTextTask = object : Runnable {
        override fun run() {
            updatePositionIndex()
            mainHandler.postDelayed(this, 3000)
        }
    }

    override fun onPause() {
        super.onPause()
        mainHandler.removeCallbacks(updateTextTask)
    }

    override fun onResume() {
        super.onResume()
        mainHandler.post(updateTextTask)
    }

    fun updatePositionIndex() {
        if (viewModel.index.value!! == 2) {
            viewModel.index.value = 0
        } else {
            viewModel.index.value = viewModel.index.value!! + 1
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val id = intent.extras!!.getInt("satelliteItemId")!!
        val name = intent.extras!!.getString("satelliteItemName")!!
        mainHandler = Handler(Looper.getMainLooper())
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.getSatelliteDetail(id)

        viewModel.index.observe(this, Observer {
            if (positionData.positions.isNotEmpty()) {
                binding.tvPosition.text =
                    HtmlCompat.fromHtml(
                        "<b>Last Position:</b>(${positionData.positions[it].posX},${positionData.positions[it].posY})",
                        HtmlCompat.FROM_HTML_MODE_COMPACT
                    )
            }
        })
        viewModel.satelliteDetail.observe(this, Observer {
            positionData = it.list
            binding.tvDate.text = it.detail.first_flight?.let { it1 -> convertDateFormat(it1) }
            binding.tvName.text = name
            binding.tvHeight.text = HtmlCompat.fromHtml(
                "<b>WhatHeight/Mass:</b>${it.detail.height}",
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )
            binding.tvCost.text = HtmlCompat.fromHtml(
                "<b>Cost:</b>${it.detail.cost_per_launch}",
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )
        }
        )
    }
}
