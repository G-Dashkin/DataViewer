package com.dashkin.dataviewer.core.parser.result

/**
 * A discriminated union representing the outcome of a domain operation.
 */
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: DataViewerException) : Result<Nothing>()
}

sealed class DataViewerException(message: String, cause: Throwable? = null) :
    Exception(message, cause) {

    class FileNotFoundException(path: String) :
        DataViewerException("File not found: $path")

    class InvalidFormatException(message: String, cause: Throwable? = null) :
        DataViewerException(message, cause)

    class OutOfMemoryException(message: String) :
        DataViewerException(message)

    class PermissionDeniedException(path: String) :
        DataViewerException("Permission denied: $path")

    class UnknownException(cause: Throwable) :
        DataViewerException(cause.message ?: "Unknown error", cause)
}

inline fun <T, R> Result<T>.map(transform: (T) -> R): Result<R> = when (this) {
    is Result.Success -> Result.Success(transform(data))
    is Result.Error -> this
}

inline fun <T> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    if (this is Result.Success) action(data)
    return this
}

inline fun <T> Result<T>.onError(action: (DataViewerException) -> Unit): Result<T> {
    if (this is Result.Error) action(exception)
    return this
}
