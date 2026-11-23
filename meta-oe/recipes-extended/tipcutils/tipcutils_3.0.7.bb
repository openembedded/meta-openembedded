SUMMARY = "Transparent Inter-Process Communication protocol"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=796417cb33987d462bc15155605a1861"

SRC_URI = "git://git.code.sf.net/p/tipc/tipcutils;branch=master \
           file://tipcutils.conf \
           file://0001-include-sys-select.h-for-FD_-definitions.patch \
           file://0002-replace-non-standard-uint-with-unsigned-int.patch \
           file://0003-test-Build-tipc_test_suite-only-when-struct-tipcc_ad.patch \
           "
# 3.0.7 is untagged, README names this commit's version
SRCREV = "103c971228a6ec05e40ea757c7283f961df66f71"
UPSTREAM_CHECK_COMMITS = "1"

inherit autotools-brokensep pkgconfig systemd

DEPENDS += "libdaemon libmnl"

RDEPENDS:${PN} = "iproute2-tipc"


do_configure:prepend() {
    ( cd ${S}; ${S}/bootstrap )
}

do_install:append() {
    for d in hello_world:hello connection_demo:conn topology_subscr_demo:top \
             multicast_demo:mcast stream_demo:stream benchmark:benchmark \
             tipcc_hello_world:tipcc_hello; do
        dir=$(echo $d | cut -d: -f1)
        pfx=$(echo $d | cut -d: -f2)
        install -d ${D}/opt/tipcutils/demos/$dir
        install ${B}/demos/$dir/"$pfx"_client ${D}/opt/tipcutils/demos/$dir/
        install ${B}/demos/$dir/"$pfx"_server ${D}/opt/tipcutils/demos/$dir/
    done
    for d in inventory_sim:inventory_sim iov_control:iov_control groupcast_demo:group_cast; do
        dir=$(echo $d | cut -d: -f1)
        prog=$(echo $d | cut -d: -f2)
        install -d ${D}/opt/tipcutils/demos/$dir
        install ${B}/demos/$dir/$prog ${D}/opt/tipcutils/demos/$dir/
    done

    install -d ${D}/opt/tipcutils/ptts
    install ${B}/test/ptts/tipcTS ${D}/opt/tipcutils/ptts/
    install ${B}/test/ptts/tipcTC ${D}/opt/tipcutils/ptts/

    sed -i -e '1s|^#!.*python$|#!/usr/bin/env python3|' ${D}${bindir}/tipc-trace

    install -d ${D}${sysconfdir}
    cp -R --no-dereference --preserve=mode,links -v ${S}/scripts/etc/* ${D}${sysconfdir}/
    chown -R root:root ${D}${sysconfdir}

    # Install systemd related configuration file
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${sysconfdir}/modules-load.d
        install -m 0644 ${UNPACKDIR}/tipcutils.conf ${D}${sysconfdir}/modules-load.d
    fi
}

SYSTEMD_SERVICE:${PN} = ""

PACKAGES =+ "${PN}-trace"
PACKAGES += "${PN}-demos"
FILES:${PN}-trace = "${bindir}/tipc-trace"
RDEPENDS:${PN}-trace = "python3-core python3-datetime python3-syslog"
FILES:${PN}-dbg += "/opt/tipcutils/demos/*/.debug /opt/tipcutils/ptts/.debug"
FILES:${PN}-demos = "/opt/tipcutils/*"
