package com.deflatedpickle.speckle.impl

import com.deflatedpickle.speckle.api.Platform
import java.io.File

internal object Win32 : Platform {
    override fun getDataHome(): File {
        TODO("Not yet implemented")
    }

    override fun getConfigHome(): File {
        TODO("Not yet implemented")
    }

    override fun getCacheDir(): File {
        TODO("Not yet implemented")
    }

    override fun getStateDir(): File {
        TODO("Not yet implemented")
    }

    override fun getDocumentsFolder(): File {
        TODO("Not yet implemented")
    }

    override fun getDesktopFolder(): File {
        TODO("Not yet implemented")
    }

    override fun getPictureFolder(): File {
        TODO("Not yet implemented")
    }

    override fun getPublicFolder(): File {
        TODO("Not yet implemented")
    }

    override fun getDownloadFolder(): File {
        TODO("Not yet implemented")
    }

    override fun getMusicFolder(): File {
        TODO("Not yet implemented")
    }

    override fun getVideoFolder(): File {
        TODO("Not yet implemented")
    }
}