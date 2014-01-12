SUMMARY = "Web-based MySQL administration interface"
HOMEPAGE = "http://www.phpmyadmin.net"
# Main code is GPLv2, libraries/tcpdf is under LGPLv3, js/jquery is under MIT
LICENSE = "GPLv2 & LGPLv3 & MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=eb723b61539feef013de476e68b5c50a \
                    file://libraries/tcpdf/LICENSE.TXT;md5=5c87b66a5358ebcc495b03e0afcd342c"

SRC_URI = "${SOURCEFORGE_MIRROR}/phpmyadmin/phpMyAdmin/${PV}/phpMyAdmin-${PV}-all-languages.tar.xz \
           file://apache.conf"

SRC_URI[md5sum] = "14fd2f6d2f92a8dfb3954513da1c1687"
SRC_URI[sha256sum] = "857f133911eddcec16824f3ecc7a68f93288ab2a2f1943bfce3b06b58b625cd6"

S = "${WORKDIR}/phpMyAdmin-${PV}-all-languages"

inherit allarch

do_install() {
    install -d ${D}${datadir}/${BPN}
    cp -a * ${D}${datadir}/${BPN}

    install -d ${D}${sysconfdir}/apache2/conf.d
    install -m 0644 ${WORKDIR}/apache.conf ${D}${sysconfdir}/apache2/conf.d/phpmyadmin.conf

    # Remove a few scripts that explicitly require bash (!)
    rm -f ${D}${datadir}/phpmyadmin/libraries/transformations/*.sh
}

FILES_${PN} = "${datadir}/${BPN} \
               ${sysconfdir}/apache2/conf.d"

