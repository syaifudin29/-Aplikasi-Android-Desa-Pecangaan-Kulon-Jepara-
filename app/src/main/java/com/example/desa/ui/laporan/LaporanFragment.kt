package com.example.desa.ui.laporan

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.desa.MainActivity
import com.example.desa.databinding.FragmentLaporanBinding
import com.example.desa.ui.login.LoginActivity


class LaporanFragment : Fragment() {


    private var _binding: FragmentLaporanBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val laporanViewModel =
            ViewModelProvider(this).get(LaporanViewModel::class.java)

        _binding = FragmentLaporanBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onResume() {
        if (MainActivity.AKSESKU != "1"){
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
        }
        super.onResume()
    }


}