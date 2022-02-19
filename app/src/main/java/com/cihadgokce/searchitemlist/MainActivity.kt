package com.cihadgokce.searchitemlist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cihadgokce.searchitemlist.core.BaseActivity
import com.cihadgokce.searchitemlist.core.adapter.SatelliteAdapter
import com.cihadgokce.searchitemlist.core.extension.StringExtensions
import com.cihadgokce.searchitemlist.databinding.ActivityMainBinding
import com.cihadgokce.searchitemlist.model.SatelliteDetail
import com.cihadgokce.searchitemlist.model.SatelliteListItem
import com.google.gson.annotations.SerializedName
import dagger.hilt.android.lifecycle.HiltViewModel
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration


class MainActivity: BaseActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: SatelliteAdapter
    lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        val satellitesList = ArrayList<SatelliteListItem>()
        viewModel.getSatelliteList()

        // Bu RecyclerView'ın kullanacağı LayoutManager'ı ayarla.
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)
        recyclerview.apply {
            var itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(getDrawable(R.drawable.divider)!!)
            addItemDecoration(itemDecoration)
        }
        viewModel.satelliteList.observe(this, Observer {
            adapter = SatelliteAdapter(this,it)
            recyclerview.adapter = adapter
        })


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })


    }



}