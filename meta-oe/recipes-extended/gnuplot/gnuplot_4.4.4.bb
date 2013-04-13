require gnuplot.inc

PR = "r1"

SRC_URI = "${SOURCEFORGE_MIRROR}/gnuplot/${PN}-${PV}.tar.gz;name=archive \
    http://www.mneuroth.de/privat/zaurus/qtplot-0.2.tar.gz;name=qtplot \
    file://subdirs.patch \
    file://gnuplot.desktop \
    file://gnuplot.png \
    file://automake-1.12.x.patch \
"

SRC_URI[archive.md5sum] = "97a43328e81e57ebed7f135ca0c07e82"
SRC_URI[archive.sha256sum] = "d228599d08d5f87bcb79af8af8bb432c77d942802b1643f920c7baaeaf1ddbfd"
SRC_URI[qtplot.md5sum] = "0a481885a496092c77eb4017540b5cf6"
SRC_URI[qtplot.sha256sum] = "6df317183ff62cc82f3dcf88207a267cd6478cb5147f55d7530c94f1ad5f4132"
