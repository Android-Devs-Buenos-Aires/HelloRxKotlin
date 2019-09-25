package com.simplex

import io.reactivex.Maybe
import io.reactivex.Single

fun getNotificationsFromCache(): Maybe<List<String>> {
    return Maybe.just(listOf("This notifications are from cache."))
}

fun getNotificationsFromNetwork(): Single<List<String>> {
    return Single.just(listOf("This notifications are from network."))
}

data class User (val userId: String)

fun getUser() : Single<User> {
    return Single.just(User("123"))
}

fun getNotificationsForUser(userId: String ): Single<List<String>> {
    return Single.just(listOf("This notifications are from the user:$userId"))
}

fun getNotificationsFromTwitter(): Single<List<String>> {
    return Single.just(listOf("This notifications are from twitter."))
}

fun getNotificationsFromFacebook(): Single<List<String>> {
    return Single.just(listOf("This notifications are from facebook."))
}