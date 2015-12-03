require xl2tpd.inc

SRC_URI = "git://github.com/xelerance/xl2tpd.git;protocol=git;tag=v${PV} \
    file://fix-inline-functions-errors-with-gcc-5.x.patch \
"
