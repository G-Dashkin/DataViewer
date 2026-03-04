package com.dashkin.dataviewer.core.utils

private const val BYTES_IN_KB = 1024L
private const val BYTES_IN_MB = BYTES_IN_KB * 1024L
private const val BYTES_IN_GB = BYTES_IN_MB * 1024L

/**
 * Formats a byte count into a human-readable string (e.g., "12.3 MB").
 */
fun formatFileSize(bytes: Long): String = when {
    bytes >= BYTES_IN_GB -> "%.1f GB".format(bytes.toDouble() / BYTES_IN_GB)
    bytes >= BYTES_IN_MB -> "%.1f MB".format(bytes.toDouble() / BYTES_IN_MB)
    bytes >= BYTES_IN_KB -> "%.1f KB".format(bytes.toDouble() / BYTES_IN_KB)
    else -> "$bytes B"
}

/**
 * Formats a child count with thousands separators (e.g., "1,024 items").
 */
fun formatChildCount(count: Int): String =
    "%,d item%s".format(count, if (count == 1) "" else "s")
