require ${BPN}.inc

SRCNAME = "enlightenment"

S = "${WORKDIR}/${SRCNAME}-${PV}"

SRC_URI = "\
    ${E_RELEASES}/apps/${SRCNAME}/${SRCNAME}-${SRCVER}.tar.gz \
    file://0001-remove-another-elm-1.8-requirement.patch \
    file://enlightenment_start.oe \
    file://applications.menu \
"

SRC_URI[md5sum] = "d3534c765d562524c63852f2f40932c3"
SRC_URI[sha256sum] = "273afe860b65e1e39323bab80ab51e44845bcddcd25063b47eadab1c925a77c9"

do_configure_prepend() {
    sed '/^ *EFL_PKG_CHECK_VAR/ s/systemduserunitdir/systemdsystemunitdir/g' -i ${S}/configure.ac
}

PACKAGECONFIG[systemd] = "--enable-systemd,--disable-systemd,systemd"
