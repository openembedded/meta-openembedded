require dfu-util_${PV}.bb

inherit native deploy
do_deploy[sstate-outputdirs] = "${DEPLOY_DIR_TOOLS}"

DEPENDS = "libusb1-native"

FILESPATH =. "${FILE_DIRNAME}/${BPN}-${PV}:"
SRC_URI += "file://0002-Revert-Makefile.am-Drop-static-dfu-util.patch"

do_deploy() {
    install -d ${DEPLOY_DIR_TOOLS}
    install -m 0755 src/dfu-util_static ${DEPLOY_DIR_TOOLS}/dfu-util-${PV}
    rm -f ${DEPLOY_DIR_TOOLS}/dfu-util
    ln -sf ./dfu-util-${PV} ${DEPLOY_DIR_TOOLS}/dfu-util

}

addtask deploy before do_package after do_install
