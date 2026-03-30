FILESEXTRAPATHS:append := "${THISDIR}/${PN}:"

SRC_URI:append = " \
        file://0001-Added-sepolicy-for-adb-service.patch \
        file://0002-Allow-mount-to-run-with-system.patch \
        "
