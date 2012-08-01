require anki.inc

SRC_URI = "${SOURCEFORGE_MIRROR}/anki/anki-${PV}.tgz"
SRC_URI[md5sum] = "90434860945de4c09d55cdb5dbe984fc"
SRC_URI[sha256sum] = "18a93fb46363ca34963fc2588cadf7415dd799dd647efa681859eb8b1b22f104"

export BUILD_PV := "${PV}"
SRC_URI += "file://no-need-for-pyqt-at-buildtime.patch"
