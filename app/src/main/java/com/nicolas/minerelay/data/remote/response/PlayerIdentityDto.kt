package com.nicolas.minerelay.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class PlayerIdentityDto(
    val uuid : String,
    val name : String
)
