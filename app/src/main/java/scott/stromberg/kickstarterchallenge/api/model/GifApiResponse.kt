package scott.stromberg.kickstarterchallenge.api.model

import com.squareup.moshi.Json

data class GifApiResponse(
    @Json(name = "data") val gifs: List<GifData>
)

data class GifData(
    @Json(name = "id") val id: String,
    @Json(name = "type") val type: String,
    @Json(name = "url") val url: String,
    @Json(name = "title") val title: String,
    @Json(name = "images") val images: Images,
)

data class Images(
    @Json(name = "original") val originalImage: ImageData
)

data class ImageData(
    @Json(name = "height") val height: String,
    @Json(name = "width") val width: String,
    @Json(name = "url") val url: String
)
