SUMMARY = "Doing dirty (but extremely useful) things with equals."
DESCRIPTION = "dirty-equals is a python library that (mis)uses the \
__eq__ method to make python code (generally unit tests) more \
declarative and therefore easier to read and write.\
\
dirty-equals can be used in whatever context you like, but it comes \
into its own when writing unit tests for applications where you're \
commonly checking the response to API calls and the contents of a database."
HOMEPAGE = "https://github.com/samuelcolvin/dirty-equals"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ab599c188b4a314d2856b3a55030c75c"

SRC_URI[sha256sum] = "a580513f3285e93656a770a04e428f8bfc513848877bf278282dbfbb907fdbb4"

S = "${WORKDIR}/dirty_equals-${PV}"

inherit pypi python_hatchling

PYPI_PACKAGE = "dirty_equals"

RDEPENDS:${PN} += "python3-pytz"
