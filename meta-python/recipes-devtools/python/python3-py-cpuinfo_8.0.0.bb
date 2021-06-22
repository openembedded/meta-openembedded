# Copyright (C) 2021 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Get CPU info with pure Python 2 & 3"
HOMEPAGE = "https://github.com/workhorsy/py-cpuinfo"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b0b97c022f12b14d9e02de0b283ee9e9"

SRC_URI[sha256sum] = "5f269be0e08e33fd959de96b34cd4aeeeacac014dd8305f70eb28d06de2345c5"

inherit pypi setuptools3

RDEPENDS_${PN} += "python3-core python3-ctypes python3-datetime python3-json python3-mmap python3-multiprocessing python3-netclient python3-pickle python3-pprint python3-shell"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    _winreg
#    cStringIO
#    winreg
BBCLASSEXTEND = "native nativesdk"
