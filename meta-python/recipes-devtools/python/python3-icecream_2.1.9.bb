SUMMARY = "Never use print() to debug again; inspect variables, expressions, and program execution with a single, simple function call."
HOMEPAGE = "https://github.com/gruns/icecream"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=eca5ce1610d64ed40920efdce85ff8d1"

SRC_URI[sha256sum] = "e47b6477d29848949ee5050127aeb88fd30f1908e5cf29772e8a1a0fcdf15473"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
        python3-asttokens \
        python3-colorama \
        python3-executing \
        python3-numbers \
        python3-pygments \
        python3-pprint \
"
