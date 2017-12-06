package com.bridgelabz.fragmentsgoogle

import android.app.Fragment
import android.app.FragmentTransaction
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fragment: Fragment = nextFragment()
        fragmentManager.beginTransaction().replace(R.id.mainFragment, fragment).addToBackStack(null).commit()
    }
}
