SUMMARY = "Extending PyYAML with a custom constructor for including YAML files within YAML files"
HOMEPAGE = "https://github.com/tanbro/pyyaml-include"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"
SRCREV = "0f86bf16343d2ad52b53b793e0b35bb7ed7cd85b"

SRC_URI = " \
            git://github.com/tanbro/pyyaml-include;protocol=https;branch=1.x \
            file://run-ptest \
          "

S = "${WORKDIR}/git"

inherit python_setuptools_build_meta ptest

do_install_ptest() {
    install -d ${D}${PTEST_PATH}/tests
    cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}

RDEPENDS:${PN} += " \
    python3-pyyaml \
    python3-toml \
"
RDEPENDS:${PN}-ptest += " \
    python3-pytest \
"
BBCLASSEXTEND = "native nativesdk"
