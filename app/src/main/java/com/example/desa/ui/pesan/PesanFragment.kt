package com.example.desa.ui.pesan

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desa.ApiConfig
import com.example.desa.MainActivity
import com.example.desa.R
import com.example.desa.databinding.FragmentPesanBinding
import com.example.desa.response.DataPesanItem
import com.example.desa.response.PesanResponse
import com.example.desa.ui.home.BeritaReviewAdapter
import com.example.desa.ui.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PesanFragment : Fragment() {

    private var _binding: FragmentPesanBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val pesanViewModel =
            ViewModelProvider(this).get(PesanViewModel::class.java)

        _binding = FragmentPesanBinding.inflate(inflater, container, false)
        val root: View = binding.root
        dataku()
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun dataku(){
//        showLoading(true)

        val client = ApiConfig.getApiService().getPesan()
        client.enqueue(object : Callback<PesanResponse> {
            override fun onResponse(
                call: Call<PesanResponse>,
                response: Response<PesanResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        Log.d("responseku", responseBody.status.toString())
                        setPesan(responseBody.dataPesan as ArrayList<DataPesanItem>)
//                        showLoading(false)
                    }
                } else {
                    Log.d("responseku", "respo: else1")
                }
            }

            override fun onFailure(call: Call<PesanResponse>, t: Throwable) {
                Log.e("Eror", "onFailure: eror")
                Log.d("responseku", "respo: else2")

            }
        })
    }

    private fun setPesan(pesan: ArrayList<DataPesanItem>) {
        binding.rvPesan.layoutManager = LinearLayoutManager(activity)
        val listAdapter = PesanAdapter(pesan)
        binding.rvPesan.adapter = listAdapter

        listAdapter.setOnItemClickCallback(object : PesanAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataPesanItem) {
                showSelectedHero(data)
            }
        })

    }
    private fun showSelectedHero(pesan: DataPesanItem) {
        val intent = Intent (getActivity(), DetailPesanActivity::class.java)
        getActivity()?.startActivity(intent)
    }
    override fun onResume() {
        if (MainActivity.AKSESKU != "1"){
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
        }
        super.onResume()
    }

}