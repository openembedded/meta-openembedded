SUMMARY = "Hierarchical, reference counted memory pool system with destructors"
HOMEPAGE = "http://talloc.samba.org"
SECTION = "libs"
LICENSE = "LGPL-3.0+ & GPL-3.0+"

SRC_URI = "http://samba.org/ftp/${BPN}/${BPN}-${PV}.tar.gz"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/LGPL-3.0;md5=bfccfe952269fff2b407dd11f2f3083b \
                    file://${COREBASE}/meta/files/common-licenses/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891"

SRC_URI[md5sum] = "5dffb86414218a91864ed4453ba9be07"
SRC_URI[sha256sum] = "3e29ce6c3ba3c4f7c2d57ce8cf0fbc24c86618c519f2b2fb6a459025488b6174"

inherit waf-samba

EXTRA_OECONF += "--disable-rpath \
                 --disable-rpath-install \
                 --bundled-libraries=NONE \
                 --builtin-libraries=replace \
                 --disable-silent-rules \
                 --with-libiconv=${STAGING_DIR_HOST}${prefix}\
                "

PACKAGES += "libtalloc libtalloc-dbg libtalloc-dev pytalloc pytalloc-dbg pytalloc-dev"

#ALLOW_EMPTY_${PN} = "1"
FILES_${PN} = ""
FILES_${PN}-dev = ""
FILES_${PN}-dbg = ""

FILES_libtalloc = "${libdir}/libtalloc.so.2 \
                   ${libdir}/libtalloc.so.2.1.1"
FILES_libtalloc-dbg = "/usr/src/debug/ \
                   ${libdir}/.debug/libtalloc.so.2.1.1"
FILES_libtalloc-dev = "${includedir}/ \
                   ${libdir}/libtalloc.so \
                   ${libdir}/pkgconfig/"

FILES_pytalloc = "${libdir}/python${PYTHON_BASEVERSION}/site-packages/* \
                  ${libdir}/libpytalloc-util.so.2 \
                  ${libdir}/libpytalloc-util.so.2.1.1 \
                 "
FILES_pytalloc-dbg = "${libdir}/python${PYTHON_BASEVERSION}/site-packages/.debug \
                      ${libdir}/.debug/libpytalloc-util.so.2.1.1"
FILES_pytalloc-dev = "${libdir}/libpytalloc-util.so"
RDEPENDS_pytalloc = "python"
