SUMMARY = "Tools to manipulate UEFI variables"
DESCRIPTION = "efivar provides a simple command line interface to the UEFI variable facility"
HOMEPAGE = "https://github.com/rhinstaller/efivar"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=6626bb1e20189cfa95f2c508ba286393"

DEPENDS_class-target = "popt efivar-native"

SRCREV = "01abee43d6d6e755f56a4135ab5aa0bfad609ce2"
SRC_URI = "git://github.com/rhinstaller/efivar.git"
SRC_URI_append_class-target = " file://0001-efivar-fix-for-cross-compile.patch \
                                file://0002-disable-static-build.patch \
                              "

S = "${WORKDIR}/git"

# Setting CROSS_COMPILE breaks pkgconfig, so just set AR
EXTRA_OEMAKE = "AR=${TARGET_PREFIX}gcc-ar"

do_compile_prepend() {
    sed -i -e s:-Werror::g ${S}/gcc.specs
}

do_compile_class-native() {
    oe_runmake -C src makeguids
}

do_install() {
    oe_runmake install DESTDIR=${D}
}

do_install_class-native() {
    install -D -m 0755 ${B}/src/makeguids ${D}${bindir}/makeguids
}

BBCLASSEXTEND = "native"

