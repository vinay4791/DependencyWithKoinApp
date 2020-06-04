package com.vinay.dependencywithkoinapp.model

sealed class UiState

object Loading : UiState()
object Success : UiState()
class Error(val error: Throwable) : UiState()
class ErrorString(val string : String) : UiState()