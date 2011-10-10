DESCRIPTION = "Meet Cloud9, development-as-a-service for Javascripters and other developers"
HOMEPAGE = "http://c9.io"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4784c3bcff601fd8f9515f52a11e7018"

PR = "r3"

SRC_URI = "git://github.com/jadonk/cloud9.git;protocol=git \
           file://cloud9.service \
          "

SRCREV = "08bae1d1cc2ba9f7f883a25afd07f0339a82fa8b"
S = "${WORKDIR}/git"

# Most of cloud9 is in git submodules that still need to be fetched.
do_configure_prepend () {
 git submodule update --init --recursive
}

# There's nothing left to be compiled at this time.
# node-o3-xml is the only compiled code module in here.
# The repository has binaries for:
#  Linux ARM armv7
#  Linux x86 32/64-bit
#  Sun Solaris 32-bit
#  Mac x86 32/64-bit
#  Windows x86 32-bit
do_compile () {
:
}

do_install () {
 install -m 0755 -d ${D}/usr/share/cloud9 ${D}${bindir} ${D}/var/lib/cloud9
 rsync -r --exclude=".*" ${S}/* ${D}/usr/share/cloud9

 for i in cygwin darwin linux32 linux64 sunos ; do
   rm -f ${D}/usr/share/cloud9/support/node-builds-v4/*$i*
   rm -f ${D}/usr/share/cloud9/support/jsdav/support/node-o3-xml-v4/lib/o3-xml/*$i*
   rm -f ${D}//usr/share/cloud9/support/gnu-builds/*$i*
 done

 touch ${D}${bindir}/cloud9
 echo "#!/bin/sh" > ${D}${bindir}/cloud9
 echo "node /usr/share/cloud9/bin/cloud9.js -l 0.0.0.0 -w /var/lib/cloud9 -p 3000" >> ${D}${bindir}/cloud9
 chmod 0755 ${D}${bindir}/cloud9

 install -d ${D}${base_libdir}/systemd/system
 install -m 0644 ${WORKDIR}/*.service ${base_libdir}/systemd/system/
}


FILES_${PN}-dbg += "/usr/share/cloud9/support/jsdav/support/node-o3-xml-v4/lib/o3-xml/.debug"

FILES_${PN} += "${base_libdir}/systemd/system"
RDEPENDS_${PN} = "nodejs gzip"

pkg_postinst_${PN} () {
    # can't do this offline
    if [ "x$D" != "x" ]; then
        exit 1
    fi

    if [ -e ${base_bindir}/systemctl ] ; then
        systemctl enable cloud9.service
    fi
}
