SUMMARY = "Hierarchical, reference counted memory pool system with destructors"
HOMEPAGE = "http://tevent.samba.org"
SECTION = "libs"
LICENSE = "LGPLv3+"

DEPENDS += "libbsd libtalloc"
RDEPENDS_python-tevent = "python"

SRC_URI = "http://samba.org/ftp/tevent/tevent-${PV}.tar.gz"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/LGPL-3.0;md5=bfccfe952269fff2b407dd11f2f3083b"

SRC_URI[md5sum] = "8d01a2076cb8cd30cab40393d27043df"
SRC_URI[sha256sum] = "fedeb0d55a11b3593b562ec09b32e44bd67619ed10e5fa10d1868adb1649c669"

inherit waf-samba

PACKAGECONFIG[attr] = ",,attr"

SRC_URI += "${@bb.utils.contains('PACKAGECONFIG', 'attr', '', 'file://avoid-attr-unless-wanted.patch', d)}"

S = "${WORKDIR}/tevent-${PV}"

EXTRA_OECONF += "--disable-rpath \
                 --bundled-libraries=NONE \
                 --builtin-libraries=replace \
                 --with-libiconv=${STAGING_DIR_HOST}${prefix}\
                 --without-gettext \
                "
DISABLE_STATIC = ""

PACKAGES += "python-tevent python-tevent-dbg"

FILES_python-tevent = "${libdir}/python${PYTHON_BASEVERSION}/site-packages/*"
FILES_python-tevent-dbg = "${libdir}/python${PYTHON_BASEVERSION}/site-packages/.debug"
