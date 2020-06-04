package com.vinay.dependencywithkoinapp.model


class UserRepository(private val api: GitHubApi) {
    fun getAllUsers() = api.getUsers()
}
