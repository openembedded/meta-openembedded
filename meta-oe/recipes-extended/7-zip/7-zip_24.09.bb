SUMMARY = "7-Zip is a file archiver with a high compression ratio."
HOMEPAGE = "https://www.7-zip.org"
LICENSE = "LGPL-2.1-or-later & BSD-3-Clause & unRAR"
LIC_FILES_CHKSUM = "file://DOC/copying.txt;md5=b0d181292c99cf9bb2ae9166dd3a0239 \
                    file://DOC/unRarLicense.txt;md5=9c87ddde469ef94aed153b0951d088de \
                    file://DOC/License.txt;md5=8bfe22e0285a3043b61ea408d234c55d"
CVE_PRODUCT = "7-zip 7zip"

PV_WITHOUT_DOT = "${@d.getVar('PV').replace('.', '')}"

SRC_URI = "https://www.7-zip.org/a/7z${PV_WITHOUT_DOT}-src.tar.xz;subdir=${BP}"
SRC_URI[sha256sum] = "49c05169f49572c1128453579af1632a952409ced028259381dac30726b6133a"

B = "${WORKDIR}/build"

EXTRA_OEMAKE += 'LDFLAGS="${LDFLAGS}" LFLAGS_STRIP="" O="${B}"'

do_compile() {
    cd ${S}/CPP/7zip/Bundles/Alone2
    oe_runmake --file makefile.gcc
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/7zz ${D}${bindir}/7zz
}

BBCLASSEXTEND = "native nativesdk"
