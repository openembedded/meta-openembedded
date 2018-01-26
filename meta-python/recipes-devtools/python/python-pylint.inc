SUMMARY="Pylint is a Python source code analyzer"
HOMEPAGE= "http://www.pylint.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=4325afd396febcb659c36b49533135d4"

SRC_URI[md5sum] = "f6c290e2ff171e83694e82096f13fd4f"
SRC_URI[sha256sum] = "4fe3b99da7e789545327b75548cee6b511e4faa98afe268130fea1af4b5ec022"

inherit pypi

DEPENDS += "${PYTHON_PN}-pytest-runner-native"

do_install_append(){
    rm ${D}${bindir}/pylint
    cat >> ${D}${bindir}/pylint <<EOF
#!/usr/bin/env ${PYTHON_PN}
from pylint import run_pylint
run_pylint()
EOF
    chmod 755 ${D}${bindir}/pylint
    sed -i -e 's:^#!/usr/bin/python:#!/usr/bin/env\ ${PYTHON_PN}:g' ${D}/${PYTHON_SITEPACKAGES_DIR}/pylint/test/data/ascript
}

PACKAGES =+ "${PN}-tests"
FILES_${PN}-tests+= " \
    ${PYTHON_SITEPACKAGES_DIR}/pylint/test/ \
    ${PYTHON_SITEPACKAGES_DIR}/pylint/testutils.py \
"

RDEPENDS_${PN} += "${PYTHON_PN}-astroid \
                   ${PYTHON_PN}-isort \
                   ${PYTHON_PN}-numbers \
                   ${PYTHON_PN}-shell \
                   ${PYTHON_PN}-json \
                   ${PYTHON_PN}-pkgutil \
                   ${PYTHON_PN}-difflib \
                   ${PYTHON_PN}-netserver \
                  "
