package com.nicolas.mine_relay.model

import kotlinx.serialization.Serializable

@Serializable
data class UserIdentity(
    val uuid : String,
    val name : String
)
