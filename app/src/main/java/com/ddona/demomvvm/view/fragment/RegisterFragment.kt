package com.ddona.demomvvm.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ddona.demomvvm.R
import com.ddona.demomvvm.databinding.FragmentRegisterBinding
import com.ddona.demomvvm.view.MainActivity

class RegisterFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(
            inflater,
            container,
            false
        )
        binding.btnRegister.setOnClickListener(this)

        val username = arguments?.getString("USERNAME")
        val password = arguments?.getString("PASSWORD")
        binding.edtUsername.setText(username)
        binding.edtPassword.setText(password)
        return binding.root
    }

    override fun onClick(v: View) {
        (activity as MainActivity).openMain()
    }
}

private fun MainActivity.openMain() {
    val manager = supportFragmentManager
    val tran = manager.beginTransaction()
    tran.replace(
        R.id.content,
        MainFragment(), MainFragment::class.java.name
    )
    tran.addToBackStack(null)
    tran.commit()
}
