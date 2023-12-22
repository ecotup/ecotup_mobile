package com.ecotup.ecotupapplication.ui.user.finishtransaction

import androidx.lifecycle.ViewModel
import com.ecotup.ecotupapplication.data.repository.EcotupRepository

class FinishTransactionUserViewModel(private val repository: EcotupRepository) : ViewModel() {
    suspend fun getDetailTransaksi(idTransaksi: String) =
        repository.getDetailTransactionFinish(idTransaksi)

    suspend fun updateRating(idDriver: String, rating: Int) =
        repository.updateRating(idDriver, rating)
}