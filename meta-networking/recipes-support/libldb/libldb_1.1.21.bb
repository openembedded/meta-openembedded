SUMMARY = "Hierarchical, reference counted memory pool system with destructors"
HOMEPAGE = "http://ldb.samba.org"
SECTION = "libs"
LICENSE = "LGPL-3.0+ & LGPL-2.1+ & GPL-3.0+"

DEPENDS += "libbsd libtdb libtalloc libtevent popt"
RDEPENDS_pyldb += "python"

SRC_URI = "http://samba.org/ftp/ldb/ldb-${PV}.tar.gz \
           file://do-not-import-target-module-while-cross-compile.patch \
          "

PACKAGECONFIG[ldap] = ",,openldap"
SRC_URI += "${@bb.utils.contains('PACKAGECONFIG', 'ldap', '', 'file://avoid-openldap-unless-wanted.patch', d)}"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/LGPL-3.0;md5=bfccfe952269fff2b407dd11f2f3083b \
                    file://${COREBASE}/meta/files/common-licenses/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780 \
                    file://${COREBASE}/meta/files/common-licenses/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891"

SRC_URI[md5sum] = "94ba09c7452fff68df3481686c56677e"
SRC_URI[sha256sum] = "267bbb7f278068eaf0de27adffda2e691a070a93c5c15ee27c828e87b4c7dbf1"

inherit waf-samba

S = "${WORKDIR}/ldb-${PV}"

EXTRA_OECONF += "--disable-rpath \
                 --disable-rpath-install \
                 --bundled-libraries=NONE \
                 --builtin-libraries=replace \
                 --with-modulesdir=${libdir}/ldb/modules \
                 --with-privatelibdir=${libdir}/ldb \
                 --with-libiconv=${STAGING_DIR_HOST}${prefix}\
                "
DISABLE_STATIC = ""

PACKAGES += "pyldb pyldb-dbg pyldb-dev"

FILES_${PN} += "${libdir}/ldb/*"
FILES_${PN}-dbg += "${libdir}/ldb/.debug/* \
                    ${libdir}/ldb/modules/ldb/.debug/*"

FILES_pyldb = "${libdir}/python${PYTHON_BASEVERSION}/site-packages/* \
               ${libdir}/libpyldb-util.so.1 \
               ${libdir}/libpyldb-util.so.1.1.17 \
              "
FILES_pyldb-dbg = "${libdir}/python${PYTHON_BASEVERSION}/site-packages/.debug \
                   ${libdir}/.debug/libpyldb-util.so.1.1.17"
FILES_pyldb-dev = "${libdir}/libpyldb-util.so"
