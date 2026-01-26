SUMMARY = "Extremely fast, in memory, JSON and reflection library for modern C++. BEVE, CBOR, CSV, MessagePack, TOML, EETF "
HOMEPAGE = "https://stephenberry.github.io/glaze/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ea4d29875d83fbbf50485c846dbbbed8"

SRC_URI = "git://github.com/stephenberry/glaze;protocol=https;branch=main;tag=v${PV}"

SRCREV = "a4af950700b8af2659f0d4a37a18a1b9c5300593"

inherit cmake

EXTRA_OECMAKE = "-Dglaze_BUILD_EXAMPLES=OFF -DBUILD_TESTING=OFF -Dglaze_ENABLE_FUZZING=OFF -Dglaze_DEVELOPER_MODE=OFF"

do_install:append() {
    install -d ${D}${datadir}/cmake/${BPN}
    mv -f ${D}${datadir}/${BPN}/*.cmake ${D}${datadir}/cmake/${BPN}
    rmdir -p --ignore-fail-on-non-empty ${D}${datadir}/${BPN}
}

# Glaze is a header-only C++ library, so the main package will be empty.
ALLOW_EMPTY:${PN} = "1"

BBCLASSEXTEND = "native"
