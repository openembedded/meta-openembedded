SUMMARY = "A daemon for managing internet connections within embedded devices"
DESCRIPTION = "The ConnMan project provides a daemon for managing \
internet connections within embedded devices running the Linux \
operating system.  The Connection Manager is designed to be slim and \
to use as few resources as possible, so it can be easily integrated. \
It is a fully modular system that can be extended, through plug-ins, \
to support all kinds of wired or wireless technologies. Also, \
configuration methods, like DHCP and domain name resolving, are \
implemented using plug-ins."
HOMEPAGE = "http://connman.net/"
BUGTRACKER = "http://bugs.meego.com/buglist.cgi?quicksearch=connman"
LICENSE  = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e \
                    file://src/main.c;beginline=1;endline=20;md5=4b55b550fa6b33cc2055ef30dd262b3e"

# we need to define the depends here, the dynamic stuff is too late
DEPENDS  = "libnl wpa-supplicant dbus glib-2.0 ppp busybox dhcp resolvconf bluez4 iptables gnutls"

EXTRA_OECONF += "\
    ac_cv_path_WPASUPPLICANT=/usr/sbin/wpa_supplicant \
    ac_cv_path_DHCLIENT=/sbin/dhclient \
    ac_cv_path_UDHCPC=/sbin/udhcpc \
    ac_cv_path_RESOLVCONF=/sbin/resolvconf \
    ac_cv_path_PPPD=/usr/sbin/pppd \
    --with-systemdunitdir=${base_libdir}/systemd/system/ \
"

INITSCRIPT_NAME = "connman"
INITSCRIPT_PARAMS = "start 05 5 2 . stop 22 0 1 6 ."

PARALLEL_MAKE = ""

inherit autotools pkgconfig update-rc.d

do_configure_append() {
	ln -sf . include/connman
}

do_compile_append() {
	sed -i -e s:deny:allow:g src/connman-dbus.conf
}

do_install_append() {
	install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/connman ${D}${sysconfdir}/init.d/connman
}

python populate_packages_prepend() {
	depmap = dict( pppd="ppp", udhcp="busybox connman-scripts", dhclient="dhcp-client", wifi="wpa-supplicant", resolvconf="resolvconf", bluetooth="bluez4" )
	packages = []
	hook = lambda file,pkg,b,c,d:packages.append((file,pkg))
	plugin_dir = bb.data.expand('${libdir}/connman/plugins/', d)
	plugin_name = bb.data.expand('${PN}-plugin-%s', d)
	do_split_packages(d, plugin_dir, '^(.*).so$', plugin_name, '${PN} plugin for %s', extra_depends='', hook=hook )
	for (file, package) in packages:
		plugintype = package.split( '-' )[-1]
		if plugintype in depmap:
			rdepends = bb.data.getVar( "RDEPENDS_%s" % package, d )
			bb.note( "Adding rdependency on %s to package %s" % ( depmap[plugintype], package ) )
			bb.data.setVar("RDEPENDS_%s" % package, depmap[plugintype], d)
}

PACKAGES_DYNAMIC = "${PN}-plugin-*"

PACKAGES += "${PN}-scripts ${PN}-test-utils"

FILES_${PN} = "${bindir}/* ${sbindir}/* ${libexecdir}/* ${libdir}/lib*.so.* \
            ${sysconfdir} ${sharedstatedir} ${localstatedir} \
            ${base_bindir}/* ${base_sbindir}/* ${base_libdir}/*.so* ${datadir}/${PN} \
            ${datadir}/pixmaps ${datadir}/applications \
            ${datadir}/idl ${datadir}/omf ${datadir}/sounds \
            ${libdir}/bonobo/servers \
            ${datadir}/dbus-1/system-services/*"

PACKAGES =+ "${PN}-systemd"
# Package up systemd files
FILES_${PN}-systemd += "${base_libdir}/systemd"

pkg_postinst_connman-systemd() {
	# can't do this offline
	if [ "x$D" != "x" ]; then
		exit 1
	fi
	
	systemctl enable connman.service
}

pkg_postrm_connman-systemd() {
	# can't do this offline
	if [ "x$D" != "x" ]; then
		exit 1
	fi

	systemctl disable connman.service
}

# Needed when using DNS proxy feature
RRECOMMENDS_${PN} += "dnsmasq-dbus"

FILES_${PN}-test-utils += "${libdir}/connman/test/*"

FILES_${PN}-scripts += "${libdir}/connman/scripts"
FILES_${PN}-dbg += "${libdir}/connman/*/.debug"
FILES_${PN}-dev += "${libdir}/connman/*/*.la"

