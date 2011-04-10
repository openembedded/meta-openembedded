DESCRIPTION = "LZMA is a general compression method. LZMA provides high compression ratio and very fast decompression."
HOMEPAGE = "http://www.7-zip.org/"
LICENSE = "LGPL"
LIC_FILES_CHKSUM = "file://lzma.txt;md5=20251cdc2e3793cceab11878d0aa11b1"
INC_PR = "r6"

SRC_URI = "http://downloads.sourceforge.net/sevenzip/lzma${@bb.data.getVar('PV',d,1).replace('.','')}.tar.bz2;subdir=${BPN}-${PV} \
           file://001-large_files.patch \
           file://002-lzmp.patch \
           file://003-compile_fixes.patch \
           file://100-static_library.patch \
           file://makefile-cleanup.patch"

EXTRA_OEMAKE = "-f makefile.gcc"

do_unpack_append() {
        import oe.process
        # Replace MS-DOS line-endings with Unix style line-endings
        oe.process.run("find . -type f -print0 | xargs -0 sed 's/\r$//' -i",
                       cwd=d.getVar("S", True))
}

do_compile() {
        oe_runmake CFLAGS='${CFLAGS} -c' -C C/LzmaUtil
        oe_runmake CXX_C='${CC} ${CFLAGS}' CXX='${CXX} ${CXXFLAGS}' \
		-C CPP/7zip/Compress/LZMA_Alone
}

do_install() {
        install -d ${D}${bindir} ${D}${libdir}
        install -m 0755 CPP/7zip/Compress/LZMA_Alone/lzma ${D}${bindir}
        oe_libinstall -a -C C/LzmaUtil liblzma ${D}${libdir}
}

NATIVE_INSTALL_WORKS = "1"
BBCLASSEXTEND = "native"
