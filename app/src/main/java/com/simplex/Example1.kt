package com.simplex

import io.reactivex.Maybe
import io.reactivex.Single

fun main() {

    val cache: Maybe<List<String>> = getNotificationsFromCache()

    val network: Single<List<String>> = getNotificationsFromNetwork()

    val result = Maybe.concat(cache, network.toMaybe()).firstElement()

    result.subscribe {
        println(it)
    }

}



