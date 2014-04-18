require ${BPN}.inc

SRCNAME = "enlightenment"

S = "${WORKDIR}/${SRCNAME}-${PV}"

SRC_URI = "\
    ${E_RELEASES}/apps/${SRCNAME}/${SRCNAME}-${SRCVER}.tar.gz \
    file://enlightenment_start.oe \
    file://applications.menu \
"

SRC_URI[md5sum] = "499b92ef38347881c72729f1e066eb20"
SRC_URI[sha256sum] = "1b05a1d986643a6603b1c20ac87b6864325d0aea22a3d1d096f4063b4fba2f4d"

do_configure_prepend() {
    sed '/^ *EFL_PKG_CHECK_VAR/ s/systemduserunitdir/systemdsystemunitdir/g' -i ${S}/configure.ac
}

PACKAGECONFIG[systemd] = "--enable-systemd,--disable-systemd,systemd"
