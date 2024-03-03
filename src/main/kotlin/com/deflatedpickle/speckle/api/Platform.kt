package com.deflatedpickle.speckle.api

import java.io.File

interface Platform {
    fun getDataHome(): File
    fun getConfigHome(): File
    fun getCacheDir(): File
    fun getStateDir(): File

    fun getDocumentsFolder(): File
    fun getDesktopFolder(): File
    fun getPictureFolder(): File
    fun getPublicFolder(): File
    fun getDownloadFolder(): File
    fun getMusicFolder(): File
    fun getVideoFolder(): File
}