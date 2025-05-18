SUMMARY     = "Multimedia processing server for Linux"
DESCRIPTION = "Linux server for handling and routing audio and video streams between applications and multimedia I/O devices"
HOMEPAGE    = "https://pipewire.org/"
BUGTRACKER  = "https://gitlab.freedesktop.org/pipewire/pipewire/issues"
AUTHOR      = "Wim Taymans <wtaymans@redhat.com>"
SECTION     = "multimedia"

LICENSE = "MIT & LGPL-2.1-or-later & GPL-2.0-only"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=2158739e172e58dc9ab1bdd2d6ec9c72 \
    file://COPYING;md5=97be96ca4fab23e9657ffa590b931c1a \
"

DEPENDS = "dbus ncurses"

SRCREV = "64cf5e80e6240284e6b757907b900507fe56f1b5"
SRC_URI = "git://gitlab.freedesktop.org/pipewire/pipewire.git;branch=master;protocol=https"

S = "${WORKDIR}/git"

inherit meson pkgconfig systemd gettext useradd

USERADD_PACKAGES = "${PN}"

GROUPADD_PARAM:${PN} = "--system pipewire"

USERADD_PARAM:${PN} = "--system --home / --no-create-home \
                       --comment 'PipeWire multimedia daemon' \
                       --gid pipewire --groups audio,video \
                       pipewire"

SYSTEMD_PACKAGES = "${PN}"

# For "EVL", look up https://evlproject.org/ . It involves
# a specially prepared kernel, and is currently unavailable
# in Yocto.
#
# Vulkan support is currently (as of version 0.3.44) not functional.
#
# manpage generation requires xmltoman, which is not available.
#
# The session-managers list specifies which session managers Meson
# shall download (via git clone) and build as subprojects. In OE,
# this is not how a session manager should be built. Instead, they
# should be integrated as separate OE recipes. To prevent PipeWire
# from using this Meson feature, set an empty list.
# This does not disable support or the need for session managers,
# it just prevents this subproject feature.
#
# AptX and LDAC are not available in OE. Currently, neither
# are lv2 and ROC.
#
# The RTKit module is deprecated in favor of the newer RT module.
# It still exists for legacy setups that still include it in
# their PipeWire configuration files.
EXTRA_OEMESON += " \
    -Devl=disabled \
    -Dtests=disabled \
    -Dudevrulesdir=${nonarch_base_libdir}/udev/rules.d/ \
    -Dsystemd-system-unit-dir=${systemd_system_unitdir} \
    -Dsystemd-user-unit-dir=${systemd_user_unitdir} \
    -Dvulkan=disabled \
    -Dman=disabled \
    -Dsession-managers='[]' \
    -Dlv2=disabled \
    -Droc=disabled \
    -Dbluez5-codec-aptx=disabled \
    -Dbluez5-codec-ldac=disabled \
    -Dlegacy-rtkit=false \
"

PACKAGECONFIG ??= "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'zeroconf', 'avahi', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', 'bluez', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd systemd-system-service', '', d)} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'alsa', d)} \
    gstreamer jack libusb pw-cat raop sndfile v4l2 \
"

