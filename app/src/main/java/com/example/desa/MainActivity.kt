package com.example.desa

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.desa.databinding.ActivityMainBinding
import com.example.desa.ui.tampilan.profil.AccountViewModel

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        supportActionBar?.hide()
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        if (NEXTMENU == "pesan"){
            navController.navigate(R.id.navigation_pesan)
        }else if(NEXTMENU == "profil"){
            navController.navigate(R.id.navigation_akun)
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_pesan, R.id.navigation_pasar, R.id.navigation_laporan
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        //mendapatkan Token
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (!it.isSuccessful) {
                return@addOnCompleteListener
            }
            val tokens = it.result
            Log.d("aaaaaaaaaa token", tokens)
        }
        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        AKSESKU = sharedPreference.getString("akses","kosong").toString()
        TOKENKU = sharedPreference.getString("token","kosong").toString()

    }

    companion object {
        var NEXTMENU = "nextmenu"
        var AKSESKU = ""
        var logout = ""
        var TOKENKU = ""

    }

}