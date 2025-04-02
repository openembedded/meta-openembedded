SUMMARY = "Zlib manipulation Library"
DESCRIPTION = "minizip-ng is a zip manipulation library written in C that is supported \
on Windows, macOS, and Linux."
HOMEPAGE = "https://github.com/zlib-ng/minizip-ng"
SECTION = "libs"
LICENSE = "Zlib"
LIC_FILES_CHKSUM = "file://LICENSE;md5=36964f044224efeedd694983c34e716f"

SRC_URI = "git://github.com/zlib-ng/minizip-ng.git;protocol=https;branch=master \
           file://0001-crypt.h-Remove-register-keyword.patch \
"

SRCREV = "55db144e03027b43263e5ebcb599bf0878ba58de"

S = "${WORKDIR}/git"

RCONFLICTS:${PN} += "minizip"

DEPENDS = "xz openssl bzip2"

inherit cmake pkgconfig

EXTRA_OECMAKE = "-DMZ_FORCE_FETCH_LIBS=OFF \
                 -DBUILD_SHARED_LIBS=ON \
"

PACKAGECONFIG ??= "zlib"
PACKAGECONFIG[zlib] = "-DMZ_ZLIB=ON,-DMZ_ZLIB=OFF, zlib"
PACKAGECONFIG[zlib-ng] = "-DMZ_ZLIB=ON,-DMZ_ZLIB=OFF, zlib-ng"


do_install:append () {
    # remove absolute paths
    sed -i -e 's|${RECIPE_SYSROOT}||g' ${D}${libdir}/cmake/minizip/minizip.cmake
}

