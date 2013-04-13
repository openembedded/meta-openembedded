SUMMARY = "Web-based system administration interface"
HOMEPAGE = "http://ajenti.org"
SECTION = "devel/python"
LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS += "python-pyopenssl python-lxml python-gevent python-greenlet \
            python-psutil python-imaging"

PV = "0.6.2+git${SRCPV}"

SRC_URI = "git://github.com/Eugeny/ajenti.git \
           file://0001-setup.py-remove-extra-data-files.patch \
           file://0002-plugins-hddstat-fix-disk-usage-check-to-work-with-bu.patch \
           file://0003-plugins-sysload-fix-to-work-with-busybox.patch \
           file://0005-plugins-power-fix-shutdown.patch \
           file://0006-plugins-services-add-basic-sysvinit-implementation.patch \
           file://0007-plugins-services-replace-s_upstart-with-s_init-in-MO.patch \
           ${DISTRO_FILES}"

# Allow this to be customised easily
DISTRO_FILES = "file://distro-detection-openembedded.patch \
                file://distributor-logo-openembedded.png"

# Corresponds to the 0.6.2 tag
SRCREV = "c08fb4da65923aebd09116750a1f43f13b98a51a"

S = "${WORKDIR}/git"

inherit setuptools update-rc.d

do_configure_prepend() {
    rm ajenti/plugins/dashboard/files/distributor-logo-*.png
    cp ${WORKDIR}/distributor-logo-*.png ajenti/plugins/dashboard/files/
    rm plugins/services/s_upstart.py
}

do_install_append() {
    install -d ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/ajenti
    install -m 0644 packaging/files/ajenti.conf ${D}${sysconfdir}/ajenti/
    install -d ${D}${sysconfdir}/ajenti/users
    install -m 0644 packaging/files/admin.conf ${D}${sysconfdir}/ajenti/users/
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 packaging/files/ajenti ${D}${sysconfdir}/init.d/
    install -d ${D}${localstatedir}
    install -d ${D}${localstatedir}/lib
    install -d ${D}${localstatedir}/lib/ajenti
    install -d ${D}${localstatedir}/lib/ajenti/plugins
    install -m 0644 packaging/files/.placeholder ${D}${localstatedir}/lib/ajenti/plugins

    for plugin in plugins/* ; do
        cp -r $plugin ${D}${localstatedir}/lib/ajenti/plugins/
    done
}

INITSCRIPT_NAME = "ajenti"
INITSCRIPT_PARAMS = "start 99 2 3 4 5 . stop 20 0 1 6 ."

python populate_packages_prepend() {
    plugindir = d.expand('${localstatedir}/lib/ajenti/plugins')
    do_split_packages(d, plugindir, '(^[^.]*$)', 'ajenti-plugin-%s', 'Ajenti plugin for %s', allow_dirs=True, prepend=False)
}

PACKAGES_DYNAMIC = "ajenti-plugin-*"
FILES_${PN} += "${sysconfdir}/ajenti.conf \
                ${sysconfdir}/ajenti \
                ${sysconfdir}/init.d \
                ${localstatedir}/lib/ajenti/plugins/.placeholder"
RDEPENDS_${PN} += "python-re python-json python-logging python-subprocess \
                   python-threading python-setuptools python-netclient \
                   python-netserver python-shell python-syslog \
                   python-pyopenssl python-lxml python-gevent python-greenlet"
RDEPENDS_${PN}-plugin-taskmgr += "python-psutil"
RDEPENDS_${PN}-plugin-services += "python-psutil"
RDEPENDS_${PN}-plugin-logs += "python-compression"
RDEPENDS_${PN}-plugin-terminal += "python-compression python-codecs python-math \
                                   python-terminal python-imaging"
RDEPENDS_${PN}-plugin-fm += "python-unixadmin"

