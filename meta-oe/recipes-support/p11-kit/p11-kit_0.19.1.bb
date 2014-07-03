SUMMARY = "Provides a way to load and enumerate PKCS#11 modules"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=02933887f609807fbb57aa4237d14a50"

inherit autotools gettext pkgconfig

DEPENDS = "libtasn1 libffi"

SRC_URI = "http://p11-glue.freedesktop.org/releases/${BP}.tar.gz"
SRC_URI[md5sum] = "d96046ab6ac00d005342caf416ed76ab"
SRC_URI[sha256sum] = "94fbed372c11d0a404762aad966e54eb4f44c1d5b871a1b79a1a3b4cf36ed256"

FILES_${PN}-dev += " \
    ${libdir}/p11-kit-proxy.so \
    ${libdir}/pkcs11/p11-kit-trust.so \
"
FILES_${PN}-dev += "${libdir}/pkcs11/*.la"
FILES_${PN}-dbg += "${libdir}/pkcs11/.debug"
