SUMMARY = "An open source MPEG-4 and MPEG-2 AAC decoding library"
HOMEPAGE = "http://www.audiocoding.com/faad2.html"
SECTION = "libs"
LICENSE = "GPL-2.0-only"

LIC_FILES_CHKSUM = "file://COPYING;md5=381c8cbe277a7bc1ee2ae6083a04c958"

LICENSE_FLAGS = "commercial"

SRC_URI = "${SOURCEFORGE_MIRROR}/faac/faad2-src/faad2-2.8.0/${BP}.tar.gz \
           file://0001-fix-heap-buffer-overflow-in-mp4read.c.patch \
           file://0001-mp4read.c-fix-stack-buffer-overflow-in-stringin-ftyp.patch \
           file://0001-Restrict-SBR-frame-length-to-960-and-1024-samples.patch \
           file://0001-Check-return-value-of-ltp_data.patch \
           "
SRC_URI[md5sum] = "28f6116efdbe9378269f8a6221767d1f"
SRC_URI[sha256sum] = "985c3fadb9789d2815e50f4ff714511c79c2710ac27a4aaaf5c0c2662141426d"

inherit autotools lib_package
