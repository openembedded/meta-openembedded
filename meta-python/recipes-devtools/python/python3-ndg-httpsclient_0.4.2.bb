inherit pypi setuptools3
require python-ndg-httpsclient.inc

PNBLACKLIST[python3-ndg-httpsclient] ?= "Fails to build with RSS http://errors.yoctoproject.org/Errors/Details/130615/"
