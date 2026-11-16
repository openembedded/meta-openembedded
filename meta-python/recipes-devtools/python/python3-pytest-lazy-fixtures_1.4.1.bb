SUMMARY = "Allows you to use fixtures in @pytest.mark.parametrize."
DESCRIPTION = "Use your fixtures in @pytest.mark.parametrize. \
\
This project was inspired by pytest-lazy-fixture.\
\
Improvements that have been made in this project:\
\
* You can use fixtures in any data structures\
* You can access the attributes of fixtures\
* You can use functions in fixtures"
HOMEPAGE = "https://github.com/dev-petrov/pytest-lazy-fixtures"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4475e66fcfabe27395e6764c8f69c876"

SRC_URI[sha256sum] = "8684abfd4914ea01f1e2f7b24524739344421d8f3f12286fb3100fb5681e1181"

inherit pypi python_hatchling

RDEPENDS:${PN} = "python3-pytest"
