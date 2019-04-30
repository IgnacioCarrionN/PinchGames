package dev.carrion.pinchgames.data.mappers

import dev.carrion.pinchgames.data.local.LocalDataEntity
import dev.carrion.pinchgames.data.network.NetworkDataEntity

fun List<NetworkDataEntity.Game>.toLocalData(): List<LocalDataEntity.Game> = this.map {
    LocalDataEntity.Game(
        id = 0,
        gameId = it.id,
        name = it.name,
        slug = it.slug,
        // We get the url without the protocol. We append https before saving on database.
        url = it.url,
        created_at = it.created_at,
        updated_at = it.updated_at,
        summary = it.summary,
        storyline = it.storyline,
        rating = it.rating,
        rating_count = it.rating_count,
        cover = it.cover?.let { cover ->
            "https:${cover.url}"
        }
    )
}