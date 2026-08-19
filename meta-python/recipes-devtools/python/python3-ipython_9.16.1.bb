SUMMARY = "IPython: Productive Interactive Computing"
HOMEPAGE = "https://ipython.org"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING.rst;md5=954609b5a3603364bc25989a92e38d67"

SRC_URI[sha256sum] = "5a3d1f9a47ff216d6cf9cf863124f6a2c1a198d1354c546a4d24a370a283b64c"

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
