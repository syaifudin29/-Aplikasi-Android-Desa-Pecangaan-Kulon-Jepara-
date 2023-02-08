package com.example.desa.ui.tampilan.profil

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.desa.R
import com.example.desa.databinding.FragmentJemputsampahBinding
import com.example.desa.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class JemputsampahFragment : Fragment() {
    private var _binding: FragmentJemputsampahBinding? = null
    private val binding get() = _binding!!
    val items = arrayOf("Sampah Logam-Kaca", "Sampah Plastik", "Sampah Kertas")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        _binding = FragmentJemputsampahBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.actionbar.namaActionbar.setText("Bank Sampah")
        binding.actionbar.backBar.setOnClickListener{
            val mFragmentManager = parentFragmentManager
            val fragmentP = BankFragment()
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
        val arrayAdapter: ArrayAdapter<*>
        arrayAdapter =ArrayAdapter(requireActivity(), R.layout.list_item_jenissampah, items)
        binding.autoCompletetxt.setAdapter(arrayAdapter)
//        binding.autoCompletetxt.setOnClickListener { data ->
//            Log.d("datakuiki", data.toString())
//        }
        binding.inputtanggal.setText(SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()))
        var cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd/MM/yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            binding.inputtanggal.setText(sdf.format(cal.time))

        }

        binding.btnDate.setOnClickListener{
            DatePickerDialog(requireActivity(), dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }



        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    }