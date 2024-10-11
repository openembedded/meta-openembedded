SUMMARY = "Rich is a Python library for rich text and beautiful formatting in the terminal"
DESCRIPTION = "The Rich API makes it easy to add color and style to terminal output. \
Rich can also render pretty tables, progress bars, markdown, syntax highlighted source code, \
tracebacks, and more."
HOMEPAGE="https://github.com/Textualize/rich"
SECTION = "devel/python"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://LICENSE;md5=b5f0b94fbc94f5ad9ae4efcf8a778303"

SRC_URI[sha256sum] = "51a2c62057461aaf7152b4d611168f93a9fc73068f8ded2790f29fe2b5366d0c"

inherit pypi python_poetry_core

RDEPENDS:${PN} = "python3-pygments"
