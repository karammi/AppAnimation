package com.asad.appanimation.core.data.dataSource

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException
import javax.inject.Inject

class DataExceptionMapper @Inject constructor(
    private val retrofitConverter: Converter<ResponseBody, CustomNetworkException>,
) {
    operator fun invoke(e: Exception): CustomNetworkException {
        return when (e) {
            is HttpException -> {
                var errorBody: CustomNetworkException? = null
                try {
                    e.response()?.errorBody()?.let {
                        errorBody = retrofitConverter.convert(it)
                    }
                    if (errorBody != null) {
                        errorBody!!.copy(
                            status = "error",
                            message = errorBody!!.message ?: "",
                            code = e.code(),
                        )
                    } else {
                        CustomNetworkException(
                            status = "error",
                            message = CoreString.UnknownNetworkError,
                            code = 1001,
                        )
                    }
                } catch (ex: Exception) {
                    CustomNetworkException(
                        status = "error",
                        message = CoreString.HTTP_UNKNOWN_ERROR_MESSAGE,
                        code = 1001,
                    )
                }
            }

            else -> {
                CustomNetworkException(
                    status = "error",
                    message = CoreString.HTTP_UNKNOWN_ERROR_MESSAGE,
                    code = 1001,
                )
            }
        }
    }
}

/**
 * These are all of the strings used in the core layer
 * */
object CoreString {

    const val IOExceptionMessage = "Internet error!"
    const val MalformedURLExceptionMessage = "Malformed url!\nPlease contact the service provider"
    const val UnknownNetworkError = "Unknown error!"

    const val HTTP_UNKNOWN_ERROR_MESSAGE = "Unknown network error!"
}

