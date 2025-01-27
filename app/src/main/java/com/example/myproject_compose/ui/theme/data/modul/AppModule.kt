package com.example.myproject_compose.ui.theme.data.modul
import com.example.myproject_compose.ui.theme.character.CharacterDetailViewModel
import com.example.myproject_compose.ui.theme.character.CharactersViewModel
import com.example.myproject_compose.ui.theme.data.api.ApiService
import com.example.myproject_compose.ui.theme.data.local.AppDatabase
import com.example.myproject_compose.ui.theme.episode.EpisodesViewModel
import com.example.myproject_compose.ui.theme.favorite.FavoriteCharacterViewModel
import com.example.myproject_compose.ui.theme.location.LocationDetailViewModel
import com.example.myproject_compose.ui.theme.location.LocationsViewModel
import com.example.myproject_compose.ui.theme.repasitory.EpisodesRepository
import com.example.myproject_compose.ui.theme.repasitory.EpisodesRepositoryImpl
import com.example.myproject_compose.ui.theme.repasitory.FavoriteCharacterRepository
import com.example.myproject_compose.ui.theme.repasitory.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {
    val module = module {
        single {
            Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }

        single { AppDatabase.getDatabase(androidContext()) }
        single { get<AppDatabase>().favoriteCharacterDao() }
        single { FavoriteCharacterRepository(get()) }
        single<EpisodesRepository> { EpisodesRepositoryImpl(get()) }
        single { Repository(get()) }

        viewModel { FavoriteCharacterViewModel(get()) }
        viewModel { CharactersViewModel(get(), get()) }
        viewModel { LocationsViewModel(get()) }
        viewModel{CharacterDetailViewModel(get())}
        //viewModel { CharactersDetailViewModel(get()) }
     //   viewModel { CharacterDetailViewModel(get()) }
        viewModel { LocationDetailViewModel(get()) }
        viewModel { EpisodesViewModel(get()) }
    }
}