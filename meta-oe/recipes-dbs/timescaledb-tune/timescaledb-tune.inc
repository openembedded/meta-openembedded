DESCRIPTION = "A utility for tuning TimescaleDB"

RDEPENDS:${PN}="timescaledb"

GO_IMPORT = "github.com/timescale/timescaledb-tune"
export GO111MODULE="off"

inherit go

SRC_URI = "\
  git://${GO_IMPORT};protocol=https;rev=${TUNE_COMMIT};destsuffix=${BPN}-${PV}/src/${GO_IMPORT} \
  git://github.com/fatih/color;protocol=https;rev=${COLOR_COMMIT};destsuffix=${BPN}-${PV}/src/github.com/fatih/color \
  git://github.com/pbnjay/memory;protocol=https;rev=${MEMORY_COMMIT};destsuffix=${BPN}-${PV}/src/github.com/pbnjay/memory \
"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=2ee41112a44fe7014dce33e26468ba93"

FILES_${PN} += "${GOBIN_FINAL}/*"
