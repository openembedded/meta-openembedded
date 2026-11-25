require ttf.inc

SUMMARY = "Liberation(tm) Fonts"
DESCRIPTION = "The Liberation(tm) Fonts is a font family originally \
created by Ascender(c) which aims at metric compatibility with \
Arial, Times New Roman, Courier New."

HOMEPAGE = "https://github.com/liberationfonts/liberation-sans-narrow"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

# 1.07.6 is attached to the release notes, not uploaded as a release asset
SRC_URI = "https://github.com/liberationfonts/liberation-sans-narrow/files/2579431/liberation-narrow-fonts-ttf-${PV}.tar.gz \
           file://30-0-liberation-sans-narrow.conf \
"

SRC_URI[sha256sum] = "8879d89b5ff7b506c9fc28efc31a5c0b954bbe9333e66e5283d27d20a8519ea3"

UPSTREAM_CHECK_URI = "https://github.com/liberationfonts/liberation-sans-narrow/releases"
UPSTREAM_CHECK_REGEX = "releases/tag/(?P<pver>\d+(\.\d+)+)"

S = "${UNPACKDIR}/liberation-narrow-fonts-ttf-${PV}"

do_install:append () {
    install -d ${D}${datadir}/fonts/TTF/
    install -d ${D}${sysconfdir}/fonts/conf.d/
    install -m 0644 LiberationSansNarrow*.ttf ${D}${datadir}/fonts/TTF/
    install -D -m 0644 ${UNPACKDIR}/30-0-liberation-sans-narrow.conf ${D}${sysconfdir}/conf.avail/30-${PN}-sans.conf
    install -D -m 0644 ${S}/License.txt ${D}${datadir}/licenses/${PN}/LICENSE
    # normal liberation fonts come from ttf-liberation recipe so delete here
    rm -rf ${D}${datadir}/fonts/truetype
}

PACKAGES = "${PN}"
FONT_PACKAGES = "${PN}"

FILES:${PN} = "${datadir}/fonts ${sysconfdir} ${datadir}/licenses"
