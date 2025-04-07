SUMMARY = "A C++ static library offering a clean and simple interface to the 7-Zip shared libraries"
HOMEPAGE = "https://github.com/rikyoz/bit7z"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=48a3fe23ed1353e0995dadfda05ffdb6"

SRC_URI = " \
    git://github.com/rikyoz/bit7z.git;protocol=https;branch=master \
    file://0001-cmake-disable-dependency-inclusion.patch \
    file://0001-Fix-reinterpret-cast-compiler-errors.patch \
    file://0001-Fix-int8_t-storage-in-BitPropVariant-on-Arm-architec.patch \
"

SRCREV = "386e00ad3286e7a10e5bb6d05a5b41b523fce623"

S = "${WORKDIR}/git"

inherit cmake

DEPENDS = "7zip"

EXTRA_OECMAKE += "-DBIT7Z_CUSTOM_7ZIP_PATH=${STAGING_INCDIR}/7zip"

do_install() {
    install -d ${D}${libdir}
    install -m 0644 ${S}/lib/*/*.a ${D}${libdir}

    install -d ${D}${includedir}/${BPN}
    install -m 0644 ${S}/include/${BPN}/*.hpp ${D}${includedir}/${BPN}
}
