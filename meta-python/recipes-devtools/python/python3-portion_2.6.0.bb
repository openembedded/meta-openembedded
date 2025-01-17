DESCRIPTION = "Python data structure and operations for intervals"
HOMEPAGE = "https://github.com/AlexandreDecan/portion"
SECTION = "devel/python"

LICENSE = "LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3000208d539ec061b899bce1d9ce9404"

inherit pypi python_setuptools_build_meta ptest-python-pytest

SRC_URI[sha256sum] = "6fb538b57a92058f0edd360667694448aa3fc028ab97e41e3091359d14ba4dd5"

RDEPENDS:${PN} += "\
	python3-sortedcontainers \
"

do_install_ptest:append () {
	# This file tests README.md and deleted as redundant.
	rm -f ${D}/tests/test_doc.py
}

BBCLASSEXTEND = "native"
