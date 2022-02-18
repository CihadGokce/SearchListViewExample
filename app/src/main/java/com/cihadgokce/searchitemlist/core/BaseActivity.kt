package com.cihadgokce.searchitemlist.core

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

// Her bir activity kod tekrarı önlemek için tek bir yerden yazılıyor. Yönetimi kolaylaştırıyor.

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }
}