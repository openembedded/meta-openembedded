inherit setuptools
# The inc file is in oe-core
require recipes-devtools/python/python-pbr.inc

SRC_URI[md5sum] = "6619780896ca81c7cd19c6e2f439b6c9"
SRC_URI[sha256sum] = "1b8be50d938c9bb75d0eaf7eda111eec1bf6dc88a62a6412e33bf077457e0f45"

do_install_append() {
        if [ -f ${D}${bindir}/pbr ]; then
                mv ${D}${bindir}/pbr ${D}${bindir}/pbr-2
        fi
}

