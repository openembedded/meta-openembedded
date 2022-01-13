DESCRIPTION = "TimescaleDB is a relational database for time-series data built as an extension for PostgreSQL."
HOMEPAGE = "https://timescale.com"
LICENSE = "APACHE-2.0 & TIMESCALE"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=a6919544b6c76fc2e0debe84e8dfc7a5 \
                    file://${S}/LICENSE-APACHE;md5=2ee41112a44fe7014dce33e26468ba93 \
                    file://${S}/tsl/LICENSE-TIMESCALE;md5=1b3b062af96cae618d6ebd31df2712c8 \
"

DEPENDS = "postgresql"
RDEPENDS:${PN} = "postgresql"

SRC_URI = "git://github.com/timescale/timescaledb.git;protocol=https;branch=${BRANCH};rev=${COMMIT};destsuffix=timescaledb-${PV}"

inherit cmake

EXTRA_OECMAKE += " -DREGRESS_CHECKS=OFF"

FILES_${PN} = "\
    ${datadir}/postgresql/extension/timescaledb* \
    ${libdir}/postgresql/timescaledb* \
"
