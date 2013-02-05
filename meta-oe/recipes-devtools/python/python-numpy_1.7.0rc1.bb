DESCRIPTION = "A sophisticated Numeric Processing Package for Python"
SECTION = "devel/python"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=f87832d854acbade6e9f5c601c8b30b1"
PR = "r0"
PV = "1.6.99+1.7.0rc1"
REALPV = "1.7.0rc1"

SRC_URI = "${SOURCEFORGE_MIRROR}/numpy/numpy-${REALPV}.tar.gz \
           ${CONFIGFILESURI} \
           file://aarch64.patch \
	  "
CONFIGFILESURI ?= ""

CONFIGFILESURI_aarch64 = "file://config.h \
	   file://_numpyconfig.h \
	  "
CONFIGFILESURI_arm = "file://config.h \
	   file://numpyconfig.h \
	  "
CONFIGFILESURI_mipsel = "file://config.h \
	   file://numpyconfig.h \
	  "

S = "${WORKDIR}/numpy-${REALPV}"

inherit distutils

# Make the build fail and replace *config.h with proper one
# This is a ugly, ugly hack - Koen
do_compile_prepend() {
         BUILD_SYS=${BUILD_SYS} HOST_SYS=${HOST_SYS} \
         ${STAGING_BINDIR_NATIVE}/python-native/python setup.py build ${DISTUTILS_BUILD_ARGS} || \
         true
	 cp ${WORKDIR}/*config.h ${S}/build/$(ls ${S}/build | grep src)/numpy/core/include/numpy/
}

FILES_${PN}-staticdev += "${PYTHON_SITEPACKAGES_DIR}/numpy/core/lib/*.a"
SRC_URI[md5sum] = "a4719f5a1853bc0f8892a5956d5c4229"
SRC_URI[sha256sum] = "45ea23622f72d86bc3614446d668ee962c0475ee7b91a93ef85a5e0493962de5"

# install what is needed for numpy.test()
RDEPENDS_${PN} = "python-unittest \
                  python-difflib \
                  python-pprint \
                  python-pickle \
                  python-shell \
                  python-nose \
                  python-doctest \
                  python-datetime \
                  python-distutils \
                  python-misc \
                  python-mmap \
                  python-netclient \
                  python-numbers \
                  python-pydoc \
                  python-pkgutil \
                  python-email \
                  python-subprocess \
                  python-compression \
                 "
