package binar.academy.challengesix.ui.splash

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import binar.academy.challengesix.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding: FragmentSplashBinding get() = _binding!!
    private val splashViewModel: SplashViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val factory = ViewModelFactory(view.context)
//        viewModel=ViewModelProvider(requireActivity(), factory)[SplashViewModel::class.java]
        Handler().postDelayed({
            navigationUi()
        }, 2000)
    }

    private fun navigationUi() {
        splashViewModel.loginCheck().observe(viewLifecycleOwner){
            if (it != "default"){
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
                Log.d("splash", "testing berhasil $it")
            }else{
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
                Log.d("splash", "testing berhasil $it")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}