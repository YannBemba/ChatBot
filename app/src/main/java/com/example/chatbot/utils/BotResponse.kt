package com.example.chatbot.utils

import com.example.chatbot.utils.Constants.OPEN_GOOGLE
import com.example.chatbot.utils.Constants.OPEN_SEARCH
import java.lang.Exception
import java.util.*

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message = _message.toLowerCase(Locale.ROOT)

        return when {

            //Bonjour
            message.contains("Bonjour") -> {
                when(random){
                    0 -> "Bonjour toi :O"
                    1 -> "Salut"
                    2 -> "Whassup"
                    else -> "Erreur"
                }
            }

            //Comment tu vas ?
            message.contains("Comment tu vas ?") -> {
                when(random){
                    0 -> "Tout va bien, merci de demander"
                    1 -> "J'ai trop faim !!"
                    2 -> "J'ai mal au CPU"
                    else -> "Erreur"
                }
            }

            message.contains("Calculer") -> {
                val calcul: String = message.substringAfter("calculer")

                return try {
                    val reponse = MathSolving.solveMath(calcul ?: "0")
                    reponse.toString()
                } catch (e: Exception) {
                    "Désolé, je ne pas résoudre ça :O"
                }
            }

            // Avoir le temps actuel
            message.contains("temp") && message.contains("?") -> {
                Time.timeStamp()
            }

            // Ouvrir Google
            message.contains("Ouvrir") && message.contains("Google") -> {
                OPEN_GOOGLE
            }

            // Ouvrir "recherche"
            message.contains("Rechercher") -> {
                OPEN_SEARCH
            }

            // l'utilisateur tape un message que le bot ne comprend pas
            else -> {
                when(random){
                    0 -> "Je ne comprends pas..."
                    1 -> "euh.. :O"
                    2 -> "Demande moi quelque chose d'autre ?"
                    else -> "Erreur"
                }
            }
        }

    }

}