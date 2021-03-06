package scott.stromberg.tmg.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import scott.stromberg.kickstarterchallenge.api.GiphyWebService

@Module
@InstallIn(ViewModelComponent::class)
object HiltModules {
    @Provides
    @ViewModelScoped
    fun provideGifWebService() = GiphyWebService.createWebService()
}