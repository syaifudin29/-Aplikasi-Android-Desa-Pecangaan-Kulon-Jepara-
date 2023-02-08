package com.example.desa.ui.pasar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.desa.databinding.FragmentPasarBinding
import com.example.desa.ui.laporan.LaporanViewModel

class PasarFragment : Fragment() {

    private var _binding: FragmentPasarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val pasarViewModel =
            ViewModelProvider(this).get(PasarViewModel::class.java)

        _binding = FragmentPasarBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}