require recipes-graphics/xorg-app/xorg-app-common.inc

SUMMARY = "a simple program for managing utmp/wtmp entries"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=d938a70c8280c265a1ccd2954365d185"

SRC_URI[md5sum] = "e238c89dabc566e1835e1ecb61b605b9"
SRC_URI[sha256sum] = "551177657835e0902b5eee7b19713035beaa1581bbd3c6506baa553e751e017c"

# | sed: file filenames.sed line 3: unterminated `s' command
# | make[2]: *** [sessreg.1] Error 1
# | make[2]: Leaving directory `/home/jenkins/oe/world/shr-core/tmp-glibc/work/armv5te-oe-linux-gnueabi/sessreg/1.1.0-r0/build/man'
PNBLACKLIST[sessreg] ?= "BROKEN: fails to generate man pages"
