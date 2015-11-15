SUMMARY = "Hierarchical, reference counted memory pool system with destructors"
HOMEPAGE = "http://tevent.samba.org"
SECTION = "libs"
LICENSE = "LGPLv3+"

DEPENDS += "talloc libcap"
RDEPENDS_${PN} += "libtalloc"
RDEPENDS_python-tevent = "python"

SRC_URI = "http://samba.org/ftp/tevent/tevent-${PV}.tar.gz"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/LGPL-3.0;md5=bfccfe952269fff2b407dd11f2f3083b"

SRC_URI[md5sum] = "3d5a76c0a1b5f724842d785fd055633f"
SRC_URI[sha256sum] = "f2be7463573dab2d8210cb57fe7e7e2aeb323274cbdc865a6e29ddcfb977f0f4"

inherit waf-samba

S = "${WORKDIR}/tevent-${PV}"

EXTRA_OECONF += "--disable-rpath \
                 --bundled-libraries=NONE \
                 --builtin-libraries=replace \
                 --with-libiconv=${STAGING_DIR_HOST}${prefix}\
                 --without-gettext \
                "

PACKAGES += "python-tevent python-tevent-dbg"

FILES_python-tevent = "${libdir}/python${PYTHON_BASEVERSION}/site-packages/*"
FILES_python-tevent-dbg = "${libdir}/python${PYTHON_BASEVERSION}/site-packages/.debug"
