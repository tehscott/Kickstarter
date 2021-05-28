package scott.stromberg.kickstarterchallenge.model

import com.squareup.moshi.Json

data class Gif(
    val height: String,
    val width: String,
    val url: String,
    val giphyUrl: String
)
