package com.simplex

import io.reactivex.Single

fun main() {

    val user : Single<User> = getUser()

    val result =  user.flatMap { user-> getNotificationsForUser(user.userId) }

    result.subscribe {
            it ->
        println(it)
    }

}