package com.ddona.demomvvm.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ddona.demomvvm.R
import com.ddona.demomvvm.databinding.FragmentLoginBinding
import com.ddona.demomvvm.view.MainActivity
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment(), View.OnClickListener {
    private lateinit var binding:FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //tai giao dien
        //khoi tao cac dt can thiet
        binding = FragmentLoginBinding.inflate(
            inflater, container, false
        )

        Log.d("LoginFragment", "onCreateView.....")
        binding.btnLogin.setOnClickListener(this)
        binding.tvRegister.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_login->{

            }
            R.id.tv_register->{
                (activity as MainActivity).openRegister(
                    binding.edtUsername.text.toString(),
                    binding.edtPassword.text.toString()
                )
            }
        }
    }
    override fun onResume() {
        super.onResume()
        Log.d("LoginFragment", "onResume.....")
    }

    override fun onPause() {
        Log.d("LoginFragment", "onPause.....")
        super.onPause()
    }

    override fun onDestroyView() {
        Log.d("LoginFragment", "onDestroyView.....")
        super.onDestroyView()
    }


}