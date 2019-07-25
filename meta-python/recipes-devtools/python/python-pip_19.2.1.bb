SUMMARY = "PIP is a tool for installing and managing Python packages"
HOMEPAGE = "https://pip.pypa.io/"
LICENSE = "MIT & LGPL-2.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=8ba06d529c955048e5ddd7c45459eb2e"

SRC_URI[md5sum] = "e9ac3e030e88b6c076a20ab371a30742"
SRC_URI[sha256sum] = "258d702483dd749400aec59c23d638a5b2249ae28a0f478b6cab12ad45681a80"

inherit pypi setuptools

# Since PIP is like CPAN for PERL we need to drag in all python modules to ensure everything works
RDEPENDS_${PN}_class-target = "python-modules python-distribute python-misc"

BBCLASSEXTEND = "native nativesdk"
