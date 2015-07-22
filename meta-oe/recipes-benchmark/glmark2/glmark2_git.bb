SUMMARY = "OpenGL (ES) 2.0 benchmark"
DESCRIPTION = "glmark2 is a benchmark for OpenGL (ES) 2.0. \
It uses only the subset of the OpenGL 2.0 API that is compatible with OpenGL ES 2.0."
HOMEPAGE = "https://launchpad.net/glmark2"
BUGTRACKER = "https://bugs.launchpad.net/glmark2"

LICENSE = "GPLv3+ & SGIv1"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
                    file://COPYING.SGI;beginline=5;md5=269cdab4af6748677acce51d9aa13552"

DEPENDS = "libpng12 jpeg virtual/libx11"

# depends on virtual/libx11
REQUIRED_DISTRO_FEATURES = "x11"

PV = "2014.03+${SRCPV}"

SRC_URI = "git://github.com/glmark2/glmark2.git;protocol=https"
SRCREV = "fa71af2dfab711fac87b9504b6fc9862f44bf72a"

S = "${WORKDIR}/git"

inherit waf pkgconfig distro_features_check

PACKAGECONFIG ?= "gl gles2"

PACKAGECONFIG[gl] = ",,virtual/libgl"
PACKAGECONFIG[gles2] = ",,virtual/libgles2"

python __anonymous() {
    packageconfig = (d.getVar("PACKAGECONFIG", True) or "").split()
    flavors = []
    if "gles2" in packageconfig:
        flavors.append("x11-glesv2")
    if "gl" in packageconfig:
        flavors.append("x11-gl")
    if flavors:
        d.appendVar("EXTRA_OECONF", " --with-flavors=%s" % ",".join(flavors))
}
