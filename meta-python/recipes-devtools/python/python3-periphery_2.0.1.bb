DESCRIPTION = "A pure Python 2/3 library for peripheral I/O (GPIO, LED, PWM, SPI, I2C, MMIO, Serial) in Linux."
HOMEPAGE = "http://pythonhosted.org/python-periphery/"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://PKG-INFO;md5=1ecf5c2354c22fb5bfd53eefb8f9e65b"

SRC_URI[md5sum] = "1d958f02575d4a19734ee2dd92336157"
SRC_URI[sha256sum] = "5da4d5f40ff8974cf6c724587baa674d7e0593f07b6f6ee896104f11c1be18ec"

inherit pypi setuptools3

PYPI_PACKAGE = "python-periphery"

RDEPENDS_${PN} += "${PYTHON_PN}-mmap"
