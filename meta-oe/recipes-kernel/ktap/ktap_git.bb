# Released under the MIT license (see COPYING.MIT for the terms)

require ktap.inc

SUMMARY = "KTAP is a scripting dynamic tracing tool for Linux"
DEPENDS = "ktap-module"

#Available package configs:
# libelf - needed to resolve symbols in DSO and for sdt
# ffi    - only supports x86_64 for now!. Needs to be enabled for ktap-module too.
PACKAGECONFIG ?= "libelf"

PACKAGECONFIG[libelf] = ",,elfutils"

# Only build the userspace app
EXTRA_OEMAKE = "${@base_contains('PACKAGECONFIG', 'libelf', '', 'NO_LIBELF=1', d)} \
             ${@base_contains('PACKAGECONFIG', 'ffi', 'FFI=1', '', d)} \
             ktap"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/ktap ${D}${bindir}/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
RDEPENDS_${PN} = "kernel-module-ktapvm"
