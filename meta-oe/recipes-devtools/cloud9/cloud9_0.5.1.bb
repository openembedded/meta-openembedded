DESCRIPTION = "Meet Cloud9, development-as-a-service for Javascripters and other developers"
HOMEPAGE = "http://c9.io"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4784c3bcff601fd8f9515f52a11e7018"

PR = "r4"

SRC_URI = "git://github.com/ajaxorg/cloud9.git;name=cloud9ide \
           git://github.com/ajaxorg/connect.git;destsuffix=git/support/connect;name=connect \
           git://github.com/ajaxorg/async.js.git;destsuffix=git/support/asyncjs;name=asyncjs \
           git://github.com/ajaxorg/jsDAV.git;destsuffix=git/support/jsdav;name=jsdav \
           git://github.com/ajaxorg/lib-v8debug.git;destsuffix=git/support/lib-v8debug;name=lib-v8debug \
           git://github.com/ajaxorg/ace.git;destsuffix=git/support/ace;name=ace \
           git://github.com/ajaxorg/apf.git;destsuffix=git/support/apf;name=apf \
           git://github.com/ajaxorg/node-builds-v4.git;destsuffix=git/support/node-builds-v4;name=node-builds-v4 \
           git://github.com/ajaxorg/gnu-builds.git;destsuffix=git/support/gnu-builds;name=gnu-builds \
           git://github.com/ajaxorg/socket.io.git;destsuffix=git/support/socket.io;name=socketio \
           git://github.com/ajaxorg/socket.io-client.git;destsuffix=git/support/socket.io-client;name=socketio-client \
           git://github.com/ajaxorg/UglifyJS.git;destsuffix=git/support/uglify-js;name=uglifyjs \
           file://cloud9.service \
          "

SRCREV_cloud9ide = "593948c9aaded845cb8f3ced49ef41428e351264"
SRCREV_connect = "3d1d77a113fa2f18020f562832dca3d4c2d717a2"
SRCREV_asyncjs = "d36ead408e2959b1e99572114ef3a1b6a48c1072"
SRCREV_jsdav = "f1d1f691fdb521701c1c06d158d213575e4e4e5e"
SRCREV_lib-v8debug = "29b20d01e6854da2173e1665ea141dab6b5a8104"
SRCREV_ace = "d85acd115e88e4461a04fafc6a93a801aeb057ec"
SRCREV_apf = "a7e0daa29573f319f22be5a6cfa275243502604b"
SRCREV_node-builds-v4 = "2e3ac47b7b8d506147feb08372cc36b988b6195a"
SRCREV_gnu-builds = "9ff133d95f9a41d749fe16220d81517dad9a2db3"
SRCREV_socketio = "8f82b1e4e1076a3092fed77e0dc9379425f59b9d"
SRCREV_socketio-client = "7b5335d1ed88564928a5d00d8a750ff9f9c627a5"
SRCREV_uglifyjs = "941c845c4a01e4e47a158458fe846eb36d0828ad"

SRCREV_FORMAT = "cloud9ide"

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
 install -m 0644 ${WORKDIR}/*.service ${D}${base_libdir}/systemd/system/
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
