SUMMARY = "discover, browse and search UPNP/DLNA media servers"
HOMEPAGE = "https://01.org/dleyna/"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
                    file://src/media-service-upnp.c;beginline=1;endline=21;md5=584eb103b4f6f3432c43d169b6a123a1"

DEPENDS = "dbus glib-2.0 gssdp gupnp gupnp-av gupnp-dlna libsoup-2.4"

SRC_URI = "https://github.com/01org/${BPN}/archive/v${PV}.zip"
SRC_URI[md5sum] = "9fc4db84b3459bc77232b24bde48b000"
SRC_URI[sha256sum] = "9cdee47bbbfa3de5aaa5cf3fc9e208ccd97e5079f6d8fa84a6d1f24a1b760237"

inherit autotools

do_install_append() {
    install -d ${D}${bindir}
    install -m 0755 dms-info ${D}${bindir}
}

PACKAGES =+ "${PN}-tests"

FILES_${PN} += "${datadir}/dbus-1/services/*.service"
FILES_${PN}-tests = "${bindir}/dms-info"
