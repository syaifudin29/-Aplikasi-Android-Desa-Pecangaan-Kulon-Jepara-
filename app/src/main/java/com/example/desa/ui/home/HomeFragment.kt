package com.example.desa.ui.home

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.desa.MainActivity
import com.example.desa.R
import com.example.desa.databinding.FragmentHomeBinding
import com.example.desa.response.DataItem
import com.example.desa.response.DataItemNews
import com.example.desa.ui.berita.BeritaActivity
import com.example.desa.ui.berita.BeritaTerkiniAdapter
import com.example.desa.ui.berita.DetailBeritaActivity
import com.example.desa.ui.login.LoginActivity
import com.example.desa.ui.pesan.PesanFragment
import com.example.desa.ui.tampilan.profil.*


class HomeFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var listHeroAdapter: BeritaReviewAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        homeViewModel.listReviewBerita.observe(viewLifecycleOwner) { data ->
            setBerita(data)
        }


        homeViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        binding.btnLogin.setOnClickListener{
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnProfile.setOnClickListener(this)
        binding.btnAnggaran.setOnClickListener(this)
        binding.btnPenduduk.setOnClickListener(this)
        binding.btnSampah.setOnClickListener(this)
        binding.btnBantuan.setOnClickListener(this)
        binding.btnLembaga.setOnClickListener(this)
        binding.btnKelompok.setOnClickListener(this)
        binding.btnBerita.setOnClickListener(this)
        binding.profileBar23.setOnClickListener(this)
        return root
    }

    override fun onClick(v: View?) {
        val mFragmentManager = parentFragmentManager

        if (v?.id == R.id.btnProfile){
            val fragment = ProfilFragment()
            mFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment_activity_main, fragment, ProfilFragment::class.java.simpleName).addToBackStack(null).
                commit()
            }
        }else if(v?.id == R.id.btnAnggaran){
            val fragment = AnggaranFragment()
            mFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment_activity_main, fragment, AnggaranFragment::class.java.simpleName).addToBackStack(null)
                commit()
            }
        }else if(v?.id == R.id.btnPenduduk){
            val fragment = PendudukFragment()
            mFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment_activity_main, fragment, PendudukFragment::class.java.simpleName).addToBackStack(null)
                commit()
            }
        }else if(v?.id == R.id.btnSampah){
            val fragment = BankFragment()
            mFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment_activity_main, fragment, BankFragment::class.java.simpleName).addToBackStack(null)
                commit()
            }
        }else if(v?.id == R.id.btnBantuan){
            val fragment = BantuanFragment()
            mFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment_activity_main, fragment, BantuanFragment::class.java.simpleName).addToBackStack(null)
                commit()
            }
        }else if(v?.id == R.id.btnLembaga){
            val fragment = LembagaFragment()
            mFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment_activity_main, fragment, LembagaFragment::class.java.simpleName).addToBackStack(null)
                commit()
            }
        }else if(v?.id == R.id.btnKelompok){
            val fragment = KelompokFragment()
            mFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment_activity_main, fragment, KelompokFragment::class.java.simpleName).addToBackStack(null)
                commit()
            }
        }else if(v?.id == R.id.btnBerita){
            val intent = Intent(activity, BeritaActivity::class.java)
            startActivity(intent)
        }else if(v?.id == R.id.profileBar23){
            val fragment = AccountFragment()
            mFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment_activity_main, fragment, AccountFragment::class.java.simpleName).addToBackStack(null)
                commit()
            }
        }


    }


    private fun setBerita(berita: ArrayList<DataItemNews>) {
            binding.rvBerita.layoutManager = LinearLayoutManager(requireActivity())
            listHeroAdapter = BeritaReviewAdapter(berita)
            binding.rvBerita.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : BeritaReviewAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataItemNews) {
                val intent = Intent(requireActivity(), DetailBeritaActivity::class.java)
                intent.putExtra("slug", data.slug)
                startActivity(intent)
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar2.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStop() {
        super.onStop()
        homeViewModel.dataku(false)
        homeViewModel.listReviewBerita.observe(viewLifecycleOwner) { data ->
            setBerita(data)
        }
    }

    override fun onResume() {
        if (MainActivity.AKSESKU == "1"){
            binding.btnLogin.isVisible = false
            binding.profileBar23.isVisible = true
        }else{
            binding.btnLogin.isVisible = true
            binding.profileBar23.isVisible = false
        }
        super.onResume()
    }

}