package binar.academy.challengesix.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import binar.academy.challengesix.R
import binar.academy.challengesix.databinding.FragmentDetailBinding
import binar.academy.challengesix.databinding.FragmentHomeBinding
import com.bumptech.glide.Glide

class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding?=null
    private val binding get() = _binding!!
    private val arguments : DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/original/"+ arguments.movies.posterPath)
            .into(binding.detailImageView)
        binding.apply {
            titleDetailTextView.text=arguments.movies.title
            releaseDetailTextView.text=arguments.movies.releaseDate
        }
    }
}