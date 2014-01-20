require ${BPN}.inc

SRCNAME = "enlightenment"

S = "${WORKDIR}/${SRCNAME}-${PV}"

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
    file://enlightenment_start.oe \
    file://applications.menu \
"

SRC_URI[md5sum] = "5aefb55df0b1a0a0ae0cf9d0610cf7c7"
SRC_URI[sha256sum] = "25d4967277cf27d4efb474a9b8eceb1200aa813a51c248b61cc23f69291bff0f"

do_install_append() {
    install -d ${D}/${datadir}/applications/
    install -m 644 ${S}/src/modules/fileman/module.desktop ${D}/${datadir}/applications/efm.desktop
    sed "s#Type=Link#Type=Application#g" -i ${D}/${datadir}/applications/efm.desktop
    echo "Exec=enlightenment_remote -efm-open-dir" >> ${D}/${datadir}/applications/efm.desktop
    echo "Terminal=false" >> ${D}/${datadir}/applications/efm.desktop
    echo "Categories=Application;" >> ${D}/${datadir}/applications/efm.desktop
    echo "StartupNotify=true" >> ${D}/${datadir}/applications/efm.desktop
    install -d ${D}/${datadir}/icons/
    install -m 644 ${S}/data/themes/img/O/icon_icon_theme.png ${D}/${datadir}/icons/e-module-fileman.png
}
