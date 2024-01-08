package entity

import kotlinx.serialization.Serializable

@Serializable
data class BirdPictureEntity(
    val category: String,
    val path: String,
    val author: String
) {
    val pictureUrl: String get() = "https://sebastianaigner.github.io/demo-image-api/$path"
    val pictureDescription: String get() = "$category by $author"
}
