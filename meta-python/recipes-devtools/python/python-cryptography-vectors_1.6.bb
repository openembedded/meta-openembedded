inherit pypi setuptools
require python-cryptography-vectors.inc

PNBLACKLIST[python-cryptography-vectors] ?= "Fails to build with RSS http://errors.yoctoproject.org/Errors/Details/130664/"
