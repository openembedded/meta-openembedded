# Copyright (C) 2022 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "API to access the kernel tracefs directory"
HOMEPAGE = "https://git.kernel.org/pub/scm/libs/libtrace/libtracefs.git/"
LICENSE = "GPL-2.0-or-later & LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0;md5=e6a75371ba4d16749254a51215d13f97 \
                    file://LICENSES/LGPL-2.1;md5=b370887980db5dd40659b50909238dbd"
SECTION = "libs"

SRCREV = "4e51588b1f4d6617df7e39a38acc0d0daa3d8a3f"
SRC_URI = "git://git.kernel.org/pub/scm/libs/libtrace/libtraceevent.git;branch=${BPN};protocol=https \
           file://0001-makefile-Do-not-preserve-ownership-in-cp-command.patch"

S = "${WORKDIR}/git"

inherit pkgconfig

do_install() {
    oe_runmake install DESTDIR=${D} pkgconfig_dir=${libdir}/pkgconfig
}

PACKAGES += "${PN}-plugins"

FILES:${PN}-plugins += "${libdir}/traceevent/plugins"
