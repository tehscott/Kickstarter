package scott.stromberg.kickstarterchallenge.ui.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import scott.stromberg.kickstarterchallenge.api.GiphyWebService
import scott.stromberg.kickstarterchallenge.model.Gif
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val webService: GiphyWebService
) : ViewModel() {
    fun doSearch(query: String) : Single<List<Gif>> {
        return webService.getGifs(query)
            .map {
                it.gifs.map { gifData ->
                    Gif(
                        gifData.images.originalImage.height,
                        gifData.images.originalImage.width,
                        gifData.images.originalImage.url,
                        gifData.url
                    )
                }
            }
    }
}