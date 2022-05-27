package binar.academy.challengesix.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import binar.academy.challengesix.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private var useernameValue = "default"
    private val homeViewModel : HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val factory = HomeViewModelFactory(view.context)
//        viewModel = ViewModelProvider(requireActivity(),factory)[]
        getAllMovie()
        navigateProfile()
        username()
    }

    private fun navigateProfile() {
        binding.personImageView.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment())
        }
    }

    private fun getAllMovie() {
        homeViewModel.getMovie().observe(viewLifecycleOwner) {
            val adapter = HomeAdapter(it)
            val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.homeRecyclerView.layoutManager = layoutManager
            binding.homeRecyclerView.adapter = adapter
        }
//           movieViewModel.getCode().observe(viewLifecycleOwner) { codeResponse ->
//                if (codeResponse == 200) {
//                    binding.progressBar.visibility = View.GONE
//                }
//            }
    }


    private fun username(){
        homeViewModel.getUsername().observe(viewLifecycleOwner){result ->
            useernameValue = result
            binding.welcomeTextView.text = result
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}