SUMMARY = "Open Source multimedia player"
DESCRIPTION = "mpv is a fork of mplayer2 and MPlayer. It shares some features with the former projects while introducing many more."
SECTION = "multimedia"
HOMEPAGE = "http://www.mpv.io/"
DEPENDS = "zlib ffmpeg jpeg virtual/libx11 xsp libxv \
           libxscrnsaver libv4l libxinerama \
"

REQUIRED_DISTRO_FEATURES = "x11"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE.GPL;md5=91f1cb870c1cc2d31351a4d2595441cb"

# While this item does not require it, it depends on ffmpeg which does
LICENSE_FLAGS = "commercial"

SRCREV_mpv = "70b991749df389bcc0a4e145b5687233a03b4ed7"
SRC_URI = " \
    git://github.com/mpv-player/mpv;name=mpv \
    https://www.freehackers.org/~tnagy/release/waf-2.0.19;name=waf;downloadfilename=waf;subdir=git \
    file://python3.patch \
"
SRC_URI[waf.md5sum] = "cef4ee82206b1843db082d0b0506bf71"
SRC_URI[waf.sha256sum] = "01bf2beab2106d1558800c8709bc2c8e496d3da4a2ca343fe091f22fca60c98b"

S = "${WORKDIR}/git"

inherit waf pkgconfig features_check mime-xdg

LUA ?= "lua"
LUA_mips64  = ""
LUA_aarch64  = ""
LUA_powerpc64  = ""
LUA_powerpc64le  = ""
LUA_powerpc  = ""

# Note: both lua and libass are required to get on-screen-display (controls)
PACKAGECONFIG ??= " \
    ${LUA} \
    libass \
    ${@bb.utils.filter('DISTRO_FEATURES', 'wayland', d)} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'opengl', d)} \
"

PACKAGECONFIG_remove_aarch64 = "lua"
PACKAGECONFIG[x11] = "--enable-x11,--disable-x11,virtual/libx11"
PACKAGECONFIG[xv] = "--enable-xv,--disable-xv,libxv"
PACKAGECONFIG[opengl] = "--enable-gl,--disable-gl,virtual/libgl"
PACKAGECONFIG[egl] = "--enable-egl,--disable-egl,"
PACKAGECONFIG[drm] = "--enable-drm,--disable-drm,libdrm"
PACKAGECONFIG[gbm] = "--enable-gbm,--disable-gbm,virtual/libgbm"
PACKAGECONFIG[lua] = "--enable-lua,--disable-lua,lua luajit"
PACKAGECONFIG[libass] = "--enable-libass,--disable-libass,libass"
PACKAGECONFIG[libarchive] = "--enable-libarchive,--disable-libarchive,libarchive"
PACKAGECONFIG[jack] = "--enable-jack, --disable-jack, jack"
PACKAGECONFIG[vaapi] = "--enable-vaapi,--disable-vaapi,libva"
PACKAGECONFIG[vdpau] = "--enable-vdpau,--disable-vdpau,libvdpau"
PACKAGECONFIG[wayland] = "--enable-wayland,--disable-wayland,wayland wayland-native libxkbcommon"

python __anonymous() {
    packageconfig = (d.getVar("PACKAGECONFIG") or "").split()
    extras = []
    if "x11" in packageconfig and "opengl" in packageconfig:
        extras.append(" --enable-gl-x11")
    if "x11" in packageconfig and "egl" in packageconfig:
        extras.append(" --enable-egl-x11")
    if "egl" in packageconfig and "drm" in packageconfig:
        extras.append(" --enable-egl-drm")
    if "vaapi" in packageconfig and "x11" in packageconfig:
        extras.append(" --enable-vaapi-x11")
    if "vaapi" in packageconfig and "drm" in packageconfig:
        extras.append(" --enable-vaapi-drm")
    if "vaapi" in packageconfig and "x11" in packageconfig and "egl" in packageconfig:
        extras.append(" --enable-vaapi-x-egl")
    if "vdpau" in packageconfig and "opengl" in packageconfig and "x11" in packageconfig:
        extras.append(" --enable-vdpau-gl-x11")
    if "wayland" in packageconfig and "opengl" in packageconfig:
        extras.append(" --enable-gl-wayland")
    if "wayland" in packageconfig and "vaapi" in packageconfig:
        extras.append(" --enable-vaapi-wayland")
    if extras:
        d.appendVar("EXTRA_OECONF", "".join(extras))
}

SIMPLE_TARGET_SYS = "${@'${TARGET_SYS}'.replace('${TARGET_VENDOR}', '')}"

EXTRA_OECONF = " \
    --prefix=${prefix} \
    --target=${SIMPLE_TARGET_SYS} \
    --confdir=${sysconfdir} \
    --datadir=${datadir} \
    --disable-manpage-build \
    --disable-libsmbclient \
    --disable-libbluray \
    --disable-dvdnav \
    --disable-cdda \
    --disable-uchardet \
    --disable-rubberband \
    --disable-lcms2 \
    --disable-vapoursynth \
    ${PACKAGECONFIG_CONFARGS} \
"

adjust_waf_perms() {
    chmod +x ${S}/waf
}

do_patch[postfuncs] += "adjust_waf_perms"

FILES_${PN} += " \
    ${datadir}/icons \
    ${datadir}/zsh \
    ${datadir}/bash-completion \
    "
