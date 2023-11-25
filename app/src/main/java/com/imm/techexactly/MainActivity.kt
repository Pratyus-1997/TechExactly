package com.imm.techexactly

import android.app.Activity
import android.app.ProgressDialog
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.farmfooddeliveryapp.utils.common.SharedPref
import com.imm.techexactly.adapter.ViewPagerAdapter
import com.imm.techexactly.fragment.ApplicationsFragment
import com.imm.techexactly.fragment.SettingsFragment
import com.kare.support.retrofit.APIClient
import com.kare.support.retrofit.ParseApiData
import com.taxi.rideapp.retrofit.ApiInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    var apiService: ApiInterface? = null
    lateinit var pDialog: ProgressDialog

    var sharePref = SharedPref

    var parseApiData: ParseApiData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        pDialog = ProgressDialog(this)
        pDialog.setCancelable(false)
        pDialog.setMessage("Loading...")
        sharePref.init(this)


        APIClient.init(this)
        apiService = APIClient.client!!.create(ApiInterface::class.java)
        parseApiData = ParseApiData()


        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ApplicationsFragment(this), "Applications")
        adapter.addFragment(SettingsFragment(this), "Settings")

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        changeStatusBarColor(
            ContextCompat.getColor(
                this,
                R.color.black
            ), false
        )
    }

    fun Activity.changeStatusBarColor(color: Int, isLight: Boolean) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color

        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = isLight
    }

    fun showProgress() {
        pDialog.show()
    }

    fun dismissProgress() {
        pDialog.dismiss()
    }

    fun ToastMsg(message: String) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show()
    }

}