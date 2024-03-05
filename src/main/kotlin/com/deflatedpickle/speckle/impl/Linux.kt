package com.deflatedpickle.speckle.impl

import com.deflatedpickle.speckle.api.Platform
import com.sun.jna.platform.linux.LibC
import java.io.File
import java.io.IOException

internal object Linux : Platform {
    // https://wiki.archlinux.org/title/XDG_user_directories
    private val userDirs = expandvars("~/.config/user-dirs.dirs")
    private val folders =
        File(userDirs)
            .readLines()
            .dropWhile { it.startsWith("#") }
            .distinct()
            .associate {
                val (left, right) = it.split("=")
                left to right
                    .replace("\"", "")
                    .replace("\$HOME", System.getProperty("user.home"))
            }

    // https://specifications.freedesktop.org/basedir-spec/latest/ar01s03.html
    private val defaultDirs = mapOf(
        "XDG_DATA_HOME" to "~/.local/share",
        "XDG_CONFIG_HOME" to "~/.config",
        "XDG_CACHE_HOME" to "~/.cache",
        "XDG_STATE_HOME" to "~/.local/state",
        "XDG_DOCUMENTS_DIR" to "~/Documents",
        "XDG_DESKTOP_DIR" to "~/Desktop",
        "XDG_PICTURES_DIR" to "~/Pictures",
        "XDG_PUBLICSHARE_DIR" to "~/Public",
        "XDG_DOWNLOAD_DIR" to "~/Downloads",
        "XDG_MUSIC_DIR" to "~/Music",
        "XDG_VIDEOS_DIR" to "~/Videos",
    )

    // todo: proper implementation
    // https://www.gnu.org/software/bash/manual/html_node/Shell-Parameter-Expansion.html
    private fun expandvars(path: String): String =
        path.replace("~", System.getProperty("user.home"))

    override fun getDataHome() =
        File(getLinuxFolderDefault("XDG_DATA_HOME"))

    override fun getConfigHome() =
        File(getLinuxFolderDefault("XDG_CONFIG_HOME"))

    override fun getCacheDir() =
        File(getLinuxFolderDefault("XDG_CACHE_HOME"))

    // todo: XDG_RUNTIME_DIR not exposed

    override fun getStateDir() =
        File(getLinuxFolderDefault("XDG_STATE_HOME"))

    override fun getDocumentsFolder() =
        File(getLinuxFolderDefault("XDG_DOCUMENTS_DIR"))

    override fun getDesktopFolder() =
        File(getLinuxFolderDefault("XDG_DESKTOP_DIR"))

    override fun getPictureFolder() =
        File(getLinuxFolderDefault("XDG_PICTURES_DIR"))

    override fun getPublicFolder() =
        File(getLinuxFolderDefault("XDG_PUBLICSHARE_DIR"))

    override fun getDownloadFolder() =
        File(getLinuxFolderDefault("XDG_DOWNLOAD_DIR"))

    override fun getMusicFolder() =
        File(getLinuxFolderDefault("XDG_MUSIC_DIR"))

    override fun getVideoFolder() =
        File(getLinuxFolderDefault("XDG_VIDEOS_DIR"))

    // todo: XDG_TEMPLATES_DIR not exposed

    // 1. look for an environment variable
    // 2. try to use xdg-user-dir
    // 3. read from ~/.config/user-dirs.dirs
    // 4. use a default value
    internal fun getLinuxFolderDefault(dir: String): String {
        val env = System.getenv(dir)
        if (env.isNullOrEmpty()) {
            try {
                if (dir !in defaultDirs.keys) throw IOException()
                val exec = Runtime.getRuntime().exec(
                    arrayOf(
                        "xdg-user-dir", dir
                            .replace("XDG_", "")
                            .replace("_DIR", "")
                    )
                )
                exec.waitFor()
                return exec.inputStream.bufferedReader().readLine()
            } catch (_: IOException) {
            }

            val entry = folders[dir]
            return if (entry.isNullOrEmpty()) {
                expandvars(defaultDirs[dir]!!)
            } else {
                entry
            }
        } else {
            return env
        }
    }

    // todo: recent files
    // https://specifications.freedesktop.org/recent-file-spec/recent-file-spec-latest.html
}