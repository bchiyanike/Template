package com.lionico.template.core.common

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val lionicoDispatcher: LionicoDispatchers)

enum class LionicoDispatchers {
    Default,
    Main,
    Unconfined,
    IO,
}
