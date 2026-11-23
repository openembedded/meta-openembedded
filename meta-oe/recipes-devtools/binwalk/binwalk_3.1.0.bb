SUMMARY = "Firmware analysis tool"
DESCRIPTION = "Binwalk identifies, and optionally extracts, files and data \
embedded inside other files, such as firmware images."
HOMEPAGE = "https://github.com/ReFirmLabs/binwalk"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b10c9218a44cbb4b7efb15b5288898b"

SRC_URI = "crate://crates.io/binwalk/${PV};name=binwalk"
SRC_URI[binwalk.sha256sum] = "99d5dc70f5021c55624f098adae4d1de9c1c07d000475664704b675aad7e0811"
S = "${CARGO_VENDORING_DIRECTORY}/binwalk-${PV}"

CVE_PRODUCT = "binwalk"

inherit cargo cargo-update-recipe-crates pkgconfig

require ${BPN}-crates.inc

# lzma-sys, bzip2-sys, yeslogic-fontconfig-sys and freetype-sys
DEPENDS += "bzip2 fontconfig freetype xz"

# External extraction utilities run by binwalk -e that OE provides
RRECOMMENDS:${PN} += " \
    7zip \
    cabextract \
    dtc \
    lz4 \
    lzop \
    tar \
    zstd \
"

RREPLACES:${PN} = "python3-binwalk"
RCONFLICTS:${PN} = "python3-binwalk"
