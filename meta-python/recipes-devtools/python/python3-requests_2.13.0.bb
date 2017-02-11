inherit pypi setuptools3
require python-requests.inc

# Add the runtime depends for selectors.py
RDEPENDS_${PN} += "${PYTHON_PN}-misc"

PNBLACKLIST[python3-requests] ?= "Runtime depends on blacklisted python3-ndg-httpsclient"

PNBLACKLIST[python3-requests] ?= "Runtime depends on blacklisted python3-requests-dev"
