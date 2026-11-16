DESCRIPTION = "A pure Python 2/3 library for peripheral I/O (GPIO, LED, PWM, SPI, I2C, MMIO, Serial) in Linux."
HOMEPAGE = "https://pythonhosted.org/python-periphery/"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://LICENSE;md5=bf8adf497db13d58fece90db6850816c"

SRCREV = "e82b08df1cd0f438d8a88d7b82b58dc8afade531"
PYPI_SRC_URI = "git://github.com/vsergeev/python-periphery;protocol=https;branch=master;tag=v${PV};destsuffix=${PYPI_PACKAGE_SDIST}-${PV}"

inherit pypi setuptools3

PYPI_PACKAGE = "python-periphery"

RDEPENDS:${PN} += "python3-mmap \
		python3-ctypes \
		python3-fcntl"
