DESCRIPTION = "HTTP/2 State-Machine based protocol implementation"
HOMEPAGE = "https://github.com/python-hyper/hyper-h2"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://LICENSE;md5=aa3b9b4395563dd427be5f022ec321c1"

SRC_URI[sha256sum] = "46b551bdcdc7e83cf5c04d0bf93badb8a939bd2287d9fee1abb23a445b9e0580"

inherit ptest-python-pytest pypi python_setuptools_build_meta

RDEPENDS:${PN} += "python3-hpack python3-hyperframe"
RDEPENDS:${PN}-ptest += "python3-hypothesis"

do_install_ptest:append(){
    # by defining CI envvar, hypothesis will use the CI-profile by default,
    # and will not use tight execution deadlines (which times out easily on qemu without kvm)
    sed -i 's/pytest/CI=1 pytest/' ${D}${PTEST_PATH}/run-ptest
}
