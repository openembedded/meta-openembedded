DESCRIPTION = "FreeRDP RDP client & server library"
HOMEPAGE = "http://www.freerdp.com"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

DEPENDS = "openssl libusb1 uriparser cairo icu pkcs11-helper zlib jpeg json-c"

inherit pkgconfig cmake

SRCREV = "11ed2c65fe1f50f8714e51be5bcb2655cd8ba31f"
SRC_URI = "git://github.com/FreeRDP/FreeRDP.git;branch=master;protocol=https;tag=${PV}"


CVE_PRODUCT = "freerdp"

PACKAGECONFIG ??= " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'pam pulseaudio wayland x11', d)} \
    ${@bb.utils.contains('LICENSE_FLAGS_ACCEPTED', 'commercial', 'ffmpeg', '', d)} \
    gstreamer cups pcsc \
"

EXTRA_OECMAKE = " \
    -DRDTK_FORCE_STATIC_BUILD=ON \
    -DUWAC_FORCE_STATIC_BUILD=ON \
    -DWITH_AAD=ON \
    -DWITH_BINARY_VERSIONING=ON \
    -DWITH_CHANNELS=ON \
    -DWITH_CLIENT_CHANNELS=ON \
    -DWITH_JPEG=ON \
    -DWITH_PKCS11=ON \
    -DWITH_SERVER_CHANNELS=ON \
    -DWITH_SERVER=ON \
    -DPKG_CONFIG_RELOCATABLE=OFF \
    -DWITH_ALSA=OFF \
    -DWITH_CLIENT_SDL=OFF \
    -DWITH_SAMPLE=OFF \
    -DWITH_CAIRO=ON \
 "

X11_DEPS = "virtual/libx11 libxinerama libxext libxcursor libxv libxi libxrender libxfixes libxdamage libxrandr libxkbfile"
PACKAGECONFIG[x11] = "-DWITH_X11=ON -DWITH_XINERAMA=ON -DWITH_XEXT=ON -DWITH_XCURSOR=ON -DWITH_XV=ON -DWITH_XI=ON -DWITH_XRENDER=ON -DWITH_XFIXES=ON -DWITH_XDAMAGE=ON -DWITH_XRANDR=ON -DWITH_XKBFILE=ON,-DWITH_X11=OFF -DWITH_SHADOW=OFF,${X11_DEPS}"
PACKAGECONFIG[wayland] = "-DWITH_WAYLAND=ON,-DWITH_WAYLAND=OFF,wayland wayland-native libxkbcommon"
PACKAGECONFIG[pam] = "-DWITH_PAM=ON,-DWITH_PAM=OFF,libpam"
PACKAGECONFIG[pulseaudio] = "-DWITH_PULSEAUDIO=ON,-DWITH_PULSEAUDIO=OFF,pulseaudio"
PACKAGECONFIG[gstreamer] = "-DWITH_GSTREAMER_1_0=ON,-DWITH_GSTREAMER_1_0=OFF,gstreamer1.0 gstreamer1.0-plugins-base"
PACKAGECONFIG[cups] = "-DWITH_CUPS=ON,-DWITH_CUPS=OFF,cups"
PACKAGECONFIG[fuse] = "-DWITH_FUSE=ON,-DWITH_FUSE=OFF,fuse3,fuse3"
PACKAGECONFIG[pcsc] = "-DWITH_PCSC=ON,-DWITH_PCSC=OFF,pcsc-lite"
PACKAGECONFIG[manpages] = "-DWITH_MANPAGES=ON,-DWITH_MANPAGES=OFF, libxslt-native docbook-xsl-stylesheets-native"
PACKAGECONFIG[ffmpeg] = "-DWITH_DSP_FFMPEG=ON -DWITH_FFMPEG=ON -DWITH_SWSCALE=ON, -DWITH_DSP_FFMPEG=OFF -DWITH_FFMPEG=OFF -DWITH_SWSCALE=OFF,ffmpeg"
PACKAGECONFIG[krb5] = "-DWITH_KRB5=ON -DWITH_KRB5_NO_NTLM_FALLBACK=OFF,-DWITH_KRB5=OFF,krb5"
PACKAGECONFIG[openh264] = "-DWITH_OPENH264=ON,-DWITH_OPENH264=OFF,openh264"
PACKAGECONFIG[opencl] = "-DWITH_OPENCL=ON,-DWITH_OPENCL=OFF,virtual/libopencl1"
PACKAGECONFIG[lame] = "-DWITH_LAME=ON,-DWITH_LAME=OFF,lame"
PACKAGECONFIG[faad] = "-DWITH_FAAD=ON,-DWITH_FAAD=OFF,faad2"
PACKAGECONFIG[faac] = "-DWITH_FAAC=ON,-DWITH_FAAC=OFF,faac"

do_configure:append() {
    sed -i -e 's|${WORKDIR}||g' ${B}/include/freerdp/buildflags.h
    sed -i -e 's|${WORKDIR}||g' ${B}/winpr/include/winpr/buildflags.h
}

PACKAGES =+ "${PN}-proxy-plugins"

FILES:${PN}-proxy-plugins += "${libdir}/${BPN}/proxy/*.so*"

FILES:${PN} += "${datadir}"

SYSROOT_DIRS += "${bindir}"

INSANE_SKIP:${PN}-proxy-plugins  += "dev-so"
