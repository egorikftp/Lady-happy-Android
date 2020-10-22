package com.egoriku.ladyhappy.koin

import android.content.Context
import com.egoriku.extensions.common.Constants.EMPTY
import com.egoriku.extensions.logDm
import com.egoriku.extensions.second
import com.egoriku.ladyhappy.beagle.Account
import java.util.*

@Suppress("UNCHECKED_CAST")
class AccountProvider(context: Context) {

    private val koinPropertyFile = "koin.properties"

    val accounts = mutableListOf<Account>()

    init {
        if (context.assets?.list(EMPTY)?.contains(koinPropertyFile) == true) {
            runCatching {
                val koinProperties = Properties()

                context.assets.open(koinPropertyFile).use { koinProperties.load(it) }

                val accountsFromProperties = (koinProperties.toMap() as Map<String, String>)
                        .map { it.value.split(",") }
                        .map { Account(it.first(), it.second()) }

                accounts.addAll(accountsFromProperties)
            }.getOrElse {
                logDm(it.message)
            }
        }
    }
}