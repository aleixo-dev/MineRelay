package com.nicolas.minerelay.data.mappers

import com.nicolas.minerelay.data.remote.response.PlayerIdentityDto
import com.nicolas.minerelay.domain.models.PlayerIdentity

fun List<PlayerIdentityDto>.toPlayerIdentity () : List<PlayerIdentity> {
    return this.map {
        PlayerIdentity(
            uuid = it.uuid,
            name = it.name
        )
    }
}