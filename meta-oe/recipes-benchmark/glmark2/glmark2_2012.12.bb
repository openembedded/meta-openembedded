SUMMARY = "OpenGL (ES) 2.0 benchmark"
DESCRIPTION = "glmark2 is a benchmark for OpenGL (ES) 2.0. \
It uses only the subset of the OpenGL 2.0 API that is compatible with OpenGL ES 2.0."
HOMEPAGE = "https://launchpad.net/glmark2"
BUGTRACKER = "https://bugs.launchpad.net/glmark2"

LICENSE = "GPLv3+ & SGIv1"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
                    file://COPYING.SGI;beginline=5;md5=269cdab4af6748677acce51d9aa13552"

DEPENDS = "libpng12 jpeg"

SRC_URI = "https://launchpad.net/${BPN}/trunk/${PV}/+download/${BP}.tar.gz"

SRC_URI[md5sum] = "4f306664aa3886fa0cf93853603603f8"
SRC_URI[sha256sum] = "bea6f9de2cdce376195bd91e4a2fdfdf80bf3e480abff8e05b90a6458c1deb47"

inherit waf pkgconfig

PACKAGECONFIG ?= "gl gles2"

PACKAGECONFIG[gl] = "--enable-gl,,virtual/libgl"
PACKAGECONFIG[gles2] = "--enable-glesv2,,virtual/libgles2"
