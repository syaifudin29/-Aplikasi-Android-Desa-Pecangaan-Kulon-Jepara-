package com.example.desa.ui.tampilan.profil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.desa.R
import com.example.desa.databinding.FragmentBeritaBinding
import com.example.desa.databinding.FragmentJemputsampahBinding
import com.example.desa.databinding.FragmentJenissampahBinding
import com.example.desa.ui.home.HomeFragment

class JenissampahFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentJenissampahBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        _binding = FragmentJenissampahBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.actionbar.namaActionbar.setText("Bank Sampan")
        binding.actionbar.backBar.setOnClickListener{
            val fragmentP = BankFragment()
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment_activity_main, fragmentP, BankFragment::class.java.simpleName).addToBackStack(null)
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

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}