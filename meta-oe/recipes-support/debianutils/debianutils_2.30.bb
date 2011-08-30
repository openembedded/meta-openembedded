DESCRIPTION = "Miscellaneous utilities specific to Debian"
SECTION = "base"
LICENSE = "GPLv2 & BSD & SMAIL_GPL"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=b948675029f79c64840e78881e91e1d4"

SRC_URI = "${DEBIAN_MIRROR}/main/d/${PN}/${PN}_${PV}.tar.gz"
SRC_URI[md5sum] = "7fdd5f8395162d8728d4b79e97b9819e"
SRC_URI[sha256sum] = "d62e98fee5b1a758d83b62eed8d8bdec473677ff782fed89fc4ae3ba3f381401"

inherit autotools

do_configure_prepend() {
	sed -i -e 's:tempfile.1 which.1:which.1:g' Makefile.am
}

do_install_append() {
    for app in ${D}${sbindir}/* ${D}${bindir}/*; do
        mv $app $app.${PN}
    done
    if [ "${base_bindir}" != "${bindir}" ]; then
        # Debian places some utils into ${base_bindir} as does busybox
        install -d ${D}${base_bindir}
        for app in run-parts.${PN} tempfile.${PN}; do
            mv ${D}${bindir}/$app ${D}${base_bindir}/$app
        done
    fi
}

pkg_prerm_${PN} () {
if [ "x$D" != "x" ]; then
    echo "can't do u-a offline" ; exit 1
else

    for app in add-shell  installkernel  mkboot  remove-shell run-parts  savelog  sensible-browser  sensible-editor  sensible-pager  tempfile  which ; do
       update-alternatives --remove $app $app.${PN}
    done

fi
}

pkg_postinst_${PN} () {
if [ "x$D" != "x" ]; then
    echo "can't do u-a offline" ; exit 1
else

    for app in add-shell  installkernel  mkboot  remove-shell ; do
        update-alternatives --install ${sbindir}/$app $app $app.${PN} 100
    done

    for app in savelog  sensible-browser  sensible-editor  sensible-pager  which ; do
        update-alternatives --install ${bindir}/$app $app $app.${PN} 100
    done

    for app in run-parts  tempfile ; do
        update-alternatives --install ${base_bindir}/$app $app $app.${PN} 100
    done

fi
}



