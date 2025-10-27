SUMMARY = "Network scanning and manipulation tool"
DESCRIPTION = "Scapy is a powerful interactive packet manipulation program. \
It is able to forge or decode packets of a wide number of protocols, send \
them on the wire, capture them, match requests and replies, and much more. \
It can easily handle most classical tasks like scanning, tracerouting, probing, \
unit tests, attacks or network discovery (it can replace hping, 85% of nmap, \
arpspoof, arp-sk, arping, tcpdump, tethereal, p0f, etc.). It also performs very \
well at a lot of other specific tasks that most other tools can't handle, like \
sending invalid frames, injecting your own 802.11 frames, combining technics \
(VLAN hopping+ARP cache poisoning, VOIP decoding on WEP encrypted channel, ...), etc."
SECTION = "security"
LICENSE = "GPL-2.0-only"

LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

# If you want ptest support, use the git repo
# UTscapy does not exist in the pypi pkg
#
SRCREV = "32cd7eb0f620d9adf171c48d55514e8326a538d7"
SRC_URI = "git://github.com/secdev/scapy.git;branch=master;protocol=https \
           file://run-ptest"

S = "${WORKDIR}/git"

UPSTREAM_CHECK_COMMITS = "1"

inherit setuptools3 ptest

do_install:append() {
        mv ${D}${bindir}/scapy ${D}${bindir}/scapy3
        mv ${D}${bindir}/UTscapy ${D}${bindir}/UTscapy3
}

do_install_ptest() {
    install -m 0644 ${S}/test/regression.uts ${D}${PTEST_PATH}
    sed -i 's,@PTEST_PATH@,${PTEST_PATH},' ${D}${PTEST_PATH}/run-ptest
    install -D -m 0644 ${S}/test/pcaps/bad_rsn_parsing_overrides_ssid.pcap ${D}${PYTHON_SITEPACKAGES_DIR}/test/pcaps/bad_rsn_parsing_overrides_ssid.pcap
    install -m 0644 ${S}/test/pcaps/macos.pcapng.gz ${D}${PYTHON_SITEPACKAGES_DIR}/test/pcaps/

    # note1: if ipv6 isn't enabled, skip the related test (add '-K ipv6' argument)
    # note2: to make this test work, your ISP also must support ipv6 - the test is trying
    #        to ping google.com through ipv6.
    if [ "${@oe.utils.all_distro_features(d, 'ipv6', 'true', 'false')}" = "false" ]; then
        sed -i 's/UTscapy3/UTscapy3 -K ipv6/g' ${D}${PTEST_PATH}/run-ptest
    fi
}

RDEPENDS:${PN} = "tcpdump ${PYTHON_PN}-compression ${PYTHON_PN}-cryptography ${PYTHON_PN}-netclient  \
                  ${PYTHON_PN}-netserver ${PYTHON_PN}-pydoc ${PYTHON_PN}-pkgutil ${PYTHON_PN}-shell \
                  ${PYTHON_PN}-threading ${PYTHON_PN}-numbers"

RDEPENDS:${PN}-ptest += "tshark"

FILES:${PN}-ptest += "${PYTHON_SITEPACKAGES_DIR}/test/pcaps"
