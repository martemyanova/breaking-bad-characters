package com.techtask.breakingbadcharacters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.techtask.breakingbadcharacters.common.di.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
