package com.example.coyhaiquebc.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

object LanguageManager {

    private const val PREFS_NAME = "language_prefs"
    private const val KEY_LANGUAGE = "selected_language"

    private var prefs: SharedPreferences? = null
    private var isInitialized = false


    fun init(context: Context) {
        if (!isInitialized) {
            prefs = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            isInitialized = true
            println("LanguageManager inicializado correctamente")
        }
    }


    fun setLanguage(languageTag: String) {
        if (!isInitialized) {
            println("Error: LanguageManager no inicializado. Llama a init() primero.")
            return
        }

        if (languageTag.isEmpty()) {
            println(" Error: languageTag vacío")
            return
        }

        println(" Cambiando idioma a: $languageTag")

        try {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(languageTag)
            )


            val appliedLocales = AppCompatDelegate.getApplicationLocales()
            val appliedLanguage = appliedLocales?.get(0)?.language ?: "desconocido"
            println("Idioma aplicado: $appliedLanguage")


            saveLanguagePreference(languageTag)
            println(" Preferencia guardada: $languageTag")

        } catch (e: Exception) {
            println("Error al cambiar idioma: ${e.message}")
            e.printStackTrace()
        }
    }


    fun getCurrentLanguage(): String {
        if (!isInitialized) {
            println(" LanguageManager no inicializado, retornando 'es' por defecto")
            return "es"
        }
        return getLanguagePreference()
    }


    fun getAppLanguage(): String {
        return try {
            val locales = AppCompatDelegate.getApplicationLocales()
            locales?.get(0)?.language ?: "es"
        } catch (e: Exception) {
            "es"
        }
    }


    fun isLanguageAvailable(languageTag: String): Boolean {
        return try {
            val locales = LocaleListCompat.forLanguageTags(languageTag)
            locales.size() > 0
        } catch (e: Exception) {
            false
        }
    }


    fun getLanguageDisplayName(languageTag: String): String {
        return when (languageTag) {
            "es" -> "Español"
            "en" -> "English"
            else -> languageTag
        }
    }


    fun getSupportedLanguages(): List<String> {
        return listOf("es", "en")
    }

    fun getSupportedLanguagesWithNames(): Map<String, String> {
        return mapOf(
            "es" to "Español",
            "en" to "English"
        )
    }


    fun applySavedLanguage() {
        if (!isInitialized) {
            println("⚠LanguageManager no inicializado, no se puede aplicar idioma")
            return
        }

        val savedLanguage = getLanguagePreference()
        println(" Aplicando idioma guardado: $savedLanguage")

        try {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(savedLanguage)
            )

            val appliedLocales = AppCompatDelegate.getApplicationLocales()
            val appliedLanguage = appliedLocales?.get(0)?.language ?: "desconocido"
            println(" Idioma aplicado desde preferencias: $appliedLanguage")

        } catch (e: Exception) {
            println(" Error al aplicar idioma guardado: ${e.message}")
            e.printStackTrace()
        }
    }


    private fun saveLanguagePreference(languageTag: String) {
        try {
            prefs?.edit()
                ?.putString(KEY_LANGUAGE, languageTag)
                ?.apply()
        } catch (e: Exception) {
            println(" Error al guardar preferencia: ${e.message}")
            e.printStackTrace()
        }
    }


    private fun getLanguagePreference(): String {
        return try {
            prefs?.getString(KEY_LANGUAGE, "es") ?: "es"
        } catch (e: Exception) {
            "es"
        }
    }


    fun isInitialized(): Boolean = isInitialized
}