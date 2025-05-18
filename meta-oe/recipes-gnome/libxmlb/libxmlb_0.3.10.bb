SUMMARY = "A library to help create and query binary XML blobs"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1803fa9c2c3ce8cb06b4861d75310742"

SRC_URI = "git://github.com/hughsie/libxmlb.git;branch=main;protocol=https \
           file://run-ptest \
           "
SRCREV = "a6cac6a715d57c393bbddee4d0381ad943341af9"
S = "${WORKDIR}/git"

inherit gobject-introspection gtk-doc meson ptest-gnome lib_package

PACKAGECONFIG ??= "\
    ${@bb.utils.contains('PTEST_ENABLED', '1', 'tests', '', d)} \
"
PACKAGECONFIG[tests] = "-Dtests=true,-Dtests=false"

GTKDOC_MESON_OPTION = "gtkdoc"

FILES:${PN}-bin += "${libexecdir}/*"

BBCLASSEXTEND = "native"
