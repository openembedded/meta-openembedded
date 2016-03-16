SUMMARY = "A Library to Access SMI MIB Information"
HOMEPAGE = "https://www.ibr.cs.tu-bs.de/projects/libsmi"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=3ad3076f9332343a21636cfd351f05b7"

SRC_URI = "https://www.ibr.cs.tu-bs.de/projects/${BPN}/download/${BP}.tar.gz \
           file://smi.conf \
          "

SRC_URI[md5sum] = "760b6b1070738158708649ed2c63425e"
SRC_URI[sha256sum] = "f048a5270f41bc88b0c3b0a8fe70ca4d716a46b531a0ecaaa87c462f49d74849"

inherit autotools

EXTRA_OECONF = "ac_cv_path_SH=${base_bindir}/sh"

do_install_append () {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/smi.conf ${D}${sysconfdir}/smi.conf
}

PACKAGES += "${PN}-mibs ${PN}-pibs"

FILES_${PN}-mibs += "${datadir}/mibs"
FILES_${PN}-pibs += "${datadir}/pibs"

RRECOMMENDS_${PN} = "${BPN}-mibs"
