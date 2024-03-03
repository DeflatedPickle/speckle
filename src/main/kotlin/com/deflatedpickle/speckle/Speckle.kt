package com.deflatedpickle.speckle

import com.deflatedpickle.speckle.impl.Linux
import com.deflatedpickle.speckle.impl.Mac
import com.deflatedpickle.speckle.impl.Win32
import com.sun.jna.Platform
import java.io.File
import com.deflatedpickle.speckle.api.Platform as SpecklePlatform

object Speckle {
    private val platform: SpecklePlatform = when {
        Platform.isLinux() -> Linux
        Platform.isWindows() -> Win32
        Platform.isMac() -> Mac
        else -> throw NotImplementedError()
    }

    val home = File(System.getProperty("user.home"))
    val config
        get() = platform.getConfigHome()
    val data
        get() = platform.getDataHome()
    val state
        get() = platform.getStateDir()
    val cache
        get() = platform.getCacheDir()

    val documents
        get() = platform.getDocumentsFolder()
    val desktop
        get() = platform.getDesktopFolder()
    val pictures
        get() = platform.getPictureFolder()
    val public
        get() = platform.getPublicFolder()
    val music
        get() = platform.getMusicFolder()
    val video
        get() = platform.getVideoFolder()
    val download
        get() = platform.getDownloadFolder()
}