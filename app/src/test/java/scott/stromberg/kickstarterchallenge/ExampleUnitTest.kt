package scott.stromberg.kickstarterchallenge

import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import scott.stromberg.kickstarterchallenge.api.GiphyWebService
import scott.stromberg.kickstarterchallenge.api.model.GifApiResponse
import scott.stromberg.kickstarterchallenge.api.model.GifData
import scott.stromberg.kickstarterchallenge.api.model.ImageData
import scott.stromberg.kickstarterchallenge.api.model.Images
import scott.stromberg.kickstarterchallenge.ui.main.MainViewModel

class ExampleUnitTest {
    private val mockWebService: GiphyWebService = mock {  }
    private val viewModel = MainViewModel(mockWebService)

    @Before
    fun init() {
        `when`(mockWebService.getGifs(eq(""))).thenReturn(Single.just(GifApiResponse(listOf())))
        `when`(mockWebService.getGifs(eq("nasa"))).thenReturn(
            Single.just(
                GifApiResponse(
                    listOf(
                        GifData("1", "gif", "http://gif1giphyurl", "gif1title", Images(ImageData("100", "100", "http://gif1url"))),
                        GifData("2", "gif", "http://gif2giphyurl", "gif2title", Images(ImageData("200", "200", "http://gif2url"))),
                        GifData("3", "gif", "http://gif3giphyurl", "gif3title", Images(ImageData("300", "300", "http://gif3url"))),
                        GifData("4", "gif", "http://gif4giphyurl", "gif4title", Images(ImageData("400", "400", "http://gif4url"))),
                    )
                )
            )
        )
    }

    @Test
    fun testEmptySearch() {
        viewModel.doSearch("").test().assertResult(listOf())
    }

    @Test
    fun testNasaSearch() {
        viewModel.doSearch("nasa").test().assertValue { it.isNotEmpty() }
    }
}