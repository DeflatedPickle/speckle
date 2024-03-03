package com.deflatedpickle.speckle.impl

import kotlin.test.Test

class LinuxTest {
    @Test
    fun `getLinuxFolderDefault$speckle`() {
        println(Linux.getLinuxFolderDefault("XDG_DESKTOP_DIR"))
    }
}