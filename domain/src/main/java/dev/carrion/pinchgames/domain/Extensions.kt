package dev.carrion.pinchgames.domain

fun String.toBigCoverUrl(): String {
    return this.replace("t_thumb", "t_cover_big")
}