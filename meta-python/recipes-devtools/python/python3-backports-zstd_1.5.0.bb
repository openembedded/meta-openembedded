SUMMARY = "Backport of compression.zstd"
DESCRIPTION = "Backport of PEP-784 'adding Zstandard to the standard library'"
HOMEPAGE = "https://github.com/Rogdham/backports.zstd/"
LICENSE = "0BSD & PSF-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=224f30639105a6ab845e068c2d0903ff \
                    file://LICENSE_zstd.txt;md5=85fffd6822a26cd7d2a6eeb939ada0da \
                    file://src/c/pythoncapi-compat/COPYING;md5=f74f54822fab8814a50330e4e4578b88 \
                    file://src/c/zstd/LICENSE;md5=0822a32f7acdbe013606746641746ee8"

inherit pypi python_setuptools_build_meta

SRC_URI += " \
        file://0001-pyproject.toml-make-license-entries-compatible-with-.patch \
        file://0002-pyprojects.toml-lower-setuptools-requirements.patch \
"

SRC_URI[sha256sum] = "a5e622a82eb183b4fbe18032755ce0a15fa9a82f2adb9b621620b91247aaedb7"

PYPI_PACKAGE = "backports_zstd"

BBCLASSEXTEND = "native nativesdk"
