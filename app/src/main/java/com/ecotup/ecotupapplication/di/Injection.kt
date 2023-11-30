package com.ecotup.ecotupapplication.di

import com.ecotup.ecotupapplication.data.repository.EcotupRepository

object Injection {
    fun provideRepository(): EcotupRepository {
        return EcotupRepository.getInstance()
    }
}