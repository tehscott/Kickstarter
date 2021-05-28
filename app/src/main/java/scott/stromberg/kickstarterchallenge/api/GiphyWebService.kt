package scott.stromberg.kickstarterchallenge.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import scott.stromberg.kickstarterchallenge.api.model.GifApiResponse

interface GiphyWebService {
    @GET("gifs/search?api_key=229ac3e932794695b695e71a9076f4e5&limit=25&offset=0&rating=G&lang=en")
    fun getGifs(
        @Query("q") query: String
    ) : Single<GifApiResponse>

    companion object {
        fun createWebService(): GiphyWebService {
            return Retrofit.Builder()
                .baseUrl("https://api.giphy.com/v1/")
                .addConverterFactory(
                    MoshiConverterFactory.create(
                        Moshi.Builder().add(
                            KotlinJsonAdapterFactory()
                        ).build()
                    )
                )
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(GiphyWebService::class.java)
        }
    }
}