DESCRIPTION = "A database migration tool for SQLAlchemy"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=28aaf14a6592d14dbfb2f4abd597aa27"

SRC_URI[md5sum] = "6ac9edfb0b3951fef3b356d2761ebb3f"
SRC_URI[sha256sum] = "49277bb7242192bbb9eac58fed4fe02ec6c3a2a4b4345d2171197459266482b2"

PYPI_PACKAGE = "alembic"

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-dateutil \
    ${PYTHON_PN}-editor \
    ${PYTHON_PN}-mako \
    ${PYTHON_PN}-sqlalchemy \
"
