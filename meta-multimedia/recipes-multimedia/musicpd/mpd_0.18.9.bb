SUMMARY = "Music Player Daemon"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
HOMEPAGE ="http://www.musicpd.org"

DEPENDS = "alsa-lib libsamplerate0 libsndfile1 libvorbis libogg faad2 ffmpeg curl sqlite bzip2 pulseaudio"

SRC_URI = " \
    http://www.musicpd.org/download/${PN}/stable/${P}.tar.xz \
    file://mpd.conf.in \
"

SRC_URI[md5sum] = "0e5156a831d4302d2032998bcfd5c36f"
SRC_URI[sha256sum] = "0d4d3e6977cb607419abbb14c63d1e2b982acb84cd2be5346f19de5f699a68de"

inherit autotools useradd systemd

EXTRA_OECONF = "enable_bzip2=yes"
EXTRA_OECONF += "${@base_contains('DISTRO_FEATURES', 'systemd', '--with-systemdsystemunitdir=${systemd_unitdir}/system/', '--without-systemdsystemunitdir', d)}"

PACKAGECONFIG[mad] = "--enable-mad,--disable-mad,libmad"
PACKAGECONFIG[id3tag] = "--enable-id3,--disable-id3,libid3tag"
PACKAGECONFIG[lame] = "--enable-lame-encoder,--disable-lame-encoder,lame"

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
SYSTEMD_SERVICE_${PN} = "mpd.service"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = " \
    --system --no-create-home \
    --home ${localstatedir}/lib/mpd \
    --groups audio \
    --user-group mpd"
