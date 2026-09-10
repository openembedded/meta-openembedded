# Copyright (c) Qualcomm Technologies, Inc. and/or its subsidiaries.
# SPDX-License-Identifier: BSD-3-Clause-Clear

SUMMARY     = "fastText + Bloom embeddings for compact, full-coverage vectors"
DESCRIPTION = "\
    floret is a fastText fork with Bloom embeddings for compact, \
    full-coverage vectors compatible with spaCy. It is designed \
    specifically for use on small, low-powered, embedded devices \
    where memory efficiency and full vocabulary coverage are required. \
"
HOMEPAGE   = "https://github.com/explosion/floret"

LICENSE          = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4ce16d1d2b4c556959d654417007b713"

DEPENDS = "zlib"

SRC_URI = "git://github.com/explosion/floret.git;protocol=https;branch=main \
           file://0001-PATCH-floret-Adjust-CMake-flags-for-cross-compilation.patch"

SRCREV = "${AUTOREV}"

inherit cmake

# Remove -march=native: invalid for cross-compilation
CXXFLAGS:remove = "-march=native"
CXXFLAGS       += "-std=c++17"

EXTRA_OECMAKE = "\
    -DCMAKE_CXX_STANDARD=17 \
    -DBUILD_SHARED_LIBS=ON \
"

do_install() {
    install -d ${D}${libdir}
    install -d ${D}${includedir}/floret
    install -d ${D}${bindir}
    install -d ${D}${libdir}/pkgconfig

    # Install shared library with versioned soname
    if [ -f ${B}/libfloret.so ]; then
        install -m 0755 ${B}/libfloret.so ${D}${libdir}/libfloret.so.${PV}
        ln -sf libfloret.so.${PV} ${D}${libdir}/libfloret.so.1
        ln -sf libfloret.so.1     ${D}${libdir}/libfloret.so
    fi

    # Install shared library
    if [ -f ${B}/libfloret.so ]; then
        install -m 0755 ${B}/libfloret.so ${D}${libdir}/libfloret.so
    fi

    # Install static library
    if [ -f ${B}/libfloret.a ]; then
        install -m 0644 ${B}/libfloret.a ${D}${libdir}/libfloret.a
    fi

    # Install floret CLI binary
    if [ -f ${B}/floret ]; then
        install -m 0755 ${B}/floret ${D}${bindir}/floret
    fi

    # Install headers
    if [ -d ${S}/src ]; then
        cp -r ${S}/src/*.h ${D}${includedir}/floret/
    fi

    # Generate and install pkg-config file
    cat > ${D}${libdir}/pkgconfig/floret.pc << EOF
prefix=/usr
exec_prefix=\${prefix}
libdir=\${exec_prefix}/lib
includedir=\${prefix}/include

Name: floret
Description: fastText + Bloom embeddings for compact, full-coverage vectors
Version: ${PV}
Libs: -L\${libdir} -lfloret
Cflags: -I\${includedir}/floret
EOF
}

PACKAGES =+ "${PN}-cli"

FILES:${PN}-cli       = "${bindir}/floret"
FILES:${PN}-dev      += "${libdir}/libfloret.so ${includedir}/floret ${libdir}/pkgconfig/floret.pc"
FILES:${PN}-staticdev = "${libdir}/libfloret.a"
FILES:${PN}          += "${libdir}/libfloret.so.*"

# Skip buildpaths/dev-elf QA on this recipe
# libs reference TMPDIR / are non-symlink .so
INSANE_SKIP:${PN}           += "buildpaths"
INSANE_SKIP:${PN}-dev       += "buildpaths dev-elf"
INSANE_SKIP:${PN}-staticdev += "buildpaths"
INSANE_SKIP:${PN}-cli       += "buildpaths"

SUMMARY:${PN}           = "floret runtime library"
SUMMARY:${PN}-cli       = "floret command-line tool for training Bloom embeddings"
SUMMARY:${PN}-dev       = "floret development headers and shared library"
SUMMARY:${PN}-staticdev = "floret static library"