SUMMARY = "Libyui is a widget abstraction library providing Qt, GTK and ncurses frontends."
LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = "file://COPYING.gpl-3;md5=d32239bcb673463ab874e80d47fae504 \
                    file://COPYING.lgpl-2.1;md5=4fbd65380cdd255951079008b364516c \
                    file://COPYING.lgpl-3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
                   "

SRC_URI = "git://github.com/libyui/libyui-old.git \
           file://0001-Fix-build-with-clang.patch \
           file://0001-Use-relative-install-paths-for-CMake.patch \
           "

SRCREV = "af06143017fc5f18c4b6488ba4aed26bcc43ff6e"

S = "${WORKDIR}/git"

inherit cmake gettext pkgconfig

DEPENDS += "boost"

EXTRA_OECMAKE += "-DCMAKE_BUILD_TYPE=RELWITHDEBINFO -DWERROR=OFF"

BBCLASSEXTEND = "native nativesdk"

do_install_append () {
        if [ "${libdir}" = "${base_prefix}/usr/lib" ] && [ -d ${D}/usr/lib64 ]; then
            mv ${D}/usr/lib64 ${D}/usr/lib
        fi
}

do_install_append_class-nativesdk () {
        mkdir -p ${D}/${base_prefix}
        mv ${D}/usr ${D}/${base_prefix}
}

FILES_${PN}-dev = "${libdir}/* ${includedir}/yui*"
