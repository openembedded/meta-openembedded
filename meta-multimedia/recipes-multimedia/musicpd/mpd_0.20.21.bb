SUMMARY = "Music Player Daemon"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
HOMEPAGE ="http://www.musicpd.org"

inherit autotools useradd systemd pkgconfig

DEPENDS += " \
    curl \
    sqlite \
    ${@bb.utils.filter('DISTRO_FEATURES', 'pulseaudio', d)} \
    tcp-wrappers \
    yajl \
    boost \
    icu \
    dbus \
    expat \
"

SRC_URI = " \
    http://www.musicpd.org/download/${BPN}/0.20/${BP}.tar.xz \
    file://mpd.conf.in \
"
SRC_URI[md5sum] = "d93c3c86f5e0fc56cc2e1020f80f8b66"
SRC_URI[sha256sum] = "8322764dc265c20f05c8c8fdfdd578b0722e74626bef56fcd8eebfb01acc58dc"

EXTRA_OECONF = "enable_bzip2=yes"
EXTRA_OECONF += "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '--with-systemdsystemunitdir=${systemd_unitdir}/system/', '--without-systemdsystemunitdir', d)}"

PACKAGECONFIG ??= "aac alsa ao bzip2 ffmpeg flac fluidsynth iso9660 jack libsamplerate mms mpg123 modplug sndfile upnp openal opus vorbis wavpack zlib"

PACKAGECONFIG[aac] = "--enable-aac,--disable-aac,faad2"
PACKAGECONFIG[alsa] = "--enable-alsa,--disable-alsa,alsa-lib"
PACKAGECONFIG[ao] = "--enable-ao,--disable-ao,libao"
PACKAGECONFIG[audiofile] = "--enable-audiofile,--disable-audiofile,audiofile"
PACKAGECONFIG[bzip2] = "--enable-bzip2,--disable-bzip2,bzip2"
PACKAGECONFIG[cdioparanoia] = "--enable-cdio-paranoia,--disable-cdio-paranoia,libcdio-paranoia"
PACKAGECONFIG[ffmpeg] = "--enable-ffmpeg,--disable-ffmpeg,ffmpeg"
PACKAGECONFIG[flac] = "--enable-flac,--disable-flac,flac"
PACKAGECONFIG[fluidsynth] = "--enable-fluidsynth,--disable-fluidsynth,fluidsynth"
PACKAGECONFIG[id3tag] = "--enable-id3,--disable-id3,libid3tag"
PACKAGECONFIG[iso9660] = "--enable-iso9660,--disable-iso9660,libcdio"
PACKAGECONFIG[jack] = "--enable-jack,--disable-jack,jack"
PACKAGECONFIG[lame] = "--enable-lame-encoder,--disable-lame-encoder,lame"
PACKAGECONFIG[libsamplerate] = "--enable-lsr,--disable-lsr,libsamplerate0"
PACKAGECONFIG[mad] = "--enable-mad,--disable-mad,libmad"
PACKAGECONFIG[mms] = "--enable-mms,--disable-mms,libmms"
PACKAGECONFIG[mpg123] = "--enable-mpg123,--disable-mpg123,mpg123"
PACKAGECONFIG[modplug] = "--enable-modplug,--disable-modplug,libmodplug"
PACKAGECONFIG[smb] = "--enable-smbclient,--disable-smbclient,samba"
PACKAGECONFIG[sndfile] = "--enable-sndfile,--disable-sndfile,libsndfile1"
PACKAGECONFIG[upnp] = "--enable-upnp,--disable-upnp,libupnp"
PACKAGECONFIG[openal] = "--enable-openal,--disable-openal,openal-soft"
PACKAGECONFIG[opus] = "--enable-opus,--disable-opus,libopus libogg"
PACKAGECONFIG[vorbis] = "--enable-vorbis,--disable-vorbis,libvorbis libogg"
PACKAGECONFIG[wavpack] = "--enable-wavpack,--disable-wavpack,wavpack"
PACKAGECONFIG[zlib] = "--enable-zlib,--disable-zlib,zlib"

do_configure_prepend() {
    sed -i -e 's|libsystemd-daemon|libsystemd|' ${S}/configure.ac
}

do_install_append() {
    install -d ${D}/${localstatedir}/lib/mpd/music
    chmod 775 ${D}/${localstatedir}/lib/mpd/music
    install -d ${D}/${localstatedir}/lib/mpd/playlists
    chown -R mpd ${D}/${localstatedir}/lib/mpd
    chown mpd:mpd ${D}/${localstatedir}/lib/mpd/music

    install -d ${D}/${sysconfdir}
    install -m 644 ${WORKDIR}/mpd.conf.in ${D}/${sysconfdir}/mpd.conf
    sed -i \
        -e 's|%music_directory%|${localstatedir}/lib/mpd/music|' \
        -e 's|%playlist_directory%|${localstatedir}/lib/mpd/playlists|' \
        -e 's|%db_file%|${localstatedir}/lib/mpd/mpd.db|' \
        -e 's|%log_file%|${localstatedir}/log/mpd.log|' \
        -e 's|%state_file%|${localstatedir}/lib/mpd/state|' \
        ${D}/${sysconfdir}/mpd.conf

    if [ -e ${D}/${systemd_unitdir}/system/mpd.service ] ; then
        sed -i \
            's|^ExecStart=.*|ExecStart=${bindir}/mpd --no-daemon|' \
            ${D}/${systemd_unitdir}/system/mpd.service
    fi
}

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "mpd.socket"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = " \
    --system --no-create-home \
    --home ${localstatedir}/lib/mpd \
    --groups audio \
    --user-group mpd"
