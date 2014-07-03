SUMMARY = "A Library to Access SMI MIB Information"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=3ad3076f9332343a21636cfd351f05b7"

SRC_URI = "ftp://ftp.ibr.cs.tu-bs.de/pub/local/libsmi/${BP}.tar.gz"

SRC_URI[md5sum] = "760b6b1070738158708649ed2c63425e"
SRC_URI[sha256sum] = "f048a5270f41bc88b0c3b0a8fe70ca4d716a46b531a0ecaaa87c462f49d74849"

inherit autotools

PACKAGES += "${PN}-mibs ${PN}-pibs"

FILES_${PN}-mibs += "${datadir}/mibs"
FILES_${PN}-pibs += "${datadir}/pibs"
