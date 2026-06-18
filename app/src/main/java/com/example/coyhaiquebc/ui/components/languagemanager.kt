package com.example.coyhaiquebc.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

/**
 * Manager para manejar el cambio de idioma en la aplicación
 * Utiliza AppCompatDelegate para cambiar el idioma de forma nativa
 * No requiere reiniciar la app
 */
object LanguageManager {

    private const val PREFS_NAME = "language_prefs"
    private const val KEY_LANGUAGE = "selected_language"

    private var prefs: SharedPreferences? = null
    private var isInitialized = false

    /**
     * Inicializar el LanguageManager con el contexto de la aplicación
     * Llamar en Application.onCreate() o MainActivity.onCreate()
     */
    fun init(context: Context) {
        if (!isInitialized) {
            prefs = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            isInitialized = true
            println("✅ LanguageManager inicializado correctamente")
        }
    }

    /**
     * Cambiar el idioma de la aplicación
     * @param languageTag Código del idioma (ej: "es", "en")
     */
    fun setLanguage(languageTag: String) {
        if (!isInitialized) {
            println("❌ Error: LanguageManager no inicializado. Llama a init() primero.")
            return
        }

        if (languageTag.isEmpty()) {
            println("❌ Error: languageTag vacío")
            return
        }

        println("🔄 Cambiando idioma a: $languageTag")

        try {
            // 1. Cambiar idioma usando AppCompatDelegate
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(languageTag)
            )

            // 2. Verificar que se aplicó correctamente
            val appliedLocales = AppCompatDelegate.getApplicationLocales()
            val appliedLanguage = appliedLocales?.get(0)?.language ?: "desconocido"
            println("✅ Idioma aplicado: $appliedLanguage")

            // 3. Guardar la preferencia
            saveLanguagePreference(languageTag)
            println("✅ Preferencia guardada: $languageTag")

        } catch (e: Exception) {
            println("❌ Error al cambiar idioma: ${e.message}")
            e.printStackTrace()
        }
    }

    /**
     * Obtener el idioma actual guardado en preferencias
     * @return Código del idioma (ej: "es", "en")
     */
    fun getCurrentLanguage(): String {
        if (!isInitialized) {
            println("⚠️ LanguageManager no inicializado, retornando 'es' por defecto")
            return "es"
        }
        return getLanguagePreference()
    }

    /**
     * Obtener el idioma actual de la configuración de la app
     * @return Código del idioma
     */
    fun getAppLanguage(): String {
        return try {
            val locales = AppCompatDelegate.getApplicationLocales()
            locales?.get(0)?.language ?: "es"
        } catch (e: Exception) {
            "es"
        }
    }

    /**
     * Verificar si un idioma está disponible
     */
    fun isLanguageAvailable(languageTag: String): Boolean {
        return try {
            val locales = LocaleListCompat.forLanguageTags(languageTag)
            locales.size() > 0
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Obtener el nombre del idioma en su propio idioma
     */
    fun getLanguageDisplayName(languageTag: String): String {
        return when (languageTag) {
            "es" -> "Español"
            "en" -> "English"
            else -> languageTag
        }
    }

    /**
     * Obtener todos los idiomas soportados
     */
    fun getSupportedLanguages(): List<String> {
        return listOf("es", "en")
    }

    /**
     * Obtener todos los idiomas soportados con sus nombres
     */
    fun getSupportedLanguagesWithNames(): Map<String, String> {
        return mapOf(
            "es" to "Español",
            "en" to "English"
        )
    }

    /**
     * Aplicar el idioma guardado al contexto
     * Útil para forzar el idioma en Activities/Fragments
     */
    fun applySavedLanguage() {
        if (!isInitialized) {
            println("⚠️ LanguageManager no inicializado, no se puede aplicar idioma")
            return
        }

        val savedLanguage = getLanguagePreference()
        println("🔄 Aplicando idioma guardado: $savedLanguage")

        try {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(savedLanguage)
            )

            val appliedLocales = AppCompatDelegate.getApplicationLocales()
            val appliedLanguage = appliedLocales?.get(0)?.language ?: "desconocido"
            println("✅ Idioma aplicado desde preferencias: $appliedLanguage")

        } catch (e: Exception) {
            println("❌ Error al aplicar idioma guardado: ${e.message}")
            e.printStackTrace()
        }
    }

    /**
     * Guardar la preferencia de idioma en SharedPreferences
     */
    private fun saveLanguagePreference(languageTag: String) {
        try {
            prefs?.edit()
                ?.putString(KEY_LANGUAGE, languageTag)
                ?.apply()
        } catch (e: Exception) {
            println("❌ Error al guardar preferencia: ${e.message}")
            e.printStackTrace()
        }
    }

    /**
     * Obtener la preferencia de idioma guardada
     * @return Código del idioma guardado o "es" por defecto
     */
    private fun getLanguagePreference(): String {
        return try {
            prefs?.getString(KEY_LANGUAGE, "es") ?: "es"
        } catch (e: Exception) {
            "es"
        }
    }

    /**
     * Verificar si el LanguageManager está inicializado
     */
    fun isInitialized(): Boolean = isInitialized
}