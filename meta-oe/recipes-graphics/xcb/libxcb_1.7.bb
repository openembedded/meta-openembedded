include libxcb.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=d763b081cb10c223435b01e00dc0aba7"

PR = "r1"

DEPENDS += "libpthread-stubs xcb-proto-native"

PACKAGES =+ "libxcb-xinerama"

SRC_URI[md5sum] = "925699df361b99491165ebc12068056b"
SRC_URI[sha256sum] = "e776dc35e2a66094e7308ce2e045fb4e27cf3ca30ab1813a8fc08538140724df"
