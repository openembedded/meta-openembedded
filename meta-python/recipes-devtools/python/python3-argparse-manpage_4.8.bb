SUMMARY = "Build manual page from python's ArgumentParser object"
HOMEPAGE = "https://github.com/praiskup/argparse-manpage"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRC_URI[sha256sum] = "d55ce1c246479ddd82871170a8cc543a6831bb68c87a298e47f0663a9330b74f"

inherit pypi python_setuptools_build_meta

DEPENDS += "python3-packaging-native"

RDEPENDS:${PN} += " \
    python3-core \
    python3-datetime \
    python3-setuptools \
"

BBCLASSEXTEND = "native nativesdk"
