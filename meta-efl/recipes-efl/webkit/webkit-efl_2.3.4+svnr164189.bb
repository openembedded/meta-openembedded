require ${BPN}.inc

SRC_URI = "\
    ${E_RELEASES}/libs/webkit-efl/webkit-efl-164189.tar.xz \
    file://0001-WebKitHelpers.cmake-Add-Wno-error-cast-align.patch \
    file://0002-ARMAssembler.h-Don-t-generate-BKPT-and-BLX-for-armv4.patch \
    file://0003-Fix-linking-issue.patch \
    file://0004-WebMemorySamplerLinux-Fix-type-limits-warning.patch \
"
SRC_URI[md5sum] = "731513fc042ec8e03840bc1ab6a66771"
SRC_URI[sha256sum] = "660aefd65c0e5c6494eaec30539cda5f40fbdff17f28e7e83d341b245227cccd"

S = "${WORKDIR}/efl-webkit"
