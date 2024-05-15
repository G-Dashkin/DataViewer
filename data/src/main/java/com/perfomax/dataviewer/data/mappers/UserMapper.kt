package com.perfomax.dataviewer.data.mappers

import com.perfomax.dataviewer.domain.models.User

internal fun String.toDomainUser(): User {
    return User(
        userName = this.split("userName:")[1].split(";")[0],
        email = this.split("email:")[1].split(";")[0],
        password = this.split("password:")[1].split(";")[0]
    )
}