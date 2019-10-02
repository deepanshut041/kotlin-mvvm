package `in`.squrlabs.kmm.data.local.util

sealed class DbResponse <out T : Any> {
    class Success<out T : Any>(val data: T) : DbResponse<T>()
    class Error(val exception: Throwable) : DbResponse<Nothing>()
}