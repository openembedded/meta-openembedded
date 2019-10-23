SUMMARY = "Easy manipulation of Python source via the AST."
HOMEPAGE = "https://github.com/berkerpeksag/astor"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=561205fdabc3ec52cae2d30815b8ade7"

SRC_URI = "git://github.com/berkerpeksag/astor.git"
SRCREV ?= "3a7607e31f0c17e747ded5cfe0b582d99f7caecf"

inherit setuptools3

S = "${WORKDIR}/git"

BBCLASSEXTEND = "native"
