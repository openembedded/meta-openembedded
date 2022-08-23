SUMMARY = "Babl is a dynamic, any to any, pixel format conversion library"
LICENSE = "LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6a6a8e020838b23406c81b19c1d46df6"

GNOMEBASEBUILDCLASS = "meson"

GIR_MESON_OPTION = "enable-gir"

inherit setuptools3 gnomebase gobject-introspection vala

DEPENDS += "lcms"

# https://bugs.llvm.org/show_bug.cgi?id=45555
CFLAGS:append:toolchain-clang:mipsarch = " -ffp-exception-behavior=ignore "
CFLAGS:append:toolchain-clang:riscv64 = " -ffp-exception-behavior=ignore "

SRC_URI = "https://download.gimp.org/pub/${BPN}/0.1/${BP}.tar.xz \
           file://0001-meson-Do-not-run-git-rev-parse-during-configure.patch \
           "
SRC_URI[sha256sum] = "b6a8b28f55e0c17f5031fb7959e72ffe0fbf8196d1968ad6efc98d1b492c3bbe"

BBCLASSEXTEND = "native"
