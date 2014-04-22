SUMMARY = "PostgreSQL is a powerful, open source relational database system."
DESCRIPTION = "\
    PostgreSQL is an advanced Object-Relational database management system \
    (DBMS) that supports almost all SQL constructs (including \
    transactions, subselects and user-defined types and functions). The \
    postgresql package includes the client programs and libraries that \
    you'll need to access a PostgreSQL DBMS server.  These PostgreSQL \
    client programs are programs that directly manipulate the internal \
    structure of PostgreSQL databases on a PostgreSQL server. These client \
    programs can be located on the same machine with the PostgreSQL \
    server, or may be on a remote machine which accesses a PostgreSQL \
    server over a network connection. This package contains the docs \
    in HTML for the whole package, as well as command-line utilities for \
    managing PostgreSQL databases on a PostgreSQL server. \
     \
    If you want to manipulate a PostgreSQL database on a local or remote \
    PostgreSQL server, you need this package. You also need to install \
    this package if you're installing the postgresql-server package. \
    "
HOMEPAGE = "http://www.postgresql.com"
LICENSE = "BSD"
DEPENDS = "zlib readline tzcode-native"
INC_PR = "r0"

ARM_INSTRUCTION_SET = "arm"

#WARNING: this recipe assumes you have the timezone compiler present in /usr/sbin/zic

SRC_URI = "http://ftp.postgresql.org/pub/source/v${PV}/${P}.tar.bz2 \
           file://postgresql.init \
           file://postgresql-bashprofile \
"

LEAD_SONAME = "libpq.so"

# LDFLAGS for shared libraries
export LDFLAGS_SL = "${LDFLAGS}"

inherit autotools pkgconfig useradd

EXTRA_OECONF += "--enable-thread-safety --disable-rpath \
                 --datadir=${datadir}/${BPN} \
                 --sysconfdir=${sysconfdir}/${BPN} \
                 --without-krb5 \
"
EXTRA_OECONF_sh4 += "--disable-spinlocks"
EXTRA_OECONF_aarch64 += "--disable-spinlocks"

do_compile_append() {
    cp /usr/sbin/zic ${S}/src/timezone/
}

# server needs to configure user and group
usernum = "28"
groupnum = "28"
USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "-M -g postgres -o -r -d ${localstatedir}/lib/${BPN} \
    -s /bin/bash -c 'PostgreSQL Server' -u ${usernum} postgres"
GROUPADD_PARAM_${PN} = "-g ${groupnum} -o -r postgres"

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME = "${BPN}-server"
INITSCRIPT_PARAMS = "start 64 . stop 36 0 1 2 3 4 5 6 ."

do_install_append() {
	# install dirs and server init
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/${BPN}.init \
	    ${D}${sysconfdir}/init.d/${BPN}-server
	sed -i -e "s/^PGVERSION=.*$/PGVERSION=${PV}/g" \
	    ${D}${sysconfdir}/init.d/${BPN}-server
	install -d -m 700 ${D}${localstatedir}/lib/${BPN}/data
	install -d -m 700 ${D}${localstatedir}/lib/${BPN}/backups
	install -m 644 ${WORKDIR}/${BPN}-bashprofile \
	    ${D}${localstatedir}/lib/${BPN}/.bash_profile
	chown -R postgres:postgres ${D}${localstatedir}/lib/${BPN}
	# multiple server config directory
	install -d -m 700 ${D}${sysconfdir}/default/${BPN}
}

SSTATE_SCAN_FILES += "Makefile.global"

PACKAGES =+ "${PN}-client ${PN}-server-dev ${PN}-timezone \
             libecpg-compat-dbg libecpg-compat \
             libecpg-dbg libecpg libecpg-dev \
             libpq-dbg libpq libpq-dev \ 
             libpgtypes-dbg libpgtypes "

FILES_${PN}-client = "${bindir}/clusterdb \
                ${bindir}/createdb \
                ${bindir}/createlang \
                ${bindir}/createuser \
                ${bindir}/dropdb \
                ${bindir}/droplang \
                ${bindir}/dropuser \
                ${bindir}/pg_dump \
                ${bindir}/pg_dumpall \
                ${bindir}/pg_restore \
                ${bindir}/psql \
                ${bindir}/reindexdb \
                ${bindir}/vacuumdb \
                ${bindir}/vacuumlo \
                ${datadir}/${PN}/psqlrc.sample"

FILES_${PN}-doc += "${prefix}/doc/"
FILES_${PN}-timezone = "${datadir}/${PN}/timezone"
FILES_${PN}-server-dev = "${includedir}/${PN}/server"

FILES_libecpg = "${libdir}/libecpg*${SOLIBS}"
FILES_libecpg-dbg = "${libdir}/.debug/libecpg*"
FILES_libecpg-dev = "${libdir}/libecpg*${SOLIBSDEV} \
                     ${libdir}/libpgtypes*${SOLIBSDEV} \
                     ${includedir}/ecpg*.h ${includedir}/${PN}/ecpg*.h \
                     ${includedir}/pgtypes*.h ${includedir}/${PN}/informix \
                     ${includedir}/sql3types.h ${includedir}/sqlca.h"
FILES_libecpg-staticdev = "${libdir}/libecpg*.a"
SECTION_libecpg-staticdev = "devel"
RDEPENDS_libecpg-staticdev = "libecpg-dev (= ${EXTENDPKGV})"

FILES_libpq = "${libdir}/libpq*${SOLIBS}"
FILES_libpq-dbg = "${libdir}/.debug/libpq* ${libdir}/${PN}/pgxs/src/test/regress/.debug/*"
FILES_libpq-dev = "${libdir}/libpq*${SOLIBSDEV} \
                   ${includedir}"
FILES_libpq-staticdev = "${libdir}/libpq*.a"
SECTION_libpq-staticdev = "devel"
RDEPENDS_libpq-staticdev = "libpq-dev (= ${EXTENDPKGV})"

FILES_libecpg-compat = "${libdir}/libecpg_compat*${SOLIBS}"
FILES_libecpg-compat-dbg = "${libdir}/.debug/libecpg_compat*"
FILES_libpgtypes = "${libdir}/libpgtypes*${SOLIBS}"
FILES_libpgtypes-dbg = "${libdir}/.debug/libpgtypes*"
