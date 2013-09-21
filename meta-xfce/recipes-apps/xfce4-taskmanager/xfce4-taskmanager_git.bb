DESCRIPTION = "Easy to use task manager"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit xfce-app

DEPENDS = "gtk+ cairo libwnck xfce4-dev-tools-native exo-native"

SRC_URI = " \
    git://git.xfce.org/apps/xfce4-taskmanager;protocol=git \
    file://0001-do-not-build-documantation-Makefile-has-no-install-t.patch \
"

SRCREV = "e5d958738b6d2828a4c8353df67d3c1656c46af5"
S = "${WORKDIR}/git"
PV = "1.0.0+git${SRCPV}"

EXTRA_OECONF += "--enable-maintainer-mode"

do_configure_prepend() {
       NOCONFIGURE=yes ./autogen.sh
}
