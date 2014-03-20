DESCRIPTION = "Programmable Completion for Bash 4"
HOMEPAGE = "http://bash-completion.alioth.debian.org/"
BUGTRACKER = "https://alioth.debian.org/projects/bash-completion/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

PR = "r1"

SECTION = "console/utils"

SRC_URI="http://bash-completion.alioth.debian.org/files/${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "0d903f398be8c8f24bc5ffa6f86127f8"
SRC_URI[sha256sum] = "e5a490a4301dfb228361bdca2ffca597958e47dd6056005ef9393a5852af5804"

PARALLEL_MAKE = ""

inherit allarch autotools

do_install_append() {
	install -d ${D}${sysconfdir}/bash_completion.d/
	echo '. ${datadir}/${BPN}/bash_completion' >${D}${sysconfdir}/bash_completion

	# Delete files already provided by util-linux
	local i
	for i in cal dmesg hwclock ionice look renice rtcwake; do
		rm ${D}${datadir}/${BPN}/completions/$i
	done
}

RDEPENDS_${PN} = "bash"

# Some recipes are providing ${PN}-bash-completion packages
PACKAGES =+ "${PN}-extra"
FILES_${PN}-extra = "${datadir}/${BPN}/completions/ \
    ${datadir}/${BPN}/helpers/"
