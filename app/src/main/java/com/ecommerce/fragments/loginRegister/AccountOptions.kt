package com.ecommerce.fragments.loginRegister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ecommerce.R
import com.ecommerce.databinding.FragmentAccountOptionsBinding

class AccountOptions: Fragment(R.layout.fragment_account_options) {
    private lateinit var binding: FragmentAccountOptionsBinding;
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountOptionsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLoginAccountOptions.setOnClickListener {
            navController.navigate(R.id.action_accountOptionsFragment_to_loginFragment)
        }
        binding.buttonRegisterAccountOptions.setOnClickListener {
            navController.navigate(R.id.action_accountOptionsFragment_to_registerFragment)
        }
    }
}