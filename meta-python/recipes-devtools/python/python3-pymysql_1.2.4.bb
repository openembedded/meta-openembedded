SUMMARY = "A pure-Python MySQL client library"
DESCRIPTION = " \
  This package contains a pure-Python MySQL client library, based on PEP 249 \
  Most public APIs are compatible with mysqlclient and MySQLdb. \
  "
SECTION = "devel/python"
HOMEPAGE = "https://pymysql.readthedocs.io"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=528175c84163bb800d23ad835c7fa0fc"

SRC_URI[sha256sum] = "491558165b8e11f3644c5f9cfb30cc9c9111ffd54e53d3aa91b9a0a20be2294f"


inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += "python3-cryptography"
