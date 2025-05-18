SUMMARY = "SMTP client library"
DESCRIPTION = "LibESMTP is a library to manage posting \
(or submission of) electronic mail using SMTP to a \
preconfigured Mail Transport Agent (MTA) such as Exim or PostFix."
HOMEPAGE = "https://libesmtp.github.io/"
LICENSE = "LGPL-2.0-or-later"
SECTION = "libs"

DEPENDS = "openssl"

SRC_URI = "git://github.com/libesmtp/libESMTP.git;branch=master;protocol=https"
SRCREV = "1d0af244310a66943ab400be56b15a9087f181eb"

S = "${WORKDIR}/git"

LIC_FILES_CHKSUM = "file://LICENSE;md5=1803fa9c2c3ce8cb06b4861d75310742 \
                    file://COPYING.GPL;md5=393a5ca445f6965873eca0259a17f833"

inherit meson pkgconfig

EXTRA_OEMESON = " \
    -Dpthreads=enabled \
    -Dtls=enabled \
    -Dxdg=false \
    -Dlwres=disabled \
    -Dbdat=true \
    -Detrn=true \
    -Dxusr=true \
"

FILES:${PN} = "${libdir}/lib*${SOLIBS} \
               ${libdir}/esmtp-plugins-6.2.0/*${SOLIBSDEV}"
