package com.ecotup.ecotupapplication.ui.user.finishtransaction

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ecotup.ecotupapplication.data.cammon.Result
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.databinding.ActivityFinishTransactionUserBinding
import com.ecotup.ecotupapplication.ui.general.login.LoginViewModel
import com.ecotup.ecotupapplication.util.IntentToMain
import com.ecotup.ecotupapplication.util.sweetAlert
import kotlinx.coroutines.runBlocking

class FinishTransactionUser : AppCompatActivity() {
    private lateinit var binding: ActivityFinishTransactionUserBinding
    private var ratingDriver: Int = 0

    private val loginViewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this@FinishTransactionUser)
    }

    private val viewModel by viewModels<FinishTransactionUserViewModel> {
        ViewModelFactory.getInstance(this@FinishTransactionUser)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishTransactionUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idTransaction: String = intent.getStringExtra("idTransaction").toString()
        val idDriver: String = intent.getStringExtra("idDriver").toString()

        getDetailTransaksi(idTransaction)
        getDetailDriver(idDriver)
        sendTransaction(idDriver)
    }

    private fun getDetailTransaksi(idTransaksi: String) {
        runBlocking{
            viewModel.getDetailTransaksi(idTransaksi = idTransaksi).observe(this@FinishTransactionUser)
            {result ->
                if(result != null)
                {
                    when(result)
                    {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            val data = result.data.data
                            binding.apply {
                                tvIdTransaction.text = data?.transactionId.toString()
                                tvStatusTransaction.text = data?.transactionStatus.toString()
                                tvTotalWeight.text = "${data?.transactionTotalWeight.toString()} Kg"
                                tvTotalPayment.text = "Rp. ${data?.transactionTotalPayment.toString()}"
                                tvTotalPoint.text = "+ ${data?.transactionTotalPoint.toString()} Points"
                                tvDescriptionTransaksi.text = data?.transactionDescription.toString()
                            }
                        }
                        is Result.Error -> {}
                    }
                }

            }
        }
    }

    private fun getDetailDriver(idDriver : String) {
        loginViewModel.getDetailDriver(idDriver).observe(this@FinishTransactionUser)
        { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {}
                    is Result.Success -> {
                        val data = result.data.data
                        binding.tvNameDriver.text = data?.driverName
                        binding.tvEmailDriver.text = data?.driverEmail
                        ratingDriver = data?.driverRating!!
                    }

                    is Result.Error -> {}
                }
            }
        }
    }

    private fun sendTransaction(idDriver : String)
    {
        binding.btnSend.setOnClickListener{
            val rate = binding.spReteDriver.selectedItem.toString()
            val calculateRate = (ratingDriver + rate.toInt()) / 2

            runBlocking {
                viewModel.updateRating(idDriver = idDriver, rating = calculateRate).observe(this@FinishTransactionUser)
                { result ->
                    if (result != null) {
                        when (result) {
                            is Result.Loading -> {}
                            is Result.Success -> {
                                sweetAlert(this@FinishTransactionUser, "Success", "Thank you for giving a rating to the driver", "success")
                                IntentToMain(this@FinishTransactionUser)
                                finish()
                            }
                            is Result.Error -> {}
                        }
                    }
                }
            }
        }
    }
}