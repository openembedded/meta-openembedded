SUMMARY = "Garbage Collection Library"
DESCRIPTION = "The GC library provides a garbage collector for C and C++."
HOMEPAGE = "http://www.hpl.hp.com/personal/Hans_Boehm/gc/"

LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = "file://doc/README;beginline=1;endline=37;md5=9f4fd307082acf7a8af3e3897245163b  \
                    file://README.QUICK;beginline=1;endline=23;md5=293ea42f3f606426deefadd5dd8ddd15 \
"

SRC_URI = "http://www.hpl.hp.com/personal/Hans_Boehm/gc/gc_source/gc-${PV}.tar.gz"

SRC_URI[md5sum] = "91340b28c61753a789eb6077675d87d2"
SRC_URI[sha256sum] = "d9fe0ae8650d43746a48bfb394cab01a319f3809cee19f8ebd16aa985b511c5e"

S = "${WORKDIR}/gc-7.2"

inherit autotools

# by default use external libatomic-ops
PACKAGECONFIG ??= "libatomic-ops"
PACKAGECONFIG[libatomic-ops] = "--with-libatomic-ops=yes,--with-libatomic-ops=no,libatomics-ops"

PACKAGES =+ "${PN}-docs"
FILES_${PN}-docs = "/usr/share/gc"
