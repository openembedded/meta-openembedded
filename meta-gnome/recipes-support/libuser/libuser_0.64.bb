SUMMARY = "user and group account administration library"
DESCRIPTION = "The libuser library implements a standardized interface for manipulating and administering user \
and group accounts"
HOMEPAGE = "https://codeberg.org/libuser/libuser"
BUGTRACKER = "https://codeberg.org/libuser/libuser/issues"

LICENSE = "LGPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=5f30f0716dfdd0d91eb439ebec522ec2 \
                    file://lib/user.h;endline=19;md5=76b301f63c39fa992062395efbdc9558 \
                    file://samples/testuser.c;endline=19;md5=3b87fa660fa3f4a6bb31d624afe30ba1"

SECTION = "base"

SRC_URI = "git://codeberg.org/libuser/libuser.git;protocol=https;branch=master;tag=${BP} \
           file://0001-docs-Disable-building.patch \
           file://0002-remove-unused-execinfo.h.patch \
           file://gtkdoc.patch \
           file://0001-configure.ac-update-version-to-0.64.patch \
           "

SRCREV = "ce81277cb5cd5235e22be453aa496e3f4190724c"

UPSTREAM_CHECK_GITTAGREGEX = "^libuser-(?P<pver>\d+(\.\d+)+)$"

DEPENDS = "bison-native popt libpam glib-2.0 python3"

inherit autotools features_check gettext python3native python3-dir pkgconfig gtk-doc

REQUIRED_DISTRO_FEATURES = "pam"

EXTRA_OEMAKE = "PYTHON_CPPFLAGS=-I${STAGING_INCDIR}/${PYTHON_DIR}${PYTHON_ABI}"

do_configure:prepend() {
    install -d ${S}/admin -d ${S}/m4
    touch ${S}/ABOUT-NLS ${S}/admin/config.rpath
    cd ${S}
    bison lib/getdate.y -o lib/getdate.c
    cd -
}

PACKAGES += "${PN}-python "

FILES:${PN}-python = "${PYTHON_SITEPACKAGES_DIR}"

