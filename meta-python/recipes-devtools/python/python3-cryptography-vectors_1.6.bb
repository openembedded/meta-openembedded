inherit pypi setuptools3
require python-cryptography-vectors.inc

PNBLACKLIST[python3-cryptography-vectors] ?= "Fails to build with RSS http://errors.yoctoproject.org/Errors/Details/130626/"
