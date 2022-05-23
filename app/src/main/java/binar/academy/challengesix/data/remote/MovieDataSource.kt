package binar.academy.challengesix.data.remote


import binar.academy.challengesix.data.remote.modal.Result
import binar.academy.challengesix.service.ApiService
import kotlinx.coroutines.*

class MovieDataSource(private val apiService: ApiService) {

    @DelicateCoroutinesApi
    fun getMovies(movieCallBack: MovieCallBack): List<Result>{
        GlobalScope.launch(Dispatchers.IO){
            val response = apiService.getAllMovie()
            runBlocking(Dispatchers.Main) {
                if (response.isSuccessful){
                    val result= response.body()
                    movieCallBack.onCompllete(result!!.results)
                }
            }

        }
        return emptyList()
    }
    interface MovieCallBack{
        fun onCompllete(result:List<Result>)
    }
}