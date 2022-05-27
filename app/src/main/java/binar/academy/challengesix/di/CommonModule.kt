package binar.academy.challengesix.di

import androidx.room.Room
import binar.academy.challengesix.data.local.ApplicationDB
import binar.academy.challengesix.data.local.UserRepository
import binar.academy.challengesix.data.remote.MovieDataSource
import binar.academy.challengesix.data.remote.MovieRepository
import binar.academy.challengesix.service.ApiService
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module{
    single{
           HttpLoggingInterceptor().apply {
                 level = HttpLoggingInterceptor.Level.BODY
            }
            OkHttpClient.Builder()
                .addInterceptor(ChuckerInterceptor(androidContext()))
                .build()
    }

    single{
        val BASE_URL = "https://api.themoviedb.org/"
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val movieDataSourceModule = module {
    single{
      MovieDataSource(get())
    }
}

val movieRepositoryModule = module{
    single {
        MovieRepository(get())
    }
}



val userDatabaseModule = module{
    factory {
        get<ApplicationDB>().userDao()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            ApplicationDB::class.java,
            "Cinema10.db"
        ).build()
    }
}

val userRepository = module {
    single{
        UserRepository(get(),androidContext())
    }
}

