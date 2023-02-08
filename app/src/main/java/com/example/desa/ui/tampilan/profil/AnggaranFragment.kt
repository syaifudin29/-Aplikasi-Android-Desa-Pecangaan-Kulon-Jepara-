package com.example.desa.ui.tampilan.profil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.desa.R
import com.example.desa.databinding.FragmentAnggaranBinding
import com.example.desa.databinding.FragmentProfilBinding
import com.example.desa.ui.home.HomeFragment



class AnggaranFragment : Fragment() {

    private var _binding: FragmentAnggaranBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAnggaranBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.actionbar.namaActionbar.setText("Anggaran Desa")
        binding.actionbar.backBar.setOnClickListener{
            val fragmentP = HomeFragment()
            val mFragmentManager = parentFragmentManager
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

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}