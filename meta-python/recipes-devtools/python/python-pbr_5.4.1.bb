inherit setuptools
# The inc file is in oe-core
require recipes-devtools/python/python-pbr.inc

SRC_URI[md5sum] = "ab6e26026ab306989a636ec2d50a435a"
SRC_URI[sha256sum] = "0ca44dc9fd3b04a22297c2a91082d8df2894862e8f4c86a49dac69eae9e85ca0"

do_install_append() {
        if [ -f ${D}${bindir}/pbr ]; then
                mv ${D}${bindir}/pbr ${D}${bindir}/pbr-2
        fi
}

