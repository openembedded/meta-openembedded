SUMMARY = "sdbus-c++"
DESCRIPTION = "High-level C++ D-Bus library designed to provide easy-to-use yet powerful API in modern C++"

SECTION = "libs"

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=1803fa9c2c3ce8cb06b4861d75310742"

inherit cmake pkgconfig systemd ptest

PACKAGECONFIG ??= "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'with-external-libsystemd', 'with-builtin-libsystemd', d)} \
                   ${@bb.utils.contains('PTEST_ENABLED', '1', 'with-tests', '', d)}"
PACKAGECONFIG[with-builtin-libsystemd] = ",,sdbus-c++-libsystemd,libcap"
PACKAGECONFIG[with-external-libsystemd] = ",,systemd,libsystemd"
PACKAGECONFIG[with-tests] = "-DBUILD_TESTS=ON -DTESTS_INSTALL_PATH=${libdir}/${BPN}/tests,-DBUILD_TESTS=OFF,googletest gmock"

DEPENDS += "expat"

SRCREV = "751c1addc4fd2f949a466f488c1b7de2ca3b76dc"

SRC_URI = "git://github.com/Kistler-Group/sdbus-cpp.git;protocol=https;branch=master"
SRC_URI += "file://run-ptest"

EXTRA_OECMAKE = "-DBUILD_CODE_GEN=OFF \
                 -DBUILD_DOC=ON \
                 -DBUILD_DOXYGEN_DOC=OFF"

S = "${WORKDIR}/git"

# Link libatomic on architectures without 64bit atomics fixes
# libsdbus-c++.so.1.1.0: undefined reference to `__atomic_load_8'
LDFLAGS:append:mips = " -Wl,--no-as-needed -latomic -Wl,--as-needed"
LDFLAGS:append:powerpc = " -Wl,--no-as-needed -latomic -Wl,--as-needed"
LDFLAGS:append:riscv32 = " -Wl,--no-as-needed -latomic -Wl,--as-needed"

do_install:append() {
    if ! ${@bb.utils.contains('PTEST_ENABLED', '1', 'true', 'false', d)}; then
        rm -rf ${D}${sysconfdir}/dbus-1
    fi
}

PTEST_PATH = "${libdir}/${BPN}/tests"
FILES:${PN}-ptest =+ "${sysconfdir}/dbus-1/system.d/"
FILES:${PN}-dev += "${bindir}/sdbus-c++-xml2cpp"

# It adds -isystem which is spurious, no idea where it gets it from
CCACHE_DISABLE = "1"
