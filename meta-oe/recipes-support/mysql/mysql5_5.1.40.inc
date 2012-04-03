DESCRIPTION = "The MySQL Open Source Database System"
HOMEPAGE = "http://www.mysql.com/"
SECTION = "libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=477ab0a4c8ca64b482b3f2a365d0fdfa"

DEPENDS = "ncurses"
PR = "r7"

SRC_URI = "http://downloads.mysql.com/archives/mysql-5.1/mysql-${PV}.tar.gz \
           file://configure.in.patch \
           file://plug.in.patch \
           file://misc.m4.patch \
           file://Makefile.am.patch \
           file://fix_host_path.patch \
	   file://configure-ps-cache-check.patch \
           file://fix-abi-check-gcc45.patch \
           file://my.cnf \
           file://mysqld.sh"

S = "${WORKDIR}/mysql-${PV}"

BINCONFIG_GLOB = "mysql_config"

inherit autotools binconfig update-rc.d

INITSCRIPT_PACKAGES = "${PN}-server"
INITSCRIPT_NAME = "mysqld"
INITSCRIPT_PARAMS = "start 45 S . stop 45 0 6 1 ."

export ac_cv_path_PS="/bin/ps"
export ac_cv_FIND_PROC="/bin/ps p \$\$PID | grep -v grep | grep mysqld > /dev/null"
PARALLEL_MAKE = " "
EXTRA_OEMAKE = "'GEN_LEX_HASH=${STAGING_BINDIR_NATIVE}/gen_lex_hash'"
EXTRA_OECONF = " --with-atomic-ops=up --with-embedded-server --prefix=/usr --sysconfdir=/etc/mysql --localstatedir=/var/mysql --disable-dependency-tracking --without-raid --without-debug --with-low-memory --without-query-cache --without-man --without-docs --without-innodb "

do_configure_append() {
     sed -i /comp_err/d ${S}/sql/share/Makefile
}

SYSROOT_PREPROCESS_FUNCS += "mysql5_sysroot_preprocess"

# We need to append this so it runs *after* binconfig's preprocess function
mysql5_sysroot_preprocess () {
	sed -i -es,^pkgincludedir=\'/usr/include/mysql\',pkgincludedir=\'${STAGING_INCDIR}\', ${SYSROOT_DESTDIR}${bindir_crossscripts}/mysql_config
	sed -i -es,^pkglibdir=\'/usr/lib/mysql\',pkglibdir=\'${STAGING_LIBDIR}\', ${SYSROOT_DESTDIR}${bindir_crossscripts}/mysql_config
}

do_install() {
	oe_runmake 'DESTDIR=${D}' install
	mv -f ${D}${libdir}/mysql/* ${D}${libdir}
	rmdir ${D}${libdir}/mysql

	install -d ${D}/etc/init.d
	install -m 0644 ${WORKDIR}/my.cnf ${D}/etc/
	install -m 0755 ${WORKDIR}/mysqld.sh ${D}/etc/init.d/mysqld
}

pkg_postinst_mysql5-server () {
	if [ "x$D" != "x" ]; then
		exit 1
	fi

	grep mysql /etc/passwd || adduser --disabled-password --home=/var/mysql --ingroup nogroup mysql

	#Install the database
	test -d /usr/bin || mkdir -p /usr/bin
	test -e /usr/bin/hostname || ln -s /bin/hostname /usr/bin/hostname
	mkdir /var/lib/mysql
	chown mysql.nogroup /var/lib/mysql

	mysql_install_db

}

pkg_postrm_mysql5-server () {
	grep mysql /etc/passwd && deluser mysql
}

PACKAGES = "${PN}-dbg ${PN} \
libmysqlclient-r libmysqlclient-r-dev libmysqlclient-r-staticdev libmysqlclient-r-dbg \
libmysqlclient libmysqlclient-dev libmysqlclient-staticdev libmysqlclient-dbg \
${PN}-client ${PN}-server ${PN}-leftovers"
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

FILES_libmysqlclient-r = "${libdir}/libmysqlclient_r.so.*"
FILES_libmysqlclient-r-dev = "\
${libdir}/libmysqlclient_r.so \
${libdir}/libmysqlclient_r.la"
FILES_libmysqlclient-r-staticdev = "${libdir}/libmysqlclient_r.a"
FILES_libmysqlclient-r-dbg = "${libdir}/plugin/.debuglibmysqlclient_r.so.*"

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
