package com.ecotup.ecotupapplication.ui.maps.loadingordernow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ecotup.ecotupapplication.databinding.ActivityLoadingOrderNowBinding
import com.ecotup.ecotupapplication.util.IntentToMapsRun
import com.ecotup.ecotupapplication.util.SPLASH_SCREEN_DURATION
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingOrderNow : AppCompatActivity() {
    private lateinit var binding: ActivityLoadingOrderNowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingOrderNowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            delay(SPLASH_SCREEN_DURATION)
            IntentToMapsRun(this@LoadingOrderNow)
        }

    }
}