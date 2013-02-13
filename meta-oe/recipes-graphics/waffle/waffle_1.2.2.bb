SUMMARY = "cross-platform C library to defer selection of GL API and of window system"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=4c5154407c2490750dd461c50ad94797 \
                    file://include/waffle/waffle.h;endline=24;md5=61dbf8697f61c78645e75a93c585b1bf"

SRC_URI = "http://people.freedesktop.org/~chadversary/waffle/files/release/${BPN}-${PV}/${BPN}-${PV}.tar.xz \
           file://cflags.patch"

SRC_URI[md5sum] = "fdd07cea7709422fbf72418ee63a285d"
SRC_URI[sha256sum] = "7e342c859b58d4e051b347ef3d7740ed2f3b6c506b93daec272724afe7dd1311"

inherit cmake

# This should be overridden per-machine to reflect the capabilities of the GL
# stack.
PACKAGECONFIG ??= "glx"

# I say virtual/libgl, actually wants gl.pc
PACKAGECONFIG[glx] = "-Dwaffle_has_glx=1,,virtual/libgl libx11"

# I say virtual/libgl, actually wants wayland-egl.pc, egl.pc, and the wayland
# DISTRO_FEATURE.
PACKAGECONFIG[wayland] = "-Dwaffle_has_wayland=1,,virtual/libgl wayland"

# I say virtual/libgl, actually wants gbm.pc egl.pc
PACKAGECONFIG[gbm] = "-Dwaffle_has_wayland=1,,virtual/libgl udev"

# I say virtual/libgl, actually wants egl.pc
PACKAGECONFIG[x11-egl] = "-Dwaffle_has_x11_egl=1,,virtual/libgl libxcb"

# Take the flags added by PACKAGECONFIG and pass them to cmake.
EXTRA_OECMAKE = "${EXTRA_OECONF}"

FILES_${PN}-dev += "${datadir}/cmake/Modules/FindWaffle.cmake"
