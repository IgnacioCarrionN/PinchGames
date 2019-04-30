package dev.carrion.pinchgames.domain

sealed class DomainEntity {
    data class Game(
        val id: Long,
        val name: String,
        val slug: String,
        val url: String,
        val createdAt: Long,
        val updatedAt: Long,
        val summary: String?,
        val storyline: String?,
        val rating: Double?,
        val ratingCount: Int?,
        val cover: String?
    ): DomainEntity()

    data class GameShort(
        val id: Long,
        val name: String,
        val cover: String?
    )
}