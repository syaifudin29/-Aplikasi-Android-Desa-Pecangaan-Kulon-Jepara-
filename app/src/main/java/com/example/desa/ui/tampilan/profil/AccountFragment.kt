package com.example.desa.ui.tampilan.profil

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.desa.ApiConfig
import com.example.desa.MainActivity
import com.example.desa.R
import com.example.desa.databinding.FragmentAccountBinding
import com.example.desa.databinding.FragmentAnggaranBinding
import com.example.desa.response.MasukResponse
import com.example.desa.response.ProfileResponse
import com.example.desa.ui.home.HomeFragment
import com.example.desa.ui.home.HomeViewModel
import com.example.desa.ui.home.Utama
import com.example.desa.ui.login.LoginActivity
import com.example.desa.ui.login.UbahpasswordActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null
    private lateinit var accountViewModel: AccountViewModel

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        accountViewModel =
            ViewModelProvider(this)[AccountViewModel::class.java]

        accountViewModel.dataprofile()
        accountViewModel.datalayanan()

        accountViewModel.listReviewProfile.observe(viewLifecycleOwner) { data ->
            binding.nama.setText(data.nama)
            binding.nik.setText(data.nik)
            binding.rtrw.setText(data.alamat?.rt+" / "+data.alamat?.rw)
            binding.ttl.setText(data.tanggalLahir)
        }
        accountViewModel.listReviewLayanan.observe(viewLifecycleOwner) { data ->
            binding.inteUser.setText(data.layanan?.interdesa?.username)
            binding.inteHarga.setText(data.layanan?.interdesa?.hargaPaket)
            binding.intePaket.setText(data.layanan?.interdesa?.namaPaket)
            binding.inteJatuh.setText(data.layanan?.interdesa?.jatuhTempo)

            binding.bankJatuh.setText(data.layanan?.jemputSampah?.jatuhTempo)
            binding.bankPaket.setText(data.layanan?.jemputSampah?.paketPelanggan)
            binding.bankTagihan.setText(data.layanan?.jemputSampah?.tagihanPerBulan)
        }

        binding.actionbar.namaActionbar.setText("Akun Desa")
        binding.actionbar.backBar.setOnClickListener{
            val fragmentP = HomeFragment()
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment_activity_main, fragmentP, HomeFragment::class.java.simpleName).addToBackStack(null)
                commit()
            }
        }

        binding.actionbar.profileBar.visibility = View.GONE
        binding.btnGantipassword.setOnClickListener{
            val intent = Intent(requireActivity(), UbahpasswordActivity::class.java)
            startActivity(intent)
        }
        binding.logout.setOnClickListener{
            val sharedPreference =  requireActivity().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putString("akses","kosong")
            editor.putString("token","kosong")
            editor.commit()

            val intent = Intent(requireActivity(), Utama::class.java)
            startActivity(intent)

        }

        return root
    }


    override fun onResume() {
        if (MainActivity.AKSESKU != "1"){
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
        }
        super.onResume()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}