# "jack" and "pipewire-jack" packageconfigs cannot be both enabled,
# since "jack" imports libjack, and "pipewire-jack" generates
# libjack.so* files, thus colliding with the libpack package. This
# is why these two are marked in their respective packageconfigs
# as being in conflict.
PACKAGECONFIG[alsa] = "-Dalsa=enabled,-Dalsa=disabled,alsa-lib udev"
PACKAGECONFIG[avahi] = "-Davahi=enabled,-Davahi=disabled,avahi"
PACKAGECONFIG[bluez] = "-Dbluez5=enabled,-Dbluez5=disabled,bluez5 sbc"
PACKAGECONFIG[bluez-aac] = "-Dbluez5-codec-aac=enabled,-Dbluez5-codec-aac=disabled,fdk-aac"
PACKAGECONFIG[docs] = "-Ddocs=enabled,-Ddocs=disabled,doxygen-native graphviz-native"
PACKAGECONFIG[ffmpeg] = "-Dffmpeg=enabled,-Dffmpeg=disabled,ffmpeg"
PACKAGECONFIG[gstreamer] = "-Dgstreamer=enabled,-Dgstreamer=disabled,glib-2.0 gstreamer1.0 gstreamer1.0-plugins-base"
PACKAGECONFIG[jack] = "-Djack=enabled,-Djack=disabled,jack,,,pipewire-jack"
PACKAGECONFIG[libcamera] = "-Dlibcamera=enabled,-Dlibcamera=disabled,libcamera"
PACKAGECONFIG[libcanberra] = "-Dlibcanberra=enabled,-Dlibcanberra=disabled,libcanberra"
PACKAGECONFIG[libusb] = "-Dlibusb=enabled,-Dlibusb=disabled,libusb"
PACKAGECONFIG[pipewire-alsa] = "-Dpipewire-alsa=enabled,-Dpipewire-alsa=disabled,alsa-lib"
PACKAGECONFIG[pipewire-jack] = "-Dpipewire-jack=enabled -Dlibjack-path=${libdir}/${PW_MODULE_SUBDIR}/jack,-Dpipewire-jack=disabled,jack,,,jack"
PACKAGECONFIG[pw-cat] = "-Dpw-cat=enabled,-Dpw-cat=disabled"
PACKAGECONFIG[raop] = "-Draop=enabled,-Draop=disabled,openssl"
PACKAGECONFIG[sdl2] = "-Dsdl2=enabled,-Dsdl2=disabled,virtual/libsdl2"
PACKAGECONFIG[sndfile] = "-Dsndfile=enabled,-Dsndfile=disabled,libsndfile1"
PACKAGECONFIG[systemd] = "-Dsystemd=enabled,-Dsystemd=disabled,systemd"
PACKAGECONFIG[systemd-system-service] = "-Dsystemd-system-service=enabled,-Dsystemd-system-service=disabled,systemd"
# "systemd-user-service" packageconfig will only install service
# files to rootfs but not enable them as systemd.bbclass
# currently lacks the feature of enabling user services.
PACKAGECONFIG[systemd-user-service] = "-Dsystemd-user-service=enabled,-Dsystemd-user-service=disabled,systemd"
# pw-cat needs sndfile packageconfig to be enabled
PACKAGECONFIG[v4l2] = "-Dv4l2=enabled,-Dv4l2=disabled,udev"
PACKAGECONFIG[webrtc-echo-cancelling] = "-Decho-cancel-webrtc=enabled,-Decho-cancel-webrtc=disabled,webrtc-audio-processing"

PACKAGESPLITFUNCS:prepend = " split_dynamic_packages "
PACKAGESPLITFUNCS:append = " set_dynamic_metapkg_rdepends "

SPA_SUBDIR = "spa-0.2"
PW_MODULE_SUBDIR = "pipewire-0.3"

remove_unused_installed_files() {
    # jack.conf is used by pipewire-jack (not the JACK SPA plugin).
    # Remove it if pipewire-jack is not built to avoid creating the
    # pipewire-jack package.
    if ${@bb.utils.contains('PACKAGECONFIG', 'pipewire-jack', 'false', 'true', d)}; then
        rm -f "${D}${datadir}/pipewire/jack.conf"
    fi

    # minimal.conf is an example of how to minimally configure the
    # daemon and is not meant to be used for production.
    rm -f "${D}${datadir}/pipewire/minimal.conf"
}

do_install[postfuncs] += "remove_unused_installed_files"

python split_dynamic_packages () {
    # Create packages for each SPA plugin. These plugins are located
    # in individual subdirectories, so a recursive search is needed.
    spa_libdir = d.expand('${libdir}/${SPA_SUBDIR}')
    do_split_packages(d, spa_libdir, r'^libspa-(.*)\.so$', d.expand('${PN}-spa-plugins-%s'), 'PipeWire SPA plugin for %s', extra_depends='', recursive=True)

    # Create packages for each PipeWire module.
    pw_module_libdir = d.expand('${libdir}/${PW_MODULE_SUBDIR}')
    do_split_packages(d, pw_module_libdir, r'^libpipewire-module-(.*)\.so$', d.expand('${PN}-modules-%s'), 'PipeWire %s module', extra_depends='', recursive=False)
}

