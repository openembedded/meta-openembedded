SUMMARY = "Serial Port Support for Python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d476d94926db6e0008a5b3860d1f5c0d"

# FIXME might stop packaging serialwin32 and serialjava files

RDEPENDS_${PN} = "\
    ${PYTHON_PN}-fcntl \
    ${PYTHON_PN}-io \
    ${PYTHON_PN}-stringold \
"

SRC_URI[md5sum] = "7142a421c8b35d2dac6c47c254db023d"
SRC_URI[sha256sum] = "1eecfe4022240f2eab5af8d414f0504e072ee68377ba63d3b6fe6e66c26f66d1"

inherit pypi

do_install_append() {
	# We don't support jpython now.
	if [ -e ${D}${PYTHON_SITEPACKAGES_DIR}/serial/serialjava.py ]; then
		rm -f ${D}${PYTHON_SITEPACKAGES_DIR}/serial/serialjava.py
		rm -f ${D}${PYTHON_SITEPACKAGES_DIR}/serial/serialjava.pyc
	fi
}
