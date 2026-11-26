SUMMARY = "Perfetto C SDK shared library"
DESCRIPTION = "Shared library built from the amalgamated Perfetto C SDK sources"
HOMEPAGE = "https://perfetto.dev"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=0b17e8eda713c18363c16102cacd41f3"

SRC_URI = "\
    https://github.com/google/perfetto/releases/download/v${PV}/perfetto-c-sdk-src.zip;downloadfilename=${BPN}-${PV}.zip;subdir=${BP} \
    file://meson.build;subdir=${BP} \
    file://libperfetto_c.map;subdir=${BP} \
"

SRC_URI[sha256sum] = "28d497a5b01c7517b2084c4b4768a1fdbbac587242775af50f5219936056361f"

S = "${UNPACKDIR}/${BP}"

inherit meson

do_configure:prepend() {
    echo "${PV}" > ${S}/VERSION
}
