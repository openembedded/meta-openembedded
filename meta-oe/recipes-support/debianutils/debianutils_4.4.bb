SUMMARY = "Miscellaneous utilities specific to Debian"
SECTION = "base"
LICENSE = "GPLv2 & SMAIL_GPL"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=f01a5203d50512fc4830b4332b696a9f"

SRC_URI = "${DEBIAN_MIRROR}/main/d/${BPN}/${BPN}_${PV}.tar.gz"
SRC_URI[md5sum] = "c0cb076754d7f4eb1e3397d00916647f"
SRC_URI[sha256sum] = "190850cdd6b5302e0a1ba1aaed1bc7074d67d3bd8d04c613f242f7145afa53a6"

inherit autotools

do_configure_prepend() {
    sed -i -e 's:tempfile.1 which.1:which.1:g' ${S}/Makefile.am
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



