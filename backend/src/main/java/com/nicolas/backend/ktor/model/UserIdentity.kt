package com.nicolas.backend.ktor.model

import kotlinx.serialization.Serializable

@Serializable
data class UserIdentity(
    val uuid : String,
    val name : String,
)
