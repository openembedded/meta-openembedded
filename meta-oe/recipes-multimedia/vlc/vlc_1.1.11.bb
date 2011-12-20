# This recipe packages vlc as a library as well, so qt4 dependencies
# can be avoided when only the library is installed.

LIC_FILES_CHKSUM = "file://COPYING;md5=ed7e492ee44e70125a5d42e118354a13"

require vlc.inc

PR = "r1"

# work around build failure
EXTRA_OECONF += " --enable-libxml2=no"

SRC_URI[md5sum] = "a64846d6f21ea179ae8e8bfb6f9447fe"
SRC_URI[sha256sum] = "682560be08b82bedfaf30d8a611d80093c5883c1de72fcbcf05715b8e9f4e1cb"

