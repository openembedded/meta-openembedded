SUMMARY = "A ``pytest`` fixture for benchmarking code. It will group the tests into rounds that are calibrated to the chosen timer."
HOMEPAGE = "https://github.com/ionelmc/pytest-benchmark"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d606e94f56c21c8e0cdde0b622dcdf57"

SRC_URI[sha256sum] = "9ea661cdc292e8231f7cd4c10b0319e56a2118e2c09d9f50e1b3d150d2aca105"

inherit pypi setuptools3

PYPI_PACKAGE_SDIST = "pytest-benchmark"

RDEPENDS:${PN} += "\
    python3-aspectlib \
    python3-core \
    python3-datetime \
    python3-json \
    python3-misc \
    python3-netclient \
    python3-numbers \
    python3-profile \
    python3-py-cpuinfo \
    python3-pytest \
    python3-statistics \
"

BBCLASSEXTEND = "native nativesdk"
