SUMMARY = "Rich toolkit for building command-line applications"
HOMEPAGE = "https://github.com/patrick91/rich-toolkit"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://pyproject.toml;md5=29c91c89ee62891477a1476375143bef;beginline=6;endline=6"

SRC_URI[sha256sum] = "04768f19438df877c6279e6e668ab0d88b5fff5c7ef3ac302838d97f7b03472e"

inherit pypi python_hatchling ptest-python-pytest

RDEPENDS:${PN} = "\
    python3-click \
    python3-rich \
    python3-terminal \
    python3-typing-extensions \
    python3-inline-snapshot \
"

RDEPENDS:${PN}-ptest += "\
    python3-pydantic \
"
