SUMMARY = "Hierarchical, reference counted memory pool system with destructors"
HOMEPAGE = "http://ldb.samba.org"
SECTION = "libs"
LICENSE = "LGPL-3.0+ & LGPL-2.1+ & GPL-3.0+"

DEPENDS += "libtdb libtalloc libtevent popt"
RDEPENDS_pyldb += "python"

SRC_URI = "http://samba.org/ftp/ldb/ldb-${PV}.tar.gz \
           file://do-not-import-target-module-while-cross-compile.patch \
           file://ldb-Add-configure-options-for-packages.patch \
          "

PACKAGECONFIG ??= "\
    ${@bb.utils.filter('DISTRO_FEATURES', 'acl', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'xattr', 'attr', '', d)} \
"
PACKAGECONFIG[acl] = "--with-acl,--without-acl,acl"
PACKAGECONFIG[attr] = "--with-attr,--without-attr,attr"
PACKAGECONFIG[ldap] = ",,openldap"
PACKAGECONFIG[libaio] = "--with-libaio,--without-libaio,libaio"
PACKAGECONFIG[libbsd] = "--with-libbsd,--without-libbsd,libbsd"
PACKAGECONFIG[libcap] = "--with-libcap,--without-libcap,libcap"
PACKAGECONFIG[valgrind] = "--with-valgrind,--without-valgrind,valgrind"

SRC_URI += "${@bb.utils.contains('PACKAGECONFIG', 'ldap', '', 'file://avoid-openldap-unless-wanted.patch', d)}"

LIC_FILES_CHKSUM = "file://pyldb.h;endline=24;md5=dfbd238cecad76957f7f860fbe9adade \
                    file://man/ldb.3.xml;beginline=261;endline=262;md5=137f9fd61040c1505d1aa1019663fd08 \
                    file://tools/ldbdump.c;endline=19;md5=a7d4fc5d1f75676b49df491575a86a42"

SRC_URI[md5sum] = "91284a12423def99a6592e393e3b7fbb"
SRC_URI[sha256sum] = "0cedeadb75de401c9735127f654dbc1a391c4327a2b83be54078be286fed1113"

inherit waf-samba

S = "${WORKDIR}/ldb-${PV}"

EXTRA_OECONF += "--disable-rpath \
                 --disable-rpath-install \
                 --bundled-libraries=cmocka \
                 --builtin-libraries=replace \
                 --with-modulesdir=${libdir}/ldb/modules \
                 --with-privatelibdir=${libdir}/ldb \
                 --with-libiconv=${STAGING_DIR_HOST}${prefix}\
                "

PACKAGES =+ "pyldb pyldb-dbg pyldb-dev"

NOAUTOPACKAGEDEBUG = "1"

FILES_${PN} += "${libdir}/ldb/*"
FILES_${PN}-dbg += "${bindir}/.debug/* \
                    ${libdir}/.debug/* \
                    ${libdir}/ldb/.debug/* \
                    ${libdir}/ldb/modules/ldb/.debug/*"

FILES_pyldb = "${libdir}/python${PYTHON_BASEVERSION}/site-packages/* \
               ${libdir}/libpyldb-util.so.* \
              "
FILES_pyldb-dbg = "${libdir}/python${PYTHON_BASEVERSION}/site-packages/.debug \
                   ${libdir}/.debug/libpyldb-util.so.*"
FILES_pyldb-dev = "${libdir}/libpyldb-util.so"
