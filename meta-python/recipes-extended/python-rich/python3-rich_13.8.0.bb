SUMMARY = "Rich is a Python library for rich text and beautiful formatting in the terminal"
DESCRIPTION = "The Rich API makes it easy to add color and style to terminal output. \
Rich can also render pretty tables, progress bars, markdown, syntax highlighted source code, \
tracebacks, and more."
HOMEPAGE="https://github.com/Textualize/rich"
SECTION = "devel/python"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://LICENSE;md5=b5f0b94fbc94f5ad9ae4efcf8a778303"

SRC_URI[sha256sum] = "a5ac1f1cd448ade0d59cc3356f7db7a7ccda2c8cbae9c7a90c28ff463d3e91f4"

inherit pypi python_poetry_core

RDEPENDS:${PN} = "python3-pygments"
