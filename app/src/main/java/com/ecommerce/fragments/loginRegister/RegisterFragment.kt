package com.ecommerce.fragments.loginRegister

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ecommerce.R
import com.ecommerce.data.User
import com.ecommerce.databinding.FragmentRegisterBinding
import com.ecommerce.util.Resource
import com.ecommerce.viewModel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private val TAG = "RegisterFragment"

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonRegisterRegister.setOnClickListener {
                val user = User(
                    edFirstNameRegister.text.toString().trim(),
                    edLastNameRegister.text.toString().trim(), // Use last name here
                    edEmailRegister.text.toString().trim()
                )
                val password = edPasswordRegister.text.toString()
                viewModel.createAccountWithEmailAndPassword(user, password)
            }
        }
        lifecycleScope.launch {
            viewModel.register.collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        binding.buttonRegisterRegister.startAnimation()
                    }
                    is Resource.Success -> {
                        Log.d("test", resource.data.toString())
                        binding.buttonRegisterRegister.revertAnimation()
                    }
                    is Resource.Error -> {
                        Log.e(TAG, resource.message ?: "Unknown error")
                    }
                    else -> Unit
                }
            }
        }
    }
}