python set_dynamic_metapkg_rdepends () {
    import os
    import oe.utils

    # Go through all generated SPA plugin and PipeWire module packages
    # (excluding the main package and the -meta package itself) and
    # add them to the -meta package as RDEPENDS.

    base_pn = d.getVar('PN')

    spa_pn = base_pn + '-spa-plugins'
    spa_metapkg =  spa_pn + '-meta'

    pw_module_pn = base_pn + '-modules'
    pw_module_metapkg =  pw_module_pn + '-meta'

    d.setVar('ALLOW_EMPTY:' + spa_metapkg, "1")
    d.setVar('FILES:' + spa_metapkg, "")

    d.setVar('ALLOW_EMPTY:' + pw_module_metapkg, "1")
    d.setVar('FILES:' + pw_module_metapkg, "")

    blacklist = [ spa_pn, spa_metapkg, pw_module_pn, pw_module_metapkg ]
    spa_metapkg_rdepends = []
    pw_module_metapkg_rdepends = []
    pkgdest = d.getVar('PKGDEST')

    for pkg in oe.utils.packages_filter_out_system(d):
        if pkg in blacklist:
            continue

        is_spa_pkg = pkg.startswith(spa_pn)
        is_pw_module_pkg = pkg.startswith(pw_module_pn)
        if not is_spa_pkg and not is_pw_module_pkg:
            continue

        if pkg in spa_metapkg_rdepends or pkg in pw_module_metapkg_rdepends:
            continue

        # See if the package is empty by looking at the contents of its
        # PKGDEST subdirectory. If this subdirectory is empty, then then
        # package is empty as well. Empty packages do not get added to
        # the meta package's RDEPENDS.
        pkgdir = os.path.join(pkgdest, pkg)
        if os.path.exists(pkgdir):
            dir_contents = os.listdir(pkgdir) or []
        else:
            dir_contents = []
        is_empty = len(dir_contents) == 0
        if not is_empty:
            if is_spa_pkg:
                spa_metapkg_rdepends.append(pkg)
            if is_pw_module_pkg:
                pw_module_metapkg_rdepends.append(pkg)

    d.setVar('RDEPENDS:' + spa_metapkg, ' '.join(spa_metapkg_rdepends))
    d.setVar('DESCRIPTION:' + spa_metapkg, spa_pn + ' meta package')

    d.setVar('RDEPENDS:' + pw_module_metapkg, ' '.join(pw_module_metapkg_rdepends))
    d.setVar('DESCRIPTION:' + pw_module_metapkg, pw_module_pn + ' meta package')
}

PACKAGES =+ "\
    libpipewire \
    ${PN}-tools \
    ${PN}-pulse \
    ${PN}-alsa \
    ${PN}-jack \
    ${PN}-spa-plugins \
    ${PN}-spa-plugins-meta \
    ${PN}-spa-tools \
    ${PN}-modules \
    ${PN}-modules-meta \
    ${PN}-alsa-card-profile \
    ${PN}-v4l2 \
    gstreamer1.0-pipewire \
"

PACKAGES_DYNAMIC = "^${PN}-spa-plugins.* ^${PN}-modules.*"

SYSTEMD_SERVICE:${PN} = "${@bb.utils.contains('PACKAGECONFIG', 'systemd-system-service', 'pipewire.service', '', d)}"
CONFFILES:${PN} += "${datadir}/pipewire/pipewire.conf"
FILES:${PN} = " \
    ${datadir}/pipewire/pipewire.conf \
    ${systemd_system_unitdir}/pipewire.* \
    ${systemd_user_unitdir}/pipewire.* \
    ${bindir}/pipewire \
"

FILES:${PN}-dev += " \
    ${libdir}/${PW_MODULE_SUBDIR}/jack/libjack*.so \
"

