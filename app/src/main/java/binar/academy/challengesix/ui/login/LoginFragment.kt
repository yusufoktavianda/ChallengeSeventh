package binar.academy.challengesix.ui.login

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import binar.academy.challengesix.databinding.FragmentLoginBinding
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showImage()
        binding.loginButton.setOnClickListener {

            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(requireContext(), "EmptyFields", Toast.LENGTH_SHORT).show()
            } else {
                loginViewModel.authLogin(email, password)
            }
        }

        binding.registershowTextView.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
        navigateUi()
    }

    private fun navigateUi() {
        loginViewModel.result().observe(viewLifecycleOwner){
            if (it == true){
                Toast.makeText(requireActivity(), "Login Success", Toast.LENGTH_SHORT).show()
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                Log.d("Login", "Login Success $it")
                loginViewModel.reset()

            }else{
                Toast.makeText(requireActivity(), "Login Failed", Toast.LENGTH_SHORT).show()
                Log.d("login", "testing ")
            }
        }
    }


    fun showImage(){
        Glide
            .with(requireContext())
            .load("https://pbs.twimg.com/profile_images/1243623122089041920/gVZIvphd_400x400.jpg")
            .centerCrop()
            .into(binding.loginImageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}