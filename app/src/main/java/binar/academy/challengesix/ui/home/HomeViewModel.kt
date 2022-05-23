package binar.academy.challengesix.ui.home

import androidx.lifecycle.*
import binar.academy.challengesix.data.local.UserRepository
import binar.academy.challengesix.data.remote.MovieDataSource
import binar.academy.challengesix.data.remote.modal.Result
import binar.academy.challengesix.data.remote.MovieRepository
import kotlinx.coroutines.launch

class HomeViewModel (private  val repository: MovieRepository, private val userRepository: UserRepository): ViewModel() {
    private val movie : MutableLiveData<List<Result>> by lazy {
        MutableLiveData<List<Result>>().also {
            loadMovie()
        }
    }

    private val respons: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().also {
            loadMovie()
        }
    }

    fun getCode(): LiveData<Int> {
        return respons
    }

    fun getMovie(): LiveData<List<Result>> {
        return movie
    }

    private fun loadMovie() {
        repository.getMovies(object : MovieDataSource.MovieCallBack{
            override fun onCompllete(result: List<Result>) {
                movie.value= result
            }

        })
    }
    fun getUsername():LiveData<String>{
        return userRepository.getUsernameValue().asLiveData()
    }

}