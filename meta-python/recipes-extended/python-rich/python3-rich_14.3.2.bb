SUMMARY = "Rich is a Python library for rich text and beautiful formatting in the terminal"
DESCRIPTION = "The Rich API makes it easy to add color and style to terminal output. \
Rich can also render pretty tables, progress bars, markdown, syntax highlighted source code, \
tracebacks, and more."
HOMEPAGE = "https://github.com/Textualize/rich"
SECTION = "devel/python"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://LICENSE;md5=b5f0b94fbc94f5ad9ae4efcf8a778303"

SRC_URI[sha256sum] = "e712f11c1a562a11843306f5ed999475f09ac31ffb64281f73ab29ffdda8b3b8"

inherit pypi python_poetry_core

RDEPENDS:${PN} = "python3-html \
                  python3-image \
                  python3-pygments \
                  python3-unixadmin \
                  "

BBCLASSEXTEND = "native nativesdk"
