SUMMARY = "HTTP and reverse proxy server"

DESCRIPTION = "Nginx is a web server and a reverse proxy server for \
HTTP, SMTP, POP3 and IMAP protocols, with a strong focus on high  \
concurrency, performance and low memory usage."

HOMEPAGE = "http://nginx.org/"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=917bfdf005ffb6fd025550414ff05a9f"
SECTION = "net"

DEPENDS = "libpcre gzip openssl"

SRC_URI = " \
	http://nginx.org/download/nginx-${PV}.tar.gz \
	file://nginx-cross.patch \
	file://nginx.conf \
	file://nginx.init \
	file://nginx-volatile.conf \
"
SRC_URI[md5sum] = "5dfaba1cbeae9087f3949860a02caa9f"
SRC_URI[sha256sum] = "7c989a58e5408c9593da0bebcd0e4ffc3d892d1316ba5042ddb0be5b0b4102b9"

inherit update-rc.d useradd

do_configure () {
	if [ "${SITEINFO_BITS}" = "64" ]; then
		PTRSIZE=8
	else
		PTRSIZE=4
	fi

	echo $CFLAGS
	echo $LDFLAGS

	./configure \
	--crossbuild=Linux:${TUNE_ARCH} \
	--with-endian=${@base_conditional('SITEINFO_ENDIANNESS', 'le', 'little', 'big', d)} \
	--with-int=4 \
	--with-long=${PTRSIZE} \
	--with-long-long=8 \
	--with-ptr-size=${PTRSIZE} \
	--with-sig-atomic-t=${PTRSIZE} \
	--with-size-t=${PTRSIZE} \
	--with-off-t=${PTRSIZE} \
	--with-time-t=${PTRSIZE} \
	--with-sys-nerr=132 \
	--conf-path=${sysconfdir}/nginx/nginx.conf \
	--http-log-path=${localstatedir}/log/nginx/access.log \
	--error-log-path=${localstatedir}/log/nginx/error.log \
	--pid-path=/run/nginx/nginx.pid \
	--prefix=${prefix} \
	--with-http_ssl_module \
	--with-http_gzip_static_module
}

do_install () {
	oe_runmake 'DESTDIR=${D}' install
	rm -fr ${D}${localstatedir}/run ${D}/run
	if ${@base_contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
		install -d ${D}${sysconfdir}/tmpfiles.d
		echo "d /run/${BPN} - - - -" \
		     > ${D}${sysconfdir}/tmpfiles.d/${BPN}.conf
	fi
	install -d ${D}${sysconfdir}/${BPN}
	ln -snf ${localstatedir}/run/${BPN} ${D}${sysconfdir}/${BPN}/run
	install -d ${D}${localstatedir}/www/localhost
	mv ${D}/usr/html ${D}${localstatedir}/www/localhost/
	chown www:www-data -R ${D}${localstatedir}

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/nginx.init ${D}${sysconfdir}/init.d/nginx
	sed -i 's,/usr/sbin/,${sbindir}/,g' ${D}${sysconfdir}/init.d/nginx
	sed -i 's,/etc/,${sysconfdir}/,g'  ${D}${sysconfdir}/init.d/nginx

	install -d ${D}${sysconfdir}/nginx
	install -m 0644 ${WORKDIR}/nginx.conf ${D}${sysconfdir}/nginx/nginx.conf
	sed -i 's,/var/,${localstatedir}/,g' ${D}${sysconfdir}/nginx/nginx.conf
	install -d ${D}${sysconfdir}/nginx/sites-enabled

	install -d ${D}${sysconfdir}/default/volatiles
	install -m 0644 ${WORKDIR}/nginx-volatile.conf ${D}${sysconfdir}/default/volatiles/99_nginx
	sed -i 's,/var/,${localstatedir}/,g' ${D}${sysconfdir}/default/volatiles/99_nginx
}

pkg_postinst_${PN} () {
	if [ -z "$D" ]; then
		if type systemd-tmpfiles >/dev/null; then
			systemd-tmpfiles --create
		elif [ -e ${sysconfdir}/init.d/populate-volatile.sh ]; then
			${sysconfdir}/init.d/populate-volatile.sh update
		fi
	fi
}

FILES_${PN} += "${localstatedir}/"

CONFFILES_${PN} = "${sysconfdir}/nginx/nginx.conf \
		${sysconfdir}/nginx/fastcgi.conf\
		${sysconfdir}/nginx/fastcgi_params \
		${sysconfdir}/nginx/koi-utf \
		${sysconfdir}/nginx/koi-win \
		${sysconfdir}/nginx/mime.types \
		${sysconfdir}/nginx/scgi_params \
		${sysconfdir}/nginx/uwsgi_params \
		${sysconfdir}/nginx/win-utf \
"

INITSCRIPT_NAME = "nginx"
INITSCRIPT_PARAMS = "defaults 92 20"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = " \
    --system --no-create-home \
    --home ${localstatedir}/www/localhost \
    --groups www-data \
    --user-group www"
