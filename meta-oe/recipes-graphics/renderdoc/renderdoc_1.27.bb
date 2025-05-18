SUMMARY = "RenderDoc recipe providing renderdoccmd"
DESCRIPTION = "RenderDoc is a frame-capture based graphics debugger"
HOMEPAGE = "https://github.com/baldurk/renderdoc"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=36d34a46cb71546195d2b0c626a52e5d"

SRCREV = "35b13a8e8fd2a331854dba6da81a20452e142d6f"
SRC_URI = " \
    git://github.com/baldurk/${BPN}.git;protocol=https;branch=v1.x \
    file://0001-renderdoc-use-xxd-instead-of-cross-compiling-shim-bi.patch \
"
S = "${WORKDIR}/git"

DEPENDS += "virtual/libx11 virtual/libgl libxcb xcb-util-keysyms vim-native"

RDEPENDS:${PN} = "libxcb xcb-util-keysyms"

inherit cmake pkgconfig python3native features_check

REQUIRED_DISTRO_FEATURES = "x11 opengl"

python __anonymous () {
    # only works on glibc systems
    if d.getVar('TCLIBC') != "glibc":
        raise bb.parse.SkipRecipe("incompatible with %s C library" % d.getVar('TCLIBC'))
}

COMPATIBLE_HOST = "(x86_64|i.86|arm|aarch64).*-linux"

EXTRA_OECMAKE += "\
    -DENABLE_QRENDERDOC=OFF \
    -DENABLE_PYRENDERDOC=OFF \
    -DENABLE_RENDERDOCCMD=ON \
    -DCMAKE_BUILD_TYPE=Release \
"

FILES:${PN} += "${libdir}"
FILES:${PN}-dev = "${includedir}"
