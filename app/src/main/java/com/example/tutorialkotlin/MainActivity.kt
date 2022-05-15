package com.example.tutorialkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.SearchView
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import com.example.tutorialkotlin.fragments.DashboardFragment
import com.example.tutorialkotlin.fragments.InfoFragment
import com.example.tutorialkotlin.fragments.SettingsFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.io.File

class MainActivity : AppCompatActivity() {


    private val dashboardFragment = DashboardFragment();
    private val settingsFragment = SettingsFragment();
    private val infoFragment = InfoFragment();

    private lateinit var todoAdapter: TodoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(dashboardFragment)

        bottom_navigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.ic_dashboard -> replaceFragment(dashboardFragment)
                R.id.ic_settings -> replaceFragment(settingsFragment)
                R.id.ic_info -> replaceFragment(infoFragment)
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

//    override fun onSupportNavigateUp() =
//        findNavController(this, R.id.navHostFragment).navigateUp()

    private fun getPhotoFile(fileName: String): File {
        val directoryStorage = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", directoryStorage)
    }
}