package com.simplex

import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.functions.BiFunction

fun main(args: Array<String>){

    exampleOf("concat") {

        val cache : Maybe<List<String>> = getNotificationsFromCache()

        val network : Single<List<String>> = getNotificationsFromNetwork()

        val result =  Maybe.concat(cache,network.toMaybe()).firstElement()

        result.subscribe {
            println(it)
        }
    }

    exampleOf("flatMap") {

        val user : Single<User> = getUser()

        val result =  user.flatMap { user-> getNotificationsForUser(user.userId) }

        result.subscribe {
            it ->
            println(it)
        }
    }

    exampleOf("zip") {

        val source1 : Single<List<String>> = getNotificationsFromNetwork()
        val source2 : Single<List<String>> = getNotificationsFromNetwork()
        val result =  Single.zip( source1, source2,
                BiFunction { twitterNotifications: List<String>, facebookNotifications: List<String> ->
                    val allNotifications = ArrayList<String>()
                    allNotifications.addAll(twitterNotifications)
                    allNotifications.addAll(facebookNotifications)
                    allNotifications
                }
        )

        result.subscribe {
            it ->
            println(it)
        }
    }

}

fun exampleOf(description: String, action: () -> Unit) {
    println("\n--- Example of: $description ---")
    action()
}

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
    return Single.just(listOf("This notifications are from the user."))
}