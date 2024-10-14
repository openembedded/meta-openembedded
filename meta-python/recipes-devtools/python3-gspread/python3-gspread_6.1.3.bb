SUMMARY = "Google Spreadsheets Python API"
HOMEPAGE = "https://github.com/burnash/gspread"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9488e21983675fa56dc05af558b83e2f"

SRC_URI[sha256sum] = "cf03627f44e9e03a0a3de241d1748709db22af4fc8c11a13aa389d0bce6053fd"

RDEPENDS:${PN} = " \
	python3-google-auth \
	python3-google-auth-oauthlib \
	python3-strenum \
	"

inherit pypi python_flit_core
