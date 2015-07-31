SUMMARY = "Python decorator utilities"
HOMEPAGE = "http://pypi.python.org/pypi/decorator/"
DESCRIPTION = "\
The aim of the decorator module it to simplify the usage of decorators \
for the average programmer, and to popularize decorators by showing \
various non-trivial examples. Of course, as all techniques, decorators \
can be abused and you should not try to solve every problem with a \
decorator, just because you can."

SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=104961fab4bc3ef8d0640bb37641b473"

SRCNAME = "decorator"

SRC_URI = "https://pypi.python.org/packages/source/d/decorator/decorator-${PV}.tar.gz"
S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

SRC_URI[md5sum] = "9e0536870d2b83ae27d58dbf22582f4d"
SRC_URI[sha256sum] = "7320002ce61dea6aa24adc945d9d7831b3669553158905cdd12f5d0027b54b44"
