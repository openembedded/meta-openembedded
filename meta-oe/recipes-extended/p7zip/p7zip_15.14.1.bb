SUMMARY = "7-zip is a commandline utility handling 7z archives."
HOMEPAGE = "http://www.7-zip.org/"
LICENSE = "LGPL-2.1+ & unRAR"
LIC_FILES_CHKSUM = "file://DOC/copying.txt;md5=4fbd65380cdd255951079008b364516c \
                    file://DOC/unRarLicense.txt;md5=9c87ddde469ef94aed153b0951d088de \
                    file://DOC/License.txt;md5=8346bfd0a2fa0987e7a3a512adf84ab9"

SRC_URI = "http://downloads.sourceforge.net/p7zip/p7zip/${PV}/p7zip_${PV}_src_all.tar.bz2 \
          file://do_not_override_compiler_and_do_not_strip.patch"
SRC_URI[md5sum] = "92cca093312b5a71a7be7dc7d1d32509"
SRC_URI[sha256sum] = "699db4da3621904113e040703220abb1148dfef477b55305e2f14a4f1f8f25d4"

S = "${WORKDIR}/${BPN}_${PV}"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/bin/* ${D}${bindir}
}

# all3: to build bin/7za, bin/7z (with its plugins), bin/7zr and bin/7zCon.sfx
EXTRA_OEMAKE_class-native = "all3"

do_install_class-native() {
    install -d ${D}${bindir}
    install -d ${D}${bindir}/Codecs
    install -m 0755 ${S}/bin/7* ${D}${bindir}
    install -m 0755 ${S}/bin/Codecs/* ${D}${bindir}/Codecs

    # Create a shell script wrapper to execute next to 7z.so
    mv ${D}${bindir}/7z ${D}${bindir}/7z.bin
    echo "#! /bin/sh" > ${D}${bindir}/7z
    echo "exec ${D}${bindir}/7z.bin \"\$@\"" >> ${D}${bindir}/7z
    chmod 0755 ${D}${bindir}/7z
}

BBCLASSEXTEND += "native"
