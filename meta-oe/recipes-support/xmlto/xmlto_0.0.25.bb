DESCRIPTION = "A shell-script tool for converting XML files to various formats" 
HOMEPAGE = "https://fedorahosted.org/xmlto/" 
SECTION = "docs/xmlto" 
LICENSE = "GPLv2" 

LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

SRC_URI = "https://fedorahosted.org/releases/x/m/xmlto/xmlto-${PV}.tar.gz" 
SRC_URI[md5sum] = "a88cd3f08789b4825d1ac89fa065170d"
SRC_URI[sha256sum] = "c52b56d929e8d20fc19cd3b7ec238f8d039730c56ee311cc352e843147e3e31a"

inherit autotools  

BBCLASSEXTEND = "native nativesdk"
