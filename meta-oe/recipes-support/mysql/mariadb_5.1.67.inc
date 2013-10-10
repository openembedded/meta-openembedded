SUMMARY = "A robust, scalable, and reliable SQL server"
HOMEPAGE = "http://mariadb.org"
SECTION = "libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "ncurses zlib"

SRC_URI = "http://mirrors.coreix.net/mariadb/mariadb-${PV}/kvm-tarbake-jaunty-x86/mariadb-${PV}.tar.gz \
           file://configure.in.patch \
           file://plug.in.patch \
           file://misc.m4.patch \
           file://Makefile.am.patch \
           file://fix_host_path.patch \
           file://configure-ps-cache-check.patch \
           file://fix-cve-2013-1861-1.patch \
           file://fix-cve-2013-1861-2.patch \
           file://zlib-let-libdir-configurable.patch \
           file://my.cnf \
           file://mysqld.sh"

SRC_URI[md5sum] = "44c331fa91943155e4796f89e17a0612"
SRC_URI[sha256sum] = "33471e9275c9b03919cabc38eb39f807d645adabf6a1a18f2e41ed73f47677c8"

S = "${WORKDIR}/mariadb-${PV}"

BINCONFIG_GLOB = "mysql_config"

inherit autotools binconfig update-rc.d useradd

INITSCRIPT_PACKAGES = "${PN}-server"
INITSCRIPT_NAME = "mysqld"
INITSCRIPT_PARAMS = "start 45 5 . stop 45 0 6 1 ."

USERADD_PACKAGES = "${PN}-server"
USERADD_PARAM_${PN}-server = "--system --home-dir /var/mysql -g nogroup mysql"


export ac_cv_path_PS="/bin/ps"
export ac_cv_FIND_PROC="/bin/ps p \$\$PID | grep -v grep | grep mysqld > /dev/null"
PARALLEL_MAKE = " "
EXTRA_OEMAKE = "'GEN_LEX_HASH=${STAGING_BINDIR_NATIVE}/gen_lex_hash'"
EXTRA_OECONF = "--with-atomic-ops=up \
                --with-embedded-server \
                --sysconfdir=/etc/mysql \
                --localstatedir=/var/mysql \
                --disable-dependency-tracking \
                --without-debug \
                --with-low-memory \
                --without-query-cache \
                --without-plugin-maria \
                --without-man \
                --without-docs \
                --with-zlib-dir=${STAGING_EXECPREFIXDIR} \
                --with-baselib-dir=${base_libdir} \
                --with-pic \
                "

do_configure_append() {
    sed -i /comp_err/d ${S}/sql/share/Makefile

    # handle distros with different values of ${libexecdir}
    libexecdir2=`echo ${libexecdir} | sed -e 's+/usr/++g'`
    sed -i -e "s:/libexec:/$libexecdir2:g" ${S}/scripts/mysql_install_db.sh
    sed -i -e "s:mysqld libexec:mysqld $libexecdir2:g" ${S}/scripts/mysql_install_db.sh
    sed -i -e "s:/libexec:/$libexecdir2:g" ${S}/scripts/mysqld_safe.sh
}

SYSROOT_PREPROCESS_FUNCS += "mariadb_sysroot_preprocess"

# We need to append this so it runs *after* binconfig's preprocess function
#
# We really don't care exactly what the directories were set to originally.
# plugindir is not fixed, but we don't create any plugins.
#
mariadb_sysroot_preprocess () {
    sed -i -es,^pkgincludedir=.*,pkgincludedir=\'${STAGING_INCDIR}/mysql\', ${SYSROOT_DESTDIR}${bindir_crossscripts}/mysql_config
    sed -i -es,^pkglibdir=.*,pkglibdir=\'${STAGING_LIBDIR}\', ${SYSROOT_DESTDIR}${bindir_crossscripts}/mysql_config
}

