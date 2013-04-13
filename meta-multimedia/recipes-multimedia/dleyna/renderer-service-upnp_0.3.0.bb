SUMMARY = "discover and manipulate UPNP/DLNA media renderers"
HOMEPAGE = "https://01.org/dleyna/"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
                    file://src/renderer-service-upnp.c;beginline=1;endline=21;md5=c25c3082684eb4ca87474b7528c6dc15"

DEPENDS = "dbus glib-2.0 gssdp gupnp gupnp-av gupnp-dlna libsoup-2.4"

SRC_URI = "https://github.com/01org/${BPN}/archive/v${PV}.zip"
SRC_URI[md5sum] = "1e19349712a34dd56a14fc280b5d8b74"
SRC_URI[sha256sum] = "d6623d98dd35ee32bc08349b4a22d12101dda38a2ece1e468ff0160e519a854d"

inherit autotools

do_install_append() {
    install -d ${D}${bindir}
    install -m 0755 test/rendererconsole.py ${D}${bindir}
}

PACKAGES =+ "${PN}-tests"

RDEPENDS_${PN}-tests = "python-dbus python-json python-misc python-pkgutil python-xml"

FILES_${PN} += "${datadir}/dbus-1/services/*.service"
# When we have GI, package cap
FILES_${PN}-tests = "${bindir}/rendererconsole.py"
