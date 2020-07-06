package com.vortex.soft.coctailapp.data.repository

import com.vortex.soft.coctailapp.utils.functional.Either
import retrofit2.Call

open class NetworkRepository {
    protected fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Error, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body() ?: default)))
                false -> Either.Left(Error.ServerError(response.errorBody()?.toString() ?: ""))
            }
        } catch (exception: Throwable) {
            Either.Left(Error.ServerError(exception))
        }
    }

}