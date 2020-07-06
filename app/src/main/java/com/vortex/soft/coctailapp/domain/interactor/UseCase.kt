package com.vortex.soft.coctailapp.domain.interactor

import com.vortex.soft.coctailapp.utils.functional.Either
import com.vortex.soft.coctailapp.data.repository.Error
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.collect

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Flow<Either<Error, Type>>

    operator fun invoke(scope: CoroutineScope, params: Params, onResult: (Either<Error, Type>) -> Unit = {}) = with(scope) {
        launch(Dispatchers.Main){
            run(params).flowOn(Dispatchers.Default)
                .catch{
                    onResult(Either.Left(Error.UndefineError(it.message ?: "Undefine error")))
                }
                .collect {
                    onResult(it)
                }
        }
    }

    class None
}