SUMMARY = "Character Based User Interface for libyui"
LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = "file://COPYING.lgpl-3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://COPYING.lgpl-2.1;md5=4fbd65380cdd255951079008b364516c \
"

SRC_URI = "git://github.com/libyui/libyui-ncurses.git \
           file://0001-Use-override-consistently.patch \
           file://0002-Define-own-resize-in-NCPad-and-fix-param-type-of-Set.patch \
           file://0003-Simplify-ncurses-finding-module.patch \
          "

SRC_URI_append_class-target = " file://0001-Fix-the-error-of-can-t-find-header-file.patch"

PV = "2.48.3+git${SRCPV}"
SRCREV = "c941b32246e8b2952fce4fd5743f8e318222ab98"

S = "${WORKDIR}/git"

inherit cmake gettext pkgconfig

DEPENDS += "boost libyui ncurses"

BBCLASSEXTEND = "nativesdk"

do_configure_prepend () {
    cd ${S}
    git checkout bootstrap.sh
    sed -i "s#/usr#${PKG_CONFIG_SYSROOT_DIR}${base_prefix}&#" bootstrap.sh
    ./bootstrap.sh
    mkdir -p ${PKG_CONFIG_SYSROOT_DIR}${base_prefix}${libdir}64/
    cp ${PKG_CONFIG_SYSROOT_DIR}${base_prefix}${libdir}/libyui.so* ${PKG_CONFIG_SYSROOT_DIR}${base_prefix}${libdir}64/
    cd -
    sed -i -e "s#\${YPREFIX}#\${PKG_CONFIG_SYSROOT_DIR}${base_prefix}&#" ${S}/CMakeLists.txt
    sed -i -e "s#/usr#${PKG_CONFIG_SYSROOT_DIR}${base_prefix}&#" ${PKG_CONFIG_SYSROOT_DIR}${libdir}/cmake/libyui/LibyuiLibraryDepends-release.cmake
}

do_install_append () {
    if [ "${libdir}" = "${base_prefix}${libdir}" ] && [ -d ${D}${libdir}64 ]; then
        mv ${D}${libdir}64 ${D}${libdir}
    fi
}

do_install_append_class-nativesdk () {
    mkdir -p ${D}/${base_prefix}
    mv ${D}/usr ${D}/${base_prefix}
}

FILES_${PN} += "${datadir}/*"

FILES_${PN}-dev += "${libdir}/*"
