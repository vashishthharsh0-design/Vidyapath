package com.example.model

data class NoteCruxSummary(
    val title: String,
    val executiveCrux: String,
    val keyHighlights: List<String>,
    val examCues: List<String> = emptyList(),
    val highYieldFormulasOrTerms: List<String> = emptyList(),
    val modelUsed: String = "gemini-3.5-flash",
    val isOfflineGenerated: Boolean = false
) {
    /**
     * Formats the executive crux, key highlights, and formulas into a clean,
     * structured Markdown text suitable for the note summary section.
     */
    fun toFormattedSummary(): String = buildString {
        appendLine("⚡ EXECUTIVE CRUX:")
        appendLine(executiveCrux)
        if (keyHighlights.isNotEmpty()) {
            appendLine()
            appendLine("📌 KEY HIGHLIGHTS:")
            keyHighlights.forEach { highlight ->
                val clean = highlight.trim().removePrefix("•").removePrefix("-").trim()
                appendLine("• $clean")
            }
        }
        if (highYieldFormulasOrTerms.isNotEmpty()) {
            appendLine()
            appendLine("📐 ESSENTIAL FORMULAS / TERMS:")
            highYieldFormulasOrTerms.forEach { term ->
                val clean = term.trim().removePrefix("•").removePrefix("-").trim()
                appendLine("• $clean")
            }
        }
    }.trim()

    /**
     * Formats the exam cues into Cornell note cue format
     */
    fun toFormattedCues(): String = buildString {
        examCues.forEach { cue ->
            val clean = cue.trim().removePrefix("?").removePrefix("•").removePrefix("-").trim()
            appendLine("? $clean")
        }
    }.trim()
}
