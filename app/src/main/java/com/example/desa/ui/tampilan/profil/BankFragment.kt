package com.example.desa.ui.tampilan.profil

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.desa.MainActivity
import com.example.desa.R
import com.example.desa.databinding.FragmentAnggaranBinding
import com.example.desa.databinding.FragmentBankBinding
import com.example.desa.ui.home.HomeFragment
import com.example.desa.ui.login.LoginActivity

class BankFragment : Fragment() {

    private var _binding: FragmentBankBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBankBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val mFragmentManager = parentFragmentManager

        binding.actionbar.namaActionbar.setText("Bank Sampah")
        binding.actionbar.backBar.setOnClickListener{
            val fragmentP = HomeFragment()
            mFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment_activity_main, fragmentP, HomeFragment::class.java.simpleName).addToBackStack(null)
                commit()
            }
        }
        binding.actionbar.profileBar.setOnClickListener{
            val fragmentP = AccountFragment()
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment_activity_main, fragmentP, AccountFragment()::class.java.simpleName).addToBackStack(null)
                commit()
            }
        }

        binding.imageView7.setOnClickListener{
            val fragmentjemput = JemputsampahFragment()
            mFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment_activity_main, fragmentjemput, JemputsampahFragment::class.java.simpleName).addToBackStack(null)
                commit()
            }
        }
        binding.btnjenissmph.setOnClickListener{
            val fragmentjenis= JenissampahFragment()
            mFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment_activity_main, fragmentjenis, JenissampahFragment::class.java.simpleName).addToBackStack(null)
                commit()
            }
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