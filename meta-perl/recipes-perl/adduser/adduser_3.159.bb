SUMMARY = "a utility to add users/groups to the system"
DESCRIPTION = "adduser, addgroup - add a user or group to the system"
HOMEPAGE = "https://salsa.debian.org/debian/adduser"
SECTION = "base/utils"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=9e982a4577268e2f3982abceafde3067"

SRC_URI = "https://launchpad.net/debian/+archive/primary/+sourcefiles/adduser/${PV}/${BPN}_${PV}.tar.xz \
           file://adduser-add-M-option-for-useradd.patch \
           file://0001-adduser-set-default-shell-with-sbin-nologin.patch \
"

SRC_URI[sha256sum] = "f732ec215661c4f1d36593493852d29fb5b17ebc4e7614730fea47dab2a888cd"

UPSTREAM_CHECK_URI = "${DEBIAN_MIRROR}/main/a/adduser"
UPSTREAM_CHECK_REGEX = "adduser_(?P<pver>\d+(\.\d+)+)"

S = "${UNPACKDIR}/work"

inherit cpan-base update-alternatives

do_install() {
    install -d ${D}${sbindir}
    install -m 0755 ${S}/adduser ${D}${sbindir}
    install -m 0755 ${S}/deluser ${D}${sbindir}

    install -d ${D}${libdir}/perl5/${@get_perl_version(d)}/Debian
    install -m 0644 ${S}/Adduser*.pm ${D}${libdir}/perl5/${@get_perl_version(d)}/Debian
    sed -i -e "s/DVERSION/${PV}/" ${D}${sbindir}/*

    install -d ${D}/${sysconfdir}
    install -m 0644 ${S}/*.conf ${D}/${sysconfdir}

    install -d ${D}${mandir}/man5
    install -m 0644 ${S}/doc/*.conf.5 ${D}${mandir}/man5
    install -d ${D}${mandir}/man8
    install -m 0644 ${S}/doc/*.8 ${D}${mandir}/man8
    install -d ${D}${docdir}/${BPN}
    cp -rf ${S}/examples ${D}${docdir}/${BPN}
}

RDEPENDS:${PN} += "\
    shadow \
    perl-module-constant \
    perl-module-encode \
    perl-module-fcntl \
    perl-module-file-find \
    perl-module-file-temp \
    perl-module-getopt-long \
    perl-module-i18n-langinfo \
    perl-module-parent \
    perl-module-posix \
    perl-module-utf8 \
    perl-module-version \
"

ALTERNATIVE:${PN} = "adduser deluser addgroup delgroup"
ALTERNATIVE_PRIORITY = "60"
ALTERNATIVE_LINK_NAME[adduser] = "${sbindir}/adduser"
ALTERNATIVE_LINK_NAME[deluser] = "${sbindir}/deluser"
ALTERNATIVE_LINK_NAME[addgroup] = "${sbindir}/addgroup"
ALTERNATIVE_LINK_NAME[delgroup] = "${sbindir}/delgroup"
ALTERNATIVE_TARGET[addgroup] = "${sbindir}/adduser.${BPN}"
ALTERNATIVE_TARGET[delgroup] = "${sbindir}/deluser.${BPN}"
