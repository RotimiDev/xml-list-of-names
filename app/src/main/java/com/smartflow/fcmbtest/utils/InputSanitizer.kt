package com.smartflow.fcmbtest.utils

object InputSanitizer {
    private const val MAX_LENGTH = 50
    private val DISALLOWED = Regex("[<>\"%;()&+=#@!*^|\\\\{}\\[\\]/]")

    fun sanitize(input: String): String {
        return input
            .trim()
            .replace(DISALLOWED, "")
            .replace(Regex("\\s{2,}"), " ")
            .take(MAX_LENGTH)
    }
}
