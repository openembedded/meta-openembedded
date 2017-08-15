require php.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=b602636d46a61c0ac0432bbf5c078fe4"

SRC_URI += "file://change-AC_TRY_RUN-to-AC_TRY_LINK.patch \
            file://pthread-check-threads-m4.patch \
            file://0001-Add-lpthread-to-link.patch \
           "
SRC_URI[md5sum] = "cb424b705cfb715fc04f499f8a8cf52e"
SRC_URI[sha256sum] = "d47aab8083a4284b905777e1b45dd7735adc53be827b29f896684750ac8b6236"

python () {
    ver = d.getVar('PV')
    if not d.getVar('php_'+ver+'_OK'):
        msg = "%s disables itself because of many outstanding CVEs!" % (ver)
        msg = msg + " Define php_%s_OK = '1' to build it." % (ver)
        raise bb.parse.SkipPackage(msg)
}

