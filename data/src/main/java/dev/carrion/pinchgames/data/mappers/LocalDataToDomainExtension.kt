package dev.carrion.pinchgames.data.mappers

import dev.carrion.pinchgames.data.local.LocalDataEntity
import dev.carrion.pinchgames.domain.DomainEntity

fun LocalDataEntity.Game.toDomain(): DomainEntity.Game = DomainEntity.Game(
    id = this.id,
    name = this.name,
    slug = this.slug,
    url = this.url,
    createdAt = this.created_at,
    updatedAt = this.updated_at,
    summary = this.summary,
    storyline = this.storyline,
    rating = this.rating,
    ratingCount = this.rating_count,
    cover = this.cover
)