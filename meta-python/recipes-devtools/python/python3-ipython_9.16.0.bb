SUMMARY = "IPython: Productive Interactive Computing"
HOMEPAGE = "https://ipython.org"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING.rst;md5=954609b5a3603364bc25989a92e38d67"

SRC_URI[sha256sum] = "d2f92587b1ef51d84f934dffe05fabb9255f0038ed0a21426f2ea761e39ad09a"

RDEPENDS:${PN} = "\
    python3-setuptools \
    python3-jedi \
    python3-decorator \
    python3-pickleshare \
    python3-traitlets \
    python3-prompt-toolkit \
    python3-pygments \
    python3-backcall \
    python3-pydoc \
    python3-debugger \
    python3-pexpect \
    python3-unixadmin \
    python3-misc \
    python3-sqlite3 \
    python3-stack-data \
"

inherit python_setuptools_build_meta pypi

CVE_PRODUCT = "ipython"
