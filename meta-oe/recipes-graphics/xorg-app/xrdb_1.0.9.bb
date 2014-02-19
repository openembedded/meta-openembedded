require recipes-graphics/xorg-app/xorg-app-common.inc
SUMMARY = "X server resource database utility"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=d1167c4f586bd41f0c62166db4384a69"

DEPENDS += "libxmu"
PR = "r6"

SRC_URI[md5sum] = "ed2e48cf33584455d74615ad4bbe4246"
SRC_URI[sha256sum] = "642401e12996efe3e5e5307a245e24c282b94a44c1f147e177c8484b862aeab7"
