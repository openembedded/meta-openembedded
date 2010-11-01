require ttf.inc

DESCRIPTION = "Droid fonts - TTF Edition"
HOMEPAGE = "http://www.droidfonts.com/"
LICENSE = "Apache-2.0"
PR = "r0"
# git magic below - SRCREV is an ID of /data/fonts subtree
# (so we don't need to fetch full platform/frameworks/base.git)
SRCREV = "31f255e44e618ff3b923f332e8fcb83403fd39f5"
PV = "0.0+${PR}+gitr${SRCREV}"

SRC_URI = "git://android.git.kernel.org/platform/frameworks/base.git;protocol=git;branch=master"

S = "${WORKDIR}/git"

do_install_prepend() {
	rm ${S}/Ahem.ttf # we're not packaging it
}

PACKAGES = "${PN}-dbg ttf-droid-sans ttf-droid-sans-mono \
            ttf-droid-sans-fallback ttf-droid-sans-japanese ttf-droid-serif"
RRECOMMENDS_${PN}-dbg = ""

FILES_ttf-droid-sans = "${datadir}/fonts/truetype/DroidSans.ttf ${datadir}/fonts/truetype/DroidSans-Bold.ttf"
FILES_ttf-droid-sans-mono = "${datadir}/fonts/truetype/DroidSansMono.ttf"
FILES_ttf-droid-sans-fallback = "${datadir}/fonts/truetype/DroidSansFallback.ttf"
FILES_ttf-droid-sans-japanese = "${datadir}/fonts/truetype/DroidSansJapanese.ttf"
FILES_ttf-droid-serif = "${datadir}/fonts/truetype/DroidSerif*.ttf"
