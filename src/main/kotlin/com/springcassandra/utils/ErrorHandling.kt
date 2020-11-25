package com.springcassandra.utils

sealed class Failable<out T>

class Success<T>(val result: T) : Failable<T>()
class Failure(val error: String) : Failable<Nothing>()
