require ruby.inc
PR = "${INC_PR}.0"

SRC_URI += "file://0001-socket-extconf-hardcode-wide-getaddr-info-test-outco.patch"

SRC_URI[md5sum] = "f446550dfde0d8162a6ed8d5a38b3ac2"
SRC_URI[sha256sum] = "5883df5204de70762602ce885b18c8bf6c856d33298c35df9151031b2ce044a1"

FILES_${PN}-dbg += "${libdir}/ruby/1.8/*/.debug \
                    ${libdir}/ruby/1.8/*/*/.debug"
BBCLASSEXTEND = "native"

