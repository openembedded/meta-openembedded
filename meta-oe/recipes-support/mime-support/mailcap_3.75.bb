SECTION = "base"
SUMMARY = "MIME files 'mailcap', and support programs"
LICENSE = "PD & Bellcore"
LICENSE:${PN} = "PD"
# mailcap.man's license is Bellcore
LICENSE:${PN}-doc = "PD & Bellcore"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=881b8546ad4d18ee8d55d42c93cda0b6"

DEPENDS = "file"
RDEPENDS:${PN} = "perl"
RRECOMMENDS:${PN} = "file"

SRC_URI = "${DEBIAN_MIRROR}/main/m/${BPN}/${BPN}_${PV}.tar.xz"
SRC_URI[sha256sum] = "44876c7241693ab32de57e799052b19a575ae0e27e85380537225708f562738f"
S = "${UNPACKDIR}/work"

FILES:${PN} += " ${libdir}/mime"

docdir:append = "/${BPN}"

do_install () {
    install -d ${D}${sysconfdir}
    install -d ${D}${libdir}/mime/packages
    install -d ${D}${docdir}
    install -d ${D}${sbindir}
    install -d ${D}${bindir}
    install -d ${D}${mandir}/man1
    install -d ${D}${mandir}/man5
    install -d ${D}${mandir}/man8
    install -m 644 mailcap            ${D}${libdir}/mime
    install -m 644 mailcap.order      ${D}${sysconfdir}/
    install -m 644 mailcap.man        ${D}${mandir}/man5/mailcap.5
    install -m 644 mailcap.order.man  ${D}${mandir}/man5/mailcap.order.5
    install -m 755 update-mime        ${D}${sbindir}/
    install -m 644 update-mime.man    ${D}${mandir}/man8/update-mime.8
    install -m 755 run-mailcap        ${D}${bindir}/
    install -m 644 run-mailcap.man    ${D}${mandir}/man1/run-mailcap.1
    install -m 644 debian/changelog   ${D}${docdir}
    install -m 644 debian/copyright   ${D}${docdir}
    install -m 755 debian-view        ${D}${libdir}/mime/
    install -m 644 mailcap.entries    ${D}${libdir}/mime/packages/mailcap
    cd ${D}${mandir}; gzip -9fv */*
    cd ${D}${docdir}; gzip -9v changelog
    cd ${D}${bindir}; ln -s run-mailcap see
    cd ${D}${bindir}; ln -s run-mailcap edit
    cd ${D}${bindir}; ln -s run-mailcap compose
    cd ${D}${bindir}; ln -s run-mailcap print
    cd ${D}${mandir}/man1; ln -s run-mailcap.1.gz see.1.gz
    cd ${D}${mandir}/man1; ln -s run-mailcap.1.gz edit.1.gz
    cd ${D}${mandir}/man1; ln -s run-mailcap.1.gz compose.1.gz
    cd ${D}${mandir}/man1; ln -s run-mailcap.1.gz print.1.gz
}

INHIBIT_DEFAULT_DEPS = "1"
