SUMMARY = "NVMe management command line interface"
HOMEPAGE = "https://github.com/linux-nvme/nvme-cli"
SECTION = "console/utils"
LICENSE = "GPL-2.0-only AND GPL-2.0-or-later AND LGPL-2.1-or-later AND CC0-1.0 AND MIT AND LicenseRef-Gary-S-Brown-CRC32"
LICENSE:libnvme3 = "LGPL-2.1-or-later AND CC0-1.0 AND MIT AND LicenseRef-Gary-S-Brown-CRC32"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8264535c0c4e9c6c335635c4026a8022 \
                    file://LICENSES/LGPL-2.1-or-later.txt;md5=310c7c93cf5181c6b0cc8229a1f3c6f6 \
                    file://LICENSES/CC0-1.0.txt;md5=65d3616852dbf7b1a6d4b53b00626032 \
                    file://LICENSES/MIT.txt;md5=3912d958d00bac4a6b550f75d7c806bb \
                    file://LICENSES/LicenseRef-Gary-S-Brown-CRC32.txt;md5=c923b33bfa4944fc9c3d644b16424ff6"
NO_GENERIC_LICENSE[Gary-S-Brown-CRC32] = "LICENSES/LicenseRef-Gary-S-Brown-CRC32.txt"

DEPENDS = "json-c"
SRCREV = "c19af560d1869636104de5ab4e41420cbfdef395"

SRC_URI = "git://github.com/linux-nvme/nvme-cli.git;branch=master;protocol=https;tag=v${PV}"

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>\d+(\.\d+)+)$"

inherit bash-completion meson pkgconfig systemd

PACKAGECONFIG ??= "keyutils openssl"
PACKAGECONFIG[discoverd] = "-Dnvme-discoverd=enabled,-Dnvme-discoverd=disabled,systemd"
PACKAGECONFIG[keyutils] = "-Dkeyutils=enabled,-Dkeyutils=disabled,keyutils"
PACKAGECONFIG[libarchive] = "-Dlibarchive=enabled,-Dlibarchive=disabled,libarchive,libarchive"
PACKAGECONFIG[libdbus] = "-Dlibdbus=enabled,-Dlibdbus=disabled,dbus"
PACKAGECONFIG[libkmod] = "-Dlibkmod=enabled,-Dlibkmod=disabled,kmod,libkmod"
PACKAGECONFIG[liburing] = "-Dliburing=enabled,-Dliburing=disabled,liburing"
PACKAGECONFIG[openssl] = "-Dopenssl=enabled,-Dopenssl=disabled,openssl"

EXTRA_OEMESON += "-Dsystemddir=${systemd_unitdir}/system \
                  -Djson-c=enabled \
                  -Dpython=disabled \
                  -Dnvme-keysd=disabled \
                  -Dtests=false \
                  -Dexamples=false \
"

pkg_postinst_ontarget:${PN}-user () {
    ${sbindir}/nvme gen-hostnqn > ${sysconfdir}/nvme/hostnqn
    ${bindir}/uuidgen > ${sysconfdir}/nvme/hostid
}

PACKAGES =+ "libnvme3 ${PN}-dracut ${PN}-zsh-completion ${PN}-user"

FILES:libnvme3 = "${libdir}/libnvme3${SOLIBS}"
FILES:${PN} += "${systemd_system_unitdir} ${nonarch_libdir}/NetworkManager ${nonarch_libdir}/nvme"
FILES:${PN}-dbg += "${libdir}/${BPN}/*/plugins/.debug"
FILES:${PN}-dracut = "${nonarch_libdir}/dracut/dracut.conf.d"
FILES:${PN}-zsh-completion = "${datadir}/zsh/site-functions"
ALLOW_EMPTY:${PN}-user = "1"

RDEPENDS:${PN}-user = "util-linux-uuidgen"
