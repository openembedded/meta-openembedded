SUMMARY = "Userspace interface to the kernel DRM services"
DESCRIPTION = "The runtime library for accessing the kernel DRM services.  DRM \
stands for \"Direct Rendering Manager\", which is the kernel portion of the \
\"Direct Rendering Infrastructure\" (DRI).  DRI is required for many hardware \
accelerated OpenGL drivers."
HOMEPAGE = "http://dri.freedesktop.org"
SECTION = "x11/base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://xf86drm.c;beginline=9;endline=32;md5=c8a3b961af7667c530816761e949dc71"
SRC_URI = "http://dri.freedesktop.org/libdrm/libdrm-${PV}.tar.bz2"
PROVIDES = "drm"
DEPENDS = "libpthread-stubs udev cairo virtual/libx11"

inherit autotools pkgconfig

PACKAGES =+ "${PN}-tests ${PN}-drivers ${PN}-kms ${@base_contains('MACHINE_FEATURES', 'x86', '${PN}-intel', '',d)}"
FILES_${PN}-tests = "${bindir}/dr* ${bindir}/mode*"
FILES_${PN}-drivers = "${libdir}/libdrm_*.so.*"
FILES_${PN}-intel = "${libdir}/libdrm_intel.so.*"
FILES_${PN}-kms = "${libdir}/libkms*.so.*"

LEAD_SONAME = "libdrm.so"

EXTRA_OECONF_append = " ${@base_contains('MACHINE_FEATURES', 'x86', '', '--disable-intel --disable-radeon',d)}"
EXTRA_OECONF_append_shr = " --enable-glamo-experimental-api"

PR = "r8"

SRC_URI += "file://installtests.patch"
SRC_URI += "file://glamo.patch"

SRC_URI[md5sum] = "8d802bf3b368f9fac0d7d17516a9436f"
SRC_URI[sha256sum] = "c7012381f64458af9f291d913309448aac7dd23a28dc86c6970e4bf38effb6a5"

do_compile_prepend_libc-uclibc() {
	eval "${@base_contains('DISTRO_FEATURES', 'largefile', '', 'sed -i -e "/_FILE_OFFSET_BITS/d" ${S}/libkms/intel.c', d)}"
	eval "${@base_contains('DISTRO_FEATURES', 'largefile', '', 'sed -i -e "/_FILE_OFFSET_BITS/d" ${S}/libkms/vmwgfx.c', d)}"
	eval "${@base_contains('DISTRO_FEATURES', 'largefile', '', 'sed -i -e "/_FILE_OFFSET_BITS/d" ${S}/libkms/nouveau.c', d)}"
}