CONFFILES:libpipewire += "${datadir}/pipewire/client.conf"
FILES:libpipewire = " \
    ${datadir}/pipewire/client.conf \
    ${libdir}/libpipewire-*.so.* \
"
# Add the bare minimum modules and plugins required to be able
# to use libpipewire. Without these, it is essentially unusable.
RDEPENDS:libpipewire += " \
    ${PN}-modules-client-node \
    ${PN}-modules-protocol-native \
    ${PN}-spa-plugins-support \
"

FILES:${PN}-tools = " \
    ${bindir}/pw-cat \
    ${bindir}/pw-cli \
    ${bindir}/pw-dot \
    ${bindir}/pw-dsdplay \
    ${bindir}/pw-dump \
    ${bindir}/pw-link \
    ${bindir}/pw-loopback \
    ${bindir}/pw-metadata \
    ${bindir}/pw-mididump \
    ${bindir}/pw-midiplay \
    ${bindir}/pw-midirecord \
    ${bindir}/pw-mon \
    ${bindir}/pw-play \
    ${bindir}/pw-profiler \
    ${bindir}/pw-record \
    ${bindir}/pw-reserve \
    ${bindir}/pw-top \
"

# This is a shim daemon that is intended to be used as a
# drop-in PulseAudio replacement, providing a pulseaudio-compatible
# socket that can be used by applications that use libpulse.
CONFFILES:${PN}-pulse += "${datadir}/pipewire/pipewire-pulse.conf"
FILES:${PN}-pulse = " \
    ${datadir}/pipewire/pipewire-pulse.conf \
    ${systemd_system_unitdir}/pipewire-pulse.* \
    ${systemd_user_unitdir}/pipewire-pulse.* \
    ${bindir}/pipewire-pulse \
"
RDEPENDS:${PN}-pulse += " \
    ${PN}-modules-protocol-pulse \
"

# ALSA plugin to redirect audio to pipewire.
FILES:${PN}-alsa = "\
    ${libdir}/alsa-lib/* \
    ${datadir}/alsa/alsa.conf.d/* \
"

# JACK drop-in libraries to redirect audio to pipewire.
CONFFILES:${PN}-jack = "${datadir}/pipewire/jack.conf"
FILES:${PN}-jack = "\
    ${bindir}/pw-jack \
    ${datadir}/pipewire/jack.conf \
    ${libdir}/${PW_MODULE_SUBDIR}/jack/libjack*.so.* \
"

# Dynamic SPA plugin packages (see set_dynamic_metapkg_rdepends).
FILES:${PN}-spa-plugins = ""
RRECOMMENDS:${PN}-spa-plugins += "${PN}-spa-plugins-meta"

FILES:${PN}-spa-plugins-bluez5 += " \
    ${datadir}/${SPA_SUBDIR}/bluez5/* \
"

FILES:${PN}-spa-tools = " \
    ${bindir}/spa-* \
"

# Dynamic PipeWire module packages (see set_dynamic_metapkg_rdepends).
FILES:${PN}-modules = ""
RRECOMMENDS:${PN}-modules += "${PN}-modules-meta"

CONFFILES:${PN}-modules-rt = "${datadir}/pipewire/client-rt.conf"
FILES:${PN}-modules-rt += " \
    ${datadir}/pipewire/client-rt.conf \
    "

CONFFILES:${PN}-modules-filter-chain = "${datadir}/pipewire/filter-chain/*"
FILES:${PN}-modules-filter-chain += " \
    ${datadir}/pipewire/filter-chain/* \
"

FILES:${PN}-alsa-card-profile = " \
    ${datadir}/alsa-card-profile/* \
    ${nonarch_base_libdir}/udev/rules.d/90-pipewire-alsa.rules \
"

# V4L2 interface emulator for sending/receiving data between PipeWire and V4L2 applications.
FILES:${PN}-v4l2 += " \
    ${bindir}/pw-v4l2 \
    ${libdir}/${PW_MODULE_SUBDIR}/v4l2/libpw-v4l2.so \
"

FILES:gstreamer1.0-pipewire = " \
    ${libdir}/gstreamer-1.0/* \
"
