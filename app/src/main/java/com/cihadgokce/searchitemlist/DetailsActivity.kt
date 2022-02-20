package com.example.searchrecyclerviewexample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import com.cihadgokce.searchitemlist.MainViewModel
import com.cihadgokce.searchitemlist.core.BaseActivity
import com.cihadgokce.searchitemlist.core.extension.StringExtensions.convertDateFormat
import com.cihadgokce.searchitemlist.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : BaseActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val id = intent.extras!!.getInt("satelliteItemId")!!
        val name = intent.extras!!.getString("satelliteItemName")!!

        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.getSatelliteDetail(id)

        viewModel.satelliteDetail.observe(this, Observer {
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
            binding.tvPosition.text =
                HtmlCompat.fromHtml(
                    "<b>Last Position:</b>($id,$id)",
                    HtmlCompat.FROM_HTML_MODE_COMPACT
                )

        }
        )


    }
}
