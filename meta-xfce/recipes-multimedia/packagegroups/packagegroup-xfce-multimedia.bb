SUMMARY = "All packages multimedia packages available for XFCE"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

inherit packagegroup

# While this item does not require it, it depends on xfmpc and xfc4-mpc-plugin
# that wants mpd which does
LICENSE_FLAGS = "commercial"

RDEPENDS_${PN} = " \
    parole \
    xfmpc \
    xfce4-mpc-plugin \
"
