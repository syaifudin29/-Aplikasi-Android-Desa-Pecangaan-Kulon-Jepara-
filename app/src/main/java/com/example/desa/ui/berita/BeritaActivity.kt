package com.example.desa.ui.berita

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desa.MainActivity
import com.example.desa.R
import com.example.desa.databinding.ActivityBeritaBinding
import com.example.desa.response.DataItem
import com.example.desa.response.DataItemNews
import com.example.desa.ui.tampilan.profil.AccountFragment


class BeritaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBeritaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.actionbarBeritaTerkini.namaActionbar.setText("Berita Terkini")
        val beritaViewModel = ViewModelProvider(this)[BeritaTerkiniViewModel::class.java]

        beritaViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        beritaViewModel.listReviewBerita.observe(this) { data ->
            setBerita(data)
        }
        binding.actionbarBeritaTerkini.backBar.setOnClickListener {
        onBackPressed()
        }
        binding.refreshSpiwe.setOnRefreshListener {
            beritaViewModel.dataku(false)
            beritaViewModel.listReviewBerita.observe(this) { data ->
                setBerita(data)
                binding.refreshSpiwe.isRefreshing = false
            }
        }
        binding.actionbarBeritaTerkini.profileBar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("profil", "profil");
            MainActivity.NEXTMENU = "profil"
            startActivity(intent)
        }
    }

    private fun setBerita(berita: ArrayList<DataItemNews>) {
        binding.rvBeritaTerkini.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = BeritaTerkiniAdapter(berita)
        binding.rvBeritaTerkini.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : BeritaTerkiniAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataItemNews) {
                val intent = Intent(this@BeritaActivity, DetailBeritaActivity::class.java)
                intent.putExtra("slug", data.slug)
                startActivity(intent)
                Log.d("SUDAH", "ADA")
            }
        })

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar2BeritaTerkini.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}