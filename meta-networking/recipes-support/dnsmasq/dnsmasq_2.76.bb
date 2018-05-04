require dnsmasq.inc

SRC_URI += "\
    file://lua.patch \
    file://dnsmasq-CVE-2017-14491.patch \
    file://dnsmasq-CVE-2017-14492.patch \
    file://dnsmasq-CVE-2017-14493.patch \
    file://dnsmasq-CVE-2017-14494.patch \
    file://dnsmasq-CVE-2017-14496.patch \
    file://dnsmasq-CVE-2017-14495.patch \
    file://dnsmasq-CVE-2017-14491-02.patch \
"

SRC_URI[dnsmasq-2.76.md5sum] = "6610f8233ca89b15a1bb47c788ffb84f"
SRC_URI[dnsmasq-2.76.sha256sum] = "777c4762d2fee3738a0380401f2d087b47faa41db2317c60660d69ad10a76c32"
