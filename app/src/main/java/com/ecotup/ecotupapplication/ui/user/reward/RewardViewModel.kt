package com.ecotup.ecotupapplication.ui.user.reward

import androidx.lifecycle.ViewModel
import com.ecotup.ecotupapplication.data.repository.EcotupRepository

class RewardViewModel(private val repository: EcotupRepository) : ViewModel() {

    fun getReward() = repository.getReward()
}