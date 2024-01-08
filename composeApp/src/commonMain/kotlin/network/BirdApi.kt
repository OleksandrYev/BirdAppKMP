package network

import entity.BirdPictureEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class BirdApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    encodeDefaults = true
                    isLenient = true
                    coerceInputValues = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    suspend fun getBirdPictures(): List<BirdPictureEntity> =
        httpClient.get(BIRD_BASE_API + GET_BIRDS_ENDPOINT)
            .body<List<BirdPictureEntity>>()

    private companion object {
        const val BIRD_BASE_API = "https://sebi.io/demo-image-api"
        const val GET_BIRDS_ENDPOINT = "/pictures.json"
    }
}