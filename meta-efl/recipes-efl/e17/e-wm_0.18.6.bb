require ${BPN}.inc

SRCNAME = "enlightenment"

S = "${WORKDIR}/${SRCNAME}-${PV}"

SRC_URI = "\
    ${E_RELEASES}/apps/${SRCNAME}/${SRCNAME}-${SRCVER}.tar.gz \
    file://enlightenment_start.oe \
    file://applications.menu \
"

SRC_URI[md5sum] = "a3a707e32fd72547e0911e3765d112f1"
SRC_URI[sha256sum] = "a75f85e65038672a0413c74bffc7f4869c3c41090afe6b66876241780e478c2b"

do_configure_prepend() {
    sed '/^ *EFL_PKG_CHECK_VAR/ s/systemduserunitdir/systemdsystemunitdir/g' -i ${S}/configure.ac
}

PACKAGECONFIG[systemd] = "--enable-systemd,--disable-systemd,systemd"
