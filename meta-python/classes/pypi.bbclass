def pypi_package(d):
    bpn = d.getVar('BPN', True)
    if bpn.startswith('python-'):
        return bpn[7:]
    elif bpn.startswith('python3-'):
        return bpn[8:]
    return bpn

PYPI_PACKAGE ?= "${@pypi_package(d)}"
PYPI_PACKAGE_EXT ?= "tar.gz"

def pypi_src_uri(d):
    package = d.getVar('PYPI_PACKAGE', True)
    package_ext = d.getVar('PYPI_PACKAGE_EXT', True)
    package_hash = d.getVar('PYPI_PACKAGE_HASH', True)
    pv = d.getVar('PV', True)
    if package_hash:
        return 'https://pypi.python.org/packages/%s/%s/%s/%s-%s.%s' % ( package_hash[:2], package_hash[2:4], package_hash[4:], package, pv, package_ext)
    else:
        return 'https://pypi.python.org/packages/source/%s/%s/%s-%s.%s' % (package[0], package, package, pv, package_ext)

PYPI_SRC_URI ?= "${@pypi_src_uri(d)}"

HOMEPAGE ?= "https://pypi.python.org/pypi/${PYPI_PACKAGE}/"
SECTION = "devel/python"
SRC_URI += "${PYPI_SRC_URI}"
S = "${WORKDIR}/${PYPI_PACKAGE}-${PV}"
