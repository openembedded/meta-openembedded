SUMMARY = "Serial Port Support for Python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d476d94926db6e0008a5b3860d1f5c0d"

# FIXME might stop packaging serialwin32 and serialjava files

RDEPENDS_${PN} = "\
    ${PYTHON_PN}-fcntl \
    ${PYTHON_PN}-io \
    ${PYTHON_PN}-stringold \
"

SRC_URI[md5sum] = "2f72100de3e410b36d575e12e82e9d27"
SRC_URI[sha256sum] = "d657051249ce3cbd0446bcfb2be07a435e1029da4d63f53ed9b4cdde7373364c"

inherit pypi

do_install_append() {
	# We don't support jpython now.
	if [ -e ${D}${PYTHON_SITEPACKAGES_DIR}/serial/serialjava.py ]; then
		rm -f ${D}${PYTHON_SITEPACKAGES_DIR}/serial/serialjava.py
		rm -f ${D}${PYTHON_SITEPACKAGES_DIR}/serial/serialjava.pyc
	fi
}
