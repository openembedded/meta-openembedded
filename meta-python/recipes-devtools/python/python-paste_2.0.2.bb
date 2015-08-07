SUMMARY = "Tools for using a Web Server Gateway Interface stack"
HOMEPAGE = "http://pythonpaste.org/"
SECTION = "devel/python"
LICENSE = "MIT"
SRCNAME = "Paste"
RDEPENDS_${PN} = "python-six"

LIC_FILES_CHKSUM = "file://docs/license.txt;md5=1798f29d55080c60365e6283cb49779c"

SRC_URI = "http://pypi.python.org/packages/source/P/${SRCNAME}/${SRCNAME}-${PV}.tar.gz;name=paste"
SRC_URI[paste.md5sum] = "4bfc8a7eaf858f6309d2ac0f40fc951c"
SRC_URI[paste.sha256sum] = "adac3ac893a2dac6b8ffd49901377dd6819e05be3436b374d698641071daba99"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

FILES_${PN} += "/usr/lib/*"
