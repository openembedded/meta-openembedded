ESCRIPTION = "The GNU Compact Disc Input and Control library (libcdio) contains a library for CD-ROM and CD image access."
HOMEPAGE = "http://www.gnu.org/software/libcdio/"
SECTION = "libs"

LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "ncurses"

SRC_URI = "${GNU_MIRROR}/${BPN}/${BP}.tar.gz"
SRC_URI[md5sum] = "1c29b18e01ab2b966162bc727bf3c360"
SRC_URI[sha256sum] = "1acb3de8e0927906ade7a34c5853173d3068b87b02dfba80d0bf11e47f0b5d39"

inherit autotools pkgconfig

EXTRA_OECONF = "ac_cv_member_struct_tm_tm_gmtoff=no --disable-rpath"
DEBUG_OPTIMIZATION_thumb = "-Os -fno-omit-frame-pointer -g"

PACKAGES += "${PN}-utils"

FILES_${PN} = "${libdir}/${PN}${SOLIB}"
FILES_${PN}-utils = "${bindir}/*"

python populate_packages_prepend () {
    glibdir = d.expand('${libdir}')
    do_split_packages(d, glibdir, '^lib(.*)\.so\..*', 'lib%s', 'gstreamer %s library', extra_depends='', allow_links=True)
}

