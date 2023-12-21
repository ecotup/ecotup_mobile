package com.ecotup.ecotupapplication.ui.maps.loadingfinddriver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ecotup.ecotupapplication.databinding.ActivityLoadingFindDriverBinding
import com.ecotup.ecotupapplication.util.IntentToMapsOrder
import com.ecotup.ecotupapplication.util.SPLASH_SCREEN_DURATION
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingFindDriver : AppCompatActivity() {

    private lateinit var binding: ActivityLoadingFindDriverBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingFindDriverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            delay(SPLASH_SCREEN_DURATION)
            IntentToMapsOrder(this@LoadingFindDriver)
        }

    }
}