do_install() {
    oe_runmake 'DESTDIR=${D}' install
    mv -f ${D}${libdir}/mysql/* ${D}${libdir}
    rmdir ${D}${libdir}/mysql
    find ${D}${libdir} -name '*.la'|xargs sed -i 's#${libdir}/mysql#${libdir}#'

    install -d ${D}/etc/init.d
    install -m 0644 ${WORKDIR}/my.cnf ${D}/etc/
    install -m 0755 ${WORKDIR}/mysqld.sh ${D}/etc/init.d/mysqld
}

pkg_postinst_${PN}-server () {
    if [ "x$D" != "x" ]; then
        exit 1
    fi

    #Install the database
    test -d /usr/bin || mkdir -p /usr/bin
    test -e /usr/bin/hostname || ln -s /bin/hostname /usr/bin/hostname
    mkdir /var/lib/mysql
    chown mysql.nogroup /var/lib/mysql

    mysql_install_db

}

PACKAGES = "${PN}-dbg ${PN} \
    libmysqlclient-r libmysqlclient-r-dev libmysqlclient-r-staticdev libmysqlclient-r-dbg \
    libmysqlclient libmysqlclient-dev libmysqlclient-staticdev libmysqlclient-dbg \
    libmysqld libmysqld-dev ${PN}-client ${PN}-server ${PN}-leftovers"
CONFFILES_${PN}-server += "${sysconfdir}/my.cnf"

FILES_${PN} = " "
RDEPENDS_${PN} = "${PN}-client ${PN}-server"
ALLOW_EMPTY_${PN} = "1"

FILES_libmysqlclient = "\
    ${libdir}/libmysqlclient.so.* \
    ${libdir}/plugin/*.so.*"
FILES_libmysqlclient-dev = " \
    ${includedir}/mysql/ \
    ${libdir}/libmysqlclient.so \
    ${libdir}/libmysqlclient.la \
    ${libdir}/plugin/*.so \
    ${libdir}/plugin/*.la \
    ${sysconfdir}/aclocal \
    ${bindir}/mysql_config"
FILES_libmysqlclient-staticdev = "\
    ${libdir}/*.a \
    ${libdir}/plugin/*.a"
FILES_libmysqlclient-dbg = "${libdir}/plugin/.debug/ \
    /usr/mysql-test/lib/My/SafeProcess/.debug/my_safe_process"

# Avoid warnings about ha_xtradb.so.0.0.0 and ha_innodb_plugin.so.0.0.0
# which are intentionally non-PIC on 32-bit x86 (see e.g.
# storage/xtradb/plug.in in the source)
INSANE_SKIP_libmysqlclient_append_i586 = " textrel"

FILES_libmysqlclient-r = "${libdir}/libmysqlclient_r.so.*"
FILES_libmysqlclient-r-dev = "\
    ${libdir}/libmysqlclient_r.so \
    ${libdir}/libmysqlclient_r.la"
FILES_libmysqlclient-r-staticdev = "${libdir}/libmysqlclient_r.a"
FILES_libmysqlclient-r-dbg = "${libdir}/plugin/.debuglibmysqlclient_r.so.*"

FILES_libmysqld = "\
    ${libdir}/libmysqld.so.*"
FILES_libmysqld-dev = "\
    ${libdir}/libmysqld.so"

FILES_${PN}-client = "\
    ${bindir}/myisam_ftdump \
    ${bindir}/mysql \
    ${bindir}/mysql_client_test \
    ${bindir}/mysql_client_test_embedded \
    ${bindir}/mysql_find_rows \
    ${bindir}/mysql_fix_extensions \
    ${bindir}/mysql_waitpid \
    ${bindir}/mysqlaccess \
    ${bindir}/mysqladmin \
    ${bindir}/mysqlbug \
    ${bindir}/mysqlcheck \
    ${bindir}/mysqldump \
    ${bindir}/mysqldumpslow \
    ${bindir}/mysqlimport \
    ${bindir}/mysqlshow \
    ${bindir}/mysqlslap \
    ${bindir}/mysqltest_embedded \
    ${libexecdir}/mysqlmanager"

FILES_${PN}-server = "\
    ${bindir}/comp_err \
    ${bindir}/isamchk \
    ${bindir}/isamlog \
    ${bindir}/msql2mysql \
    ${bindir}/my_print_defaults \
    ${bindir}/myisamchk \
    ${bindir}/myisamlog \
    ${bindir}/myisampack \
    ${bindir}/mysql_convert_table_format \
    ${bindir}/mysql_fix_privilege_tables \
    ${bindir}/mysql_install_db \
    ${bindir}/mysql_secure_installation \
    ${bindir}/mysql_setpermission \
    ${bindir}/mysql_tzinfo_to_sql \
    ${bindir}/mysql_upgrade \
    ${bindir}/mysql_zap \
    ${bindir}/mysqlbinlog \
    ${bindir}/mysqld_multi \
    ${bindir}/mysqld_safe \
    ${bindir}/mysqlhotcopy \
    ${bindir}/mysqltest \
    ${bindir}/ndb_delete_all \
    ${bindir}/ndb_desc \
    ${bindir}/ndb_drop_index \
    ${bindir}/ndb_drop_table \
    ${bindir}/ndb_mgm \
    ${bindir}/ndb_restore \
    ${bindir}/ndb_select_all \
    ${bindir}/ndb_select_count \
    ${bindir}/ndb_show_tables \
    ${bindir}/ndb_waiter \
    ${bindir}/pack_isam \
    ${bindir}/perror \
    ${bindir}/replace \
    ${bindir}/resolve_stack_dump \
    ${bindir}/resolveip \
    ${libexecdir}/mysqld \
    ${sbindir}/mysqld \
    ${sbindir}/ndb_cpcd \
    ${sbindir}/ndbd \
    ${sbindir}/ndb_mgmd \
    ${datadir}/mysql/ \
    ${localstatedir}/mysql/ \
    ${sysconfdir}/init.d \
    ${sysconfdir}/my.cnf"

DESCRIPTION_${PN}-leftovers = "unpackaged and probably unneeded files for ${PN}"
FILES_${PN}-leftovers = "/"
