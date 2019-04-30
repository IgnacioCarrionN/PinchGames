package dev.carrion.pinchgames.data.network

sealed class NetworkDataEntity {
    data class Game(
        val id: Long,
        val name: String,
        val slug: String,
        val url: String,
        val created_at: Long,
        val updated_at: Long,
        val summary: String?,
        val storyline: String?,
        val rating: Double?,
        val rating_count: Int?,
        val cover: Cover?
    ): NetworkDataEntity()

    data class Cover(
        val id: Long,
        val game: Long,
        val height: Long,
        val width: Long,
        val imageId: String,
        val url: String
    )

    data class GameBodyRequest(
        val fields: String,
        val limit: Int,
        val offset: Int
    ): NetworkDataEntity() {
        fun toRawRequest(): String {
            return "fields = $fields;\n" +
                    "limit = $limit;\n" +
                    "offset = $offset;"
        }
    }
}