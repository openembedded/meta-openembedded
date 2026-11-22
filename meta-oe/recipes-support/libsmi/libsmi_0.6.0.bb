SUMMARY = "A Library to Access SMI MIB Information"
HOMEPAGE = "https://www.ibr.cs.tu-bs.de/projects/libsmi"

# Per REUSE.toml: libsmi license (TCL), snprintf BSD-3-Clause, shhopt notice, IETF modules BSD-2-Clause
LICENSE = "TCL AND BSD-3-Clause AND BSD-2-Clause AND LicenseRef-shhopt"
LICENSE:${PN}-mibs = "TCL AND BSD-2-Clause"
LICENSE:${PN}-pibs = "TCL AND BSD-2-Clause"
LICENSE:${PN}-yang = "TCL AND BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5c05af6b3935c81a2358608b0c5acf98 \
                    file://LICENSES/BSD-3-Clause.txt;md5=2524f8052574e48a807f7d7dc43be505 \
                    file://LICENSES/LicenseRef-IETF-Trust.txt;md5=6edd3f52ac05da3698b5b87d022e60c0 \
                    file://LICENSES/LicenseRef-shhopt.txt;md5=c1d5556736f40d903ed610df3922545d \
                    "
NO_GENERIC_LICENSE[shhopt] = "LICENSES/LicenseRef-shhopt.txt"

SRC_URI = "git://gitlab.ibr.cs.tu-bs.de/nm/libsmi.git;protocol=https;branch=main;tag=v${PV} \
           file://smi.conf \
          "
SRCREV = "5e88c72eb68924263ccb180ba89e5ed66feb86a6"

UPSTREAM_CHECK_GITTAGREGEX = "v(?P<pver>\d+(\.\d+)+)$"

DEPENDS += "bison-native flex-native wget-native gawk-native"

inherit autotools

EXTRA_OECONF:class-target = "ac_cv_path_WGET=${bindir}/wget ac_cv_prog_AWK=awk"

do_install:append () {
    install -d ${D}${sysconfdir}
    install -m 0644 ${UNPACKDIR}/smi.conf ${D}${sysconfdir}/smi.conf
}

PACKAGES += "${PN}-mibs ${PN}-pibs ${PN}-yang"

FILES:${PN}-mibs += "${datadir}/mibs"
FILES:${PN}-pibs += "${datadir}/pibs"
FILES:${PN}-yang += "${datadir}/yang"

RRECOMMENDS:${PN} = "${BPN}-mibs"

BBCLASSEXTEND = "native nativesdk"
