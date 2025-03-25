DESCRIPTION = "Python SQL toolkit and Object Relational Mapper that gives \
application developers the full power and flexibility of SQL"
HOMEPAGE = "http://www.sqlalchemy.org/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=061025f14213ac2818ff353223d6eca6"

SRC_URI[sha256sum] = "5d2d1fe548def3267b4c70a8568f108d1fed7cbbeccb9cc166e05af2abc25c22"

inherit pypi python_setuptools_build_meta cython

RDEPENDS:${PN} += " \
    python3-asyncio \
    python3-compression \
    python3-json \
    python3-logging \
    python3-netclient \
    python3-numbers \
    python3-pickle \
    python3-profile \
    python3-threading \
    python3-typing-extensions \
"

BBCLASSEXTEND = "native nativesdk"
