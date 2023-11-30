package com.ecotup.ecotupapplication.data.repository

class EcotupRepository {


    companion object {
        @Volatile
        private var instance: EcotupRepository? = null
        fun getInstance(): EcotupRepository =
            instance ?: synchronized(this) {
                instance ?: EcotupRepository().apply {
                    instance = this
                }
            }
    }
}