DESCRIPTION = "Filesystem and Disk Benchmarking Tool"
HOMEPAGE = "http://www.iozone.org/"
AUTHOR = "Don Capps <don.capps2@verizon.net>, William D. Norcott <wnorcott@us.oracle.com>"
SECTION = "console/tests"
LICENSE = "iozone3"
LIC_FILES_CHKSUM = "file://iozone.c;beginline=237;endline=241;md5=ab42a6185fd0443978871f11a007ac0b"


SRC_URI = "http://www.iozone.org/src/current/iozone3_263.tar \
	   file://copyright.txt \
           file://fileop-arm.patch \
           "
SRC_URI[md5sum] = "44fd88df361ec4508e10c8d6615245fa"
SRC_URI[sha256sum] = "920fde1a3843539570e2df4aa611e74df102e52d363c5973d5a9d15bdf976461"

S = "${WORKDIR}/${PN}_${PV}/src/current/"

EXTRA_OEMAKE_powerpc = "linux-powerpc CC='${CC}'"
EXTRA_OEMAKE_powerpc64 = "linux-powerpc64 CC='${CC}'"
EXTRA_OEMAKE_arm = "linux-arm CC='${CC}'"
EXTRA_OEMAKE = "linux CC='${CC}'"

TARGET_CC_ARCH += "${LDFLAGS}"

do_install() {
       install -d ${D}${bindir} \
                  ${D}${mandir}/man1 \
		  ${D}${datadir}/doc/${PN}/examples

       install -m 0755 ${S}/iozone ${D}${bindir}
       install -m 0755 ${S}/fileop ${D}${bindir}
       install -m 0644 ${WORKDIR}/${PN}_${PV}/docs/iozone.1 ${D}${mandir}/man1/
       install -m 0644 ${WORKDIR}/copyright.txt ${D}${datadir}/doc/${PN}/

       install -m 0644 ${S}/*.dem ${D}${datadir}/doc/${PN}/examples
       install -m 0644 ${S}/client_list ${D}${datadir}/doc/${PN}/examples
       install -m 0644 ${S}/Gnuplot.txt ${D}${datadir}/doc/${PN}/examples

       install -m 0755 ${S}/Generate_Graphs ${D}${datadir}/doc/${PN}/examples
       install -m 0755 ${S}/gengnuplot.sh ${D}${datadir}/doc/${PN}/examples
       install -m 0755 ${S}/report.pl ${D}${datadir}/doc/${PN}/examples

       install -m 0644 ${WORKDIR}/${PN}_${PV}/docs/Iozone_ps.gz ${D}${datadir}/doc/${PN}/
       install -m 0644 ${WORKDIR}/${PN}_${PV}/docs/IOzone_msword_98.pdf ${D}${datadir}/doc/${PN}/
       install -m 0644 ${WORKDIR}/${PN}_${PV}/docs/Run_rules.doc ${D}${datadir}/doc/${PN}/
}

FILES_${PN} += "${datadir}/doc/${PN}/copyright.txt"

# LICENSE:
#
#  Copyright 1991, 1992, 1994, 1998, 1999, 2002   William D. Norcott
#
#  License to freely use and distribute this software is hereby granted
#  by the author, subject to the condition that this copyright notice
#  remains intact.  The author retains the exclusive right to publish
#  derivative works based on this work, including, but not limited to
#  revised versions of this work.
#

#
# Below is author reply to question about distributing iozone3 in
# OpenEmbedded:
#
# ========================================================================
#
# Marcin,
#
#         Re-distribution is permitted as long as the copyright is
#     maintained and the source code is not changed. I do not
#     see a problem with your mods to enable fileop for Linux-arm,
#     as these mods have been returned to the Iozone folks,
#     and they have been accepted for inclusion in the next
#     release :-)
#
# Thank you for your contribution,
# Don Capps
#
# ----- Original Message -----
# From: "Marcin Juszkiewicz" <firma@hrw.one.pl>
# To: "Don Capps" <don.capps2@verizon.net>; "William D. Norcott"
# <wnorcott@us.oracle.com>
# Sent: Sunday, October 29, 2006 4:55 PM
# Subject: iozone3 263 patch for arm and License question
#
#
# > Morning
# >
# > I want to include iozone3 in OpenEmbedded [1] metadata to give it for
# > other developers. Currently OE is used to build few distributions for
# > misc platforms: ARM, SH3, SH4, x86, PowerPC and different types of
# > machines (PDA, settopbox, devboards, desktops, thin clients, routers).
# >
# > According to your distribution of derivations is forbidden. Packaging
# > iozone3 in OpenEmbedded will not involve any source code changes. But
# > when I was building it for ARM I discovered that fileop binary was not
# > built - so I created patch for it (attached). Not yet tested it on target
# > device.
# >
# > Thus, I seek your written permission via e-mail to distribute a package of
# > the unmodified source code and also a package of the pre-compiled binary.
# > Your copyright statement will be included in the package.
# >
# >
# > 1. http://www.openembedded.org/
# >
# > Regards
# > --
# > JID: hrw-jabber.org
# > OpenEmbedded developer/consultant

