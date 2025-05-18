SUMMARY = "pytest xdist plugin for distributed testing and loop-on-failing modes"
HOMEPAGE = "https://github.com/pytest-dev/pytest-xdist"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=772fcdaca14b378878d05c7d857e6c3e"

SRC_URI[sha256sum] = "4580deca3ff04ddb2ac53eba39d76cb5dd5edeac050cb6fbc768b0dd712b4edf"

inherit pypi python_setuptools_build_meta

DEPENDS += "python3-setuptools-scm-native"

# Note that the dependency on pytest-forked is scheduled to be dropped in 3.0
RDEPENDS:${PN} += " \
    python3-execnet \
    python3-pytest \
    python3-pytest-forked \
"
