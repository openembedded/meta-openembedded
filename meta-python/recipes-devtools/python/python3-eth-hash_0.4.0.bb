SUMMARY = "The Ethereum hashing function, keccak256, sometimes (erroneously) called sha3."
HOMEPAGE = "https://github.com/ethereum/eth-hash"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=287820ad3553117aa2f92bf84c219324"

SRC_URI[sha256sum] = "ae8ba88c1c2f0074e5e169bc0c08318c063054532809aa1bfc8367962b1b7088"

inherit pypi setuptools3

RDEPENDS:${PN} = "\
	${PYTHON_PN}-logging \
	${PYTHON_PN}-pycryptodome \
"
