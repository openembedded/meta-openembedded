require ${BPN}.inc

SRCNAME = "enlightenment"

S = "${WORKDIR}/${SRCNAME}-${PV}"

SRC_URI = "\
    ${E_RELEASES}/apps/${SRCNAME}/${SRCNAME}-${SRCVER}.tar.gz \
    file://enlightenment_start.oe \
    file://applications.menu \
"

SRC_URI[md5sum] = "9f6e6c296b88b038ac05415006c678fd"
SRC_URI[sha256sum] = "17d207246bf10dec5a5fa854da5763e0e6dd61decde3474ff5fedffbb9dc8629"

do_configure_prepend() {
    sed '/^ *EFL_PKG_CHECK_VAR/ s/systemduserunitdir/systemdsystemunitdir/g' -i ${S}/configure.ac
}

PACKAGECONFIG[systemd] = "--enable-systemd,--disable-systemd,systemd"
