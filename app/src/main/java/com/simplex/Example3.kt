package com.simplex

import io.reactivex.Single
import io.reactivex.functions.BiFunction

fun main() {

    val twitterSource: Single<List<String>> = getNotificationsFromTwitter()
    val facebookSource: Single<List<String>> = getNotificationsFromFacebook()
    val result = Single.zip(twitterSource, facebookSource,
        BiFunction { twitterNotifications: List<String>, facebookNotifications: List<String> ->
            val allNotifications = ArrayList<String>()
            allNotifications.addAll(twitterNotifications)
            allNotifications.addAll(facebookNotifications)
            allNotifications
        }
    )

    result.subscribe { it ->
        println(it)
    }

}
