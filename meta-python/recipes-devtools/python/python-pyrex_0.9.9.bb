SUMMARY = "Language for writing Python extension modules"
DESCRIPTION = "Pyrex is a language specially designed for writing Python extension modules. \
It's designed to bridge the gap between the nice, high-level, easy-to-use world of Python \
and the messy, low-level world of C."
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=771d472f53f933033f57eeee7808e5bd"
SRCNAME = "Pyrex"
PR = "r4"

SRC_URI = "\
  http://www.cosc.canterbury.ac.nz/greg.ewing/python/${SRCNAME}/${SRCNAME}-${PV}.tar.gz \
  file://pyrex-fix-optimized-mode.patch \
"

SRC_URI[md5sum] = "70ff83ad4207f07deb531745a71df813"
SRC_URI[sha256sum] = "6a596d594b4b4b1ebfc09ccaa36732523eda12ca2acba05e1cbf3b12ad172107"
S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit distutils

FILES_${PN} += "${datadir}${base_libdir}/${PYTHON_DIR}/site-packages/Pyrex/Compiler/Lexicon.pickle"
