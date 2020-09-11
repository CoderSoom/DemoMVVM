package com.ddona.demomvvm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ddona.demomvvm.R
import com.ddona.demomvvm.view.fragment.LoginFragment
import com.ddona.demomvvm.view.fragment.RegisterFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addLoginFragment()
    }

    //cach dua fragment len activity
    private fun addLoginFragment() {
        val manager = supportFragmentManager
//        manager.findFragmentByTag()
        val tran = manager.beginTransaction()
        tran.add(
            R.id.content,
            LoginFragment(),
            LoginFragment::class.java.name
        )
        tran.commit()
    }

    fun openRegister(username: String, password: String) {
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fgLogin = manager.findFragmentByTag(
            LoginFragment::class.java.name
        )

        val reg = RegisterFragment()
        val b = Bundle()
        b.putString("USERNAME", username)
        b.putString("PASSWORD", password)

        //dua bundl vao fragment
        reg.arguments = b
        tran.setCustomAnimations(
            R.anim.open_enter, R.anim.open_exit,
            R.anim.exit_enter, R.anim.exit_exit
        )

        tran.remove(fgLogin!!)
        tran.add(R.id.content, reg, RegisterFragment::class.java.name)


        tran.addToBackStack(null)
        tran.commit()
    }
}