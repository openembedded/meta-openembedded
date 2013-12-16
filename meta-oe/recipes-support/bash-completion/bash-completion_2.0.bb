DESCRIPTION = "Programmable Completion for Bash 4"
HOMEPAGE = "http://bash-completion.alioth.debian.org/"
BUGTRACKER = "https://alioth.debian.org/projects/bash-completion/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SECTION = "console/utils"

SRC_URI="http://bash-completion.alioth.debian.org/files/${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "0d903f398be8c8f24bc5ffa6f86127f8"
SRC_URI[sha256sum] = "e5a490a4301dfb228361bdca2ffca597958e47dd6056005ef9393a5852af5804"

inherit allarch autotools

do_install_append() {
	install -d ${D}${sysconfdir}/bash_completion.d/
	echo '. ${datadir}/${BPN}/bash_completion' >${D}${sysconfdir}/bash_completion
}

RDEPENDS_${PN} = "bash"

# Some recipes are providing ${PN}-bash-completion packages
PACKAGES =+ "${PN}-extra"
FILES_${PN}-extra = "${datadir}/${BPN}/completions/ \
    ${datadir}/${BPN}/helpers/"
